package com.lnyapps.geneticsandevolution.problems;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Jonathan Tseng on 11/1/2014.
 */
public class CrossMappingProblem extends GenEvolProblem {

    private int[] mParams;
    private int mPopulation = 1000;

    @Override
    public String solution() {
        double[] solution = calculateCrossMapping(mParams[0], mParams[1], mParams[2], mParams[3],
                mParams[4], mParams[5], mParams[6], mParams[7]);
        return
                //TODO: Calculate the predicated linkage
                /*"Predicted phase, order & linkage:" +
                "\na--c--B//A--C--b" +*/
                "\nAB rec fraction: " + solution[0] +
                "\nBC rec fraction: " + solution[2] +
                "\nAC rec fraction: " + solution[1];
    }

    @Override
    public void randomValues() {
        mParams = new int[8];
        int sum = 0;
        for (int i = 0; i < mParams.length; i ++) {
            double scale = 1.5 * (Math.min(Math.abs(i), Math.abs(8 - i))) + 1;
            mParams[i] = (int) (Math.random() * 1000 * scale);
            sum += mParams[i];
        }
        for (int i = 0; i < mParams.length; i ++) {
            mParams[i] = (int) Math.round(mParams[i] * 1000 / sum);
        }
    }

    @Override
    public String emptyGivenString() {
        return "Gamete counts observed for heterozygous individual (AaBbCc): " +
                "\nABC: " +
                "\nABc: " +
                "\nAbC: " +
                "\nAbc: " +
                "\naBC: " +
                "\naBc: " +
                "\nabC: " +
                "\nabc: ";
    }

    @Override
    public String nonEmptyGivenString() {
        return "Gamete counts observed for heterozygous individual (AaBbCc): " +
                "\nABC: " + mParams[0] +
                "\nABc: " + mParams[1] +
                "\nAbC: " + mParams[2] +
                "\nAbc: " + mParams[3] +
                "\naBC: " + mParams[4] +
                "\naBc: " + mParams[5] +
                "\nabC: " + mParams[6] +
                "\nabc: " + mParams[7];
    }

    @Override
    public String emptySolveString() {
        return "1) recombination rates, " +
                "\n2) predicted gene order, gametic phase and linkage (for example: bAC // Bac)";
    }

    private double[] calculateCrossMapping(int ABC, int ABc, int AbC, int Abc, int aBC, int aBc, int abC, int abc){
        //creating all the data structures needed
        int[] values = new int[8];
        values[0]=ABC;
        values[1]=ABc;
        values[2]=AbC;
        values[3]=Abc;
        values[4]=aBC;
        values[5]=aBc;
        values[6]=abC;
        values[7]=abc;
        HashMap<Integer, ArrayList<String>> name_map = new HashMap<Integer, ArrayList<String>>();
        ArrayList<String> zero = new ArrayList<String>();
        zero.add("A");
        zero.add("B");
        zero.add("C");
        ArrayList<String> one = new ArrayList<String>();
        one.add("A");
        one.add("B");
        one.add("c");
        ArrayList<String> two = new ArrayList<String>();
        two.add("A");
        two.add("b");
        two.add("C");
        ArrayList<String> three = new ArrayList<String>();
        three.add("A");
        three.add("b");
        three.add("c");
        ArrayList<String> four = new ArrayList<String>();
        four.add("a");
        four.add("B");
        four.add("C");
        ArrayList<String> five = new ArrayList<String>();
        five.add("a");
        five.add("B");
        five.add("c");
        ArrayList<String> six = new ArrayList<String>();
        six.add("a");
        six.add("b");
        six.add("C");
        ArrayList<String> seven = new ArrayList<String>();
        seven.add("a");
        seven.add("b");
        seven.add("c");
        name_map.put(0, zero);
        name_map.put(1, one);
        name_map.put(2, two);
        name_map.put(3, three);
        name_map.put(4, four);
        name_map.put(5, five);
        name_map.put(6, six);
        name_map.put(7, seven);

        //calculating total number of individuals
        int total = 0;
        for(int num: values){
            total+=num;
        }
        //calculating the parental gametes
        int maxIndex = 0;
        for(int i=0; i<values.length; i++){
            if(values[i]>values[maxIndex]){
                maxIndex=i;
            }
        }
        int secondMaxIndex = 0;
        secondMaxIndex = secondHighest(values);

        //calculating the recombinants and determining recombinant gamete frequency
        double AtoB = 0;
        String aMax = name_map.get(maxIndex).get(0);
        String bMax = name_map.get(maxIndex).get(1);
        String cMax = name_map.get(maxIndex).get(2);
        String aMax2 = name_map.get(secondMaxIndex).get(0);
        String bMax2 = name_map.get(secondMaxIndex).get(1);
        String cMax2 = name_map.get(secondMaxIndex).get(2);
        for(int i=0; i<8; i++){
            ArrayList<String> temp = name_map.get(i);
            String tempA = temp.get(0);
            String tempB = temp.get(1);
            if((!tempA.equals(aMax)||!tempB.equals(bMax)) && (!tempA.equals(aMax2)||!tempB.equals(bMax2))){
                AtoB += values[i];
            }
        }
        double AtoC = 0;
        for(int i=0; i<8; i++){
            ArrayList<String> temp = name_map.get(i);
            String tempA = temp.get(0);
            String tempC = temp.get(2);
            if((!tempA.equals(aMax)||!tempC.equals(cMax)) && (!tempA.equals(aMax2)||!tempC.equals(cMax2))){
                AtoC += values[i];
            }
        }
        double BtoC = 0;
        for(int i=0; i<8; i++){
            ArrayList<String> temp = name_map.get(i);
            String tempB = temp.get(1);
            String tempC = temp.get(2);
            if((!tempB.equals(bMax)||!tempC.equals(cMax)) && (!tempB.equals(bMax2)||!tempC.equals(cMax2))){
                BtoC += values[i];
            }
        }
        double aToBfreq = AtoB/total;
        double aToCfreq = AtoC/total;
        double bToCfreq = BtoC/total;
        double[] output = new double[3];
        output[0] = aToBfreq;
        output[1] = aToCfreq;
        output[2] = bToCfreq;
        return output;
    }

    /**
     * @param nums an array of ints
     * @return returns the index of the second highest int in the array
     */
    static int secondHighest(int[] nums) {
        int high1 = 0;
        int high2 = 0;
        for(int i = 0; i<nums.length; i++){
            if(nums[i] >= nums[high1]){
                high2 = high1;
                high1 = i;
            }
            else if(nums[i] > nums[high2]){
                high2 = i;
            }
        }
        return high2;
    }

}
