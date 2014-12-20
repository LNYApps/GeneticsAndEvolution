package com.lnyapps.geneticsandevolution.crosssimulator;

import com.lnyapps.geneticsandevolution.crosssimulator.inheritance.InheritanceType;
import com.lnyapps.geneticsandevolution.crosssimulator.organisms.Organism;

/**
 * Created by jonathantseng on 12/18/14.
 */
public class CrossSimulatorArgs {

    private Organism mMale;
    private Organism mFemale;
    private InheritanceType mInheritanceType;

    public CrossSimulatorArgs(Organism male, Organism female, InheritanceType inheritanceType) {
        mMale = male;
        mFemale = female;
    }

    public Organism getMale() {
        return mMale;
    }

    public Organism getFemale() {
        return mFemale;
    }

    public InheritanceType getInheritanceType() {
        return mInheritanceType;
    }

}
