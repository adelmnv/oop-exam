package communication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
	    
	    public static List<Request> getUnsignedRequests() {
	        List<Request> unsigned = new ArrayList<>();
	        for (Request request : requests.values()) {
	            if (!request.isSigned()) {
	            	unsigned.add(request);
	            }
	        }
	        return unsigned;
	    }
	    
	    public static List<Request> getSignedRequests() {
	        List<Request> signed = new ArrayList<>();
	        for (Request request : requests.values()) {
	            if (request.isSigned()) {
	            	signed.add(request);
	            }
	        }
	        return signed;
	    }
	    
}
