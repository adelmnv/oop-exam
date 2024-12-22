package test;
import communication.Message;
import users.Employee;
import utils.IdGenerator;

public class MessageTest {

    public static void main(String[] args) {
        // Создание двух сотрудников для отправки и получения сообщений
        Employee sender = new Employee(IdGenerator.generateUniqueId("E"), "John", "Doe", "john.doe@example.com", "password123", 5000) {
            @Override
            public void displayFunct() {
                System.out.println("Employee Functions");
            }
        };
        
        Employee recipient = new Employee(IdGenerator.generateUniqueId("E"), "Jane", "Smith", "jane.smith@example.com", "password456", 6000) {
            @Override
            public void displayFunct() {
                System.out.println("Employee Functions");
            }
        };

        // Создание и отправка сообщений
        String messageContent1 = "Hello Jane, this is a test message 1!";
        sender.sendMessage(recipient, messageContent1);
        
        String messageContent2 = "Hello Jane, this is another test message!";
        sender.sendMessage(recipient, messageContent2);
        
        // Извлечение и вывод сообщений из MessageRepository
        System.out.println("Fetching and displaying sent messages for sender: " + sender.getFullName());
        for (Message message : sender.getSentMessages()) {
            System.out.println("Message content: " + message.getContent());
            System.out.println("Message sent from: " + message.getSender().getFullName() + " to: " + message.getRecipient().getFullName());
        }
        
        System.out.println("\nFetching and displaying received messages for recipient: " + recipient.getFullName());
        for (Message message : recipient.getReceivedMessages()) {
            System.out.println("Message content: " + message.getContent());
            System.out.println("Message received from: " + message.getSender().getFullName() + " to: " + message.getRecipient().getFullName());
        }
        
      
}
}
