package com.lnyapps.geneticsandevolution.problemsolver;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lnyapps.geneticsandevolution.R;
import com.lnyapps.geneticsandevolution.problems.BreederProblem;

/**
 * Created by Alex on 11/1/2014.
 */
public class BreederFragment extends ProblemSolverInputFragment {

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
        return rootView;
    }

    @Override
    public boolean canSolve() {
        return false;
    }

    @Override
    public void clearInputs() {

    }
}
