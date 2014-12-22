package com.lnyapps.geneticsandevolution.crosssimulator.inheritance.chromosome;

import com.lnyapps.geneticsandevolution.crosssimulator.inheritance.Allele;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jonathantseng on 12/17/14.
 */
public class Chromosome {

    protected List<Allele> mAlleles;

    public Chromosome(List<Allele> alleles) {
        mAlleles = alleles;
    }

    public Allele getAllele(int index) {
        return mAlleles.get(index);
    }

    public int numAlleles() {
        return mAlleles.size();
    }

    public Chromosome(Allele... alleles) {
        this(new ArrayList<Allele>(Arrays.asList(alleles)));
    }

    public List<Boolean> getAlleleDominances() {
        List<Boolean> dominances = new ArrayList<Boolean>();
        for (Allele a : mAlleles) {
            dominances.add((a.toString().equals(a.toString().toUpperCase())) ? true : false);
        }
        return dominances;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Allele allele : mAlleles) {
            sb.append(allele.toString());
        }
        return sb.toString();
    }
}