package com.lnyapps.geneticsandevolution.crosssimulator.inheritance;

import com.lnyapps.geneticsandevolution.crosssimulator.inheritance.chromosome.Chromosome;

/**
 * Created by jonathantseng on 12/17/14.
 */
public class AutosomalInheritance implements InheritanceType {

    @Override
    public Genotype getDefaultFemaleGenotype(Trait... traits) {
        return new Genotype(getChromosome(true, traits), getChromosome(false, traits));
    }

    @Override
    public Genotype getDefaultMaleGenotype(Trait... traits) {
        return getDefaultFemaleGenotype(traits);
    }

    private Chromosome getChromosome(boolean dominant, Trait ... traits) {
        Allele[] alleles = new Allele[traits.length];
        for (int i = 0; i < traits.length; i++) {
            alleles[i] = (dominant) ? new Allele(traits[i].getDominant()) : new Allele(traits[i].getRecessive());
        }
        return new Chromosome(alleles);
    }

}
