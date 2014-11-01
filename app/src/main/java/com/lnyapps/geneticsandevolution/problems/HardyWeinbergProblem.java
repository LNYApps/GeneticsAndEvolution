package com.lnyapps.geneticsandevolution.problems;

/**
 * Created by Jonathan Tseng on 11/1/2014.
 */
public class HardyWeinbergProblem extends GenEvolProblem {

    private int mAAcount;
    private int mAacount;
    private int maacount;

    @Override
    public String emptySolveString() {
        return "1) observed genotype frequencies, " +
                "\n2) genotype frequencies expected, " +
                "\n3) the resulting value of F";
    }

    @Override
    public String solution() {
        double[] solution = solve();
        return "Observed AA: " + solution[0] +
                "\nObserved Aa: " + solution[1] +
                "\nObserved aa: " + solution[2] +
                "\nExpected AA: " + solution[3] +
                "\nExpected Aa: " + solution[4] +
                "\nExpected aa: " + solution[5] +
                "\nF: " + solution[6];
    }

    public double[] solve() {
        double totalCount = mAAcount + mAacount + maacount;
        double observedAA = mAAcount/totalCount;
        double observedAa = mAacount/totalCount;
        double observedaa = maacount/totalCount;
        double freqA = observedAA + (0.5*observedAa);
        double freqa = 1 - freqA;
        double expectedAA = freqA*freqA;
        double expectedAa = 2*freqA*freqa;
        double expectedaa = freqa*freqa;
        double f = (expectedAa - observedAa)/expectedAa;
        double[] output = new double[7];
        output[0] = observedAA;
        output[1] = observedAa;
        output[2] = observedaa;
        output[3] = expectedAA;
        output[4] = expectedAa;
        output[5] = expectedaa;
        output[6] = f;
        return output;
    }


    @Override
    public void randomValues() {
        mAAcount = (int) (Math.random() * 3000);
        mAacount = (int) (Math.random() * 3000);
        maacount = (int) (Math.random() * 3000);
    }

    @Override
    public String emptyGivenString() {
        return "AA count:\nAacount:\naacount:";
    }

    @Override
    public String nonEmptyGivenString() {
        return "AA count: " + mAAcount +
               "\nAacount: " + mAacount +
               "\naacount: " + maacount;
    }
}
