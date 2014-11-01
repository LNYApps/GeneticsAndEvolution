package com.lnyapps.geneticsandevolution.fragments.problemgeneratorfragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lnyapps.geneticsandevolution.R;

/**
 * Created by Alex on 11/1/2014.
 */
public class PopGrowthGeneratorFragment extends Fragment {
    public PopGrowthGeneratorFragment() {
        Bundle args = new Bundle();
        //args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        setArguments(args);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pop_growth_generator, container, false);
        return rootView;
    }
}
