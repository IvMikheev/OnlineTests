package com.testing.domain;

public class Answer {
    private Long id;
    private String text;
    private String parentQuestion;
    private boolean correctAnswer;

    public Answer() {
    }

    public Answer(String text, String parentQuestion, boolean correctAnswer) {
        this.text = text;
        this.parentQuestion = parentQuestion;
        this.correctAnswer = correctAnswer;
    }

    public Answer(Long id, String text, String parentQuestion, boolean correctAnswer) {
        this.id = id;
        this.text = text;
        this.parentQuestion = parentQuestion;
        this.correctAnswer = correctAnswer;
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

    public String getParentQuestion() {
        return parentQuestion;
    }

    public void setParentQuestion(String parentQuestion) {
        this.parentQuestion = parentQuestion;
    }

    public boolean isCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    @Override
    public String toString() {
        return text;
    }
}
