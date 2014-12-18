package com.lnyapps.geneticsandevolution.crosssimulator.organisms;

import com.lnyapps.geneticsandevolution.App;
import com.lnyapps.geneticsandevolution.R;
import com.lnyapps.geneticsandevolution.crosssimulator.inheritance.Trait;

/**
 * Created by jonathantseng on 12/17/14.
 */
public enum OrganismType {

    PEA(new Trait("Shape", 'a'), new Trait("Color", 'b'), "pea"),
    CLOVER(new Trait("Color", 'p'), new Trait("Stamen size", 'l'), "clover"),
    SNOWMAN(new Trait("Antennae", 't'), new Trait("Eyes", 'n'), "snowman"),
    BEARDED_DRAGON(new Trait("Hypo", 'h'), new Trait("Trans", 't'), "beardeddragon");

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
