package com.lnyapps.geneticsandevolution.crosssimulator;

import com.lnyapps.geneticsandevolution.crosssimulator.organisms.Organism;

/**
 * Created by jonathantseng on 12/18/14.
 */
public class CrossSimulatorArgs {

    private Organism mMale;
    private Organism mFemale;

    public CrossSimulatorArgs(Organism male, Organism female) {
        mMale = male;
        mFemale = female;
    }

    public Organism getMale() {
        return mMale;
    }

    public Organism getFemale() {
        return mFemale;
    }

}
