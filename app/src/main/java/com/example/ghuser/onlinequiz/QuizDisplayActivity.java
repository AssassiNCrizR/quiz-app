package com.example.ghuser.onlinequiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ghuser.onlinequiz.model.DataHolder;
import com.example.ghuser.onlinequiz.model.Exam;
import com.example.ghuser.onlinequiz.model.MCQQuestion;
import com.example.ghuser.onlinequiz.model.Question;
import com.example.ghuser.onlinequiz.model.QuestionType;

import java.util.ArrayList;
import java.util.Iterator;

public class QuizDisplayActivity extends AppCompatActivity {

    ArrayList<Question> questionList = new ArrayList<Question>();
    Iterator<Question> qitr = new Iterator<Question>() {
        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Question next() {
            return null;
        }
    };
    Question examquestion;
    String given_ans = "";
    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_display);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String key = intent.getStringExtra("e1");

        Iterator<Exam> itr = DataHolder.newInstance().examL.iterator();

        while(itr.hasNext()){
            Exam examid = itr.next();
            if(examid.id.matches(key)){
                questionList = examid.arrL;
            }
        }
        qitr = questionList.iterator();
        examquestion = qitr.next();
        if((qitr.hasNext()) == false){
            ((Button)findViewById(R.id.nextbtn)).setText("Finish");
        }
        if(examquestion.getType() == QuestionType.MCQ){
            ((TextView)findViewById(R.id.questiontv)).setText("Q: "+examquestion.getQuestion());
            ((EditText)findViewById(R.id.answerspace)).setVisibility(View.GONE);
            ((RadioButton)findViewById(R.id.radio_optionA)).setText("A. "+((MCQQuestion)examquestion).getOption1());
            ((RadioButton)findViewById(R.id.radio_optionB)).setText("B. "+((MCQQuestion)examquestion).getOption2());
            ((RadioButton)findViewById(R.id.radio_optionC)).setText("C. "+((MCQQuestion)examquestion).getOption3());
            ((RadioButton)findViewById(R.id.radio_optionD)).setText("D. "+((MCQQuestion)examquestion).getOption4());
        }
        else if(examquestion.getType() == QuestionType.FILL_BLANK){
            ((TextView)findViewById(R.id.questiontv)).setText("Q: "+examquestion.getQuestion());
            ((RadioButton)findViewById(R.id.radio_optionA)).setVisibility(View.GONE);
            ((RadioButton)findViewById(R.id.radio_optionB)).setVisibility(View.GONE);
            ((RadioButton)findViewById(R.id.radio_optionC)).setVisibility(View.GONE);
            ((RadioButton)findViewById(R.id.radio_optionD)).setVisibility(View.GONE);
        }
    }

    public void checkfn(View v){
        if(examquestion.getType() == QuestionType.MCQ){
            if(((RadioButton)findViewById(R.id.radio_optionA)).isChecked() == true){
                given_ans = "a";
            }
            if(((RadioButton)findViewById(R.id.radio_optionB)).isChecked() == true){
                given_ans = "b";
            }
            if(((RadioButton)findViewById(R.id.radio_optionC)).isChecked() == true){
                given_ans = "c";
            }
            if(((RadioButton)findViewById(R.id.radio_optionD)).isChecked() == true){
                given_ans = "d";
            }
            if(given_ans.matches(examquestion.getAns().toLowerCase())){
                i++;
                android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(this);
                alertDialogBuilder.setMessage("Correct Answer!!");
                alertDialogBuilder.setPositiveButton("Ok",
                        new DialogInterface.OnClickListener(){

                            public void onClick(DialogInterface dialog, int which){
                                //Toast.makeText(QuizDisplayActivity.this, "GoodLuck!", Toast.LENGTH_LONG).show();
                                Snackbar.make(findViewById(android.R.id.content),"GoodLuck!",Snackbar.LENGTH_LONG).show();
                            }
                        });
                android.app.AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
            else{
                android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(this);
                alertDialogBuilder.setMessage("Wrong Answer!! Right option is: "+examquestion.getAns().toUpperCase());
                alertDialogBuilder.setPositiveButton("Ok",
                        new DialogInterface.OnClickListener(){

                            public void onClick(DialogInterface dialog, int which){
                                //Toast.makeText(QuizDisplayActivity.this, "GoodLuck!", Toast.LENGTH_LONG).show();
                                Snackbar.make(findViewById(android.R.id.content),"GoodLuck!",Snackbar.LENGTH_LONG).show();
                            }
                        });
                android.app.AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        }
        else if(examquestion.getType() == QuestionType.FILL_BLANK){
            given_ans = ((EditText)findViewById(R.id.answerspace)).getText().toString();
            if(given_ans.toLowerCase().matches(examquestion.getAns().toLowerCase())){
                i++;
                android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(this);
                alertDialogBuilder.setMessage("Correct Answer!!");
                alertDialogBuilder.setPositiveButton("Ok",
                        new DialogInterface.OnClickListener(){

                            public void onClick(DialogInterface dialog, int which){
                                //Toast.makeText(QuizDisplayActivity.this, "GoodLuck!", Toast.LENGTH_LONG).show();
                                Snackbar.make(findViewById(android.R.id.content),"GoodLuck!",Snackbar.LENGTH_LONG).show();
                            }
                        });
                android.app.AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
            else{
                android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(this);
                alertDialogBuilder.setMessage("Wrong Answer!! Correct answer is: '"+examquestion.getAns()+"'");
                alertDialogBuilder.setPositiveButton("Ok",
                        new DialogInterface.OnClickListener(){

                            public void onClick(DialogInterface dialog, int which){
                                //Toast.makeText(QuizDisplayActivity.this, "GoodLuck!", Toast.LENGTH_LONG).show();
                                Snackbar.make(findViewById(android.R.id.content),"GoodLuck!",Snackbar.LENGTH_LONG).show();
                            }
                        });
                android.app.AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        }
    }

    public void nextfn(View v){
        if((qitr.hasNext()) == false){
            String correct_no = String.valueOf(i);
            Intent activityChangeIntent = new Intent(QuizDisplayActivity.this, ResultActivity.class);
            activityChangeIntent.putExtra("e1", correct_no);
            startActivity(activityChangeIntent);
        }
        else{
            examquestion = qitr.next();
            if((qitr.hasNext()) == false){
                ((Button)findViewById(R.id.nextbtn)).setText("Finish");
            }
            if(examquestion.getType() == QuestionType.MCQ){
                ((TextView)findViewById(R.id.questiontv)).setText("Q: "+examquestion.getQuestion());
                ((EditText)findViewById(R.id.answerspace)).setVisibility(View.GONE);
                ((RadioButton)findViewById(R.id.radio_optionA)).setVisibility(View.VISIBLE);
                ((RadioButton)findViewById(R.id.radio_optionB)).setVisibility(View.VISIBLE);
                ((RadioButton)findViewById(R.id.radio_optionC)).setVisibility(View.VISIBLE);
                ((RadioButton)findViewById(R.id.radio_optionD)).setVisibility(View.VISIBLE);
                ((RadioButton)findViewById(R.id.radio_optionA)).setText("A. "+((MCQQuestion)examquestion).getOption1());
                ((RadioButton)findViewById(R.id.radio_optionB)).setText("B. "+((MCQQuestion)examquestion).getOption2());
                ((RadioButton)findViewById(R.id.radio_optionC)).setText("C. "+((MCQQuestion)examquestion).getOption3());
                ((RadioButton)findViewById(R.id.radio_optionD)).setText("D. "+((MCQQuestion)examquestion).getOption4());
            }
            else if(examquestion.getType() == QuestionType.FILL_BLANK){
                ((TextView)findViewById(R.id.questiontv)).setText("Q: "+examquestion.getQuestion());
                ((RadioButton)findViewById(R.id.radio_optionA)).setVisibility(View.GONE);
                ((RadioButton)findViewById(R.id.radio_optionB)).setVisibility(View.GONE);
                ((RadioButton)findViewById(R.id.radio_optionC)).setVisibility(View.GONE);
                ((RadioButton)findViewById(R.id.radio_optionD)).setVisibility(View.GONE);
                ((EditText)findViewById(R.id.answerspace)).setVisibility(View.VISIBLE);
            }
        }
    }
}
