package news;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

// Observer interface
interface Observer {
    void update(News news);
}

// Observable class
class NewsManager {
    private final List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(News news) {
        for (Observer observer : observers) {
            observer.update(news);
        }
    }
}

// News class
public class News implements Cloneable, Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 232;
	private Date date;
    private String description;
    private String title;

    public News() {}

    public News(String description, String title) {
        this.description = description;
        this.title = title;
        this.date = new Date(); 
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Title: " + title + "\nDescription: " + description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        News news = (News) o;

        if (!Objects.equals(date, news.date)) return false;
        if (!Objects.equals(description, news.description)) return false;
        return Objects.equals(title, news.title);
    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }
}

// Example Observer implementation
class Student implements Observer {
    private final String name;

    public Student(String name) {
        this.name = name;
    }
 
    @Override
    public void update(News news) {
        System.out.println("Student " + name + " received news update: \n" + news);
    }
}

// Main class to demonstrate functionality
class Main {
    public static void main(String[] args) {
        NewsManager newsManager = new NewsManager();

        Student student1 = new Student("Alice");
        Student student2 = new Student("Bob");

        newsManager.addObserver(student1);
        newsManager.addObserver(student2);

        News news1 = new News("Midterm exams are scheduled for next week.", "Midterm Announcement");
        News news2 = new News("University will be closed tomorrow due to weather conditions.", "Weather Update");

        newsManager.notifyObservers(news1);
        newsManager.notifyObservers(news2);
    }
}
