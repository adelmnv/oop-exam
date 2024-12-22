package repositories;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import news.Journal;

public class JournalRepository {
    private List<Journal> journals = new ArrayList<>();
    private static final JournalRepository INSTANCE = new JournalRepository();
    private static final String FILE_PATH = "src/data/journals.dat";
    
    private JournalRepository() {
        loadJournalsFromFile(); 
    }

    public static JournalRepository getInstance() {
        return INSTANCE;
    }

    public void addJournal(Journal journal) {
        if (journal != null && !journals.contains(journal)) {
            journals.add(journal);
            System.out.println("Journal added: " + journal.getName());
            saveJournalsToFile();
        } else {
            System.out.println("Journal already exists or is null.");
        }
    }

    public void removeJournal(Journal journal) {
        if (journals.remove(journal)) {
            System.out.println("Journal removed: " + journal.getName());
            saveJournalsToFile();
        } else {
            System.out.println("Journal not found in the repository.");
        }
    }

    public Optional<Journal> findJournalByName(String name) {
        return journals.stream()
                       .filter(journal -> journal.getName().equalsIgnoreCase(name))
                       .findFirst();
    }

    public void printAllJournals() {
        if (journals.isEmpty()) {
            System.out.println("No journals in the repository.");
        } else {
            System.out.println("Journals in the repository:");
            journals.forEach(journal -> System.out.println("- " + journal.getName()));
        }
    }

    public void saveJournalsToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            out.writeObject(journals);
            System.out.println("Journals saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving journals to file: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
	public void loadJournalsFromFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            Object obj = in.readObject();
            if (obj instanceof List<?>) {
                journals.clear();
                journals.addAll((List<Journal>) obj);
                System.out.println("Journals loaded from file.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("No saved journals found, starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading journals from file: " + e.getMessage());
        }
    }
}
