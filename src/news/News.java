package news;

import enums.NewsTopic;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class News implements Cloneable, Serializable {

    private Date date;
    private String description;
    private String title;
    private NewsTopic topic;

    // Конструктор по умолчанию
    public News() {}

    // Конструктор с параметрами
    public News(String description, String title, NewsTopic topic) {
        this.description = description;
        this.title = title;
        this.topic = topic;
        this.date = new Date(); // Устанавливаем текущую дату по умолчанию
    }

    // Геттеры и сеттеры
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

    public NewsTopic getTopic() {
        return this.topic;
    }

    public void setTopic(NewsTopic topic) {
        this.topic = topic;
    }

    // Метод клонирования
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    // Переопределение метода toString
    @Override
    public String toString() {
        return "Title: " + title + "\nDescription: " + description + "\nTopic: " + topic + "\nDate: " + date;
    }

    // Переопределение метода equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return Objects.equals(date, news.date) &&
               Objects.equals(description, news.description) &&
               Objects.equals(title, news.title) &&
               topic == news.topic;
    }

    // Переопределение метода hashCode
    @Override
    public int hashCode() {
        return Objects.hash(date, description, title, topic);
    }
}
