package com.lnyapps.geneticsandevolution.crosssimulator.inheritance;

/**
 * Created by jonathantseng on 12/17/14.
 */
public interface InheritanceType {

    public abstract Genotype getDefaultFemaleGenotype(Trait ... traits);

    public abstract Genotype getDefaultMaleGenotype(Trait ... traits);

}