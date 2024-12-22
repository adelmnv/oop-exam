package main;

import java.util.Scanner;

import repositories.UserRepository;
import users.User;
import utils.DataInitializer;

public class MainProgram {

	public static void main(String[] args) {
		DataInitializer.initialize();
		
		try (Scanner scanner = new Scanner(System.in)) {
            User authenticatedUser = null;
            
            while (authenticatedUser == null) {
                try {
                    System.out.print("\n=== Login ===\n");
                    System.out.print("\nEnter login: ");
                    String login = scanner.next();
                    System.out.print("\nEnter password: ");
                    String password = scanner.next();
                    
                    authenticatedUser = UserRepository.getInstance().authenticate(login, password);
                    
                    if (authenticatedUser != null) {
                        System.out.println("\n\nWelcome, " + authenticatedUser.getFirstName());
                    } else {
                        System.out.println("Login failed. Please try again.");
                    }

                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                    System.out.println("\nPlease try again.");
                }
            }

            authenticatedUser.displayFunct();
        }

	}

}
