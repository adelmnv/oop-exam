package research;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import users.Student;

public class StudentResearcher extends ResearcherDecorator {
	private static final long serialVersionUID = 1L;
	private Student student;
	private List<ResearchPaper> publications = new ArrayList<>();
	private List<ResearchProject> projects = new ArrayList<>();
	
    public StudentResearcher(Student student) {
		this.student = student;
	}
    
    public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	public List<ResearchProject> getProjects() {
		return projects;
	}

	public void setProjects(List<ResearchProject> projects) {
		this.projects = projects;
	}

    @Override
    public void publishPaper(ResearchPaper paper) {
        publications.add(paper);
    }

    @Override
    public int calculateHIndex() {
    	List<ResearchPaper> allPapers = new ArrayList<>(publications);
    	allPapers.addAll(getAllAuthoredProjectPapers());
    	allPapers.sort(new ComparatorByCitation().reversed());
        int hIndex = 0;
        for (int i = 0; i < allPapers.size(); i++) {
        	if (allPapers.get(i).getCitationNumber() >= i + 1) {
                hIndex = i + 1;
            } else {
                break;
            }
        }
        return hIndex;
    }

    @Override
    public void printPapers(Comparator<ResearchPaper> comparator) {
    	List<ResearchPaper> allPapers = new ArrayList<>(publications);
    	allPapers.addAll(getAllAuthoredProjectPapers());
        allPapers.sort(comparator);
        allPapers.forEach(paper -> System.out.println(paper));
    }

    @Override
    public void proposeProject(String projectName) {
    	for (ResearchProject project : projects) {
            if (project.getProjectName().equals(projectName)) {
                System.out.println("Project with the same name already exists.");
                return;
            }
        }
    	ResearchProject newProject = new ResearchProject(projectName, this);
        projects.add(newProject);
        System.out.println("Project " + newProject.getProjectName() + " has been proposed by " + getResearcherName());
    }

    @Override
    public void joinProject(ResearchProject project) {
        if (!projects.contains(project)) {
            projects.add(project);
            project.addParticipant(this);
            System.out.println("Joined project: " + project.getProjectName());
        } else {
            System.out.println("Already part of the project: " + project.getProjectName());
        }
    }
    
    @Override
    public void becomeProjectLeader(ResearchProject project) {
        if (projects.contains(project)) {
            project.setProjectLeader(this);
            System.out.println(this.getResearcherName() + " is now the project leader of " + project.getProjectName());
        } else {
            System.out.println(this.getResearcherName() + " is not part of the project: " + project.getProjectName());
        }
    }

    @Override
    public List<ResearchPaper> getPublications() {
        return publications;
    }
    
    @Override
    public List<ResearchPaper> getAllProjectPapers() {
        List<ResearchPaper> projectPapers = new ArrayList<>();
        for (ResearchProject project : projects) {
        	projectPapers.addAll(project.getPapers());
        }
        return projectPapers;
    }
    
    @Override
    public List<ResearchPaper> getAllAuthoredProjectPapers() {
        List<ResearchPaper> authoredPapers = new ArrayList<>();
        for (ResearchProject project : projects) {
            for (ResearchPaper paper : project.getPapers()) {
                if (paper.getAuthor().equals(this)) {
                    authoredPapers.add(paper);
                }
            }
        }
        return authoredPapers;
    }

	@Override
	public String getResearcherName() {
		return student.getFullName();
	}

	@Override
	public void citePaper(ResearchPaper paper) {
	    paper.addCitation();
	}
	
	@Override
	public String toString() {
	    StringBuilder researcherInfo = new StringBuilder();
	    researcherInfo.append("Student Researcher: ").append(student.getFullName())
	                  .append("\nPublications: ").append(publications.size())
	                  .append("\nProjects: ").append(projects.size()).append("\n");

	    if (!publications.isEmpty()) {
	        researcherInfo.append("Research Papers:\n");
	        for (ResearchPaper paper : publications) {
	            researcherInfo.append("  - " + paper.toString() + "\n");
	        }
	    }

	    if (!projects.isEmpty()) {
	        researcherInfo.append("Projects Participated In:\n");
	        for (ResearchProject project : projects) {
	            researcherInfo.append("  - " + project.getProjectName() + "\n");
	        }
	    }
	    return researcherInfo.toString();
	}
}
