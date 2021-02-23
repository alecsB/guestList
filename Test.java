
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);

        System.out.println("Number of spots available in the list:");
        int numberOfSpots = input.nextInt();
        System.out.println(" Asteapta comanda: (help - Afiseaza lista de comenzi): ");

        GuestList guestList = new GuestList(numberOfSpots);
        String command;
        
        do {
        	try {
        		command = input.next();
        		switch (command) {
        		case "help":
        			renderHelpMenu();
        			break;
        		case "add":
        			System.out.println("Se adauga o noua persoana...");
        			System.out.println("Introduceti numele de familie:");
        			String lastName = input.next();
        			System.out.println("Introduceti prenumele:");
        			String firstName = input.next();
        			System.out.println("Introduceti email:");
        			String email = input.next();
        			System.out.println("Introduceti numar de telefon (format +40733386463):");
        			String phoneNumber = input.next();

        			Guest newGuest = new Guest(lastName, firstName, email, phoneNumber);
        			guestList.addGuest(newGuest);

        			System.out.println("[" + lastName + " " + firstName + "] Felicitari! Locul tau la eveniment este confirmat. Te asteptam");
        			System.out.println(" Asteapta comanda: (help - Afiseaza lista de comenzi): ");
        			break;
        		case "check":
        			int criteria = getSearchCriteria(input);
        			Guest guest = getGuestInput(criteria, input);

        			if (guestList.getGuests().contains(guest)) {
        				System.out.println("Persoana cu detaliile introduse este deja inscrisa in lista de participare");
        			} else {
        				System.out.println("Persoana cu detaliile introduse NU se afla in lista de participare");
        			}


        			System.out.println(" Asteapta comanda: (help - Afiseaza lista de comenzi): ");
        			break;
        		case "remove":
        			int removeCriteria = getSearchCriteria(input);
        			Guest guestToRemove = getGuestInput(removeCriteria, input);

        			if (guestList.checkGuestExistence(guestToRemove)) {
        				guestList.removeGuest(guestToRemove);
        				System.out.println("Persoana cu detaliile introduse a fost stearsa.");
        			} else {
        				System.out.println("Persoana cu detaliile introduse NU se afla in niciuna din liste");
        			}

        			System.out.println(" Asteapta comanda: (help - Afiseaza lista de comenzi): ");
        			break;
        		case "update":
        			int updateCriteria = getSearchCriteria(input);
        			Guest existingGuestToUpdate = getGuestInput(updateCriteria, input);


        			if (guestList.checkGuestExistence(existingGuestToUpdate)) {
        				System.out.println("Tastati numarul asociat campului pe care doriti sa-l actualizati:\n" +
        						"\t1. Numele de familie\n" +
        						"\t2. Prenumele\n" +
        						"\t3. Email\n" +
        						"\t4. Numar de telefon");
        				int fieldCriteria = input.nextInt();

        				Guest updatedGuest = null;
        				if (updateCriteria == 1) {
        					updatedGuest = guestList.getGuestByFirstAndLastName(existingGuestToUpdate.getFirstName(), existingGuestToUpdate.getLastName());
        				} else if (updateCriteria == 2) {
        					updatedGuest = guestList.getGuestByEmail(existingGuestToUpdate.getEmail());
        				} else if (updateCriteria == 3) {
        					updatedGuest = guestList.getGuestByPhoneNumber(existingGuestToUpdate.getPhoneNumber());
        				}


        				if (fieldCriteria == 1) {
        					System.out.println("Introduceti numele de familie:");
        					updatedGuest.setLastName(input.next());
        				} else if (fieldCriteria == 2) {
        					System.out.println("Introduceti prenumele:");
        					updatedGuest.setFirstName(input.next());
        				} else if (fieldCriteria == 3) {
        					System.out.println("Introduceti email:");
        					updatedGuest.setEmail(input.next());
        				} else if (fieldCriteria == 4) {
        					System.out.println("Introduceti numar de telefon (format +40733386463):");
        					updatedGuest.setPhoneNumber(input.next());
        				} else {
        					System.out.println("Err!");
        				}

        				guestList.updateGuest(existingGuestToUpdate, updatedGuest);

        				System.out.println("Campul ales al persoanei cu detaliile introduse a fost actualizat");
        			} else {
        				System.out.println("Persoana cu detaliile introduse NU se afla in niciuna din liste");
        			}

        			System.out.println(" Asteapta comanda: (help - Afiseaza lista de comenzi): ");
        			break;
        		case "guests":
        			List<Guest> guests = guestList.getGuests();

        			for (int i = 0; i < guests.size(); i++) {
        				Guest existingGuest = guests.get(i);
        				System.out.println(i + 1 + ". Nume: " + existingGuest.getLastName() + " " + existingGuest.getFirstName() + " Email: " + existingGuest.getEmail() + ", Telefon:" + existingGuest.getPhoneNumber());
        			}

        			if(guests.size() == 0) {
        				System.out.println(" Niciun participant inscris...");
        			}

        			System.out.println(" Asteapta comanda: (help - Afiseaza lista de comenzi): ");
        			break;
        		case "waitlist":
        			List<Guest> waitingGuests = guestList.getWaitlistGuests();

        			if(waitingGuests.isEmpty()) {
        				System.out.println(" Nu exista nicio persoana inscrisa in lista de asteptare.");
        			}

        			for (int i = 0; i < waitingGuests.size(); i++) {
        				Guest waitingGuest = waitingGuests.get(i);
        				System.out.println(i + 1 + ". Nume: " + waitingGuest.getLastName() + " " + waitingGuest.getFirstName() + " Email: " + waitingGuest.getEmail() + ", Telefon:" + waitingGuest.getPhoneNumber());
        			}

        			System.out.println(" Asteapta comanda: (help - Afiseaza lista de comenzi): ");
        			break;
        		case "available":
        			System.out.println(" Numarul de locuri ramase: " + guestList.getAvailableSpots());
        			System.out.println(" Asteapta comanda: (help - Afiseaza lista de comenzi): ");
        			break;
        		case "guests_no":
        			System.out.println(" Numarul de participanti: " + guestList.getNumberOfGuests());
        			System.out.println(" Asteapta comanda: (help - Afiseaza lista de comenzi): ");
        			break;
        		case "waitlist_no":
        			System.out.println(" Dimensiunea listei de asteptare: " + guestList.getNumberOfGuestsOnWaitlist());
        			System.out.println(" Asteapta comanda: (help - Afiseaza lista de comenzi): ");
        			break;
        		case "subscribe_no":
        			System.out.println(" Numarul total de persoane inscrise la eveniment: " + guestList.getAllGuests());
        			System.out.println(" Asteapta comanda: (help - Afiseaza lista de comenzi): ");
        			break;
        		case "search":
        			System.out.println("Introduceti sirul de caractere dorit pt cautarea partiala:");
        			String searchTerm = input.next();

        			List<Guest> matchingGuests = guestList.partialSearch(searchTerm);

        			for (int i = 0; i < matchingGuests.size(); i++) {
        				Guest matchingGuest = matchingGuests.get(i);
        				System.out.println(i + 1 + ". Nume: " + matchingGuest.getLastName() + " " + matchingGuest.getFirstName() + " Email: " + matchingGuest.getEmail() + ", Telefon:" + matchingGuest.getPhoneNumber());
        			}

        			System.out.println(" Asteapta comanda: (help - Afiseaza lista de comenzi): ");
        			break;
        		case "backup":
//        			writeToBinaryFile();
        		case "quit":
        			System.exit(0);
        			break;
        		default:
        			System.out.println("Unknown command");
        		}
        	} catch(NoSuchElementException e) {
        		System.out.println("Introducerea datelor a survenit o eroare!...");
        		break;
        	}
        } while (!"quit".equalsIgnoreCase(command));
    }

    public static void writeToBinaryFile(List<Guest> data) throws IOException {
    	try(ObjectOutputStream binaryFileOut = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("Guests.txt")))) {
    		binaryFileOut.writeObject(data);
    	}
    }
    
	private static int getSearchCriteria(Scanner input) {

		System.out.println("Tastati numarul criteriului dupa care doriti sa efectuati verificarea:\n" +
				"\t1. Numele de familie si prenumele\n" +
				"\t2. Email\n" +
				"\t3. Numar de telefon");
		return input.nextInt();
	}

	public static Guest getGuestInput(int criteria, Scanner input) {
		String lastName = "", firstName = "", email = "", phoneNumber = "";

		
		if (criteria == 1) {
			System.out.println("Introduceti numele de familie:");
			lastName = input.next();
			System.out.println("Introduceti prenumele:");
			firstName = input.next();
		} else if (criteria == 2) {
			System.out.println("Introduceti email:");
			email = input.next();
		} else if (criteria == 3) {
			System.out.println("Introduceti numar de telefon (format +40733386463):");
			phoneNumber = input.next();
		}

		return new Guest(lastName, firstName, email, phoneNumber);
	}

	public static void renderHelpMenu() {

		System.out.println("help         - Afiseaza aceasta lista de comenzi\r\n" + 
						   "add          - Adauga o noua persoana (inscriere)\r\n" + 
						   "check        - Verifica daca o persoana este inscrisa la eveniment\r\n" + 
						   "remove       - Sterge o persoana existenta din lista\r\n" + 
						   "update       - Actualizeaza detaliile unei persoane\r\n" + 
						   "guests       - Lista de persoane care participa la eveniment\r\n" + 
						   "waitlist     - Persoanele din lista de asteptare\r\n" + 
						   "available    - Numarul de locuri libere\r\n" + 
						   "guests_no    - Numarul de persoane care participa la eveniment\r\n" + 
						   "waitlist_no  - Numarul de persoane din lista de asteptare\r\n" + 
						   "subscribe_no - Numarul total de persoane inscrise\r\n" + 
						   "search       - Cauta toti invitatii conform sirului de caractere introdus\r\n" + 
							"quit        - Inchide aplicatia"	);
    }
}