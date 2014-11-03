package com.lnyapps.geneticsandevolution.problemsolver;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.lnyapps.geneticsandevolution.R;
import com.lnyapps.geneticsandevolution.problems.PopGrowthProblem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 11/1/2014.
 */
public class PopGrowthFragment extends ProblemSolverInputFragment {

    private List<EditText> mInputs;
    private EditText mGrowthRate;
    private EditText mBirthRate;
    private EditText mDeathRate;
    private EditText mInitPop;
    private EditText mFinalPop;
    private EditText mNumYears;

    public PopGrowthFragment() {
        super(new PopGrowthProblem());
        Bundle args = new Bundle();
        //args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        setArguments(args);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.ps_fragment_pop_growth, container, false);
        mInputs = new ArrayList<EditText>();
        mGrowthRate = (EditText) rootView.findViewById(R.id.popgrowth_edittext_growthrate);
        mBirthRate = (EditText) rootView.findViewById(R.id.popgrowth_edittext_birthsper1000);
        mDeathRate = (EditText) rootView.findViewById(R.id.popgrowth_edittext_deathsper1000);
        mInitPop = (EditText) rootView.findViewById(R.id.popgrowth_edittext_initialpopulation);
        mFinalPop = (EditText) rootView.findViewById(R.id.popgrowth_edittext_finalpopulation);
        mNumYears = (EditText) rootView.findViewById(R.id.popgrowth_edittext_numberyears);
        mInputs.add(mGrowthRate);
        mInputs.add(mBirthRate);
        mInputs.add(mDeathRate);
        mInputs.add(mInitPop);
        mInputs.add(mFinalPop);
        mInputs.add(mNumYears);

        setUpRatesEditTextListeners();
        return rootView;
    }

    @Override
    public boolean canSolve() {
        return false;
    }

    @Override
    public void loadArguments() {

    }

    @Override
    public void clearInputs() {
        for (EditText input : mInputs) {
            input.setText("");
        }
    }

    @Override
    public void clearInputFocus() {
        for (EditText input : mInputs) {
            input.clearFocus();
        }
    }

    private void setUpRatesEditTextListeners() {
        mGrowthRate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {}

            @Override
            public void afterTextChanged(Editable editable) {
                boolean enabled = editable.toString().isEmpty();
                mBirthRate.setEnabled(enabled);
                mDeathRate.setEnabled(enabled);
            }
        });

        mBirthRate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {}

            @Override
            public void afterTextChanged(Editable editable) {
                mGrowthRate.setEnabled(editable.toString().isEmpty());
            }
        });

        mDeathRate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {}

            @Override
            public void afterTextChanged(Editable editable) {
                mGrowthRate.setEnabled(editable.toString().isEmpty());
            }
        });
    }

}
