package com.lnyapps.geneticsandevolution.crosssimulator;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;

import com.lnyapps.geneticsandevolution.MainActivity;
import com.lnyapps.geneticsandevolution.R;

/**
 * Created by Alex on 11/25/2014.
 */
public class CrossSimulatorFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_crosssimulator, container, false);
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(getResources().getString(R.string.cross_simulator));
    }
}
