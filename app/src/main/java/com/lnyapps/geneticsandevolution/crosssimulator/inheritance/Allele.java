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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Allele allele = (Allele) o;
        return mAllele.equals(allele.mAllele);
    }

    @Override
    public int hashCode() {
        return mAllele != null ? mAllele.hashCode() : 0;
    }
}
