package com.example.ghuser.onlinequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class QuizDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String s;
        TextView tv = (TextView) findViewById(R.id.exam_key_text);
        Intent intent = getIntent();
        s = intent.getStringExtra("e1");
        tv.setText("Your Quiz Key is = "+ s);
    }


    public void geback(View v){
        Intent activityChangeIntent = new Intent(QuizDetailActivity.this, MainActivity.class);
        startActivity(activityChangeIntent);
    }
}
