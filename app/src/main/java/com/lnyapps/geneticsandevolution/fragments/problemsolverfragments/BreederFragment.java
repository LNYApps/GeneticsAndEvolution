package com.lnyapps.geneticsandevolution.fragments.problemsolverfragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lnyapps.geneticsandevolution.R;

/**
 * Created by Alex on 11/1/2014.
 */
public class BreederFragment extends Fragment {

    public BreederFragment() {
        Bundle args = new Bundle();
        //args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        setArguments(args);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_hardy_weinberg, container, false);
        return rootView;
    }

    public double calcHeritability(double startingPhenotype, double selectedPhenotype, double responsePhenotype){
        double output = (startingPhenotype - responsePhenotype)/(startingPhenotype - selectedPhenotype);
        return output;
    }

    public double calcStartingPhenotype(double selectedPhenotype, double responsePhenotype, double heritability){
        double output = (responsePhenotype - selectedPhenotype*heritability)/(1-heritability);
        return output;
    }

    public double calcSelectedPhenotype(double startingPhenotype, double responsePhenotype, double heritability){
        double output = (responsePhenotype-startingPhenotype)/heritability + startingPhenotype;
        return output;
    }

    public double calcResponsePhenotype(double startingPhenotype, double selectedPhenotype, double heritability){
        double output = startingPhenotype - (startingPhenotype-selectedPhenotype)*heritability;
        return output;
    }
}
