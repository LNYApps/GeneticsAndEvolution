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

        for(int i=0; i<this.generationsNumber; i++){
            float avgFit = averageFitness(tempGen.qfreq, tempGen.fitAA, tempGen.fitAa, tempGen.fitaa);
            float iterFreq = qFreqOneGen(tempGen.qfreq, tempGen.fitAa, tempGen.fitaa, avgFit);
            tempGen.qfreq = iterFreq;
            Entry entry = new Entry(qfreq, i);
            output.add(entry);
        }
        return output;
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
