package com.lnyapps.geneticsandevolution.problemsolver;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.lnyapps.geneticsandevolution.R;
import com.lnyapps.geneticsandevolution.problems.CrossMappingProblem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 11/1/2014.
 */
public class GeneticCrossMappingFragment extends ProblemSolverInputFragment {

    private List<EditText> mInputs;
    private EditText mABC;
    private EditText mABc;
    private EditText mAbC;
    private EditText mAbc;
    private EditText maBC;
    private EditText maBc;
    private EditText mabC;
    private EditText mabc;

    public GeneticCrossMappingFragment() {
        super(new CrossMappingProblem());
        Bundle args = new Bundle();
        //args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        setArguments(args);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.ps_fragment_genetic_cross_mapping, container, false);

        mInputs = new ArrayList<EditText>();
        mABC = (EditText) rootView.findViewById(R.id.ABC_count);
        mABc = (EditText) rootView.findViewById(R.id.ABc_count);
        mAbC = (EditText) rootView.findViewById(R.id.AbC_count);
        mAbc = (EditText) rootView.findViewById(R.id.Abc_count);
        maBC = (EditText) rootView.findViewById(R.id.aBC_count);
        maBc = (EditText) rootView.findViewById(R.id.aBc_count);
        mabC = (EditText) rootView.findViewById(R.id.abC_count);
        mabc = (EditText) rootView.findViewById(R.id.abc_count);
        mInputs.add(mABC);
        mInputs.add(mABc);
        mInputs.add(mAbC);
        mInputs.add(mAbc);
        mInputs.add(maBC);
        mInputs.add(maBc);
        mInputs.add(mabC);
        mInputs.add(mabc);

        return rootView;
    }

    @Override
    public boolean canSolve() {
        boolean canSolve = true;
        for (EditText input : mInputs) {
            canSolve = canSolve & (!input.getText().toString().isEmpty());
        }
        return canSolve;
    }

    @Override
    public void loadArguments() {
        double[] args = new double[8];
        for (int i = 0; i < 8; i ++) {
            args[i] = Integer.parseInt(mInputs.get(i).getText().toString());
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
}
