package com.lnyapps.geneticsandevolution.crosssimulator.organisms;

import com.lnyapps.geneticsandevolution.crosssimulator.inheritance.AutosomalInheritance;
import com.lnyapps.geneticsandevolution.crosssimulator.inheritance.Genotype;
import com.lnyapps.geneticsandevolution.crosssimulator.inheritance.InheritanceType;
import com.lnyapps.geneticsandevolution.crosssimulator.inheritance.chromosome.Chromosome;
import com.lnyapps.geneticsandevolution.crosssimulator.inheritance.chromosome.XChromosome;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jonathantseng on 12/18/14.
 */
public class Organism {

    private OrganismType mType;
    private Genotype mGenotype;
    private InheritanceType mInheritanceType;

    public Organism(OrganismType mType, Genotype mGenotype, InheritanceType mInheritanceType) {
        this.mType = mType;
        this.mGenotype = mGenotype;
        this.mInheritanceType = mInheritanceType;
    }

    public OrganismType getType() {
        return mType;
    }

    public Genotype getGenotype() {
        return mGenotype;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Organism organism = (Organism) o;
        return (this.mGenotype.genotypicDominance().equals(organism.mGenotype.genotypicDominance()));
    }

    @Override
    public int hashCode() {
        int result = 31 * mGenotype.genotypicDominance().hashCode();
        return result;
    }

    // TODO maybe clean up entier hierarchy for genotype algorithms; currently pretty hardcoded
    public List<Chromosome> getPossibleChromosomes() {
        if (mInheritanceType instanceof AutosomalInheritance) {
            // need to do crossing over possibilities when 2 alleles
            if (mGenotype.getChromosome1().numAlleles() == 2) {
                return new ArrayList<Chromosome>(Arrays.asList(new Chromosome[]{
                        mGenotype.getChromosome1(),
                        mGenotype.getChromosome2(),
                        new Chromosome(mGenotype.getChromosome1().getAllele(0), mGenotype.getChromosome2().getAllele(1)),
                        new Chromosome(mGenotype.getChromosome2().getAllele(0), mGenotype.getChromosome1().getAllele(1))
                }));
            } else { // no crossing over
                return new ArrayList<Chromosome>(Arrays.asList(new Chromosome[]{
                        mGenotype.getChromosome1(),
                        mGenotype.getChromosome2()
                }));
            }
        } else { // i.e., X-linked
            // Crossing over when 2 genes and not male
            if (mGenotype.getChromosome1().numAlleles() == 2 && mGenotype.getChromosome2() instanceof XChromosome) {
                return new ArrayList<Chromosome>(Arrays.asList(new Chromosome[]{
                        mGenotype.getChromosome1(),
                        mGenotype.getChromosome2(),
                        new XChromosome(mGenotype.getChromosome1().getAllele(0), mGenotype.getChromosome2().getAllele(1)),
                        new XChromosome(mGenotype.getChromosome2().getAllele(0), mGenotype.getChromosome1().getAllele(1))
                }));
            } else {
                return new ArrayList<Chromosome>(Arrays.asList(new Chromosome[]{
                        mGenotype.getChromosome1(),
                        mGenotype.getChromosome2()
                }));
            }
        }

    }

}
