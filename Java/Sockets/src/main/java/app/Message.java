package app;

import java.io.Serializable;

public class Message implements Serializable {
    public Message(int number, String content) {
        this.number = number;
        this.content = content;
    }

    static final long serialVersionUID = 13L;
    private int number;
    private String content;

    public void setNumber(int number) {
        this.number = number;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getNumber() {
        return number;
    }

    public String getContent() {
        return content;
    }
}
