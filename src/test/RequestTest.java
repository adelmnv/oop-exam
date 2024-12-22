package test;
import communication.Request;
import communication.RequestRepository;
import users.Employee;
import utils.IdGenerator;

public class RequestTest {

    public static void main(String[] args) {
        // Создание сотрудника для отправки запросов
        Employee sender = new Employee(IdGenerator.generateUniqueId("E"), "John", "Doe", "john.doe@example.com", "password123", 5000) {
            @Override
            public void displayFunct() {
                System.out.println("Employee Functions");
            }
        };

        // Создание запросов и добавление их в репозиторий
        String requestContent1 = "Please approve the project proposal.";
        sender.createRequest(requestContent1);
        
        String requestContent2 = "Requesting additional budget for the team.";
        sender.createRequest(requestContent2);
        
        // Извлечение и вывод всех запросов
        System.out.println("Fetching and displaying all sent requests from: " + sender.getFullName());
        for (Request request : sender.getSentRequests()) {
            System.out.println("Request content: " + request.getContent());
            System.out.println("Request sent by: " + request.getSender().getFullName());
        }
        
        // Извлечение и вывод всех неподписанных запросов
        System.out.println("\nFetching and displaying all unsigned requests:");
        for (Request request : RequestRepository.getUnsignedRequests()) {
            System.out.println("Unsigned request content: " + request.getContent());
            System.out.println("Request sent by: " + request.getSender().getFullName());
        }
        
        // Извлечение и вывод всех подписанных запросов
        System.out.println("\nFetching and displaying all signed requests:");
        for (Request request : RequestRepository.getSignedRequests()) {
            System.out.println("Signed request content: " + request.getContent());
            System.out.println("Request sent by: " + request.getSender().getFullName());
        }
        
        // Подписание запроса
        System.out.println("\nSigning the first request...");
        Request firstRequest = sender.getSentRequests().get(0);
        firstRequest.setSigned(true);
        
        // Проверка подписанных запросов после изменения
        System.out.println("\nFetching and displaying all signed requests after signing one:");
        for (Request request : RequestRepository.getSignedRequests()) {
            System.out.println("Signed request content: " + request.getContent());
            System.out.println("Request sent by: " + request.getSender().getFullName());
        }
    }
}
