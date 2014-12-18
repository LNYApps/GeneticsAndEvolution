package com.lnyapps.geneticsandevolution.crosssimulator.inheritance;

import com.lnyapps.geneticsandevolution.crosssimulator.inheritance.chromosome.XChromosome;
import com.lnyapps.geneticsandevolution.crosssimulator.inheritance.chromosome.YChromosome;

/**
 * Created by jonathantseng on 12/17/14.
 */
public class XlinkedInheritance implements InheritanceType {

    @Override
    public Genotype getDefaultFemaleGenotype(Trait ... traits) {
        return new Genotype(getXChromosome(true, traits), getXChromosome(false, traits));
    }

    @Override
    public Genotype getDefaultMaleGenotype(Trait ... traits) {
        return new Genotype(getXChromosome(false, traits), new YChromosome());
    }

    private XChromosome getXChromosome(boolean dominant, Trait ... traits) {
        Allele[] alleles = new Allele[traits.length];
        for (int i = 0; i < traits.length; i++) {
            alleles[i] = (dominant) ? new Allele(traits[i].getDominant()) : new Allele(traits[i].getRecessive());
        }
        return new XChromosome(alleles);
    }

}


