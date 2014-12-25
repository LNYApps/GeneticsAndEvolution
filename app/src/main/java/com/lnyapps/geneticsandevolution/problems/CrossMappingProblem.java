package com.lnyapps.geneticsandevolution.problems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by Jonathan Tseng on 11/1/2014.
 */
public class CrossMappingProblem extends GenEvolProblem {

    private int[] mParams;

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

        //TODO: In the future, make the dashes indicating distance optional (via a settings on/off switch)
        int dashesAB = 0;
        int dashesBC = 0;
        int dashesAC = 0;
        double recAB = solution[0];
        double recAC = solution[1];
        double recBC = solution[2];

        while(recAB > 0.05){
            dashesAB++;
            recAB = recAB - 0.05;
        }
        while(recAC > 0.05){
            dashesAC++;
            recAC = recAC - 0.05;
        }
        while(recBC > 0.05){
            dashesBC++;
            recBC = recBC - 0.05;
        }

        String firstParental = genotypes.get(firstParentalIndex);
        String secondParental = genotypes.get(secondParentalIndex);

        String linkage = "";
        if(solution[0]<0.45f && solution[1]<0.45f && solution[2]<0.45f){
            String tempFirst = firstParental.substring(0,1);
            String tempSecond = secondParental.substring(0,1);
            for(int i=0; i<dashesAB; i++){
                tempFirst += "-";
                tempSecond += "-";
            }
            tempFirst+= firstParental.substring(1,2);
            tempSecond+= secondParental.substring(1,2);
            for(int i=0; i<dashesBC; i++){
                tempFirst += "-";
                tempSecond += "-";
            }
            tempFirst += firstParental.substring(2);
            tempSecond += secondParental.substring(2);
            linkage = tempFirst + "//" + tempSecond;
        }
        else if((solution[0]<0.45f && solution[1]<0.45f && solution[2]>0.45f)
                || (solution[0]<0.45f && solution[1]>0.45f && solution[2]<0.45f)
                || (solution[0]>0.45f && solution[1]<0.45f && solution[2]<0.45f)){
            linkage = "The threshold fraction for unlinked genes is 0.45. There must be at least 2 values greater than 0.45 " +
                    "in order for the genetic cross-mapping to be calculated. Please re-generate or enter different values.";
        }
        else if(solution[0]<0.45f && solution[2]>0.45f && solution[1]>0.45f){
            String tempFirst = firstParental.substring(0,1);
            String tempSecond = secondParental.substring(0,1);
            for(int i=0; i<dashesAB; i++){
                tempFirst += "-";
                tempSecond += "-";
            }
            tempFirst += firstParental.substring(1,2);
            tempSecond += secondParental.substring(1,2);
            linkage = tempFirst + "//" + tempSecond + " ; " + firstParental.substring(2) + "//" + secondParental.substring(2);
        }
        else if(solution[0]>0.45f && solution[2]<0.45f && solution[1]>0.45f){
            String tempFirst = firstParental.substring(1,2);
            String tempSecond = secondParental.substring(1,2);
            for(int i=0; i<dashesBC; i++){
                tempFirst += "-";
                tempSecond += "-";
            }
            tempFirst += firstParental.substring(2);
            tempSecond += secondParental.substring(2);
            linkage = firstParental.substring(0,1) + "//" + secondParental.substring(0,1) + " ; " + tempFirst + "//" + tempSecond;
        }
        else if(solution[0]>0.45f && solution[2]>0.45f && solution[1]<0.45f){
            String tempFirst = firstParental.substring(0,1);
            String tempSecond = secondParental.substring(0,1);
            for(int i=0; i<dashesAC; i++){
                tempFirst += "-";
                tempSecond += "-";
            }
            tempFirst += firstParental.substring(2);
            tempSecond += secondParental.substring(2);
            linkage = tempFirst + "//" + tempSecond +
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
        double randomAllocateA = Math.random();
        double randomAllocateB = Math.random();
        double randomAllocateC = Math.random();
        ArrayList<String> chromo1 = new ArrayList<String>();
        ArrayList<String> chromo2 = new ArrayList<String>();
        ArrayList<String> chromo3 = new ArrayList<String>();
        ArrayList<String> chromo1Parental1 = new ArrayList<String>();
        ArrayList<String> chromo2Parental1 = new ArrayList<String>();
        ArrayList<String> chromo3Parental1 = new ArrayList<String>();


        if(randomAllocateA <= 0.92){
            chromo1.add("a");
            chromo1Parental1.add(startPhaseString.substring(0, 1));
        }
        else if(randomAllocateA <= 0.96){
            chromo2.add("a");
            chromo2Parental1.add(startPhaseString.substring(0, 1));
        }
        else{
            chromo3.add("a");
            chromo3Parental1.add(startPhaseString.substring(0, 1));
        }
        if(randomAllocateB <= 0.92){
            chromo1.add("b");
            chromo1Parental1.add(startPhaseString.substring(1, 2));
        }
        else if(randomAllocateB <= 0.96){
            chromo2.add("b");
            chromo2Parental1.add(startPhaseString.substring(1, 2));
        }
        else{
            chromo3.add("b");
            chromo3Parental1.add(startPhaseString.substring(1, 2));
        }
        if(randomAllocateC <= 0.92){
            chromo1.add("c");
            chromo1Parental1.add(startPhaseString.substring(2, 3));
        }
        else if(randomAllocateC <= 0.96){
            chromo2.add("c");
            chromo2Parental1.add(startPhaseString.substring(2, 3));
        }
        else{
            chromo3.add("c");
            chromo3Parental1.add(startPhaseString.substring(2, 3));
        }

    //calculating the recombination fractions for AB, BC, and AC
        double recAB = 0.0;
        double recBC = 0.0;
        double recAC = 0.0;

        //cases where the alleles are linked
        if(chromo1.contains("a") && chromo1.contains("b")){
            recAB = 0.2 * Math.random();
        }
        if(chromo1.contains("a") && chromo1.contains("c")){
            recAC = 0.2 * Math.random();
        }
        if(chromo1.contains("b") && chromo1.contains("c")){
            recBC = 0.2 * Math.random();
        }
        if(chromo2.contains("a") && chromo2.contains("b")){
            recAB = 0.2 * Math.random();
        }
        if(chromo2.contains("a") && chromo2.contains("c")){
            recAC = 0.2 * Math.random();
        }
        if(chromo2.contains("b") && chromo2.contains("c")){
            recBC = 0.2 * Math.random();
        }
        if(chromo3.contains("a") && chromo3.contains("b")){
            recAB = 0.2 * Math.random();
        }
        if(chromo3.contains("a") && chromo3.contains("c")){
            recAC = 0.2 * Math.random();
        }
        if(chromo3.contains("b") && chromo3.contains("c")){
            recBC = 0.2 * Math.random();
        }

        //randomizing the order of alleles on the genes
        Collections.shuffle(chromo1Parental1);
        Collections.shuffle(chromo2Parental1);
        Collections.shuffle(chromo3Parental1);
        ArrayList<String> chromo1Parental2 = new ArrayList<String>();
        ArrayList<String> chromo2Parental2 = new ArrayList<String>();
        ArrayList<String> chromo3Parental2 = new ArrayList<String>();
        for(String s: chromo1Parental1){
            String add = "";
            char c = s.toCharArray()[0];
            if(Character.isUpperCase(c)){
                c = Character.toLowerCase(c);
            }
            else{
                c = Character.toUpperCase(c);
            }
            add += c;
            chromo1Parental2.add(add);
        }
        for(String s: chromo2Parental1){
            String add = "";
            char c = s.toCharArray()[0];
            if(Character.isUpperCase(c)){
                c = Character.toLowerCase(c);
            }
            else{
                c = Character.toUpperCase(c);
            }
            add += c;
            chromo2Parental2.add(add);
        }
        for(String s: chromo3Parental1){
            String add = "";
            char c = s.toCharArray()[0];
            if(Character.isUpperCase(c)){
                c = Character.toLowerCase(c);
            }
            else{
                c = Character.toUpperCase(c);
            }
            add += c;
            chromo3Parental2.add(add);
        }

        //at this point, recombinant fractions are calculated and the alleles in randomized order are in the chromosomes (implemented as arraylists)
        //now need to generate offspring genotypes one by one up to the sample size
        for(int i = 0; i<mParams.length; i++){
            mParams[i] = 0;
        }

        for(int i = 0; i<1000; i++){

            String finalGenotype = simulateOneSample(chromo1Parental1, chromo2Parental1, chromo3Parental1,
                    chromo1Parental2, chromo2Parental2, chromo3Parental2, recAB, recBC, recAC);

            finalGenotype = reOrderGenes(finalGenotype);
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
    }

    /**
     * Simulates a single sample of the cross between two parentals
     * @param chromo1Parental1 alleles on the 1st chromosome of the 1st Parental
     * @param chromo2Parental1 alleles on the 2nd chromosome of the 1st Parental
     * @param chromo3Parental1 alleles on the 3rd chromosome of the 1st Parental
     * @param chromo1Parental2 alleles on the 1st chromosome of the 2nd Parental
     * @param chromo2Parental2 alleles on the 2nd chromosome of the 2nd Parental
     * @param chromo3Parental2 alleles on the 3rd chromosome of the 2nd Parental
     * @param recAB fraction of AB
     * @param recBC fraction of BC
     * @param recAC fraction of AC
     * @return genotype of the simulated cross for one sample
     */
    private String simulateOneSample(ArrayList<String> chromo1Parental1, ArrayList<String> chromo2Parental1, ArrayList<String> chromo3Parental1,
                                     ArrayList<String> chromo1Parental2, ArrayList<String> chromo2Parental2, ArrayList<String> chromo3Parental2,
                                     double recAB, double recBC, double recAC){
        String output = "";
        String parental1 = "";
        String parental2 = "";
        String unspacedParental1 = "";
        for(String s: chromo1Parental1){
            parental1 += s;
            unspacedParental1 += s.toLowerCase();
        }
        parental1 += " ";
        for(String s: chromo2Parental1){
            parental1 += s;
            unspacedParental1 += s.toLowerCase();
        }
        parental1 += " ";
        for(String s: chromo3Parental1){
            parental1 += s;
            unspacedParental1 += s.toLowerCase();
        }

        for(String s: chromo1Parental2){
            parental2 += s;
        }
        parental2 += " ";
        for(String s: chromo2Parental2){
            parental2 += s;
        }
        parental2 += " ";
        for(String s: chromo3Parental2){
            parental2 += s;
        }

        int currentChromo = 1;
        //parentals are written, with spaces between chromosomes

        int indexOfFirst = 0;
        for(int i = 0; i<parental1.length(); i++){
            String s = parental1.substring(i, i+1);
            if(!s.equals(" ")){
                indexOfFirst = i;
                break;
            }
        }

        if(Math.random() > 0.5){
            output += parental1.substring(indexOfFirst, indexOfFirst+1);
        }
        else{
            output += parental2.substring(indexOfFirst, indexOfFirst+1);
            currentChromo = -1;
        }

        int indexOfSecond = 0;
        for(int i=indexOfFirst+1; i<parental1.length(); i++){
            String s = parental1.substring(i, i+1);
            if(!s.equals(" ")){
                indexOfSecond = i;
                break;
            }
        }
        //case where second is unlinked
        if((indexOfSecond - indexOfFirst) > 1){
            if(Math.random() > 0.5){
                output += parental1.substring(indexOfSecond, indexOfSecond+1);
                currentChromo = 1;
            }
            else{
                output += parental2.substring(indexOfSecond, indexOfSecond+1);
            }
        }
        //case where second is linked
        else{
            String gene = unspacedParental1.substring(0,2);
            double compareRec = getCorrectRec(gene, recAB, recAC, recBC);
            if(Math.random() < compareRec){
                if(currentChromo==1){
                    output += parental2.substring(indexOfSecond, indexOfSecond+1);
                    currentChromo = -1;
                }
                else{
                    output += parental1.substring(indexOfSecond, indexOfSecond+1);
                    currentChromo = 1;
                }
            }
            else{
                if(currentChromo==1){
                    output += parental1.substring(indexOfSecond, indexOfSecond+1);
                }
                else{
                    output += parental2.substring(indexOfSecond, indexOfSecond+1);
                }
            }
        }
        int indexOfThird = 0;
        for(int i=indexOfSecond+1; i<parental1.length(); i++){
            String s = parental1.substring(i, i+1);
            if(!s.equals(" ")){
                indexOfThird = i;
                break;
            }
        }

        //case where third is unlinked
        if((indexOfThird - indexOfSecond) > 1){
            if(Math.random() > 0.5){
                output += parental1.substring(indexOfThird, indexOfThird+1);
            }
            else{
                output += parental2.substring(indexOfThird, indexOfThird+1);
            }
        }
        //case where third is linked
        else{
            String gene = unspacedParental1.substring(1);
            double compareRec = getCorrectRec(gene, recAB, recAC, recBC);
            if(Math.random() < compareRec){
                if(currentChromo==1){
                    output += parental2.substring(indexOfThird, indexOfThird+1);
                }
                else{
                    output += parental1.substring(indexOfThird, indexOfThird+1);
                }
            }
            else{
                if(currentChromo==1){
                    output += parental1.substring(indexOfThird, indexOfThird+1);
                }
                else{
                    output += parental2.substring(indexOfThird, indexOfThird+1);
                }
            }
        }
        return output;
    }

    private double getCorrectRec(String genes, double recAB, double recAC, double recBC){
        if(genes.equals("ab") || genes.equals("ba")){
            return recAB;
        }
        else if(genes.equals("ac") || genes.equals("ca")){
            return recAC;
        }
        else if(genes.equals("bc") || genes.equals("cb")){
            return recBC;
        }
        else{
            return 0.0;
        }
    }

    private String reOrderGenes(String gene){
        char[] test = gene.toCharArray();
        for(int i=0; i<test.length; i++){
            if(test[i] == 'a' || test[i] =='A'){
                char temp = test[0];
                test[0] = test[i];
                test[i] = temp;
            }
        }
        for(int i=0; i<test.length; i++){
            if(test[i] == 'b' || test[i] == 'B'){
                char temp = test[1];
                test[1] = test[i];
                test[i] = temp;
            }
        }
        String output = "";
        for(int i=0; i<test.length; i++){
            output+= test[i];
        }
        return output;
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
