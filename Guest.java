import java.io.Serializable;
import java.util.Objects;

public class Guest {
	
	private String lastName;
	private String firstName;
	private String email;
	private String phoneNumber;

	public Guest(String lastName, String firstName, String email, String phoneNumber) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

	@Override
	public boolean equals(Object o) {

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
