package com.bridgelabz.greetingapp.model;

import java.time.LocalDateTime;

public class Greeting {

    private long greetingId;
    private  String userName;
    private String greetingMessage;
    private LocalDateTime createdDate;

    public Greeting() {
    }

    public Greeting(Long greetingId,
                    String userName,
                    String greetingMessage,
                    LocalDateTime createdDate) {

        this.greetingId = greetingId;
        this.userName = userName;
        this.greetingMessage = greetingMessage;
        this.createdDate = createdDate;
    }

    public Long getGreetingId() {
        return greetingId;
    }

    public void setGreetingId(Long greetingId) {
        this.greetingId = greetingId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGreetingMessage() {
        return greetingMessage;
    }

    public void setGreetingMessage(String greetingMessage) {
        this.greetingMessage = greetingMessage;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Greeting{" +
                "greetingId=" + greetingId +
                ", userName='" + userName + '\'' +
                ", greetingMessage='" + greetingMessage + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }

}
