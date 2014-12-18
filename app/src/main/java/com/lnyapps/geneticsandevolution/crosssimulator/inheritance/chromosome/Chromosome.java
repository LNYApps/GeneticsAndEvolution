package com.lnyapps.geneticsandevolution.crosssimulator.inheritance.chromosome;

import com.lnyapps.geneticsandevolution.crosssimulator.inheritance.Allele;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by jonathantseng on 12/17/14.
 */
public class Chromosome {

    protected List<Allele> mAlleles;

    public Chromosome(Allele... alleles) {
        mAlleles = new ArrayList<Allele>(Arrays.asList(alleles));
    }

    public List<Allele> getAlleles() {
        return Collections.unmodifiableList(mAlleles);
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