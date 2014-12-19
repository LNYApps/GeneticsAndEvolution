package com.lnyapps.geneticsandevolution.crosssimulator.organisms;

import android.graphics.drawable.Drawable;

import com.lnyapps.geneticsandevolution.App;
import com.lnyapps.geneticsandevolution.crosssimulator.inheritance.Genotype;

/**
 * Created by jonathantseng on 12/18/14.
 */
public class OrganismManager {

    public static Drawable getDrawableOrganism(Organism organism) {
        return getDrawableOrganism(organism.getType(), organism.getGenotype());
    }

    public static Drawable getDrawableOrganism(OrganismType organismType, Genotype genotype) {
        String uri = "drawable/" + organismType.getImageName() + "_" + genotype.genotypicDominance();
        int id = App.getContext().getResources().getIdentifier(uri, null, App.getContext().getPackageName());
        return App.getContext().getResources().getDrawable(id);
    }

}
