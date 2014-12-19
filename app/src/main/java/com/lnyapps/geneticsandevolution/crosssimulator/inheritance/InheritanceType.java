package com.lnyapps.geneticsandevolution.crosssimulator.inheritance;

import java.util.List;

/**
 * Created by jonathantseng on 12/17/14.
 */
public interface InheritanceType {

    public abstract List<Genotype> getFemaleGenotypes(Trait... traits);

    public abstract List<Genotype> getMaleGenotypes(Trait... traits);

}