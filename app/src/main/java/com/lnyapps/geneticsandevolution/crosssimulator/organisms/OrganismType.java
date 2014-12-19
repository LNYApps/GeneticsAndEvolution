package com.lnyapps.geneticsandevolution.crosssimulator.organisms;

import com.lnyapps.geneticsandevolution.App;
import com.lnyapps.geneticsandevolution.R;
import com.lnyapps.geneticsandevolution.crosssimulator.inheritance.Trait;

/**
 * Created by jonathantseng on 12/17/14.
 */
public enum OrganismType {

    PEA(new Trait("Shape", "Round", "Wrinkled", 'a'), new Trait("Color", "Yellow", "Green", 'b'), "pea"),
    CLOVER(new Trait("Color", "Purple", "Green", 'p'), new Trait("Stamen size", "Small", "Large", 'l'), "clover"),
    SNOWMAN(new Trait("Antennae", "No antennae", "Antennae", 't'), new Trait("Eyes", "Eyes", "No eyes", 'n'), "snowman"),
    BEARDED_DRAGON(new Trait("Hypo", "Normal", "Hypo", 'h'), new Trait("Trans", "Normal", "Trans", 't'), "beardeddragon");

    private Trait mTrait1, mTrait2;
    private String mImageName;

    private OrganismType(Trait trait1, Trait trait2, String imageName) {
        mTrait1 = trait1;
        mTrait2 = trait2;
        mImageName = imageName;
    }

    public static OrganismType createOrganism(String organism) {
        if (organism.equalsIgnoreCase(App.getContext().getString(R.string.cs_pea))) {
            return PEA;
        } else if (organism.equalsIgnoreCase(App.getContext().getString(R.string.cs_clover))) {
            return CLOVER;
        } else if (organism.equalsIgnoreCase(App.getContext().getString(R.string.cs_snowman))) {
            return SNOWMAN;
        } else {
            return BEARDED_DRAGON;
        }
    }

    public String getImageName() {
        return mImageName;
    }

    public Trait getFirstTrait() {
        return mTrait1;
    }

    public Trait getSecondTrait() {
        return mTrait2;
    }

}
