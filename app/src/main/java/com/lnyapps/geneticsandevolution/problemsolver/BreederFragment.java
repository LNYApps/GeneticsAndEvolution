package com.lnyapps.geneticsandevolution.problemsolver;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.lnyapps.geneticsandevolution.R;
import com.lnyapps.geneticsandevolution.problems.BreederProblem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 11/1/2014.
 */
public class BreederFragment extends ProblemSolverInputFragment {

    private List<EditText> mInputs;
    private EditText mAvgStartPhen;
    private EditText mAvgSelectPhen;
    private EditText mAvgRespPhen;
    private EditText mBroadHeritability;

    public BreederFragment() {
        super(new BreederProblem());
        Bundle args = new Bundle();
        //args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        setArguments(args);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.ps_fragment_breeder, container, false);
        mAvgStartPhen = (EditText) rootView.findViewById(R.id.edittext_avgstartpheno);
        mAvgSelectPhen = (EditText) rootView.findViewById(R.id.edittext_avgselectpheno);
        mAvgRespPhen = (EditText) rootView.findViewById(R.id.edittext_avgresponsepheno);
        mBroadHeritability = (EditText) rootView.findViewById(R.id.edittext_broadheritability);


        mBroadHeritability.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b && !mBroadHeritability.getText().toString().isEmpty()) {
                    double val = Double.parseDouble(mBroadHeritability.getText().toString());
                    if (val >= 1) {
                        val = .999;
                    } else if (val <= 0) {
                        val = .001;
                    }
                    mBroadHeritability.setText(Double.toString(val));
                }
            }
        });

        mInputs = new ArrayList<EditText>();
        mInputs.add(mAvgStartPhen);
        mInputs.add(mAvgSelectPhen);
        mInputs.add(mAvgRespPhen);
        mInputs.add(mBroadHeritability);
        for (EditText input : mInputs) {
            input.addTextChangedListener(new BreederTextWatcher());
        }
        return rootView;
    }

    @Override
    public boolean canSolve() {
        int params = 0;
        for (EditText input : mInputs) {
            if (!input.getText().toString().isEmpty()) {
                params++;
            }
        }
        return (params == 3);
    }

    @Override
    public void loadArguments() {
        double[] params = new double[4];
        int ind = 0;
        for (EditText input : mInputs) {
            if (!input.getText().toString().isEmpty()) {
                params[ind] = Double.parseDouble(input.getText().toString());
            } else {
                params[ind] = Double.NaN;
            }
            ind++;
        }
        mProblem.setArguments(params);
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

    private class BreederTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {}

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {}
        @Override
        public void afterTextChanged(Editable editable) {
            int numEmpty = 0;
            EditText empty = new EditText(getActivity());
            for (EditText input : mInputs) {
                if (input.getText().toString().isEmpty()) {
                    numEmpty++;
                    empty = input;
                }
            }
            if (numEmpty == 1) {
                empty.setEnabled(false);
            } else {
                for (EditText input : mInputs) {
                    input.setEnabled(true);
                }
            }
        }
    }

}
