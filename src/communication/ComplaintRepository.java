package communication;

import java.util.HashMap;
import java.util.Map;

public class ComplaintRepository {
	 private static final Map<Integer, Complaint> complaints = new HashMap<>();
	    private static int currentId = 1;

	    public static int addComplaint(Complaint complaint) {
	        int id = currentId++;
	        complaints.put(id, complaint);
	        return id;
	    }

	    public static Complaint getComplaintById(int id) {
	        return complaints.get(id);
	    }
}
