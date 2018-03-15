package com.example.ghuser.onlinequiz.model;

/**
 * Created by ghuser on 12/6/2017.
 */

public class Question {
    private QuestionType type;
    private String question;
    private String ans;

    public QuestionType getType() {
        return type;
    }
    public void setType(QuestionType type) {
        this.type = type;
    }
    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    public String getAns() {
        return ans;
    }
    public void setAns(String ans) {
        this.ans = ans;
    }
}
