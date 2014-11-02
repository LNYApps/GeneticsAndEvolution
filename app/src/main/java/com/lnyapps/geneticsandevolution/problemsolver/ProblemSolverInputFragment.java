package com.lnyapps.geneticsandevolution.problemsolver;

import android.support.v4.app.Fragment;

import com.lnyapps.geneticsandevolution.problems.GenEvolProblem;

/**
 * Created by Jonathan Tseng on 11/2/2014.
 */
public abstract class ProblemSolverInputFragment extends Fragment {

    protected GenEvolProblem mProblem;

    protected  ProblemSolverInputFragment(GenEvolProblem problem) {
        mProblem = problem;
    }

    public void destroyFragment() {
        getChildFragmentManager().beginTransaction().hide(this).commit();
    }

    public void createFragment() {
        getChildFragmentManager().beginTransaction().show(this).commit();
    }

    public String solve() {
        return mProblem.solution();
    }

    public abstract boolean canSolve();

    public abstract void clearInputs();

}
