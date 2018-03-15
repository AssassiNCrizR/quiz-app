package com.example.ghuser.onlinequiz.model;

/**
 * Created by ghuser on 12/6/2017.
 */

public class FillBlankQuestion extends Question {
    public FillBlankQuestion(String ques, String answer) {
        this.setType(QuestionType.FILL_BLANK);
        this.setQuestion(ques);
        this.setAns(answer);
    }
}
