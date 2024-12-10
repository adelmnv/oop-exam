package communication;

import java.util.HashMap;
import java.util.Map;

public class RequestRepository {
	 private static final Map<Integer, Request> requests = new HashMap<>();
	    private static int currentId = 1;

	    public static int addRequest(Request request) {
	        int id = currentId++;
	        requests.put(id, request);
	        return id;
	    }

	    public static Request getRequestById(int id) {
	        return requests.get(id);
	    }
}
