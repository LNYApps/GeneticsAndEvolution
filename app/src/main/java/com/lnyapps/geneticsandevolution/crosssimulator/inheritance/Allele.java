package com.lnyapps.geneticsandevolution.crosssimulator.inheritance;

/**
 * Created by jonathantseng on 12/17/14.
 */
public class Allele {

    private String mAllele;

    public Allele(String allele) {
        mAllele = allele;
    }

    @Override
    public String toString() {
        return mAllele;
    }
}
