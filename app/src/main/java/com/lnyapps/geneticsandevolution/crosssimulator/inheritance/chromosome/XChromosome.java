package com.lnyapps.geneticsandevolution.crosssimulator.inheritance.chromosome;

import com.lnyapps.geneticsandevolution.crosssimulator.inheritance.Allele;

/**
 * Created by jonathantseng on 12/17/14.
 */
public class XChromosome extends Chromosome {

    public XChromosome(Allele... alleles) {
        super(alleles);
    }

    @Override
    public String toString() {
        return String.format("X%s", super.toString());
    }
}
