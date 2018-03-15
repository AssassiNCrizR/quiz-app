package com.example.ghuser.onlinequiz.model;

import java.util.ArrayList;

/**
 * Created by ghuser on 12/7/2017.
 */

public class DataHolder {
    public ArrayList<Exam> examL;
    private static DataHolder mDataHolder;

    private DataHolder() {
        // nothing
    }

    public static DataHolder newInstance() {
        if (mDataHolder == null) {
            mDataHolder = new DataHolder();
            mDataHolder.examL = new ArrayList<Exam>();
        }
        return mDataHolder;
    }
}
