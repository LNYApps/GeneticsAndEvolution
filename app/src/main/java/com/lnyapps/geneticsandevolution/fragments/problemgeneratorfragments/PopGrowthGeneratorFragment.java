package com.lnyapps.geneticsandevolution.fragments.problemgeneratorfragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lnyapps.geneticsandevolution.R;

/**
 * Created by Jonathan Tseng on 11/1/2014.
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

    public void destroyFragment() {
        getChildFragmentManager().beginTransaction().hide(this).commit();
    }

    public void createFragment() {
        getChildFragmentManager().beginTransaction().show(this).commit();
    }


}
