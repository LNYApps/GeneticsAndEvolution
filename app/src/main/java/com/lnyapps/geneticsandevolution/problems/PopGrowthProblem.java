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


    //initialPopSize, double finalPopSize, double time
    @Override
    public String solution() {
        if (mUnknown3 == 2) {
            return mParams[0] + String.format("%.3f", calcPopGrowthRate(mVals[3], mVals[4], mVals[5]));
        } else {
            StringBuilder sb = new StringBuilder();
            double rateAnswer = (mUnknown1 == 1) ?
                    1000 * mVals[0] + mVals[2] : mVals[1] - 1000 *mVals[0];
            sb.append(mParams[mUnknown1] + String.format("%.3f", rateAnswer) + "\n");
            double otherAnswer;
            if (mUnknown2 == 3) {
                otherAnswer = calcInitialPop(mVals[0], mVals[4], mVals[5]);
            } else if (mUnknown2 == 4) {
                otherAnswer = calcFinalPop(mVals[0], mVals[3], mVals[5]);
            } else {
                otherAnswer = calcTime(mVals[0], mVals[3], mVals[4]);
            }
            sb.append(mParams[mUnknown2] + String.format("%.3f", otherAnswer));
            return sb.toString();
        }
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
        mVals[0] = mVals[0]*0.2; //rate
        mVals[2] = mVals[2] * 300; //death rate
        mVals[1] = mVals[2] + mVals[1] * 300; //birth rate
        mVals[3] = ((int)(mVals[3] * 50) + 1) * 1000000; //initial pop
        mVals[4] = mVals[3] + ((int)(mVals[4] * 50)) * 1000000; //final pop
        mVals[5] = (int) (mVals[5] * 150); //time
    }

    @Override
    public void setArguments(double[] args) {
        randomValues();
        for (int i = 0; i < args.length; i ++) {
            mVals[i] = args[i];
        }
        if (Double.isNaN(args[0])) {
            mUnknown1 = 0;
            mUnknown2 = 1;
            mUnknown3 = 2;
        } else {
            for (int i = 1; i < 3; i ++) {
                if (Double.isNaN(args[i])) {
                    mUnknown1 = i;
                }
            }
            for (int i = 3; i < 6; i ++) {
                if (Double.isNaN(args[i])) {
                    mUnknown2 = i;
                }
            }
            mUnknown3 = -1;
        }

    }

    @Override
    public String emptyGivenString() {
        return TextUtils.join("\n", mParams);
    }

    @Override
    public String nonEmptyGivenString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mParams.length; i ++) {
            sb.append(mParams[i]);
            if (mUnknown3 == 2) {
                if (i == 1 || i == 2) {
                    sb.append("n/a");
                } else if (i != 0) {
                    sb.append(String.format("%.3f", mVals[i]));
                }
            } else {
                if (i != mUnknown1 && i != mUnknown2) {
                    sb.append(String.format("%.3f", mVals[i]));
                }
            }
            sb.append("\n");
        }
        return sb.toString();
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
