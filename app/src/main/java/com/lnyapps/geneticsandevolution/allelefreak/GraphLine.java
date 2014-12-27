package com.lnyapps.geneticsandevolution.allelefreak;

import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;

/**
 * Created by Alex on 12/1/2014.
 */
public class GraphLine {

    private float qfreq;
    private float fitAA;
    private float fitAa;
    private float fitaa;
    private int pop;
    private float inbreeding;
    private int generationsNumber;


    public GraphLine(float initFreq, float alleleFitAA, float alleleFitAa, float alleleFitaa,
                     int allelePop, float alleleInBreeding, int generations) {
        qfreq = initFreq;
        fitAA = alleleFitAA;
        fitAa = alleleFitAa;
        fitaa = alleleFitaa;
        pop = allelePop;
        inbreeding = alleleInBreeding;
        generationsNumber = generations;
    }

    public ArrayList<Entry> createData() {
        ArrayList<Entry> output = new ArrayList<Entry>();
        GraphLine tempGen = new GraphLine(this.qfreq, this.fitAA, this.fitAa, this.fitaa,
                this.pop, this.inbreeding, this.generationsNumber);

        //calculate initial frequencies
        float fAa = calcAafreq(this.inbreeding, this.qfreq);
        float fAA = calcAAfreq(fAa, this.qfreq);
        float faa = calcaafreq(fAa, this.qfreq);

        //apply fitness
        fAa = adjustFreq(fAa, this.fitAa);
        fAA = adjustFreq(fAA, this.fitAA);
        faa = adjustFreq(faa, this.fitaa);

        //renormalize adjusted frequencies so they add up to 1
        float totalFreq = fAa + fAA + faa;
        fAa = fAa / totalFreq;
        fAA = fAA / totalFreq;
        faa = faa / totalFreq;
        //assuming no genetic drift
        if (tempGen.pop == 0) {
            float outputfreq = faa + 0.5f * fAa;

            Entry entry = new Entry(outputfreq, 0);
            output.add(entry);

            for (int i = 1; i < this.generationsNumber; i++) {
                tempGen.qfreq = outputfreq;
                //calculate initial frequencies
                fAa = calcAafreq(this.inbreeding, tempGen.qfreq);
                fAA = calcAAfreq(fAa, tempGen.qfreq);
                faa = calcaafreq(fAa, tempGen.qfreq);

                //apply fitness
                fAa = adjustFreq(fAa, this.fitAa);
                fAA = adjustFreq(fAA, this.fitAA);
                faa = adjustFreq(faa, this.fitaa);

                //renormalize adjusted frequencies so they add up to 1
                totalFreq = fAa + fAA + faa;
                fAa = fAa / totalFreq;
                fAA = fAA / totalFreq;
                faa = faa / totalFreq;
                outputfreq = faa + 0.5f * fAa;
                entry = new Entry(outputfreq, i);
                output.add(entry);
            }
        } else {
            float AAcount = 0f;
            float Aacount = 0f;
            float aacount = 0f;
            //taking random samples at each generation
            for(int i=0; i<this.pop; i++){
                float sample = (float) Math.random();
                if(sample<faa){
                    aacount++;
                }
                else if(sample < (faa+fAa)){
                    Aacount++;
                }
                else{
                    AAcount++;
                }
            }
            float totalCount = aacount + Aacount + AAcount;
            faa = aacount/totalCount;
            fAa = Aacount/totalCount;
            fAA = AAcount/totalCount;

            float outputfreq = faa + 0.5f * fAa;
            Entry entry = new Entry(outputfreq, 0);
            output.add(entry);

            for (int i = 1; i < this.generationsNumber; i++) {
                tempGen.qfreq = outputfreq;
                //calculate initial frequencies
                fAa = calcAafreq(this.inbreeding, tempGen.qfreq);
                fAA = calcAAfreq(fAa, tempGen.qfreq);
                faa = calcaafreq(fAa, tempGen.qfreq);

                //apply fitness
                fAa = adjustFreq(fAa, this.fitAa);
                fAA = adjustFreq(fAA, this.fitAA);
                faa = adjustFreq(faa, this.fitaa);

                //renormalize adjusted frequencies so they add up to 1
                totalFreq = fAa + fAA + faa;
                fAa = fAa / totalFreq;
                fAA = fAA / totalFreq;
                faa = faa / totalFreq;

                AAcount = 0f;
                Aacount = 0f;
                aacount = 0f;
                //taking random samples at each generation
                for(int k=0; k<this.pop; k++){
                    float sample = (float) Math.random();
                    if(sample<faa){
                        aacount++;
                    }
                    else if(sample < (faa+fAa)){
                        Aacount++;
                    }
                    else{
                        AAcount++;
                    }
                }
                totalCount = aacount + Aacount + AAcount;
                faa = aacount/totalCount;
                fAa = Aacount/totalCount;
                fAA = AAcount/totalCount;

                outputfreq = faa + 0.5f * fAa;
                entry = new Entry(outputfreq, i);
                output.add(entry);
            }
        }

        return output;
    }

    private float calcAafreq(float inbreeding, float q) {
        float fAa = (1 - inbreeding) * 2 * q * (1 - q);
        return fAa;
    }

    private float calcAAfreq(float fAa, float q) {
        float fAA = (1 - q) - 0.5f * (fAa);
        return fAA;
    }

    private float calcaafreq(float fAa, float q) {
        float faa = q - 0.5f * (fAa);
        return faa;
    }

    /**
     * Calculates frequency (not normalized) of an allele
     *
     * @param freq    frequency of AA, Aa, or aa
     * @param fitness fitness of AA, Aa, or aa
     * @return frequencies adjusted for fitness
     */
    private float adjustFreq(float freq, float fitness) {
        return freq * fitness;
    }

}
