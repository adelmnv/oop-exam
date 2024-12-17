package news;

import news.News;
import enums.NewsTopic;
import observers.NewsObserver;

import java.util.ArrayList;
import java.util.List;

public class NewsManagerr {
    private final List<NewsObserver> observers = new ArrayList<>();
    private final List<News> newsList = new ArrayList<>();

    // Добавить наблюдателя
    public void addObserver(NewsObserver observer) {
        observers.add(observer);
    }

    // Удалить наблюдателя
    public void removeObserver(NewsObserver observer) {
        observers.remove(observer);
    }

    // Уведомить всех наблюдателей
    public void notifyObservers(News news) {
        for (NewsObserver observer : observers) {
            observer.update(news);
        }
    }

    // Добавить новость
    public void addNews(News news) {
        if (news.getTopic() == NewsTopic.RESEARCH) {
            newsList.add(0, news); // Приоритет для новостей о исследованиях
        } else {
            newsList.add(news);
        }
        notifyObservers(news); // Уведомляем всех
    }

    public List<News> getNewsList() {
        return newsList;
    }
}