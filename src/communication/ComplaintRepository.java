package communication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
	    
	    public static List<Complaint> getUnansweredComplaints() {
	        List<Complaint> unanswered = new ArrayList<>();
	        for (Complaint complaint : complaints.values()) {
	            if (!complaint.isAnswered()) {
	                unanswered.add(complaint);
	            }
	        }
	        return unanswered;
	    }
}
