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
        setUpOtherEditTextListeners();
        return rootView;
    }

    @Override
    public boolean canSolve() {
        for (EditText input : mInputs) {
            if (input.isEnabled() && input.getText().toString().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void loadArguments() {
        double[] args = new double[6];
        args[0] = (mGrowthRate.getText().toString().isEmpty()) ? Double.NaN : Double.parseDouble(mGrowthRate.getText().toString());
        for (int i = 1; i < 6; i ++) {
            if (mInputs.get(i).getText().toString().isEmpty()) {
                args[i] = Double.NaN;
            } else {
                args[i] = Integer.parseInt(mInputs.get(i).getText().toString());
            }
        }
        mProblem.setArguments(args);
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

    private void setUpOtherEditTextListeners() {
        for (EditText input : mInputs.subList(3, 6)) {
            input.addTextChangedListener(new PopGrowthTextWatcher());
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
                boolean empty = editable.toString().isEmpty();
                if (!empty && !mBirthRate.getText().toString().isEmpty()) {
                    mDeathRate.setEnabled(false);
                } else if (!empty && !mDeathRate.getText().toString().isEmpty()) {
                    mBirthRate.setEnabled(false);
                } else {
                    mBirthRate.setEnabled(true);
                    mDeathRate.setEnabled(true);
                }
            }
        });

        mGrowthRate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b && !mGrowthRate.getText().toString().isEmpty()) {
                    double val = Double.parseDouble(mGrowthRate.getText().toString());
                    if (val >= 1) {
                        val = .999;
                    } else if (val <= 0) {
                        val = .001;
                    }
                    mGrowthRate.setText(Double.toString(val));
                }
            }
        });

        mBirthRate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {}

            @Override
            public void afterTextChanged(Editable editable) {
                mDeathRate.setEnabled(editable.toString().isEmpty());
            }
        });

        mDeathRate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {}

            @Override
            public void afterTextChanged(Editable editable) {
                mBirthRate.setEnabled(editable.toString().isEmpty());
            }
        });
    }

    private class PopGrowthTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {}

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {}
        @Override
        public void afterTextChanged(Editable editable) {
            int numEmpty = 0;
            EditText empty = new EditText(getActivity());

            if (mGrowthRate.getText().toString().isEmpty()) {
                numEmpty++;
                empty = mGrowthRate;
            }
            for (EditText input : mInputs.subList(3, 6)) {
                if (input.getText().toString().isEmpty()) {
                    numEmpty++;
                    empty = input;
                }
            }
            if (numEmpty == 1) {
                empty.setEnabled(false);
                if (empty == mGrowthRate) {
                    mDeathRate.setEnabled(false);
                    mBirthRate.setEnabled(false);
                }
            } else {
                for (EditText input : mInputs.subList(3, 6)) {
                    input.setEnabled(true);
                }
                mGrowthRate.setEnabled(true);
            }
        }
    }

}
