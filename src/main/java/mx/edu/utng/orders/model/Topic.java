package mx.edu.utng.orders.model;

import android.widget.Toast;

/**
 * Created by user on 23/02/2017.
 */

public class Topic {
    private String idTopic;
    private String topicSubject;
    private String topicDate;

    public Topic(String idTopic, String topicSubject, String topicDate) {
        this.idTopic = idTopic;
        this.topicSubject = topicSubject;
        this.topicDate = topicDate;
    }

    public Topic(){
        this("","","");
    }

    public String getIdTopic() {
        return idTopic;
    }

    public void setIdTopic(String idTopic) {
        this.idTopic = idTopic;
    }

    public String getTopicSubject() {
        return topicSubject;
    }

    public void setTopicSubject(String topicSubject) {
        this.topicSubject = topicSubject;
    }

    public String getTopicDate() {
        return topicDate;
    }

    public void setTopicDate(String topicDate) {
        this.topicDate = topicDate;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "idTopic='" + idTopic + '\'' +
                ", topicSubject='" + topicSubject + '\'' +
                ", topicDate='" + topicDate + '\'' +
                '}';
    }
}
