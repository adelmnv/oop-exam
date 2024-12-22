package repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import communication.Request;

public class RequestRepository {
    private static final RequestRepository INSTANCE = new RequestRepository();
    private final Map<Integer, Request> requests = new HashMap<>();
    private int currentId = 1;

    private RequestRepository() {}

    public static RequestRepository getInstance() {
        return INSTANCE;
    }

    public int addRequest(Request request) {
        int id = currentId++;
        requests.put(id, request);
        return id;
    }

    public Request getRequestById(int id) {
        return requests.get(id);
    }

    public List<Request> getUnsignedRequests() {
        List<Request> unsigned = new ArrayList<>();
        for (Request request : requests.values()) {
            if (!request.isSigned()) {
                unsigned.add(request);
            }
        }
        return unsigned;
    }

    public List<Request> getSignedRequests() {
        List<Request> signed = new ArrayList<>();
        for (Request request : requests.values()) {
            if (request.isSigned()) {
                signed.add(request);
            }
        }
        return signed;
    }

    public List<Request> getAllRequests() {
        return new ArrayList<>(requests.values());
    }

    public boolean deleteRequestById(int id) {
        return requests.remove(id) != null;
    }

    public boolean updateRequestById(int id, Request updatedRequest) {
        if (requests.containsKey(id)) {
            requests.put(id, updatedRequest);
            return true;
        }
        return false;
    }

    public boolean requestExists(int id) {
        return requests.containsKey(id);
    }
}
