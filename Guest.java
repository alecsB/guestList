import java.io.Serializable;
import java.util.Objects;

public class Guest {
	
	private String lastName;
	private String firstName;
	private String email;
	private String phoneNumber;

    public Guest(String lastName, String firstName, String email, String phoneNumber) {   // constructor
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {  // returns firstName of guest
        return firstName;
    }

    public void setFirstName(String firstName) {     // sets firstName of guest
        this.firstName = firstName;
    }

    public String getLastName() {    // returns lastName of guest
        return lastName;
    }

    public void setLastName(String lastName) {   // sets lastName of the guest
        this.lastName = lastName;
    }

    public String getEmail() { // returns email of guest
        return email;
    }

    public void setEmail(String email) {  // sets email of guest
        this.email = email;
    }

    public String getPhoneNumber() {   // returns phoneNumber
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {   // sets guest phone number
        this.phoneNumber = phoneNumber;
    }

@Override
    public boolean equals(Object o) {   // method used to compare strings in application(ex: search, check, update etc)

	if (this == o) return true;
	if (o == null || getClass() != o.getClass()) return false;
	Guest guest = (Guest) o;
	return (lastName.equals(guest.lastName) && firstName.equals(guest.firstName))
			|| email.equals(guest.email)
			|| phoneNumber.equals(guest.phoneNumber);
    }

     @Override
     public int hashCode() {

	return Objects.hash(lastName, firstName, email, phoneNumber);
     }
}
