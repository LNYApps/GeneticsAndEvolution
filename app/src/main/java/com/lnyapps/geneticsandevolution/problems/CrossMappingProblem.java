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

        ArrayList<String> genotypes = new ArrayList<String>();
        genotypes.add("ABC");
        genotypes.add("ABc");
        genotypes.add("AbC");
        genotypes.add("Abc");
        genotypes.add("aBC");
        genotypes.add("aBc");
        genotypes.add("abC");
        genotypes.add("abc");

        int firstParentalIndex = (int) solution[3];
        int secondParentalIndex = (int) solution[4];

        String firstParental = genotypes.get(firstParentalIndex);
        String secondParental = genotypes.get(secondParentalIndex);

        String linkage = "";
        if(solution[0]<0.45f && solution[1]<0.45f && solution[2]<0.45f){
            linkage = firstParental + "//" + secondParental;
        }
        else if(solution[0]<0.45f && solution[2]>0.45f && solution[1]>0.45f){
            linkage = firstParental.substring(0,2) + "//" + secondParental.substring(0,2) + " ; " + firstParental.substring(2) + "//" + secondParental.substring(2);
        }
        else if(solution[0]>0.45f && solution[2]<0.45f && solution[1]>0.45f){
            linkage = firstParental.substring(0,1) + "//" + secondParental.substring(0,1) + " ; " + firstParental.substring(1) + "//" + secondParental.substring(1);
        }
        else if(solution[0]>0.45f && solution[2]>0.45f && solution[1]<0.45f){
            linkage = firstParental.substring(0,1) + firstParental.substring(2) + "//" + secondParental.substring(0,1) + secondParental.substring(2) +
                    " ; " + firstParental.substring(1,2) + "//" + secondParental.substring(1,2);
        }
        else if(solution[0]>0.45f && solution[1]>0.45f && solution[2]>0.45f){
            linkage = firstParental.substring(0,1) + "//" + secondParental.substring(0,1) +
                    " ; " + firstParental.substring(1,2) + "//" + secondParental.substring(1,2) +
                    " ; " + firstParental.substring(2) + "//" + secondParental.substring(2);
        }
        return
                "Predicted phase, order, and linkage:\n" +
                linkage +
                "\nAB rec fraction: " + String.format("%.3f", solution[0]) +
                "\nBC rec fraction: " + String.format("%.3f", solution[2]) +
                "\nAC rec fraction: " + String.format("%.3f", solution[1]);
    }

    @Override
    public void randomValues() {
        mParams = new int[8];
        double startPhaseRandomVal = Math.random();
        int startPhase;
        if(startPhaseRandomVal < 0.25){
            startPhase = 0;
        }
        else if(startPhaseRandomVal <0.5){
            startPhase = 1;
        }
        else if(startPhaseRandomVal < 0.75){
            startPhase = 2;
        }
        else{
            startPhase = 3;
        }

        ArrayList<String> phases = new ArrayList<String>();
        phases.add("ABCabc");
        phases.add("ABcabC");
        phases.add("AbCaBc");
        phases.add("aBCAbc");

        String startPhaseString = phases.get(startPhase);
        String startFirstParental = "";
        String startSecondParental = "";
        double randomAllocateA = Math.random();
        double randomAllocateB = Math.random();
        double randomAllocateC = Math.random();
        ArrayList<String> chromo1 = new ArrayList<String>();
        ArrayList<String> chromo2 = new ArrayList<String>();
        ArrayList<String> chromo3 = new ArrayList<String>();

        if(randomAllocateA <= 0.92){
            chromo1.add("a");
        }
        else if(randomAllocateA <= 0.96){
            chromo2.add("a");
        }
        else{
            chromo3.add("a");
        }
        if(randomAllocateB <= 0.92){
            chromo1.add("b");
        }
        else if(randomAllocateB <= 0.96){
            chromo2.add("b");
        }
        else{
            chromo3.add("b");
        }
        if(randomAllocateC <= 0.92){
            chromo1.add("c");
        }
        else if(randomAllocateC <= 0.96){
            chromo2.add("c");
        }
        else{
            chromo3.add("c");
        }

    //calculating the recombination fractions for AB, BC, and AC
        double recAB = 0.0;
        boolean changedAB = false;
        double recBC = 0.0;
        boolean changedBC = false;
        double recAC = 0.0;
        boolean changedAC = false;

        //cases where the alleles are linked
        if(chromo1.contains("a") && chromo1.contains("b")){
            recAB = 0.2 * Math.random();
            changedAB = true;
        }
        if(chromo1.contains("a") && chromo1.contains("c")){
            recAC = 0.2 * Math.random();
            changedAC = true;
        }
        if(chromo1.contains("b") && chromo1.contains("c")){
            recBC = 0.2 * Math.random();
            changedBC = true;
        }
        if(chromo2.contains("a") && chromo2.contains("b")){
            recAB = 0.2 * Math.random();
            changedAB = true;
        }
        if(chromo2.contains("a") && chromo2.contains("c")){
            recAC = 0.2 * Math.random();
            changedAC = true;
        }
        if(chromo2.contains("b") && chromo2.contains("c")){
            recBC = 0.2 * Math.random();
            changedBC = true;
        }
        if(chromo3.contains("a") && chromo3.contains("b")){
            recAB = 0.2 * Math.random();
            changedAB = true;
        }
        if(chromo3.contains("a") && chromo3.contains("c")){
            recAC = 0.2 * Math.random();
            changedAC = true;
        }
        if(chromo3.contains("b") && chromo3.contains("c")){
            recBC = 0.2 * Math.random();
            changedBC = true;
        }

        //calculating the genotypes
        /**if(chromo1.size()==3){
            double choose
            startFirstParental +=
        }*/
        if(chromo1.contains("a") && chromo1.contains("b")){
            double chooseFirst = Math.random();
            if(chooseFirst <= 0.5){
                startFirstParental += startPhaseString.substring(0,2);
            }
            else{
                startFirstParental += startPhaseString.substring(1,2);
                startFirstParental += startPhaseString.substring(0,1);
            }
        }
        //at this point, recombinant fractions are calculated
        //now need to generate offspring genotypes one by one up to the sample size

        for(int i = 0; i<mParams.length; i++){
            mParams[i] = 0;
        }

        for(int i = 0; i<1000; i++){
            String genotypeA = startPhaseString.substring(0,3);
            String genotypeB = startPhaseString.substring(3);
            double chooseFirst = Math.random();
            String startingGenotype;
            String startingOtherGenotype;
            String finalGenotype = "";
            int currentGeneIndex;

            //determining the first allele
            if(chooseFirst <= 0.5){
                startingGenotype = genotypeA;
                startingOtherGenotype = genotypeB;
                currentGeneIndex = -1;
            }
            else{
                startingGenotype = genotypeB;
                startingOtherGenotype = genotypeA;
                currentGeneIndex = 1;
            }
            finalGenotype += startingGenotype.substring(0,1);

            //determining the second allele
            if(changedAB){
                double crossover = Math.random();
                if(crossover < recAB){
                    finalGenotype += startingOtherGenotype.substring(1,2);
                    currentGeneIndex = currentGeneIndex * (-1);
                }
                else{
                    finalGenotype += startingGenotype.substring(1,2);
                }

                //determining the third allele
                if(changedBC){
                    double crossover2 = Math.random();
                    if(crossover2 < recBC){
                        if(currentGeneIndex == -1){
                            finalGenotype += startingOtherGenotype.substring(2);
                        }
                        else{
                            finalGenotype += startingGenotype.substring(2);
                        }
                    }
                    else{
                        if(currentGeneIndex == -1){
                            finalGenotype += startingGenotype.substring(2);
                        }
                        else{
                            finalGenotype += startingOtherGenotype.substring(2);
                        }
                    }
                }
            }
            else{
                double crossover = Math.random();
                if(crossover <= 0.5){
                    finalGenotype += startingOtherGenotype.substring(1,2);
                    currentGeneIndex = currentGeneIndex * -1;

                }
                else{
                    finalGenotype += startingGenotype.substring(1,2);
                }

                //check to see if A and C are linked if A and B are not linked
                if(changedAC){
                    double crossover3 = Math.random();
                    if(crossover3 <= recAC){
                        if(currentGeneIndex == -1){
                            finalGenotype += startingOtherGenotype.substring(2);
                        }
                        else{
                            finalGenotype += startingGenotype.substring(2);
                        }
                    }
                    else{
                        if(currentGeneIndex == -1){
                            finalGenotype += startingGenotype.substring(2);
                        }
                        else{
                            finalGenotype += startingOtherGenotype.substring(2);
                        }
                    }
                }

                //if A and C are not linked, then go to this step and check if B and C are linked
                else {
                    //determining the third allele
                    if (changedBC) {
                        double crossover2 = Math.random();
                        if (crossover2 < recBC) {
                            if (currentGeneIndex == -1) {
                                finalGenotype += startingOtherGenotype.substring(2);
                            }
                            else {
                                finalGenotype += startingGenotype.substring(2);
                            }
                        }
                        else {
                            if (currentGeneIndex == -1) {
                                finalGenotype += startingGenotype.substring(2);
                            }
                            else {
                                finalGenotype += startingOtherGenotype.substring(2);
                            }
                        }
                    }
                }
            }



            //genotype of one sample is now calculated
            //update the counts of the output
            ArrayList<String> genotypes = new ArrayList<String>();
            genotypes.add("ABC");
            genotypes.add("ABc");
            genotypes.add("AbC");
            genotypes.add("Abc");
            genotypes.add("aBC");
            genotypes.add("aBc");
            genotypes.add("abC");
            genotypes.add("abc");

            for(int j = 0; j<genotypes.size(); j++){
                if(finalGenotype.equals(genotypes.get(j))){
                    mParams[j]++;
                }
            }
        }


        //case where A, B, and C are all linked
        double allLinked = Math.random();
        if(allLinked <= 0.8464) {

            double chooseParentals = Math.random();
            

            /**
            for(int k = 0; k<mParams.length; k++){
                mParams[k] = 0;
            }
            recAB = Math.random()*0.2;
            recAC = Math.random()*0.2;
            recBC = Math.random()*0.2;
            for(int i=0; i<1000; i++){
                String genotypeA = startPhaseString.substring(0,3);
                String genotypeB = startPhaseString.substring(3);
                double chooseFirst = Math.random();
                String startingGenotype;
                String startingOtherGenotype;
                String finalGenotype = "";
                int currentGeneIndex;

                //determining the first allele
                if(chooseFirst <= 0.5){
                    startingGenotype = genotypeA;
                    startingOtherGenotype = genotypeB;
                    currentGeneIndex = -1;
                }
                else{
                    startingGenotype = genotypeB;
                    startingOtherGenotype = genotypeA;
                    currentGeneIndex = 1;
                }
                finalGenotype += startingGenotype.substring(0,1);

                //determining second allele
                double crossoverSecond = Math.random();
                if(crossoverSecond < recAB){
                    finalGenotype += startingOtherGenotype.substring(1,2);
                    currentGeneIndex = currentGeneIndex* (-1);
                }
                else{
                    finalGenotype += startingGenotype.substring(1,2);
                }

                //determining third allele

                double chooseThird = Math.random();
                double crossoverThird = Math.random();
                if(chooseThird <= 0.5){
                    if(crossoverThird < recBC){
                        if(currentGeneIndex == -1){
                            finalGenotype += startingOtherGenotype.substring(2);
                        }
                        else{
                            finalGenotype += startingGenotype.substring(2);
                        }
                    }
                    else{
                        if(currentGeneIndex == -1){
                            finalGenotype += startingGenotype.substring(2);
                        }
                        else{
                            finalGenotype += startingOtherGenotype.substring(2);
                        }
                    }
                }
                else{
                    if(crossoverThird < recAC){
                        if(currentGeneIndex == -1){
                            finalGenotype += startingOtherGenotype.substring(2);
                        }
                        else{
                            finalGenotype += startingGenotype.substring(2);
                        }
                    }
                    else{
                        if(currentGeneIndex == -1){
                            finalGenotype += startingGenotype.substring(2);
                        }
                        else{
                            finalGenotype += startingOtherGenotype.substring(2);
                        }
                    }
                }

                //updating outputs
                ArrayList<String> genotypes = new ArrayList<String>();
                genotypes.add("ABC");
                genotypes.add("ABc");
                genotypes.add("AbC");
                genotypes.add("Abc");
                genotypes.add("aBC");
                genotypes.add("aBc");
                genotypes.add("abC");
                genotypes.add("abc");

                for(int j = 0; j<genotypes.size(); j++){
                    if(finalGenotype.equals(genotypes.get(j))){
                        mParams[j]++;
                    }
                }
            }
        */}
    }

    @Override
    public void setArguments(double[] args) {
        randomValues();
        for (int i = 0; i < args.length; i++) {
            if (i < 8) {
                mParams[i] = (int) args[i];
            }
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
        secondMaxIndex = secondParental(values, maxIndex);

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
        double[] output = new double[5];
        output[0] = aToBfreq;
        output[1] = aToCfreq;
        output[2] = bToCfreq;
        output[3] = maxIndex;
        output[4] = secondMaxIndex;
        return output;
    }

    /**
     * @param nums an array of ints
     * @param max the first max of the array of values
     * @return returns the index of the second highest int in the array
     */
    static int secondHighest(int[] nums, int max) {
        int secondMax;
        if(max == 0){
            secondMax = 1;
        }
        else{
            secondMax = 0;
        }
        for(int i=0; i<nums.length; i++){
            if(nums[i] < nums[max] && nums[i] > nums[secondMax]){
                secondMax = i;
            }
        }
        return secondMax;
    }

    static int secondParental(int[] nums, int max){
        int secondParentalIndex;
        ArrayList<String> genotypes = new ArrayList<String>();
        genotypes.add("ABC");
        genotypes.add("ABc");
        genotypes.add("AbC");
        genotypes.add("Abc");
        genotypes.add("aBC");
        genotypes.add("aBc");
        genotypes.add("abC");
        genotypes.add("abc");
        String firstParental = genotypes.get(max);
        char[] firstParentalgenotype = new char[3];
        char[] secondParentalgenotype = new char[3];
        for(int i=0; i<firstParental.length(); i++){
            firstParentalgenotype[i] = firstParental.charAt(i);
        }
        for(int i=0; i<firstParentalgenotype.length; i++){
            if(Character.isUpperCase(firstParentalgenotype[i])){
                secondParentalgenotype[i] = Character.toLowerCase(firstParentalgenotype[i]);
            }
            if(Character.isLowerCase(firstParentalgenotype[i])){
                secondParentalgenotype[i] = Character.toUpperCase(firstParentalgenotype[i]);
            }
        }
        String secondParentalString = "";
        for(int i=0; i<secondParentalgenotype.length; i++){
            secondParentalString += secondParentalgenotype[i];
        }
        secondParentalIndex = genotypes.indexOf(secondParentalString);
        return secondParentalIndex;
    }

}
