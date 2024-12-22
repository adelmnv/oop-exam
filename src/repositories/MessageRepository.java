package repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import communication.Message;

public class MessageRepository {
    private static final MessageRepository INSTANCE = new MessageRepository();
    private final Map<Integer, Message> messages = new HashMap<>();
    private int currentId = 1;

    private MessageRepository() {}

    public static MessageRepository getInstance() {
        return INSTANCE;
    }

    public synchronized int addMessage(Message message) {
        int id = currentId++;
        messages.put(id, message);
        return id;
    }

    public Message getMessageById(int id) {
        return messages.get(id);
    }

    public List<Message> getAllMessages() {
        return new ArrayList<>(messages.values());
    }

    public boolean deleteMessageById(int id) {
        return messages.remove(id) != null;
    }

    public List<Message> getUnreadMessages() {
        List<Message> unreadMessages = new ArrayList<>();
        for (Message message : messages.values()) {
            if (!message.getIsOpened()) {
                unreadMessages.add(message);
            }
        }
        return unreadMessages;
    }

    public boolean markMessageAsRead(int id) {
        Message message = messages.get(id);
        if (message != null && !message.getIsOpened()) {
            message.markAsOpened();
            return true;
        }
        return false;
    }
}
