package com.lnyapps.geneticsandevolution.crosssimulator.inheritance;

import com.lnyapps.geneticsandevolution.crosssimulator.inheritance.chromosome.Chromosome;

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
