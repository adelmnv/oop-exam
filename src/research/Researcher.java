package research;

import java.util.Comparator;
import java.util.List;

public interface Researcher {
    void publishPaper(ResearchPaper paper);
    int calculateHIndex();
    void printPapers(Comparator<ResearchPaper> comparator);
    void proposeProject(String projectName);
    void joinProject(ResearchProject project);
    List<ResearchPaper> getPublications();
    String getResearcherName();
    void citePaper(ResearchPaper paper);
    void becomeProjectLeader(ResearchProject project);
    List<ResearchPaper> getAllProjectPapers();
    List<ResearchPaper> getAllAuthoredProjectPapers();
}

