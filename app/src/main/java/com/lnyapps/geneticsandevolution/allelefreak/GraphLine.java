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

    public GraphLine() {
        qfreq = 0.0f;
        fitAA = 0.0f;
        fitAa = 0.0f;
        fitaa = 0.0f;
        pop = 0;
        inbreeding = 0.0f;
        generationsNumber = 0;
    }

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

    public ArrayList<Entry> createData(){
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
        fAa = fAa/totalFreq;
        fAA = fAA/totalFreq;
        faa = faa/totalFreq;
        float outputfreq = faa + 0.5f*fAa;
        Entry entry = new Entry(outputfreq, 0);
        output.add(entry);
/**
        for(int i=1; i<this.generationsNumber; i++){
            float avgFit = averageFitness(tempGen.qfreq, tempGen.fitAA, tempGen.fitAa, tempGen.fitaa);
            float iterFreq = qFreqOneGen(tempGen.qfreq, tempGen.fitAa, tempGen.fitaa, avgFit);
            tempGen.qfreq = iterFreq;
            Entry entry = new Entry(tempGen.qfreq, i);
            output.add(entry);
        }
*/
        //assuming no genetic drift
        for(int i=1; i<this.generationsNumber; i++){
            tempGen.qfreq = outputfreq;
            //calculate initial frequencies
            fAa = calcAafreq(this.inbreeding, this.qfreq);
            fAA = calcAAfreq(fAa, tempGen.qfreq);
            faa = calcaafreq(fAa, tempGen.qfreq);

            //apply fitness
            fAa = adjustFreq(fAa, this.fitAa);
            fAA = adjustFreq(fAA, this.fitAA);
            faa = adjustFreq(faa, this.fitaa);

            //renormalize adjusted frequencies so they add up to 1
            totalFreq = fAa + fAA + faa;
            fAa = fAa/totalFreq;
            fAA = fAA/totalFreq;
            faa = faa/totalFreq;
            outputfreq = faa + 0.5f*fAa;
            entry = new Entry(outputfreq, i);
            output.add(entry);
        }

        return output;
    }

    private float calcAafreq(float inbreeding, float q){
        float fAa = (1-inbreeding) * 2 * q * (1-q);
        return fAa;
    }

    private float calcAAfreq(float fAa, float q){
        float fAA = (1-q) - 0.5f*(fAa);
        return fAA;
    }

    private float calcaafreq(float fAa, float q){
        float faa = q - 0.5f* (fAa);
        return faa;
    }

    /**
     *Calculates frequency (not normalized) of an allele
     * @param freq frequency of AA, Aa, or aa
     * @param fitness fitness of AA, Aa, or aa
     * @return frequencies adjusted for fitness
     */
    private float adjustFreq(float freq, float fitness){
        return freq * fitness;
    }

    private float averageFitness(float q, float AAFit, float AaFit, float aaFit){
        float avgFit;
        float p = 1-q;
        avgFit = p*p*AAFit + 2*p*q*AaFit + q*q*aaFit;
        return avgFit;
    }

    private float qFreqOneGen(float qFreq, float AaFit, float aaFit, float avgFit){
        float output;
        float p = 1-qFreq;
        output = (qFreq*qFreq*aaFit)/avgFit + (p*qFreq*AaFit)/avgFit;
        return output;
    }

}
