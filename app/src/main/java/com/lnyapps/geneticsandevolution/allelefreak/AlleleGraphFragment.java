package com.lnyapps.geneticsandevolution.allelefreak;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.lnyapps.geneticsandevolution.MainActivity;
import com.lnyapps.geneticsandevolution.R;

/**
 * Created by Alex on 11/30/2014.
 */
public class AlleleGraphFragment extends Fragment {

    private LineChart mChart;

    public AlleleGraphFragment() {
        Bundle args = new Bundle();
        //args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        setArguments(args);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_allelegraph, container, false);
        //start of graphing code

        mChart = (LineChart) rootView.findViewById(R.id.chart);
        mChart.setDescription("");
        mChart.setDrawYValues(false);
        mChart.setHighlightIndicatorEnabled(false);
        mChart.setDrawBorder(true);
        mChart.setDrawGridBackground(true);
        mChart.setDrawVerticalGrid(true);
        mChart.setDrawXLabels(true);
        mChart.setDrawYValues(true);
        mChart.setStartAtZero(true);
        mChart.setYRange(0, 1.0f, false);

        mChart.animateX(3000); //animates in 3000ms

        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(),"OpenSans-Light.ttf");

        //end
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(getResources().getString(R.string.allele_freak));
    }
}
