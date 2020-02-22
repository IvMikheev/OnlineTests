package com.testing.domain;

public class UserAnswer {
    private Long id;
    private String username;
    private String question;
    private String answer;
    private boolean correct;

    public UserAnswer() {
    }

    public UserAnswer(String username, String question, String answer, boolean correct) {
        this.username = username;
        this.question = question;
        this.answer = answer;
        this.correct = correct;
    }

    public UserAnswer(Long id, String username, String question, String answer, boolean correct) {
        this.id = id;
        this.username = username;
        this.question = question;
        this.answer = answer;
        this.correct = correct;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
}
