package repositories;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import users.StudentOrganization;

public class StudentOrganizationRepository {
    private static final String FILE_PATH = "src/data/student_organizations.dat";
    private final Map<String, StudentOrganization> organizations = new HashMap<>();
    private static final StudentOrganizationRepository INSTANCE = new StudentOrganizationRepository();

    private StudentOrganizationRepository() {
        loadOrganizationsFromFile();
    }

    public static StudentOrganizationRepository getInstance() {
        return INSTANCE;
    }

    public void addOrganization(StudentOrganization organization) {
        if (organization != null && organization.getName() != null) {
            if (!organizations.containsKey(organization.getName())) {
                organizations.put(organization.getName(), organization);
                System.out.println("Organization added: " + organization.getName());
                saveOrganizationsToFile();
            } else {
                System.out.println("Organization with name " + organization.getName() + " already exists.");
            }
        } else {
            System.out.println("Invalid organization.");
        }
    }

    public StudentOrganization getOrganizationByName(String name) {
        return organizations.get(name);
    }

    public List<StudentOrganization> getAllOrganizations() {
        return new ArrayList<>(organizations.values());
    }

    public boolean removeOrganizationByName(String name) {
        if (organizations.containsKey(name)) {
            organizations.remove(name);
            System.out.println("Organization with name " + name + " removed.");
            saveOrganizationsToFile();
            return true;
        } else {
            System.out.println("No organization found with name " + name);
            return false;
        }
    }

    private void saveOrganizationsToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            out.writeObject(organizations);
            System.out.println("Organizations saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving organizations to file: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
	private void loadOrganizationsFromFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            Object obj = in.readObject();
            if (obj instanceof Map<?, ?>) {
                organizations.clear();
                organizations.putAll((Map<String, StudentOrganization>) obj);
                System.out.println("Organizations loaded from file.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("No saved organizations found, starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading organizations from file: " + e.getMessage());
        }
    }
}

