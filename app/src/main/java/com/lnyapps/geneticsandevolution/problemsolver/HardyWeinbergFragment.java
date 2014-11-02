package com.lnyapps.geneticsandevolution.problemsolver;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.lnyapps.geneticsandevolution.R;
import com.lnyapps.geneticsandevolution.problems.HardyWeinbergProblem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 10/31/2014.
 */
public class HardyWeinbergFragment extends ProblemSolverInputFragment {

    private List<EditText> mInputs;
    private EditText mAA;
    private EditText mAa;
    private EditText maa;

    public HardyWeinbergFragment() {
        super(new HardyWeinbergProblem());
        Bundle args = new Bundle();
        //args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        setArguments(args);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.ps_fragment_hardy_weinberg, container, false);
        mAA = (EditText) rootView.findViewById(R.id.AA_count);
        mAa = (EditText) rootView.findViewById(R.id.Aa_count);
        maa = (EditText) rootView.findViewById(R.id.aa_count);
        mInputs = new ArrayList<EditText>();
        mInputs.add(mAA);
        mInputs.add(mAa);
        mInputs.add(maa);
        return rootView;
    }

    @Override
    public boolean canSolve() {
        boolean canSolve = true;
        for (EditText input : mInputs) {
            canSolve = canSolve & !input.getText().toString().isEmpty();
        }
        return canSolve;
    }

    @Override
    public void loadArguments() {
        double[] args = new double[4];
        args[0] = Integer.parseInt(mAA.getText().toString());
        args[1] = Integer.parseInt(mAa.getText().toString());
        args[2] = Integer.parseInt(maa.getText().toString());
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
}
