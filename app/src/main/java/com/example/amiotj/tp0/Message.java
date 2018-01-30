package com.example.amiotj.tp0;


public class Message {
    public String content;
    public String userName;
    public String userEmail;
    public Long timestamp;

    public Message() {

    }

    public Message(String content, String userName, String email, Long timestamp) {
        this.content = content;
        this.userName = userName;
        this.userEmail = email;
        this.timestamp = timestamp;
    }
}
