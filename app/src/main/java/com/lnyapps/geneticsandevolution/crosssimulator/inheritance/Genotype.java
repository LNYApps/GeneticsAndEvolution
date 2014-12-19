package com.lnyapps.geneticsandevolution.crosssimulator.inheritance;

import com.lnyapps.geneticsandevolution.crosssimulator.inheritance.chromosome.Chromosome;
import com.lnyapps.geneticsandevolution.crosssimulator.inheritance.chromosome.YChromosome;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonathantseng on 12/17/14.
 */
public class Genotype {

    private Chromosome mChromosome1;
    private Chromosome mChromosome2;

    public Genotype(Chromosome chromosome1, Chromosome chromosome2) {
        mChromosome1 = chromosome1;
        mChromosome2 = chromosome2;
    }

    public String genotypicDominance() {
        List<Boolean> dom1 = mChromosome1.getAlleleDominances();
        List<Boolean> dom2;
        if (mChromosome2 instanceof YChromosome) {
            dom2 = new ArrayList<Boolean>();
            for (int i = 0; i < dom1.size(); i++) {
                dom2.add(false);
            }
        } else {
            dom2 = mChromosome2.getAlleleDominances();
        }
        String dominances = "";
        for (int i = 0; i < dom1.size(); i++) {
            dominances += (dom1.get(i).booleanValue() || dom2.get(i).booleanValue()) ? "d" : "r";
        }
        if (dominances.length() == 1) dominances += "d";
        return dominances;
    }

    public Chromosome getChromosome1() {
        return mChromosome1;
    }

    public Chromosome getChromosome2() {
        return mChromosome2;
    }

    @Override
    public String toString() {
        return String.format("%s %s", mChromosome1.toString(), mChromosome2.toString());
    }

}
