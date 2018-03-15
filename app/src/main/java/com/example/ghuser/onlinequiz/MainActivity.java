package com.example.ghuser.onlinequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_create_quiz:
                Intent activityChangeIntent = new Intent(MainActivity.this, CreateQuizActivity.class);
                startActivity(activityChangeIntent);
                break;
            case R.id.button_enter_quiz:
                Intent activityChangeIntent1 = new Intent(MainActivity.this, FragmentHolderActivity.class);
                startActivity(activityChangeIntent1);
                break;
        }
    }

    /*public void createQuiz(View view){
        Intent activityChangeIntent = new Intent(MainActivity.this, CreateQuizActivity.class);
        startActivity(activityChangeIntent);
    }

    public void enterQuiz(View view){
        Intent activityChangeIntent = new Intent(MainActivity.this, EnterQuizActivity.class);
        startActivity(activityChangeIntent);
    }*/
}
