package com.lnyapps.geneticsandevolution.fragments.problemsolverfragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.lnyapps.geneticsandevolution.R;

/**
 * Created by Alex on 11/1/2014.
 */
public class GeneticCrossMappingFragment extends Fragment{

    public GeneticCrossMappingFragment() {
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

    public double[] calculateCrossMapping(int ABC, int ABc, int AbC, int Abc, int aBC, int aBc, int abC, int abc){
        int[] values = new int[8];
        values[0]=ABC;
        values[1]=ABc;
        values[2]=AbC;
        values[3]=Abc;
        values[4]=aBC;
        values[5]=aBc;
        values[6]=abC;
        values[7]=abc;
        String[] value_names = new String[8];
        value_names[0]="ABC";
        value_names[1]="ABc";
        value_names[2]="AbC";
        value_names[3]="Abc";
        value_names[4]="aBC";
        value_names[5]="aBc";
        value_names[6]="abC";
        value_names[7]="abc";
        int maxIndex = 0;
        int secondMaxIndex = 0;
        for(int i=0; i<values.length; i++){
            if(values[i]>values[maxIndex]){
                maxIndex=i;
            }
        }
        secondMaxIndex = secondHighest(values);

        double[] output = new double[8];
        return output;
    }

    /**
     * @param nums an array of ints
     * @return second highest int in the array
     */
    static int secondHighest(int[] nums) {
        int high1 = Integer.MIN_VALUE;
        int high2 = Integer.MIN_VALUE;
        for(int i = 0; i<nums.length; i++){
            if(nums[i] > high1){
                high2 = high1;
                high1 = i;
            }
            else if(nums[i] > high2){
                high2 = i;
            }
        }
        return high2;
    }
}
