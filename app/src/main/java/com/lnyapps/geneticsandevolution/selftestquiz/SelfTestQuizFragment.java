package com.lnyapps.geneticsandevolution.selftestquiz;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lnyapps.geneticsandevolution.MainActivity;
import com.lnyapps.geneticsandevolution.R;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

/**
 * Created by Jonathan Tseng on 10/31/2014.
 */
public class SelfTestQuizFragment extends Fragment {

    private VocabList mVocabList;
    private VocabQuestion mCurrentVocabQuestion;

    public SelfTestQuizFragment() {
        setHasOptionsMenu(true);
        Bundle args = new Bundle();
        //args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        setArguments(args);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_selftestquiz, container, false);
        setUpSubmitButton(rootView);
        setQuizTerms();
        setQuizQuestion(rootView);
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_selftestquiz, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.stq_action_refresh) {
            mVocabList.shuffle();
            setQuizQuestion(getView());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setQuizQuestion(View root) {
        if (mVocabList != null) {
            mCurrentVocabQuestion = mVocabList.getNextQuestion();
            TextView question = (TextView) root.findViewById(R.id.stq_textview_question);
            question.setText(mCurrentVocabQuestion.getQuestion());
            RadioGroup choices = (RadioGroup) root.findViewById(R.id.stq_radiogroup_multiplechoice);
            choices.clearCheck();
            RadioButton choice0 = (RadioButton) root.findViewById(R.id.stq_radio_choice1);
            choice0.setText(mCurrentVocabQuestion.getChoices()[0]);
            RadioButton choice1 = (RadioButton) root.findViewById(R.id.stq_radio_choice2);
            choice1.setText(mCurrentVocabQuestion.getChoices()[1]);
            RadioButton choice2 = (RadioButton) root.findViewById(R.id.stq_radio_choice3);
            choice2.setText(mCurrentVocabQuestion.getChoices()[2]);
            RadioButton choice3 = (RadioButton) root.findViewById(R.id.stq_radio_choice4);
            choice3.setText(mCurrentVocabQuestion.getChoices()[3]);
        } else {
            Toast.makeText(getActivity(), "No quiz questions found.", Toast.LENGTH_SHORT).show();
        }
    }

    private void setQuizTerms() {
        try {
            File file = new File(getActivity().getFilesDir() + "/" + "GenEvolTerms.json");
            FileInputStream inputStream = new FileInputStream(file);
            VocabJsonParser parser = new VocabJsonParser();
            mVocabList = new VocabList(new ArrayList<VocabTerm>(parser.parse(inputStream)));
        } catch (Exception e) {
            Log.e("VocabTermParsing", "failed to set vocab terms", e);
        }
    }

    private void setUpSubmitButton(View root) {
        Button submitButton = (Button) root.findViewById(R.id.stq_button_submit);
        final RadioGroup answerGroup = (RadioGroup) root.findViewById(R.id.stq_radiogroup_multiplechoice);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int choice = answerGroup.getCheckedRadioButtonId();
                RadioButton answer = (RadioButton) answerGroup.findViewById(choice);
                if (choice != -1) {
                    answerSubmitted((String)answer.getText());
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "No answer choice selected", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private  void answerSubmitted(String answer) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        if (answer.equals(mCurrentVocabQuestion.getAnswer())) {
            alertDialogBuilder
                    .setTitle("Correct!")
                    .setCancelable(false)
                    .setPositiveButton("Next Question", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            setQuizQuestion(getView());
                        }
                    });
        } else {
            alertDialogBuilder
                    .setTitle("Incorrect.")
                    .setCancelable(false)
                    .setPositiveButton("Next Question", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            setQuizQuestion(getView());
                        }
                    })
                    .setNegativeButton("Try Again", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //do nothing, close dialog box
                        }
                    });
        }
        AlertDialog dialog = alertDialogBuilder.create();
        dialog.show();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(getResources().getString(R.string.self_test_quiz));
    }

}
