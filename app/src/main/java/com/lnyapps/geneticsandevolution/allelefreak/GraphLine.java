package com.lnyapps.geneticsandevolution.allelefreak;

import com.github.mikephil.charting.data.ChartData;

import java.util.ArrayList;

/**
 * Created by Alex on 12/1/2014.
 */
public class GraphLine {

    private double freq;
    private double fitAA;
    private double fitAa;
    private double fitaa;
    private int pop;
    private double inbreeding;

    public GraphLine() {
        freq = 0.0;
        fitAA = 0.0;
        fitAa = 0.0;
        fitaa = 0.0;
        pop = 0;
        inbreeding = 0.0;
    }

    public GraphLine(double initFreq, double alleleFitAA, double alleleFitAa, double alleleFitaa,
                     int allelePop, double alleleInBreeding) {
        initFreq = freq;
        alleleFitAA = fitAA;
        alleleFitAa = fitAa;
        alleleFitaa = fitaa;
        allelePop = pop;
        alleleInBreeding = inbreeding;
    }

    public ChartData graph(){
        ChartData output = new ChartData

        return output;
    }
}
