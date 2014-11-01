package com.lnyapps.geneticsandevolution.fragments.problemsolverfragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lnyapps.geneticsandevolution.R;

/**
 * Created by Alex on 11/1/2014.
 */
public class PopGrowthFragment extends Fragment {

    public PopGrowthFragment() {
        Bundle args = new Bundle();
        //args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        setArguments(args);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pop_growth, container, false);
        return rootView;
    }

    public void destroyFragment() {
        getChildFragmentManager().beginTransaction().hide(this).commit();
    }

    public void createFragment() {
        getChildFragmentManager().beginTransaction().show(this).commit();
    }

    //TODO: finish PopGrowthProblem class with this stuff
    //Still need this
    /*
    public double calcPopGrowthRate(double initialPopSize, double finalPopSize, double time){
        double output = Math.log(finalPopSize/initialPopSize)/time;
        return output;
    }

    public double calcTime(double growthRate, double initialPopSize, double finalPopSize){
        double output = Math.log(finalPopSize/initialPopSize)/growthRate;
        return output;
    }

    public double calcFinalPop(double growthRate, double initialPopSize, double time){
        double output = initialPopSize*Math.exp(growthRate*time);
        return output;
    }

    public double calcInitialPop(double growthRate, double finalPopSize, double time){
        double output = finalPopSize/Math.exp(growthRate*time);
        return output;
    }
    */
}
