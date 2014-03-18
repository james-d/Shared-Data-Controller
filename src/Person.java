import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {
	private final StringProperty firstName ;
	private final StringProperty lastName ;
	private final StringProperty email ;
	
	public Person(String firstName, String lastName, String email) {
		this.firstName = new SimpleStringProperty(this, "firstName", firstName);
		this.lastName = new SimpleStringProperty(this, "lastName", lastName);
		this.email = new SimpleStringProperty(this, "email", email);
	}
	
	public Person() {
		this("","","");
	}
	
	public final String getFirstName() {
		return firstName.get();
	}
	
	public final void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}
	
	public final StringProperty firstNameProperty() {
		return firstName ;
	}
	
	public final String getLastName() {
		return lastName.get();
	}
	
	public final void setLastName(String lastName) {
		this.lastName.set(lastName);
	}
	
	public final StringProperty lastNameProperty() {
		return lastName ;
	}
	
	public final String getEmail() {
		return email.get();
	}
	
	public final void setEmail(String email) {
		this.email.set(email);
	}
	
	public final StringProperty emailProperty() {
		return email ;
	}	
	
	@Override
	public String toString() {
		return getFirstName() + " " + getLastName() ;
	}
}