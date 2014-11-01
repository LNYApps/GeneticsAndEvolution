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

import com.lnyapps.geneticsandevolution.MainActivity;
import com.lnyapps.geneticsandevolution.R;

/**
 * Created by Alex on 10/31/2014.
 */
public class HardyWeinbergFragment extends Fragment {

    public HardyWeinbergFragment() {
        Bundle args = new Bundle();
        //args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        setArguments(args);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_hardy_weinberg, container, false);
       // EditText AA = (EditText) rootView.findViewById(R.id.AA_count);
       // EditText Aa = (EditText) rootView.findViewById(R.id.Aa_count);
       // EditText aa = (EditText) rootView.findViewById(R.id.aa_count);
        return rootView;
    }

    public double[] calculateHardyWeinberg(int AAcount, int Aacount, int aacount){
        double totalCount = AAcount + Aacount + aacount;
        double observedAA = AAcount/totalCount;
        double observedAa = Aacount/totalCount;
        double observedaa = aacount/totalCount;
        double freqA = observedAA + (0.5*observedAa);
        double freqa = 1 - freqA;
        double expectedAA = freqA*freqA;
        double expectedAa = 2*freqA*freqa;
        double expectedaa = freqa*freqa;
        double f = (expectedAa - observedAa)/expectedAa;
        double[] output = new double[7];
        output[0] = observedAA;
        output[1] = observedAa;
        output[2] = observedaa;
        output[3] = expectedAA;
        output[4] = expectedAa;
        output[5] = expectedaa;
        output[6] = f;
        return output;
    }
}
