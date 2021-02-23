
import java.util.ArrayList;
import java.util.List;

public class GuestList {

    private int numberOfSpots;
    private List<Guest> guests = new ArrayList<>();
    private List<Guest> waitlist = new ArrayList<>();

    public GuestList(int numberOfSpots) {
        this.numberOfSpots = numberOfSpots;
    }

    public int addGuest(Guest newGuest) {

		if (!checkGuestExistence(newGuest)) {
			if (guests.size() < numberOfSpots) {
				guests.add(newGuest);
				System.out.println("Felicitari! Locul tau la eveniment este confirmat. Te asteptam!");
				return 0;
			} else {
				waitlist.add(newGuest);
				System.out.println("Te-ai inscris cu succes in lista de asteptare si ai primit numarul de ordine " + waitlist.size() + ". Te vom notifica daca un loc devine disponibil.");
				return waitlist.size();
			}
		}
		
		return -1;
    }

    public boolean checkGuestExistence(Guest newGuest) {

		if (guests.contains(newGuest) || waitlist.contains(newGuest)) return true;

		return false;
	}
    
    public Guest getGuestByFirstAndLastName(String firstName, String lastName) {
    		
    	for (Guest guest : guests) {
    		if (guest.getFirstName().equals(firstName) && guest.getLastName().equals(lastName)) {
    			return guest;
    		}
    	}
    	for (Guest guest : waitlist) {
    		if (guest.getFirstName().equals(firstName) && guest.getLastName().equals(lastName)) {
    			return guest;
    		}
    	}
    	return null;
    }
    
    public Guest getGuestByEmail(String email) {

    	for (Guest guest : guests) {
    		if (guest.getEmail().equals(email)) {
    			return guest;
    		}
    	}
    	for (Guest guest : waitlist) {
    		if (guest.getEmail().equals(email)) {
    			return guest;
    		}
    	}
    	return null;
    }
    
    public Guest getGuestByPhoneNumber(String phoneNumber) {
    	
    	for (Guest guest : guests) {
    		if (guest.getPhoneNumber().equals(phoneNumber)) {
    			return guest;
    		}
    	}
    	for (Guest guest : waitlist) {
    		if (guest.getPhoneNumber().equals(phoneNumber)) {
    			return guest;
    		}
    	}
    	return null;
    }

	public boolean removeGuest(Guest guest) {

		if (guests.contains(guest)) {
			guests.remove(guest);
			if (!waitlist.isEmpty()) {
				Guest firstGuestInWaiting = waitlist.get(0);
				guests.add(firstGuestInWaiting);
				waitlist.remove(firstGuestInWaiting);
			}
			return true;
		}

		if (waitlist.contains(guest)) {
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

	public List<Guest> getGuests() {
        return guests;
    }

    public List<Guest> getWaitlistGuests() {
        return waitlist;
    }

    public int getAvailableSpots() {

    	if (guests.size() == numberOfSpots) {
    		return 0;
		}

    	return numberOfSpots - guests.size();
    }

    public int getNumberOfGuests() {
        return guests.size();
    }

    public int getNumberOfGuestsOnWaitlist() {
        return waitlist.size();
    }

    public int getAllGuests() {
        return guests.size() + waitlist.size();
    }

    public List<Guest> partialSearch(String searchTerm) {

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

	private Guest getGuestWithMatchingFieldFromList(String searchTerm, List<Guest> list) {

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