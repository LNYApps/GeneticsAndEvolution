package com.lnyapps.geneticsandevolution.problems;

import android.text.TextUtils;

/**
 * Created by Jonathan Tseng on 11/1/2014.
 */
public class BreederProblem extends GenEvolProblem {

    private String[] mParams = new String[] {
            "Avg starting phenotype: ",
            "Avg selected phenotype: ",
            "Avg response phenotype: ",
            "Broad sense heritability: "
    };
    private double[] mVals;
    private int mUnknown;
    private double mSolution;

    @Override
    public String solution() {
        if (mUnknown == 0) {
            mSolution = calcStartingPhenotype(mVals[1], mVals[2], mVals[3]);
        } else if (mUnknown == 1) {
            mSolution = calcSelectedPhenotype(mVals[0], mVals[2], mVals[3]);
        } else if (mUnknown == 2) {
            mSolution = calcResponsePhenotype(mVals[0], mVals[1], mVals[3]);
        } else if (mUnknown == 3) {
            mSolution = calcHeritability(mVals[0], mVals[1], mVals[2]);
        }
        return mParams[mUnknown] + String.format("%.3f", mSolution);
    }

    @Override
    public void randomValues() {
        mVals = new double[4];
        mUnknown = (int) (Math.random() * 4);
        for (int i = 0; i < 3; i++) {
            if (i != mUnknown) {
                if(i==0) {
                   mVals[i] = 20 + (int) (Math.random() * 80);
                }
                else if(i==1) {
                    if(mUnknown ==0 ){
                        mVals[i] = mVals[i-1] + 30 + (int) (Math.random()*20);
                    }
                    else if(mUnknown==3){
                        mVals[i] = mVals[i-1] - 10 - (int) (Math.random()*20);
                    }
                    else {
                        mVals[i] = mVals[i-1] - 10 - (int) (Math.random()*10);
                    }
                }
                else if(i==2) {
                    if(mUnknown ==0){
                        mVals[i] = mVals[i-1] + 10 + (int) (Math.random()*20);
                    }
                    else if(mUnknown ==1){
                        mVals[i] = mVals[i-2] + 10 + (int) (Math.random()*20);
                    }
                    else if(mUnknown ==2){
                        mVals[i] = mVals[i-1] - 10 - (int) (Math.random()*20);
                    }
                    else{
                        mVals[i] = mVals[i-1] + (int) (Math.random()*10);
                    }
                }
            }
        }
        if (mUnknown != 3) {
            mVals[3] = ((int) (Math.random()*0.8*1000))/1000.0 + 0.2;
        }
    }

    @Override
    public void setArguments(double[] args) {
        mVals = new double[4];
        for (int i = 0; i < args.length; i ++) {
            if (Double.isNaN(args[i])) {
                mUnknown = i;
            } else {
                mVals[i] = args[i];
            }
        }
    }

    @Override
    public String emptyGivenString() {
        return TextUtils.join("\n", mParams);
    }

    @Override
    public String nonEmptyGivenString() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 4; i ++) {
            sb.append(mParams[i]);
            if (i != mUnknown) {
                if (i == 3) {
                    sb.append(String.format("%.3f", mVals[i]));
                } else {
                    sb.append(String.format("%.0f", mVals[i]));
                }
            }
            if (i != 3) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    @Override
    public String emptySolveString() {
        return "Unknown value";
    }

    private double calcHeritability(double startingPhenotype, double selectedPhenotype, double responsePhenotype){
        return (startingPhenotype - responsePhenotype)/(startingPhenotype - selectedPhenotype);
    }

    private double calcStartingPhenotype(double selectedPhenotype, double responsePhenotype, double heritability){
        return (responsePhenotype - selectedPhenotype*heritability)/(1-heritability);
    }

    private double calcSelectedPhenotype(double startingPhenotype, double responsePhenotype, double heritability){
        return (responsePhenotype-startingPhenotype)/heritability + startingPhenotype;
    }

    private double calcResponsePhenotype(double startingPhenotype, double selectedPhenotype, double heritability){
        return startingPhenotype - (startingPhenotype-selectedPhenotype)*heritability;
    }

}
