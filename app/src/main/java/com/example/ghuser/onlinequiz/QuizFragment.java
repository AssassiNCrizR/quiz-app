package com.example.ghuser.onlinequiz;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ghuser.onlinequiz.model.DataHolder;
import com.example.ghuser.onlinequiz.model.Exam;
import com.example.ghuser.onlinequiz.model.MCQQuestion;
import com.example.ghuser.onlinequiz.model.Question;
import com.example.ghuser.onlinequiz.model.QuestionType;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link QuizFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link QuizFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuizFragment extends Fragment {
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
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public QuizFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QuizFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuizFragment newInstance(String param1, String param2) {
        QuizFragment fragment = new QuizFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String key = getArguments().getString("key");
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_quiz, container, false);
        Iterator<Exam> itr = DataHolder.newInstance().examL.iterator();

        if (savedInstanceState != null) {
            i = savedInstanceState.getInt("corr_ans");
            while (itr.hasNext()) {
                Exam examid = itr.next();
                if (examid.id.matches(key)) {
                    questionList = examid.arrL;
                }
            }
            qitr = questionList.iterator();
            examquestion = qitr.next();

            //Restore the fragment's state here
            String questionText = savedInstanceState.getString("qText");
            while((("Q: " + examquestion.getQuestion()).matches(questionText)) == false){
                examquestion = qitr.next();
            }
            if ((qitr.hasNext()) == false) {
                ((Button) view.findViewById(R.id.nextbutton)).setText("Finish");
            }
            if (examquestion.getType() == QuestionType.MCQ) {
                ((RadioButton) view.findViewById(R.id.optionA_radio)).setText("A. " + ((MCQQuestion) examquestion).getOption1());
                ((RadioButton) view.findViewById(R.id.optionB_radio)).setText("B. " + ((MCQQuestion) examquestion).getOption2());
                ((RadioButton) view.findViewById(R.id.optionC_radio)).setText("C. " + ((MCQQuestion) examquestion).getOption3());
                ((RadioButton) view.findViewById(R.id.optionD_radio)).setText("D. " + ((MCQQuestion) examquestion).getOption4());
            }
            String answerText = savedInstanceState.getString("ansText");
            int etVisibility = savedInstanceState.getInt("etVis");
            int r1Visibility = savedInstanceState.getInt("r1Vis");
            int r2Visibility = savedInstanceState.getInt("r2Vis");
            int r3Visibility = savedInstanceState.getInt("r3Vis");
            int r4Visibility = savedInstanceState.getInt("r4Vis");
            Boolean checked1 = savedInstanceState.getBoolean("chk1");
            Boolean checked2 = savedInstanceState.getBoolean("chk2");
            Boolean checked3 = savedInstanceState.getBoolean("chk3");
            Boolean checked4 = savedInstanceState.getBoolean("chk4");
            ((TextView)view.findViewById(R.id.questiontextview)).setText(questionText);
            ((EditText)view.findViewById(R.id.answeret)).setVisibility(etVisibility);
            ((EditText)view.findViewById(R.id.answeret)).setText(answerText);
            ((RadioButton)view.findViewById(R.id.optionA_radio)).setVisibility(r1Visibility);
            ((RadioButton)view.findViewById(R.id.optionB_radio)).setVisibility(r2Visibility);
            ((RadioButton)view.findViewById(R.id.optionC_radio)).setVisibility(r3Visibility);
            ((RadioButton)view.findViewById(R.id.optionD_radio)).setVisibility(r4Visibility);
            ((RadioButton)view.findViewById(R.id.optionA_radio)).setChecked(checked1);
            ((RadioButton)view.findViewById(R.id.optionB_radio)).setChecked(checked2);
            ((RadioButton)view.findViewById(R.id.optionC_radio)).setChecked(checked3);
            ((RadioButton)view.findViewById(R.id.optionD_radio)).setChecked(checked4);
        }
        else {

            while (itr.hasNext()) {
                Exam examid = itr.next();
                if (examid.id.matches(key)) {
                    questionList = examid.arrL;
                }
            }
            qitr = questionList.iterator();
            examquestion = qitr.next();
            if ((qitr.hasNext()) == false) {
                ((Button) view.findViewById(R.id.nextbutton)).setText("Finish");
            }
            if (examquestion.getType() == QuestionType.MCQ) {
                ((TextView) view.findViewById(R.id.questiontextview)).setText("Q: " + examquestion.getQuestion());
                ((EditText) view.findViewById(R.id.answeret)).setVisibility(View.GONE);
                ((RadioButton) view.findViewById(R.id.optionA_radio)).setVisibility(View.VISIBLE);
                ((RadioButton) view.findViewById(R.id.optionB_radio)).setVisibility(View.VISIBLE);
                ((RadioButton) view.findViewById(R.id.optionC_radio)).setVisibility(View.VISIBLE);
                ((RadioButton) view.findViewById(R.id.optionD_radio)).setVisibility(View.VISIBLE);
                ((RadioButton) view.findViewById(R.id.optionA_radio)).setText("A. " + ((MCQQuestion) examquestion).getOption1());
                ((RadioButton) view.findViewById(R.id.optionB_radio)).setText("B. " + ((MCQQuestion) examquestion).getOption2());
                ((RadioButton) view.findViewById(R.id.optionC_radio)).setText("C. " + ((MCQQuestion) examquestion).getOption3());
                ((RadioButton) view.findViewById(R.id.optionD_radio)).setText("D. " + ((MCQQuestion) examquestion).getOption4());
            } else if (examquestion.getType() == QuestionType.FILL_BLANK) {
                ((TextView) view.findViewById(R.id.questiontextview)).setText("Q: " + examquestion.getQuestion());
                ((EditText) view.findViewById(R.id.answeret)).setVisibility(View.VISIBLE);
                ((RadioButton) view.findViewById(R.id.optionA_radio)).setVisibility(View.GONE);
                ((RadioButton) view.findViewById(R.id.optionB_radio)).setVisibility(View.GONE);
                ((RadioButton) view.findViewById(R.id.optionC_radio)).setVisibility(View.GONE);
                ((RadioButton) view.findViewById(R.id.optionD_radio)).setVisibility(View.GONE);
            }
        }
            Button button1 = (Button) view.findViewById(R.id.checkbutton);
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // do something
                    if (examquestion.getType() == QuestionType.MCQ) {
                        if (((RadioButton) view.findViewById(R.id.optionA_radio)).isChecked() == true) {
                            given_ans = "a";
                        }
                        if (((RadioButton) view.findViewById(R.id.optionB_radio)).isChecked() == true) {
                            given_ans = "b";
                        }
                        if (((RadioButton) view.findViewById(R.id.optionC_radio)).isChecked() == true) {
                            given_ans = "c";
                        }
                        if (((RadioButton) view.findViewById(R.id.optionD_radio)).isChecked() == true) {
                            given_ans = "d";
                        }
                        if (given_ans.matches(examquestion.getAns().toLowerCase())) {
                            i++;
                            android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(getActivity());
                            alertDialogBuilder.setMessage("Correct Answer!!");
                            alertDialogBuilder.setPositiveButton("Ok",
                                    new DialogInterface.OnClickListener() {

                                        public void onClick(DialogInterface dialog, int which) {
                                            //Toast.makeText(QuizDisplayActivity.this, "GoodLuck!", Toast.LENGTH_LONG).show();
                                            Snackbar.make(getActivity().findViewById(android.R.id.content), "GoodLuck!", Snackbar.LENGTH_LONG).show();
                                        }
                                    });
                            android.app.AlertDialog alertDialog = alertDialogBuilder.create();
                            alertDialog.show();
                        } else {
                            android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(getActivity());
                            alertDialogBuilder.setMessage("Wrong Answer!! Right option is: " + examquestion.getAns().toUpperCase());
                            alertDialogBuilder.setPositiveButton("Ok",
                                    new DialogInterface.OnClickListener() {

                                        public void onClick(DialogInterface dialog, int which) {
                                            //Toast.makeText(QuizDisplayActivity.this, "GoodLuck!", Toast.LENGTH_LONG).show();
                                            Snackbar.make(getActivity().findViewById(android.R.id.content), "GoodLuck!", Snackbar.LENGTH_LONG).show();
                                        }
                                    });
                            android.app.AlertDialog alertDialog = alertDialogBuilder.create();
                            alertDialog.show();
                        }
                    } else if (examquestion.getType() == QuestionType.FILL_BLANK) {
                        given_ans = ((EditText) view.findViewById(R.id.answeret)).getText().toString();
                        if (given_ans.toLowerCase().matches(examquestion.getAns().toLowerCase())) {
                            i++;
                            android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(getActivity());
                            alertDialogBuilder.setMessage("Correct Answer!!");
                            alertDialogBuilder.setPositiveButton("Ok",
                                    new DialogInterface.OnClickListener() {

                                        public void onClick(DialogInterface dialog, int which) {
                                            //Toast.makeText(QuizDisplayActivity.this, "GoodLuck!", Toast.LENGTH_LONG).show();
                                            Snackbar.make(getActivity().findViewById(android.R.id.content), "GoodLuck!", Snackbar.LENGTH_LONG).show();
                                        }
                                    });
                            android.app.AlertDialog alertDialog = alertDialogBuilder.create();
                            alertDialog.show();
                        } else {
                            android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(getActivity());
                            alertDialogBuilder.setMessage("Wrong Answer!! Correct answer is: '" + examquestion.getAns() + "'");
                            alertDialogBuilder.setPositiveButton("Ok",
                                    new DialogInterface.OnClickListener() {

                                        public void onClick(DialogInterface dialog, int which) {
                                            //Toast.makeText(QuizDisplayActivity.this, "GoodLuck!", Toast.LENGTH_LONG).show();
                                            Snackbar.make(getActivity().findViewById(android.R.id.content), "GoodLuck!", Snackbar.LENGTH_LONG).show();
                                        }
                                    });
                            android.app.AlertDialog alertDialog = alertDialogBuilder.create();
                            alertDialog.show();
                        }
                    }
                }
            });
            Button button2 = (Button) view.findViewById(R.id.nextbutton);
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // do something
                    if ((qitr.hasNext()) == false) {
                        String correct_no = String.valueOf(i);
                        ResultFragment result = new ResultFragment();
                        Bundle args = new Bundle();
                        args.putString("cn", correct_no);
                        result.setArguments(args);
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragment_container, result);
                        transaction.addToBackStack(null);
                        transaction.commit();
                    } else {
                        examquestion = qitr.next();
                        if ((qitr.hasNext()) == false) {
                            ((Button) view.findViewById(R.id.nextbutton)).setText("Finish");
                        }
                        if (examquestion.getType() == QuestionType.MCQ) {
                            ((TextView) view.findViewById(R.id.questiontextview)).setText("Q: " + examquestion.getQuestion());
                            ((EditText) view.findViewById(R.id.answeret)).setVisibility(View.GONE);
                            ((RadioButton) view.findViewById(R.id.optionA_radio)).setVisibility(View.VISIBLE);
                            ((RadioButton) view.findViewById(R.id.optionB_radio)).setVisibility(View.VISIBLE);
                            ((RadioButton) view.findViewById(R.id.optionC_radio)).setVisibility(View.VISIBLE);
                            ((RadioButton) view.findViewById(R.id.optionD_radio)).setVisibility(View.VISIBLE);
                            ((RadioButton) view.findViewById(R.id.optionA_radio)).setText("A. " + ((MCQQuestion) examquestion).getOption1());
                            ((RadioButton) view.findViewById(R.id.optionB_radio)).setText("B. " + ((MCQQuestion) examquestion).getOption2());
                            ((RadioButton) view.findViewById(R.id.optionC_radio)).setText("C. " + ((MCQQuestion) examquestion).getOption3());
                            ((RadioButton) view.findViewById(R.id.optionD_radio)).setText("D. " + ((MCQQuestion) examquestion).getOption4());
                        } else if (examquestion.getType() == QuestionType.FILL_BLANK) {
                            ((TextView) view.findViewById(R.id.questiontextview)).setText("Q: " + examquestion.getQuestion());
                            ((RadioButton) view.findViewById(R.id.optionA_radio)).setVisibility(View.GONE);
                            ((RadioButton) view.findViewById(R.id.optionB_radio)).setVisibility(View.GONE);
                            ((RadioButton) view.findViewById(R.id.optionC_radio)).setVisibility(View.GONE);
                            ((RadioButton) view.findViewById(R.id.optionD_radio)).setVisibility(View.GONE);
                            ((EditText) view.findViewById(R.id.answeret)).setVisibility(View.VISIBLE);
                        }
                    }
                }
            });
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        QuizFragment myQuiz = (QuizFragment)getFragmentManager().findFragmentByTag("Quiz_Frag");
        if (myQuiz != null && myQuiz.isVisible()) {
            //Save the fragment's state here
            String questionText = ((TextView) getView().findViewById(R.id.questiontextview)).getText().toString();
            outState.putString("qText", questionText);
            int etVisibility = ((EditText) getView().findViewById(R.id.answeret)).getVisibility();
            outState.putInt("etVis", etVisibility);
            int r1Visibility = ((RadioButton) getView().findViewById(R.id.optionA_radio)).getVisibility();
            int r2Visibility = ((RadioButton) getView().findViewById(R.id.optionB_radio)).getVisibility();
            int r3Visibility = ((RadioButton) getView().findViewById(R.id.optionC_radio)).getVisibility();
            int r4Visibility = ((RadioButton) getView().findViewById(R.id.optionD_radio)).getVisibility();
            outState.putInt("r1Vis", r1Visibility);
            outState.putInt("r2Vis", r2Visibility);
            outState.putInt("r3Vis", r3Visibility);
            outState.putInt("r4Vis", r4Visibility);
            String answerText = ((EditText) getView().findViewById(R.id.answeret)).getText().toString();
            outState.putString("ansText", answerText);
            boolean checked1 = ((RadioButton) getView().findViewById(R.id.optionA_radio)).isChecked();
            boolean checked2 = ((RadioButton) getView().findViewById(R.id.optionB_radio)).isChecked();
            boolean checked3 = ((RadioButton) getView().findViewById(R.id.optionC_radio)).isChecked();
            boolean checked4 = ((RadioButton) getView().findViewById(R.id.optionD_radio)).isChecked();
            outState.putBoolean("chk1", checked1);
            outState.putBoolean("chk2", checked2);
            outState.putBoolean("chk3", checked3);
            outState.putBoolean("chk4", checked4);
            outState.putInt("corr_ans", i);
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
