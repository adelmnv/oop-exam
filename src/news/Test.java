package news;

import news.NewsManagerr;
import news.News;
import enums.NewsTopic;
import news.NewsStudent;

public class Test {
    public static void main(String[] args) {
        // Создаём менеджер новостей
        NewsManagerr newsManager = new NewsManagerr();

        // Создаём студентов-наблюдателей
        NewsStudent student1 = new NewsStudent("Alice");
        NewsStudent student2 = new NewsStudent("Bob");

        // Регистрируем наблюдателей
        newsManager.addObserver(student1);
        newsManager.addObserver(student2);

        // Добавляем новости
        News generalNews = new News(
            "Midterm exams are scheduled for next week.",
            "Midterm Announcement",
            NewsTopic.ANNOUNCEMENT
        );
        newsManager.addNews(generalNews);

        News researchNews = new News(
            "New research on AI published!",
            "AI Research",
            NewsTopic.RESEARCH
        );
        newsManager.addNews(researchNews);

        News eventNews = new News(
            "University annual sports day is coming!",
            "Sports Day Announcement",
            NewsTopic.EVENTS
        );
        newsManager.addNews(eventNews);
    }
}
