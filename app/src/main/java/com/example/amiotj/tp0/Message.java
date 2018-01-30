package com.example.amiotj.tp0;


public class Message {
    public String content;
    public String userName;
    public Long timestamp;

    public Message() {

    }

    public Message(String content, String userName, Long timestamp) {
        this.content = content;
        this.userName = userName;
        this.timestamp = timestamp;
    }
}
