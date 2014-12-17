package com.lnyapps.geneticsandevolution.crosssimulator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lnyapps.geneticsandevolution.R;

/**
 * Created by jonathantseng on 12/17/14.
 */
public class CrossSetUpFragment extends CrossSimulatorSubFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_crosssimulator_setup, container, false);
        Button nextButton = (Button) rootView.findViewById(R.id.cs_setup_button_next);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myParent.switchToSimulator();
            }
        });
        return rootView;
    }

}
