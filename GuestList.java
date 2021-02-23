
import java.util.ArrayList;
import java.util.List;

public class GuestList {

    private int numberOfSpots;  
    private List<Guest> guests = new ArrayList<>();
    private List<Guest> waitlist = new ArrayList<>();

    public GuestList(int numberOfSpots) {
        this.numberOfSpots = numberOfSpots;
    }

    public int addGuest(Guest newGuest) {   // adds new guest

		if (!checkGuestExistence(newGuest)) {     // verifies if the guest is already signed up
			if (guests.size() < numberOfSpots) { // verifies if number of guests is smaller then number of spots available 
				guests.add(newGuest);	// if YES, adds guest to list and prints message
				System.out.println("Felicitari! Locul tau la eveniment este confirmat. Te asteptam!");
				return 0;
			} else {		// if NO, adds guest to wailist
				waitlist.add(newGuest);
				System.out.println("Te-ai inscris cu succes in lista de asteptare si ai primit numarul de ordine " + waitlist.size() + ". Te vom notifica daca un loc devine disponibil.");
				return waitlist.size();
			}
		}
		
		return -1;
    }

    public boolean checkGuestExistence(Guest newGuest) {  // checks if a guest is already on list 

		if (guests.contains(newGuest) || waitlist.contains(newGuest)) return true;

		return false;
	}
    
    public Guest getGuestByFirstAndLastName(String firstName, String lastName) {   // returns the guest by name and last name criteria
    		
    	for (Guest guest : guests) {  // iteration through guest list
    		if (guest.getFirstName().equals(firstName) && guest.getLastName().equals(lastName)) {
    			return guest;
    		}
    	}
    	for (Guest guest : waitlist) {   // iteration through waitlist
    		if (guest.getFirstName().equals(firstName) && guest.getLastName().equals(lastName)) {
    			return guest;
    		}
    	}
    	return null;
    }
    
    public Guest getGuestByEmail(String email) {    // returns guest by email criteria

    	for (Guest guest : guests) {  // iteration through guest list
    		if (guest.getEmail().equals(email)) {
    			return guest;
    		}
    	}
    	for (Guest guest : waitlist) {   // iteration through waitlist
    		if (guest.getEmail().equals(email)) {
    			return guest;
    		}
    	}
    	return null;
    }
    
    public Guest getGuestByPhoneNumber(String phoneNumber) {   // reuturns guest by phone number criteria
    	
    	for (Guest guest : guests) {   // iteration through guest list
    		if (guest.getPhoneNumber().equals(phoneNumber)) {
    			return guest;
    		}
    	}
    	for (Guest guest : waitlist) {   // iteration through waitlist
    		if (guest.getPhoneNumber().equals(phoneNumber)) {
    			return guest;
    		}
    	}
    	return null;
    }

	public boolean removeGuest(Guest guest) {    // removes guest from guestlist

		if (guests.contains(guest)) {    // condition for removing guest
			guests.remove(guest);
			if (!waitlist.isEmpty()) {  // condition for adding a guest from waitlist to the available spot
				Guest firstGuestInWaiting = waitlist.get(0);
				guests.add(firstGuestInWaiting);
				waitlist.remove(firstGuestInWaiting);
			}
			return true;
		}

		if (waitlist.contains(guest)) {  // condition for guest to be removed from waitlist
			waitlist.remove(guest);
			return true;
		}

		return false;
    }

    public void updateGuest(Guest oldGuest, Guest newGuest) { 

		if (guests.contains(oldGuest)) { 
			guests.remove(oldGuest);
			guests.add(newGuest);
		} else if (waitlist.contains(oldGuest)) {
			waitlist.remove(oldGuest);
			waitlist.add(newGuest);
		}
	}

    public List<Guest> getGuests() {  // returns list of guests attending to event
        return guests;
    }

    public List<Guest> getWaitlistGuests() {  // returns list of guests from waitlist
        return waitlist;
    }

    public int getAvailableSpots() {  // returns number of available spots

    	if (guests.size() == numberOfSpots) {
    		return 0;
		}

    	return numberOfSpots - guests.size();
    }

    public int getNumberOfGuests() {    // returns number of people that will attend the event
        return guests.size();
    }

    public int getNumberOfGuestsOnWaitlist() { //   returns number of guests from waiting list
        return waitlist.size();
    }

    public int getAllGuests() {     // returns number of total guests at event(on guest list + waitlist);
        return guests.size() + waitlist.size();
    }

    public List<Guest> partialSearch(String searchTerm) {   // searches for guest according to the substring given by user

    	List<Guest> matchingGuests = new ArrayList<>();   

		Guest matchingGuest = getGuestWithMatchingFieldFromList(searchTerm, guests);
		if (matchingGuest != null) {
			matchingGuests.add(matchingGuest);
		}

		Guest matchingWaitingGuest = getGuestWithMatchingFieldFromList(searchTerm, waitlist);
		if (matchingWaitingGuest != null) {
			matchingGuests.add(matchingWaitingGuest);
		}

		return matchingGuests;
    }

	private Guest getGuestWithMatchingFieldFromList(String searchTerm, List<Guest> list) {   // returns the guest after partial search by substring

		for (Guest guest : list) {
			if (guestFieldContainsTerm(guest.getFirstName(), searchTerm)
					|| guestFieldContainsTerm(guest.getLastName(), searchTerm)
					|| guestFieldContainsTerm(guest.getEmail(), searchTerm)
					|| guestFieldContainsTerm(guest.getPhoneNumber(), searchTerm)) {
				return guest;
			}
		}

		return null;
	}

	private boolean guestFieldContainsTerm(String guestField, String searchTerm) {

		return guestField.toLowerCase().contains(searchTerm);
	}

//	private void renderGuestList(List<Guest> guests) {}
}
