package users;

import java.io.Serializable;

//доделать
public abstract class User implements Serializable /*, Observer*/ {
	private static final long serialVersionUID = 1L;
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private boolean isLogged;

	 public User(String id, String firstName, String lastName, String email, String password) {
		 this.id = id;
	     this.firstName = firstName;
	     this.lastName = lastName;
	     this.email = email;
	     this.password = password;
	     this.isLogged = false; 
	 }
	 
	 public String getId() {
		 return id;
	 }

	 public String getFirstName() {
	     return firstName;
	 }

	 public String getLastName() {
		 return lastName;
	 }

	 public String getEmail() {
		 return email;
	 }

	 public boolean isLogged() {
	     return isLogged;
	 }
	 
	 public void setFirstName(String firstName) {
		 this.firstName = firstName;
	 }
	 
	 public void setLastName(String lastName) {
		 this.lastName = lastName;
	 }
	 
	 public void setEmail(String email) {
		 this.email = email;
	 }
		
	 public boolean login(String email, String password) {
		 if (this.email.equals(email) && this.password.equals(password)) {
			 this.isLogged = true;
	         System.out.println("Login successful.");
	         return true;
	     } else {
	    	 System.out.println("Invalid credentials.");
	         return false;
	     }
	 }

	 public void logout() {
		 this.isLogged = false;
	     System.out.println("Logged out successfully.");
	 }

	 public void changePassword(String newPassword) {
		 this.password = newPassword;
		 System.out.println("Password changed successfully.");
	 }

	 //public List<String> viewNotifications() {}
	 
	 //subscribeToJournal(Journal journal){}
	 
	 //unsubsribeToJournal(Journal journal){}
	 
	 public abstract void displayFunct();
	 

}
