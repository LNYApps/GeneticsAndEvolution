package com.lnyapps.geneticsandevolution.crosssimulator.inheritance;

/**
 * Created by jonathantseng on 12/17/14.
 */
public class Trait {

    private String mDescription;
    private String mCharacter;

    public Trait(String description, char character) {
        mDescription = description;
        mCharacter = Character.toString(Character.toLowerCase(character));
    }

    public String getDominant() {
        return mCharacter.toUpperCase();
    }

    public String getRecessive() {
        return mCharacter.toLowerCase();
    }

    @Override
    public String toString() {
        return mDescription;
    }
}