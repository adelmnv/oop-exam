package research;

import java.util.Comparator;
import java.util.List;

public interface Researcher {
    void publishPaper(ResearchPaper paper);
    int calculateHIndex();
    void printPapers(Comparator<ResearchPaper> comparator);
    void proposeProject();
    void joinProject(ResearchProject project);
    List<ResearchPaper> getPublications();
}

