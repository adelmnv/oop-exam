package repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import communication.Complaint;

public class ComplaintRepository {
    private static final ComplaintRepository INSTANCE = new ComplaintRepository();
    private final Map<Integer, Complaint> complaints = new HashMap<>();
    private int currentId = 1;

    private ComplaintRepository() {}

    public static ComplaintRepository getInstance() {
        return INSTANCE;
    }

    public int addComplaint(Complaint complaint) {
        int id = currentId++;
        complaints.put(id, complaint);
        return id;
    }

    public Complaint getComplaintById(int id) {
        return complaints.get(id);
    }

    public List<Complaint> getUnansweredComplaints() {
        List<Complaint> unanswered = new ArrayList<>();
        for (Complaint complaint : complaints.values()) {
            if (!complaint.isAnswered()) {
                unanswered.add(complaint);
            }
        }
        return unanswered;
    }
}
