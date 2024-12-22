package repositories;

import java.util.HashMap;
import java.util.Map;

import research.ResearchProject;
import research.Researcher;

public class ResearchProjectRepository {

    private static ResearchProjectRepository instance;
    private Map<String, ResearchProject> projects;

    private ResearchProjectRepository() {
        projects = new HashMap<>();
    }

    public static ResearchProjectRepository getInstance() {
        if (instance == null) {
            instance = new ResearchProjectRepository();
        }
        return instance;
    }

    public void addProject(ResearchProject project) {
        projects.put(project.getProjectId(), project);
    }

    public void removeProject(String projectId) {
        projects.remove(projectId);
    }

    public ResearchProject getProjectById(String projectId) {
        return projects.get(projectId);
    }

    public ResearchProject getProjectByName(String projectName) {
        for (ResearchProject project : projects.values()) {
            if (project.getProjectName().equalsIgnoreCase(projectName)) {
                return project;
            }
        }
        return null;
    }

    public Map<String, ResearchProject> getAllProjects() {
        return projects;
    }

    public boolean exists(String projectId) {
        return projects.containsKey(projectId);
    }

    public Map<String, ResearchProject> getProjectsByResearcher(Researcher researcher) {
        Map<String, ResearchProject> researcherProjects = new HashMap<>();
        for (ResearchProject project : projects.values()) {
            if (project.getParticipants().contains(researcher)) {
                researcherProjects.put(project.getProjectId(), project);
            }
        }
        return researcherProjects;
    }

    public Map<String, ResearchProject> getProjectsByLeader(Researcher leader) {
        Map<String, ResearchProject> leaderProjects = new HashMap<>();
        for (ResearchProject project : projects.values()) {
            if (project.getProjectLeader().equals(leader)) {
                leaderProjects.put(project.getProjectId(), project);
            }
        }
        return leaderProjects;
    }

    public void printAllProjects() {
        if (projects.isEmpty()) {
            System.out.println("No projects found.");
        } else {
            for (ResearchProject project : projects.values()) {
                System.out.println(project);
            }
        }
    }
}
