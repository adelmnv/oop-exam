package news;

import observers.Observer;
import research.ResearchProject;

import java.util.ArrayList;
import java.util.List;

public class Journal {
	private String name;
    private final List<Observer> subscribers = new ArrayList<>();
    private final List<ResearchProject> publishedProjects = new ArrayList<>();

    public Journal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public void addSubscriber(Observer subscriber) {
        subscribers.add(subscriber);
    }

    public void removeSubscriber(Observer subscriber) {
        subscribers.remove(subscriber);
    }

    private void notifySubscribers(ResearchProject project) {
        for (Observer subscriber : subscribers) {
            subscriber.update(this,project);
        }
    }

    public void publishProject(ResearchProject project) {
        publishedProjects.add(project);
        System.out.println("Published new project: " + project.getProjectName());
        notifySubscribers(project);
    }

    public List<ResearchProject> getPublishedProjects() {
        return publishedProjects;
    }
}
