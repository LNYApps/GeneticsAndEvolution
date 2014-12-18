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
 * Created by jonathantseng on 12/17/2014.
 */
public class CrossSimulatorParentFragment extends Fragment {

    private CrossSetUpFragment mSetUpFragment;
    private CrossSimulatorFragment mSimulatorFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_crosssimulator_parent, container, false);
        setUpFragments();
        return rootView;
    }

    private void setUpFragments() {
        mSetUpFragment = (CrossSetUpFragment) getChildFragmentManager().findFragmentById(R.id.cs_fragment_setup);
        mSimulatorFragment = (CrossSimulatorFragment) getChildFragmentManager().findFragmentById(R.id.cs_fragment_simulator);
        mSetUpFragment.attachParent(this);
        mSimulatorFragment.attachParent(this);
        switchToSetup();
    }

    public void switchToSetup() {
        mSetUpFragment.createFragment();
        mSimulatorFragment.destroyFragment();
    }

    public void switchToSimulator(CrossSimulatorArgs args) {
        mSetUpFragment.destroyFragment();
        mSimulatorFragment.loadCrossSimulatorArgs(args);
        mSimulatorFragment.createFragment();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(getResources().getString(R.string.cross_simulator));
    }
}
