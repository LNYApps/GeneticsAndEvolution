package com.lnyapps.geneticsandevolution.problems;

/**
 * Created by Jonathan Tseng on 11/1/2014.
 */
public abstract class GenEvolProblem {

    public abstract String solution();

    public abstract void randomValues();

    /**
     * no error checking done--expects perfect input
     * if too few arguments, may add random arguments
     * if too many arguments, extra ones ignored
     * @param args
     */
    public abstract void setArguments(double[] args);

    public abstract String emptyGivenString();

    public abstract String nonEmptyGivenString();

    public abstract String emptySolveString();

}
