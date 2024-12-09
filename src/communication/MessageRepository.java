package communication;
import java.util.HashMap;
import java.util.Map;

public class MessageRepository {
    private static final Map<Integer, Message> messages = new HashMap<>();
    private static int currentId = 1;

    public static int addMessage(Message message) {
        int id = currentId++;
        messages.put(id, message);
        return id;
    }

    public static Message getMessageById(int id) {
        return messages.get(id);
    }
}

