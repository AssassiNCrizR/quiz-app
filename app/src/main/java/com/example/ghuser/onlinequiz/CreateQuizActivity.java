package com.example.ghuser.onlinequiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.ghuser.onlinequiz.model.DataHolder;
import com.example.ghuser.onlinequiz.model.Exam;
import com.example.ghuser.onlinequiz.model.FillBlankQuestion;
import com.example.ghuser.onlinequiz.model.MCQQuestion;
import com.example.ghuser.onlinequiz.model.Question;

import java.util.ArrayList;

public class CreateQuizActivity extends AppCompatActivity {

    public ArrayList<Question> arrL = new ArrayList<Question>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quiz);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        boolean checked1 = ((RadioButton)findViewById(R.id.radio_mcq)).isChecked();
        boolean checked2 = ((RadioButton)findViewById(R.id.radio_fillblank)).isChecked();
        savedInstanceState.putBoolean("mcqBoolean", checked1);
        savedInstanceState.putBoolean("fillBoolean", checked2);

        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);

        boolean mcqBoolean = savedInstanceState.getBoolean("mcqBoolean");
        boolean fillBoolean = savedInstanceState.getBoolean("fillBoolean");
        if(mcqBoolean == true){
            ((RadioButton)findViewById(R.id.radio_mcq)).performClick();
        }
        else if(fillBoolean == true){
            ((RadioButton)findViewById(R.id.radio_fillblank)).performClick();
        }
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_mcq:
                if (checked)
                    // Pirates are the best
                    findViewById(R.id.questionText).setEnabled(true);

                    findViewById(R.id.optionAText).setEnabled(true);

                    findViewById(R.id.optionBText).setEnabled(true);

                    findViewById(R.id.optionCText).setEnabled(true);

                    findViewById(R.id.optionDText).setEnabled(true);

                    findViewById(R.id.answerText).setEnabled(true);
                findViewById(R.id.optionAText).setFocusable(true);
                findViewById(R.id.optionBText).setFocusable(true);
                findViewById(R.id.optionCText).setFocusable(true);
                findViewById(R.id.optionDText).setFocusable(true);
                    break;
            case R.id.radio_fillblank:
                if (checked)
                    // Ninjas rule
                    findViewById(R.id.questionText).setEnabled(true);

                    findViewById(R.id.answerText).setEnabled(true);

                findViewById(R.id.optionAText).setFocusable(false);
                findViewById(R.id.optionBText).setFocusable(false);
                findViewById(R.id.optionCText).setFocusable(false);
                findViewById(R.id.optionDText).setFocusable(false);
                    break;
        }
    }

    public void openpop (View view){
        EditText ed = (EditText)findViewById(R.id.answerText);
        String value = ed.getText().toString();

        if(value.length() == 0){
            Toast.makeText(CreateQuizActivity.this, "Please fill the required fields before submitting!", Toast.LENGTH_LONG).show();
        }
        else {
            if(((RadioButton)findViewById(R.id.radio_mcq)).isChecked()){
                String question = ((EditText)findViewById(R.id.questionText)).getText().toString();
                String optionA = ((EditText)findViewById(R.id.optionAText)).getText().toString();
                String optionB = ((EditText)findViewById(R.id.optionBText)).getText().toString();
                String optionC = ((EditText)findViewById(R.id.optionCText)).getText().toString();
                String optionD = ((EditText)findViewById(R.id.optionDText)).getText().toString();
                String answer = ((EditText)findViewById(R.id.answerText)).getText().toString();
                Question questionObject = new MCQQuestion(question,optionA,optionB,optionC,optionD,answer);
                arrL.add(questionObject);
            }
            else{
                String question = ((EditText)findViewById(R.id.questionText)).getText().toString();
                String answer = ((EditText)findViewById(R.id.answerText)).getText().toString();
                Question questionObject = new FillBlankQuestion(question,answer);
                arrL.add(questionObject);
            }
            android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("Do you want to add more questions?");
            alertDialogBuilder.setPositiveButton("Yes",
                    new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            ((EditText)findViewById(R.id.questionText)).setText("");
                            ((EditText)findViewById(R.id.optionAText)).setText("");
                            ((EditText)findViewById(R.id.optionBText)).setText("");
                            ((EditText)findViewById(R.id.optionCText)).setText("");
                            ((EditText)findViewById(R.id.optionDText)).setText("");
                            ((EditText)findViewById(R.id.answerText)).setText("");
                            ((RadioButton)findViewById(R.id.radio_mcq)).setChecked(false);
                            ((RadioButton)findViewById(R.id.radio_fillblank)).setChecked(false);
                            //System.out.println(arrL);
                        }
                    });
            alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    //System.out.println(arrL);
                    int randomPIN = (int) (Math.random() * 9000) + 1000;
                    String PINString = String.valueOf(randomPIN);
                    Exam examObject = new Exam(PINString, arrL);
                    DataHolder.newInstance().examL.add(examObject);
                    Intent activityChangeIntent = new Intent(CreateQuizActivity.this, QuizDetailActivity.class);
                    activityChangeIntent.putExtra("e1", PINString);
                    startActivity(activityChangeIntent);
                }
            });
            android.app.AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }
}
