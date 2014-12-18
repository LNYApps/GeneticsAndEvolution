package com.lnyapps.geneticsandevolution.crosssimulator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TableLayout;
import android.widget.Toast;

import com.lnyapps.geneticsandevolution.R;
import com.lnyapps.geneticsandevolution.crosssimulator.inheritance.InheritanceType;
import com.lnyapps.geneticsandevolution.crosssimulator.organisms.OrganismType;

/**
 * Created by jonathantseng on 12/17/14.
 */
public class CrossSetUpFragment extends CrossSimulatorSubFragment {

    private Button mNextButton;
    private Spinner mOrganismSpinner;
    private TableLayout mTable;
    private Switch mNumTraitsSwitch;
    private Switch mInheritanceSwitch;
    private ImageView mImageView1;
    private ImageView mImageView2;

    private OrganismType mOrganismType;

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

    private void updateImage() {
        //TODO change "ddd" depending on actual traits
        String uri = "drawable/" + mOrganismType.getImageName() + "_" + "ddd";
        int imageResource = getResources().getIdentifier(uri, null, getActivity().getPackageName());
        mImageView1.setImageDrawable(getResources().getDrawable(imageResource));
        mImageView2.setImageDrawable(getResources().getDrawable(imageResource));
    }

    private void setUpSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this.getActivity(),
                R.array.cs_organisms_array,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mOrganismSpinner.setAdapter(adapter);
        mOrganismSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long l) {
                String organism = getResources().getStringArray(R.array.cs_organisms_array)[pos];
                mOrganismType = OrganismType.createOrganism(organism);
                updateImage();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getActivity().getApplicationContext(), "Nothing was selected", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setUpButton() {
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (allFieldsReady()) {
                    mParent.switchToSimulator(gatherInputs());
                }
            }
        });
    }

    // TODO return true if all fields have been filled out and ready to move on to next
    private boolean allFieldsReady() {
        return true;
    }

    // TODO return the CrossSimulatorArgs wrapper for the info needed to do an actual simulation
    private CrossSimulatorArgs gatherInputs() {
        return null;
    }

    // TODO change the number of traits
    private void setUpNumTraitsSwitch() {

    }

    // TODO change the inheritance type
    private void setUpInheritanceSwitch() {

    }

    private void initViewReferences(View root) {
        mNextButton = (Button) root.findViewById(R.id.cs_setup_button_next);
        mOrganismSpinner = (Spinner) root.findViewById(R.id.cs_setup_spinner_organisms);
        mTable = (TableLayout) root.findViewById(R.id.cs_setup_table);
        mNumTraitsSwitch = (Switch) root.findViewById(R.id.cs_setup_switch_numtraits);
        mInheritanceSwitch = (Switch) root.findViewById(R.id.cs_setup_switch_inheritance);
        mImageView1 = (ImageView) root.findViewById(R.id.cs_setup_imageview1);
        mImageView2 = (ImageView) root.findViewById(R.id.cs_setup_imageview2);
    }

}
