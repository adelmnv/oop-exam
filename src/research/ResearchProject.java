package research;

import java.util.ArrayList;
import java.util.List;

public class ResearchProject {
    private int projectId;
    private String projectName;
    private int numberOfPapers;
    private List<ResearchPaper> papers;

    public ResearchProject(int projectId, String projectName) {
        this.setProjectId(projectId);
        this.setProjectName(projectName);
        this.setNumberOfPapers(numberOfPapers);
        this.papers = new ArrayList<>();
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getNumberOfPapers() {
        return numberOfPapers;
    }

    public void setNumberOfPapers(int numberOfPapers) {
        this.numberOfPapers = numberOfPapers;
    }

    public List<ResearchPaper> getPapers() {
        return papers;
    }

    public void addPaper(ResearchPaper paper) {
        papers.add(paper);
        setNumberOfPapers(papers.size());
    }

    public void deletePaper(ResearchPaper paper) {
        papers.remove(paper);
        setNumberOfPapers(papers.size());
    }

    public void renamePaper(ResearchPaper paper, String newTitle) {
        paper.setTitle(newTitle);
    }

    public void renameProjectName(String newProjectName) {
        setProjectName(newProjectName);
    }
    public static void main(String[] args) {
        ResearcherClass researcher = new ResearcherClass();
        researcher.setName("Aibar");

        ResearchPaper paper = new ResearchPaper(1, "KBTU research", researcher, 2003, 05, 30);
        ResearchPaper paper1 = new ResearchPaper(2, "Almaty research", researcher, 2014, 17, 23);

        ResearchProject project = new ResearchProject(101, "Research Project 1");

        project.addPaper(paper);
        project.addPaper(paper1);

        System.out.println("Project ID: " + project.getProjectId());
        System.out.println("Project Name: " + project.getProjectName());
        System.out.println("Number of Papers: " + project.getNumberOfPapers());
        System.out.println(" ");
        for (ResearchPaper p : project.getPapers()) {
            System.out.println("Paper ID: " + p.getPaperId());
            System.out.println("Paper Title: " + p.getTitle());
            System.out.println("Author: " + p.getAuthor().getName());
            System.out.println("Publication Year: " + p.getPublicationYear());
            System.out.println("Number of Pages: " + p.getNumberOfPages());
            System.out.println("Citation Number: " + p.getCitationNumber());
            System.out.println(" ");
        }
    }

}