package com.lnyapps.geneticsandevolution.crosssimulator.inheritance.chromosome;

import com.lnyapps.geneticsandevolution.crosssimulator.inheritance.Allele;

/**
 * Created by jonathantseng on 12/17/14.
 */
public class YChromosome extends Chromosome {

    public YChromosome(Allele... alleles) {
        this();
    }

    public YChromosome() {
        super();
    }

    @Override
    public String toString() {
        return "Y";
    }
}
