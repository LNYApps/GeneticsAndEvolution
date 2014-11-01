package com.lnyapps.geneticsandevolution.problems;

/**
 * Created by Jonathan Tseng on 11/1/2014.
 */
public class PopGrowthProblem extends GenEvolProblem {

    private String[] mParams = new String[] {
    };
    private double[] mVals;
    private int mUnknown;
    private double mSolution;

    @Override
    public String solution() {
        return "TODO";
    }

    @Override
    public void randomValues() {

    }

    @Override
    public String emptyGivenString() {
        return "TODO";
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
