package com.lnyapps.geneticsandevolution.crosssimulator;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.lnyapps.geneticsandevolution.MainActivity;
import com.lnyapps.geneticsandevolution.R;

/**
 * Created by jonathantseng on 12/17/14.
 */
public abstract class CrossSimulatorSubFragment extends Fragment {

    protected CrossSimulatorParentFragment mParent;

    public void attachParent(CrossSimulatorParentFragment parent) {
        mParent = parent;
    }

    public void destroyFragment() {
        getChildFragmentManager().beginTransaction().hide(this).commit();
    }

    public void createFragment() {
        getChildFragmentManager().beginTransaction().show(this).commit();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(getResources().getString(R.string.cross_simulator));
    }


}
