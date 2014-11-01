package com.lnyapps.geneticsandevolution.problems;

import android.text.Html;
import android.text.TextUtils;

/**
 * Created by Jonathan Tseng on 11/1/2014.
 */
public class PopGrowthProblem extends GenEvolProblem {

    private String[] mParams = new String[] {
            "Net Growth Rate (r): ",
            "Annual # Births per 1000 (b): ",
            "Annual # Deaths per 1000 (d): ",
            Html.fromHtml("Initial Population (N<sub>0</sub>): ").toString(),
            Html.fromHtml("Final Population (N<sub>t</sub>): ").toString(),
            "Time in years (T): "
    };
    private double[] mVals;
    private int mUnknown1;
    private int mUnknown2;
    private int mUnknown3;
    private double mSolution;

    @Override
    public String solution() {
        return "TODO";
    }

    @Override
    public void randomValues() {
        mVals = new double[6];
        mUnknown1 = (int) (Math.random() * 3);
        if (mUnknown1 == 0) {
            mUnknown2 = 1;
            mUnknown3 = 2;
        } else {
            mUnknown2 = (int) (Math.random() * 3) + 3;
            mUnknown3 = -1;
        }

        for (int i = 0; i < mParams.length; i++) {
            mVals[i] = Math.random();
        }
        mVals[0] = mVals[0];
        mVals[1] = mVals[1] * 180;
        mVals[2] = mVals[2] * 180;
        mVals[3] = ((int)(mVals[3] * 100)) * 1000000;
        mVals[4] = ((int)(mVals[4] * 100)) * 1000000;
        mVals[5] = mVals[5] * 200;
        if (invalidQuestion()) {
            randomValues();
        }
    }

    private boolean invalidQuestion() {
        return false;
    }

    @Override
    public String emptyGivenString() {
        return TextUtils.join("\n", mParams);
    }

    @Override
    public String nonEmptyGivenString() {
        return "TODO";
    }

    @Override
    public String emptySolveString() {
        return "Unknown values";
    }

    private double calcPopGrowthRate(double initialPopSize, double finalPopSize, double time){
        double output = Math.log(finalPopSize/initialPopSize)/time;
        return output;
    }

    private double calcTime(double growthRate, double initialPopSize, double finalPopSize){
        double output = Math.log(finalPopSize/initialPopSize)/growthRate;
        return output;
    }

    private double calcFinalPop(double growthRate, double initialPopSize, double time){
        double output = initialPopSize*Math.exp(growthRate*time);
        return output;
    }

    private double calcInitialPop(double growthRate, double finalPopSize, double time){
        double output = finalPopSize/Math.exp(growthRate*time);
        return output;
    }

}
