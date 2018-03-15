package com.example.ghuser.onlinequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String correct_no = intent.getStringExtra("e1");

        ((TextView)findViewById(R.id.resultText)).setText("You have answered "+correct_no+" question(s) correctly!");
    }

    public void finalfn(View v){
        Intent activityChangeIntent = new Intent(ResultActivity.this, MainActivity.class);
        startActivity(activityChangeIntent);
    }
}
