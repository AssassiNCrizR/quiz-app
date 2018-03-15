package com.example.ghuser.onlinequiz.model;

import java.util.ArrayList;

/**
 * Created by ghuser on 12/6/2017.
 */

public class Exam {
    public String id;
    public ArrayList<Question> arrL;

    public Exam(String id, ArrayList<Question> arrL) {
        this.id = id;
        this.arrL = arrL;
    }
}
