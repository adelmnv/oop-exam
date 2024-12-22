package research;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

public abstract class ResearcherDecorator implements Researcher, Serializable{
	private static final long serialVersionUID = 1L;
	protected Researcher wrappedResearcher;

    public void setResearcher(Researcher researcher) {
        this.wrappedResearcher = researcher;
    }
    
    public Researcher getResearcher() {
    	return this.wrappedResearcher;
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
    public void proposeProject(String projectName) {
        wrappedResearcher.proposeProject(projectName);
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


//public String printPapers(ResearchProject project, Comparator<ResearchPaper> comparator) {
//	List<ResearchPaper> papers = project.getPapers();
//    Collections.sort(papers, comparator);
//
//    StringBuilder result = new StringBuilder();
//    result.append("Papers for Project: ").append(project.getProjectName()).append("\n");
//
//    for (ResearchPaper paper : papers) {
//        result.append("Paper ID: ").append(paper.getPaperId())
//              .append(", Paper Title: ").append(paper.getTitle())
//              .append(", Author: ").append(paper.getAuthor().getName())
//              .append(", Publication Year: ").append(paper.getPublicationYear())
//              .append(", Number of Pages: ").append(paper.getNumberOfPages())
//              .append(", Citation Number: ").append(paper.getCitationNumber())
//              .append("\n");
//    }
//
//    return result.toString();
//}
