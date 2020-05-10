package model;

import java.util.Date;

public class Message {
    private Integer id;
    private String text;
    private Date data;

    public Message(String text, Date data) {
        this.text = text;
        this.data = data;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", data=" + data +
                '}';
    }
}
