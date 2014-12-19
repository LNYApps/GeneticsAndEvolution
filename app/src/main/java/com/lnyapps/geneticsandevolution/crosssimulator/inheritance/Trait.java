package com.lnyapps.geneticsandevolution.crosssimulator.inheritance;

/**
 * Created by jonathantseng on 12/17/14.
 */
public class Trait {

    private String mDescription;
    private String mDominant;
    private String mRecessive;
    private String mCharacter;

    public Trait(String description, String dominant, String recessive, char character) {
        mDescription = description;
        mDominant = dominant;
        mRecessive = recessive;
        mCharacter = Character.toString(Character.toLowerCase(character));
    }

    public Allele getDominant() {
        return new Allele(mCharacter.toUpperCase());
    }

    public Allele getRecessive() {
        return new Allele(mCharacter.toLowerCase());
    }

    public String getDominantDescription() {
        return mDominant;
    }

    public String getRecessiveDescription() {
        return mRecessive;
    }

    @Override
    public String toString() {
        return mDescription;
    }
}