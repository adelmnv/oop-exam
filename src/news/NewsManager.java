package news;

import enums.NewsTopic;
import observers.Observer;

import java.util.ArrayList;
import java.util.List;

public class NewsManager {
    private final List<Observer> observers = new ArrayList<>();
    private final List<News> newsList = new ArrayList<>();
    private static final NewsManager INSTANCE = new NewsManager();

    private NewsManager() {}

    public static NewsManager getInstance() {
        return INSTANCE;
    }
    
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

    public void addNews(News news) {
        if (news.getTopic() == NewsTopic.RESEARCH) {
            newsList.add(0, news);
        } else {
            newsList.add(news);
        }
        notifyObservers(news);
    }

    public List<News> getNewsList() {
        return newsList;
    }
}