package com.example.ghuser.onlinequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ghuser.onlinequiz.model.DataHolder;
import com.example.ghuser.onlinequiz.model.Exam;

import java.util.Iterator;

public class EnterQuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_quiz);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void sendkey(View v){
        String key = ((EditText) findViewById(R.id.keyText)).getText().toString();

        Iterator<Exam> itr = DataHolder.newInstance().examL.iterator();
        int flag = 0;
        while(itr.hasNext()){
            Exam examid = itr.next();
            if(examid.id.matches(key)){
                flag = 1;
            }
        }

        if(flag == 1){
            Intent activityChangeIntent = new Intent(EnterQuizActivity.this, QuizDisplayActivity.class);
            activityChangeIntent.putExtra("e1", key);
            startActivity(activityChangeIntent);
        }
        else if(flag == 0){
            Toast.makeText(EnterQuizActivity.this, "Wrong Key! Please Try Again.", Toast.LENGTH_LONG).show();
        }
    }
}
