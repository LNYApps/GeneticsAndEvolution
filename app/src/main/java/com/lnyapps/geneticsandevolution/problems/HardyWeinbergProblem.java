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
        return "1) Observed genotype frequencies " +
                "\n2) Expected genotype frequencies " +
                "\n3) Resulting value of F";
    }

    @Override
    public String solution() {
        double[] solution = solve();
        return "Observed AA: " + String.format("%.3f", solution[0]) +
                "\nObserved Aa: " + String.format("%.3f", solution[1]) +
                "\nObserved aa: " + String.format("%.3f", solution[2]) +
                "\nExpected AA: " + String.format("%.3f", solution[3]) +
                "\nExpected Aa: " + String.format("%.3f", solution[4]) +
                "\nExpected aa: " + String.format("%.3f", solution[5]) +
                "\nF: " + String.format("%.3f", solution[6]);
    }

    public double[] solve() {
        double totalCount = mAAcount + mAacount + maacount;
        double observedAA = mAAcount / totalCount;
        double observedAa = mAacount / totalCount;
        double observedaa = maacount / totalCount;
        double freqA = observedAA + (0.5 * observedAa);
        double freqa = 1 - freqA;
        double expectedAA = freqA * freqA;
        double expectedAa = 2 * freqA * freqa;
        double expectedaa = freqa * freqa;
        double f = (expectedAa - observedAa) / expectedAa;
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
        if (Math.random() > 0.5) {
            double first = (Math.random() * 99 + 1) / 100.0;
            double second = 1 - first;
            int popSize = (int) (Math.random() * 9901) + 100;
            mAAcount = (int) (popSize * Math.pow(first, 2));
            mAacount = (int) (popSize * 2 * first * second);
            maacount = (int) (popSize * Math.pow(second, 2));
        } else {
            mAAcount = (int) (Math.random() * 3000) + 1;
            mAacount = (int) (Math.random() * 3000) + 1;
            maacount = (int) (Math.random() * 3000) + 1;
        }
    }

    @Override
    public void setArguments(double[] args) {
        randomValues();
        for (int i = 0; i < args.length; i++) {
            if (i == 0) {
                mAAcount = (int) args[0];
            } else if (i == 1) {
                mAacount = (int) args[1];
            } else if (i == 2) {
                maacount = (int) args[2];
            }
        }
    }

    @Override
    public String emptyGivenString() {
        return "AA count:\nAa count:\naa count:";
    }

    @Override
    public String nonEmptyGivenString() {
        return "AA count: " + mAAcount +
                "\nAa count: " + mAacount +
                "\naa count: " + maacount;
    }
}
