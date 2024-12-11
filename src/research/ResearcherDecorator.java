package research;

import java.util.Comparator;
import java.util.List;

public abstract class ResearcherDecorator implements Researcher {
    protected Researcher wrappedResearcher;

    public ResearcherDecorator(Researcher researcher) {
        this.wrappedResearcher = researcher;
    }

    @Override
    public void publishPaper(ResearchPaper paper) {
        wrappedResearcher.publishPaper(paper);
    }

    @Override
    public int calculateHIndex() {
        return wrappedResearcher.calculateHIndex();
    }

    @Override
    public void printPapers(Comparator<ResearchPaper> comparator) {
        wrappedResearcher.printPapers(comparator);
    }

    @Override
    public void proposeProject() {
        wrappedResearcher.proposeProject();
    }

    @Override
    public void joinProject(ResearchProject project) {
        wrappedResearcher.joinProject(project);
    }

    @Override
    public List<ResearchPaper> getPublications() {
        return wrappedResearcher.getPublications();
    }
}