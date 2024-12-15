package research;

import java.util.ArrayList;
import java.util.List;

import utils.IdGenerator;

public class ResearchProject {
    private String projectId;
    private String projectName;
    private List<ResearchPaper> papers;
    private List<Researcher> participants = new ArrayList<>();
    private Researcher projectLeader; 

    public ResearchProject(String projectName, Researcher projectLeader) {
        this.setProjectId(IdGenerator.generateUniqueId("RPJ"));
        this.setProjectName(projectName);
        this.projectLeader = projectLeader;
        this.papers = new ArrayList<>();
        participants.add(projectLeader);
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }


    public List<ResearchPaper> getPapers() {
        return papers;
    }

    public boolean addPaper(ResearchPaper paper) {
        return papers.add(paper);
    }

    public boolean deletePaper(ResearchPaper paper) {
        return papers.remove(paper);
    }

    public void renamePaper(ResearchPaper paper, String newTitle) {
        paper.setTitle(newTitle);
    }

    public void renameProjectName(String newProjectName) {
        setProjectName(newProjectName);
    }
    
    public void citeProjectPaper(ResearchPaper paper) {
        paper.addCitation();
    }
    
    public int getProjectPapersNumber() {
    	return papers.size();
    }
    
    public List<Researcher> getParticipants() {
        return participants;
    }

    public void addParticipant(Researcher researcher) {
        if (!participants.contains(researcher)) {
            participants.add(researcher);
        }
    }

    public void removeParticipant(Researcher researcher) {
        participants.remove(researcher);
    }

    public Researcher getProjectLeader() {
        return projectLeader;
    }

    public void setProjectLeader(Researcher projectLeader) {
        this.projectLeader = projectLeader;
    }
    
    @Override
    public String toString() {
        StringBuilder projectInfo = new StringBuilder();
        projectInfo.append(String.format("Project ID: %s\nProject Name: \"%s\"\n", projectId, projectName));
        projectInfo.append(String.format("Project Leader: %s\n", projectLeader.getResearcherName()));
        
        if (!participants.isEmpty()) {
            projectInfo.append("Participants:\n");
            for (Researcher participant : participants) {
                projectInfo.append("  - " + participant.getResearcherName() + "\n");
            }
        } else {
            projectInfo.append("No participants in this project.\n");
        }

        if (!papers.isEmpty()) {
            projectInfo.append("Research Papers:\n");
            for (ResearchPaper paper : papers) {
                projectInfo.append("  - " + paper.toString() + "\n");
            }
        } else {
            projectInfo.append("No papers in this project.\n");
        }
        
        return projectInfo.toString();
    }
     
//    public static void main(String[] args) {
//        ResearcherClass researcher = new ResearcherClass();
//        researcher.setName("Aibar");
//
//        ResearchPaper paper = new ResearchPaper(1, "KBTU research", researcher, 2003, 05, 30);
//        ResearchPaper paper1 = new ResearchPaper(2, "Almaty research", researcher, 2014, 17, 23);
//
//        ResearchProject project = new ResearchProject(101, "Research Project 1");
//
//        project.addPaper(paper);
//        project.addPaper(paper1);
//
//        System.out.println("Project ID: " + project.getProjectId());
//        System.out.println("Project Name: " + project.getProjectName());
//        System.out.println("Number of Papers: " + project.getNumberOfPapers());
//        System.out.println(" ");
//        for (ResearchPaper p : project.getPapers()) {
//            System.out.println("Paper ID: " + p.getPaperId());
//            System.out.println("Paper Title: " + p.getTitle());
//            System.out.println("Author: " + p.getAuthor().getName());
//            System.out.println("Publication Year: " + p.getPublicationYear());
//            System.out.println("Number of Pages: " + p.getNumberOfPages());
//            System.out.println("Citation Number: " + p.getCitationNumber());
//            System.out.println(" ");
//        }
//    }

}