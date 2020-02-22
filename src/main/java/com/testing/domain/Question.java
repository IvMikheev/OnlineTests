package com.testing.domain;

public class Question {
    private Long id;
    private String text;

    public Question() {
    }

    public Question(String text) {
        this.text = text;
    }

    public Question(Long id, String text) {
        this.id = id;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
