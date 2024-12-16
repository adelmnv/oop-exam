package news;

import observers.NewsObserver;

public class NewsStudent implements NewsObserver {
    private final String name;

    public NewsStudent(String name) {
        this.name = name;
    }

    @Override
    public void update(News news) {
        System.out.println("Student " + name + " received news update: \n" + news);
    }
}
