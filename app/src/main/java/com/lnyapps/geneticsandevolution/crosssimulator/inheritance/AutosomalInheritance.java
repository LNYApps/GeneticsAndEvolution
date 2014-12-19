package com.lnyapps.geneticsandevolution.crosssimulator.inheritance;

import com.lnyapps.geneticsandevolution.crosssimulator.inheritance.chromosome.Chromosome;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jonathantseng on 12/17/14.
 */
public class AutosomalInheritance implements InheritanceType {

    @Override
    public List<Genotype> getMaleGenotypes(Trait... traits) {
        return getFemaleGenotypes(traits);
    }

    @Override
    public List<Genotype> getFemaleGenotypes(Trait... traits) {
        List<Trait> traitList = new ArrayList<Trait>(Arrays.asList(traits));
        List<List<Allele>> combinations = getAlleleCombinations(traitList);
        List<Chromosome> chromosomes = new ArrayList<Chromosome>();
        for (List<Allele> combination : combinations) {
            chromosomes.add(new Chromosome(combination));
        }
        List<Genotype> genotypes = new ArrayList<Genotype>();
        for (int i = 0; i < chromosomes.size(); i++) {
            for (int j = 0; j < chromosomes.size(); j++) {
                Genotype genotype = new Genotype(chromosomes.get(i), chromosomes.get(j));
                if (!genotypes.contains(genotype)) {
                    genotypes.add(genotype);
                }
            }
        }
        return genotypes;
    }

    private List<List<Allele>> getAlleleCombinations(List<Trait> traits) {
        List<List<Allele>> combs = new ArrayList<List<Allele>>();
        if (traits.size() == 0) {
            combs.add(new ArrayList<Allele>());
        } else {
            for (List<Allele> comb : getAlleleCombinations(traits.subList(1, traits.size()))) {
                List<Allele> list1 = new ArrayList<Allele>();
                List<Allele> list2 = new ArrayList<Allele>();
                list1.add(traits.get(0).getDominant());
                list2.add(traits.get(0).getRecessive());
                list1.addAll(comb);
                list2.addAll(comb);
                combs.add(list1);
                combs.add(list2);
            }
        }
        return combs;
    }

}
