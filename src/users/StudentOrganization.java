package users;

import java.util.*;

public class StudentOrganization {
    private String name;
    private List<Student> members;
    private Map<Student, String> studentRoles;
    private List<String> events;
    private Map<Student, List<String>> studentEventParticipation;

    public StudentOrganization(String name) {
        this.name = name;
        this.members = new ArrayList<>();
        this.studentRoles = new HashMap<>();
        this.events = new ArrayList<>();
        this.studentEventParticipation = new HashMap<>();
    }

    public void addMember(Student student, String role) {
        if (!members.contains(student)) {
            members.add(student);
            studentRoles.put(student, role);
            studentEventParticipation.put(student, new ArrayList<>());
            System.out.println("Added " + student.getFullName() + " as a member with role: " + role);
        } else {
            System.out.println(student.getFullName() + " is already a member of the organization.");
        }
    }

    public void removeMember(Student student) {
        if (members.contains(student)) {
            members.remove(student);
            studentRoles.remove(student);
            studentEventParticipation.remove(student);
            System.out.println("Removed " + student.getFullName() + " from the organization.");
        } else {
            System.out.println(student.getFullName() + " is not a member of the organization.");
        }
    }

    public void assignRole(Student student, String role) {
        if (members.contains(student)) {
            studentRoles.put(student, role);
            System.out.println("Assigned role: " + role + " to " + student.getFullName());
        } else {
            System.out.println(student.getFullName() + " is not a member of the organization.");
        }
    }

    public void addEvent(String eventName) {
        events.add(eventName);
        System.out.println("Added event: " + eventName);
    }

    public void registerForEvent(Student student, String eventName) {
        if (members.contains(student)) {
            if (events.contains(eventName)) {
                List<String> participatedEvents = studentEventParticipation.get(student);
                if (!participatedEvents.contains(eventName)) {
                    participatedEvents.add(eventName);
                    System.out.println(student.getFullName() + " has been registered for the event: " + eventName);
                } else {
                    System.out.println(student.getFullName() + " has already registered for this event.");
                }
            } else {
                System.out.println("Event " + eventName + " does not exist.");
            }
        } else {
            System.out.println(student.getFullName() + " is not a member of the organization.");
        }
    }

    public void generateReport() {
        System.out.println("Organization: " + name);
        System.out.println("Members and their roles:");
        for (Student student : members) {
            System.out.println(student.getFullName() + " - Role: " + studentRoles.get(student));
        }
        System.out.println("\nEvents and participation:");
        for (String event : events) {
            System.out.println("Event: " + event);
            for (Student student : members) {
                if (studentEventParticipation.get(student).contains(event)) {
                    System.out.println("- " + student.getFullName() + " participated in the event.");
                }
            }
        }
    }

    public List<Student> getParticipants(String eventName) {
        List<Student> participants = new ArrayList<>();
        if (events.contains(eventName)) {
            for (Student student : members) {
                if (studentEventParticipation.get(student).contains(eventName)) {
                    participants.add(student);
                }
            }
        }
        return participants;
    }

    public String getRole(Student student) {
        return studentRoles.getOrDefault(student, "No role assigned");
    }

    public List<Student> getMembers() {
        return members;
    }

    public List<String> getEvents() {
        return events;
    }
}
