package users;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import news.Journal;
import news.News;
import news.NewsManager;
import observers.Observer;
import research.ResearchProject;

public abstract class User implements Serializable, Observer {
	private static final long serialVersionUID = 1L;
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private boolean isLogged;
	private List<Journal> subscribedJournals = new ArrayList<>();
	private List<String> notifications = new ArrayList<>();

	 public User(String id, String firstName, String lastName, String email, String password) {
		 this.id = id;
	     this.firstName = firstName;
	     this.lastName = lastName;
	     this.email = email;
	     this.password = password;
	     this.isLogged = false; 
	     
	     NewsManager.getInstance().addObserver(this);
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

	 public void subscribeToJournal(Journal journal) {
	        if (!subscribedJournals.contains(journal)) {
	            subscribedJournals.add(journal);
	            journal.addSubscriber(this);
	            System.out.println("Subscribed to journal: " + journal.getName());
	        }
	    }

	    public void unsubscribeFromJournal(Journal journal) {
	        if (subscribedJournals.remove(journal)) {
	            journal.removeSubscriber(this);
	            System.out.println("Unsubscribed from journal: " + journal.getName());
	        }
	    }

	    public List<String> viewNotifications() {
	        return new ArrayList<>(notifications);
	    }
	    
	    @Override
	    public void update(Journal journal, ResearchProject researchProject) {
	        String message = "New research project in " + journal.getName() + ": " + researchProject.getProjectName();
	        notifications.add(message);
	        System.out.println(message);
	    }

	    @Override
	    public void update(News news) {
	        String message = "News: " + news.getTitle();
	        notifications.add(message);
	        System.out.println(message);
	    }
	 
	 public abstract void displayFunct();
	 

}
