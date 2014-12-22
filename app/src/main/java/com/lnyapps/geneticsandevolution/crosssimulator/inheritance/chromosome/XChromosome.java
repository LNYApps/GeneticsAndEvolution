package com.lnyapps.geneticsandevolution.crosssimulator.inheritance.chromosome;

import com.lnyapps.geneticsandevolution.crosssimulator.inheritance.Allele;

import java.util.List;

/**
 * Created by jonathantseng on 12/17/14.
 */
public class XChromosome extends Chromosome {

    public XChromosome(List<Allele> alleles) {
        super(alleles);
    }

    public XChromosome(Allele... alleles) {
        super(alleles);
    }

    @Override
    public String toString() {
        return String.format("X<sup><small>%s</small></sup>", super.toString());
    }
}
