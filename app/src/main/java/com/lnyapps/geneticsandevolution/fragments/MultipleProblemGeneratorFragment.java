package com.lnyapps.geneticsandevolution.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lnyapps.geneticsandevolution.MainActivity;
import com.lnyapps.geneticsandevolution.R;

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
                if (!b && mFileEditText.getText() != null) {
                    if (!validFileName(mFileEditText.getText().toString())) {
                        mFileEditText.setText(null);
                        Toast.makeText(getActivity(), "Invalid file name", Toast.LENGTH_SHORT).show();
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
        Toast.makeText(getActivity(), "Generating questions", Toast.LENGTH_SHORT).show();

    }

    private boolean questionsSelected() {
        int total = 0;
        if (!mPopGrowthEditText.getText().toString().isEmpty()) {
            total += Integer.parseInt(mPopGrowthEditText.getText().toString());
        }
        if (!mCrossMappingEditText.getText().toString().isEmpty()) {
            total += Integer.parseInt(mCrossMappingEditText.getText().toString());
        }
        if (!mHardyWeinbergEditText.getText().toString().isEmpty()) {
            total += Integer.parseInt(mHardyWeinbergEditText.getText().toString());
        }
        if (!mBreedersEditText.getText().toString().isEmpty()) {
            total += Integer.parseInt(mBreedersEditText.getText().toString());
        }
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
