package com.lnyapps.geneticsandevolution.problemgenerator;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lnyapps.geneticsandevolution.MainActivity;
import com.lnyapps.geneticsandevolution.R;
import com.lnyapps.geneticsandevolution.problems.BreederProblem;
import com.lnyapps.geneticsandevolution.problems.CrossMappingProblem;
import com.lnyapps.geneticsandevolution.problems.GenEvolProblem;
import com.lnyapps.geneticsandevolution.problems.HardyWeinbergProblem;
import com.lnyapps.geneticsandevolution.problems.PopGrowthProblem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Jonathan Tseng on 10/31/2014.
 */
public class MultipleProblemGeneratorFragment extends Fragment {

    private final static String[] mReservedChars = {"|", "\\", "?", "*", "<", "\"", ":", ">"};
    private final static int mMaxProblemsPerType = 25;

    private EditText mFileEditText;
    private EditText mPopGrowthEditText;
    private EditText mHardyWeinbergEditText;
    private EditText mCrossMappingEditText;
    private EditText mBreedersEditText;
    private Button mGenerateButton;

    public MultipleProblemGeneratorFragment() {
        Bundle args = new Bundle();
        //args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        setArguments(args);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_multipleproblemgenerator, container, false);
        mFileEditText = (EditText) rootView.findViewById(R.id.mpg_edittext_filename);
        mPopGrowthEditText = (EditText) rootView.findViewById(R.id.mpg_edittext_numpopgrowth);
        mBreedersEditText = (EditText) rootView.findViewById(R.id.mpg_edittext_numbreeders);
        mHardyWeinbergEditText = (EditText) rootView.findViewById(R.id.mpg_edittext_numhardyweinberg);
        mCrossMappingEditText = (EditText) rootView.findViewById(R.id.mpg_edittext_numcrossmapping);
        mGenerateButton = (Button) rootView.findViewById(R.id.mpg_button_generate);
        setUpFileEditText();
        setUpNumberEditTexts();
        setUpGenerateButton();
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(getResources().getString(R.string.problem_generator));
    }

    private void setUpFileEditText() {
        mFileEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b && !mFileEditText.getText().toString().isEmpty()) {
                    if (!validFileName(mFileEditText.getText().toString())) {
                        mFileEditText.setText(null);
                        Toast.makeText(getActivity(), "Invalid file name", Toast.LENGTH_SHORT).show();
                    } else {
                        String extension =
                                (mFileEditText.getText().toString().endsWith(".txt")) ? "" : ".txt";
                        mFileEditText.setText(mFileEditText.getText().toString().replaceAll("\\s+", "") + extension);
                    }
                }
            }
        });
    }

    private void setUpNumberEditTexts() {
        View.OnFocusChangeListener listener = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                EditText editText = (EditText) view;
                if (!b && !editText.getText().toString().isEmpty() && Integer.parseInt(editText.getText().toString()) > mMaxProblemsPerType) {
                    editText.setText(Integer.toString(mMaxProblemsPerType));
                }
            }
        };
        mPopGrowthEditText.setOnFocusChangeListener(listener);
        mCrossMappingEditText.setOnFocusChangeListener(listener);
        mHardyWeinbergEditText.setOnFocusChangeListener(listener);
        mBreedersEditText.setOnFocusChangeListener(listener);
    }

    private void setUpGenerateButton() {
        mGenerateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopGrowthEditText.clearFocus();
                mCrossMappingEditText.clearFocus();
                mBreedersEditText.clearFocus();
                mHardyWeinbergEditText.clearFocus();
                mFileEditText.clearFocus();
                if (mFileEditText.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Please enter a file name.", Toast.LENGTH_SHORT).show();
                } else if (!questionsSelected()) {
                    Toast.makeText(getActivity(), "Please select questions to generate.", Toast.LENGTH_SHORT).show();
                } else {
                    generateQuestions();
                }
            }
        });
    }

    private void generateQuestions() {
        if (isExternalStorageWritable()) {
            String text = createDocumentText();
            try {

                File file = new File(
                        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                        mFileEditText.getText().toString());
                FileWriter writer = new FileWriter(file);
                writer.append(text);
                writer.flush();
                writer.close();
                Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
                startActivity(Intent.createChooser(intent, getResources().getText(R.string.send_to)));
            } catch (IOException e) {
                Log.e("Generating Multiple Problems", "Failed to write to file", e);
            }
        }
    }

    private String createDocumentText() {
        StringBuilder questions = new StringBuilder();
        StringBuilder answers = new StringBuilder();
        questions.append("Practice Problems\n\n");
        answers.append("\n\nAnswers\n\n");
        int count = 1;
        count = createQuestions(count, questions, answers, mPopGrowthEditText);
        count = createQuestions(count, questions, answers, mBreedersEditText);
        count = createQuestions(count, questions, answers, mCrossMappingEditText);
        createQuestions(count, questions, answers, mHardyWeinbergEditText);
        questions.append(answers);
        return questions.toString();
    }

    private int createQuestions(int count, StringBuilder questions, StringBuilder answers, EditText editText) {
        GenEvolProblem problem;
        if (editText.equals(mPopGrowthEditText)) {
            problem = new PopGrowthProblem();
        } else if (editText.equals(mBreedersEditText)) {
            problem = new BreederProblem();
        } else if (editText.equals(mCrossMappingEditText)) {
            problem = new CrossMappingProblem();
        } else {
            problem = new HardyWeinbergProblem();
        }

        for (int i = 0; i < getNumQuestions(editText); i++) {
            problem.randomValues();
            questions.append(count + ".\n");
            questions.append(problem.nonEmptyGivenString());
            questions.append("\n");
            questions.append(problem.emptySolveString());
            questions.append("\n");

            answers.append(count + ".\n");
            answers.append(problem.solution());
            answers.append("\n");
            count++;
        }
        return count;
    }

    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    private int getNumQuestions(EditText editText) {
        if (editText.getInputType() != InputType.TYPE_CLASS_NUMBER || editText.getText().toString().isEmpty()) {
            return 0;
        }
        return Integer.parseInt(editText.getText().toString());
    }

    private boolean questionsSelected() {
        int total = 0;
        total += getNumQuestions(mPopGrowthEditText);
        total += getNumQuestions(mCrossMappingEditText);
        total += getNumQuestions(mHardyWeinbergEditText);
        total += getNumQuestions(mBreedersEditText);
        return (total > 0);
    }

    private boolean validFileName(String fileName) {
        boolean valid = true;
        for (int i = 0; i < mReservedChars.length; i++) {
            if (fileName.contains(mReservedChars[i])) {
                valid = false;
            }
        }
        return valid;
    }

}
