package com.lnyapps.geneticsandevolution.crosssimulator.organisms;

import com.lnyapps.geneticsandevolution.crosssimulator.inheritance.Genotype;

/**
 * Created by jonathantseng on 12/18/14.
 */
public class Organism {

    private OrganismType mType;
    private Genotype mGenotype;

    public Organism(OrganismType mType, Genotype mGenotype) {
        this.mType = mType;
        this.mGenotype = mGenotype;
    }

    // TODO update Chromosome 1 based on alleles
    public void updateChromosome1(String alleles) {

    }

    // TODO update Chromosome 1 based on alleles
    public void updateChromosome2(String alleles) {

    }

}
