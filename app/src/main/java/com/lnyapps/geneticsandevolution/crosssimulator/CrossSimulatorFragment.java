package com.lnyapps.geneticsandevolution.crosssimulator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lnyapps.geneticsandevolution.R;

/**
 * Created by jonathantseng on 12/17/14.
 */
public class CrossSimulatorFragment extends CrossSimulatorSubFragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_crosssimulator_simulator, container, false);
        TextView t = (TextView) rootView.findViewById(R.id.hi);
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mParent.switchToSetup();
            }
        });
        return rootView;
    }

    // TODO initialize the view based on the argument wrapper
    public void loadCrossSimulatorArgs(CrossSimulatorArgs args) {

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_crosssimsetup, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }




}
