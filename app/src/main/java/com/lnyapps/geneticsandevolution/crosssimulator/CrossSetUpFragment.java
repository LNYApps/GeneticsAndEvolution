package com.lnyapps.geneticsandevolution.crosssimulator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TableLayout;

import com.lnyapps.geneticsandevolution.R;
import com.lnyapps.geneticsandevolution.crosssimulator.inheritance.InheritanceType;

/**
 * Created by jonathantseng on 12/17/14.
 */
public class CrossSetUpFragment extends CrossSimulatorSubFragment {

    private Button mNextButton;
    private Spinner mOrganismSpinner;
    private TableLayout mTable;
    private Switch mNumTraitsSwitch;
    private Switch mInheritanceSwitch;

    private InheritanceType mInheritanceType;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_crosssimulator_setup, container, false);
        initViewReferences(rootView);
        setUpSpinner();
        setUpButton();
        setUpNumTraitsSwitch();
        setUpInheritanceSwitch();
        return rootView;
    }

    private void setUpSpinner() {

    }

    private void setUpButton() {

    }

    private void setUpNumTraitsSwitch() {

    }

    private void setUpInheritanceSwitch() {

    }

    private void initViewReferences(View root) {
        mNextButton = (Button) root.findViewById(R.id.cs_setup_button_next);
        mOrganismSpinner = (Spinner) root.findViewById(R.id.cs_setup_spinner_organisms);
        mTable = (TableLayout) root.findViewById(R.id.cs_setup_table);
        mNumTraitsSwitch = (Switch) root.findViewById(R.id.cs_setup_switch_numtraits);
        mInheritanceSwitch = (Switch) root.findViewById(R.id.cs_setup_switch_inheritance);
    }

}
