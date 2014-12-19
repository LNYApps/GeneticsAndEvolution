package com.lnyapps.geneticsandevolution.crosssimulator;

import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.lnyapps.geneticsandevolution.R;
import com.lnyapps.geneticsandevolution.crosssimulator.inheritance.AutosomalInheritance;
import com.lnyapps.geneticsandevolution.crosssimulator.inheritance.Genotype;
import com.lnyapps.geneticsandevolution.crosssimulator.inheritance.InheritanceType;
import com.lnyapps.geneticsandevolution.crosssimulator.inheritance.Trait;
import com.lnyapps.geneticsandevolution.crosssimulator.inheritance.XlinkedInheritance;
import com.lnyapps.geneticsandevolution.crosssimulator.organisms.Organism;
import com.lnyapps.geneticsandevolution.crosssimulator.organisms.OrganismManager;
import com.lnyapps.geneticsandevolution.crosssimulator.organisms.OrganismType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonathantseng on 12/17/14.
 */
public class CrossSetUpFragment extends CrossSimulatorSubFragment {

    private Button mNextButton;
    private Spinner mOrganismSpinner;
    private Switch mNumTraitsSwitch;
    private Switch mInheritanceSwitch;
    private ImageView mImageView1;
    private ImageView mImageView2;
    private Spinner mGenotypeSpinner1;
    private Spinner mGenotypeSpinner2;
    private ArrayAdapter<Spanned> mGenotypeAdapter1;
    private ArrayAdapter<Spanned> mGenotypeAdapter2;

    private TextView mTrait1Name;
    private TextView mTrait1Dominant;
    private TextView mTrait1Recessive;
    private TextView mTrait2Name;
    private TextView mTrait2Dominant;
    private TextView mTrait2Recessive;

    private int mNumTraits;
    private InheritanceType mInheritanceType;
    private OrganismType mOrganismType;
    private Genotype mFemaleGenotype;
    private Genotype mMaleGenotype;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_crosssimulator_setup, container, false);
        mNumTraits = 1;
        mInheritanceType = new AutosomalInheritance();
        mOrganismType = OrganismType.PEA;
        mMaleGenotype = findMaleGenotypes().get(0);
        mFemaleGenotype = findFemaleGenotypes().get(0);
        initViewReferences(rootView);
        setUpSpinner();
        setUpButton();
        setUpNumTraitsSwitch();
        setUpInheritanceSwitch();
        setUpGenotypeSpinners();
        initDefaults();
        return rootView;
    }

    private void initDefaults() {
        // ensures that checked changed listener is called
        mNumTraitsSwitch.setChecked(false);
        mInheritanceSwitch.setChecked(false);
        mNumTraitsSwitch.setChecked(true);
        mInheritanceSwitch.setChecked(true);
        mOrganismSpinner.setSelection(0);
    }

    private void updateImage() {
        mImageView1.setImageDrawable(OrganismManager.getDrawableOrganism(mOrganismType, mFemaleGenotype));
        mImageView2.setImageDrawable(OrganismManager.getDrawableOrganism(mOrganismType, mMaleGenotype));
    }

    private void updateTable() {
        mTrait1Name.setText(mOrganismType.getFirstTrait().toString());
        mTrait1Dominant.setText(String.format("%s (%s)", mOrganismType.getFirstTrait().getDominantDescription(), mOrganismType.getFirstTrait().getDominant()));
        mTrait1Recessive.setText(String.format("%s (%s)", mOrganismType.getFirstTrait().getRecessiveDescription(), mOrganismType.getFirstTrait().getRecessive()));
        if (mNumTraits == 1) {
            mTrait2Name.setText("");
            mTrait2Dominant.setText("");
            mTrait2Recessive.setText("");
        } else {
            mTrait2Name.setText(mOrganismType.getSecondTrait().toString());
            mTrait2Dominant.setText(String.format("%s (%s)", mOrganismType.getSecondTrait().getDominantDescription(), mOrganismType.getSecondTrait().getDominant()));
            mTrait2Recessive.setText(String.format("%s (%s)", mOrganismType.getSecondTrait().getRecessiveDescription(), mOrganismType.getSecondTrait().getRecessive()));
        }
    }

    private void updateView() {
        updateTable();
        updateGenotypeSpinners();
        updateImage();
    }

    // TODO actually get correct list of genotypes (currently has duplicates, need to get rid of repeats)
    private void updateGenotypeSpinners() {
        mGenotypeAdapter1 = new ArrayAdapter<Spanned>(this.getActivity(), android.R.layout.simple_spinner_item);
        mGenotypeAdapter2 = new ArrayAdapter<Spanned>(this.getActivity(), android.R.layout.simple_spinner_item);
        List<Spanned> femaleGenotypes = genotypeToString(findFemaleGenotypes());
        List<Spanned> maleGenotypes = genotypeToString(findMaleGenotypes());
        mGenotypeAdapter1.addAll(femaleGenotypes);
        mGenotypeAdapter2.addAll(maleGenotypes);
        mGenotypeSpinner1.setAdapter(mGenotypeAdapter1);
        mGenotypeSpinner2.setAdapter(mGenotypeAdapter2);
    }

    private List<Genotype> findFemaleGenotypes() {
        Trait[] traits = (mNumTraits == 1) ? new Trait[]{mOrganismType.getFirstTrait()} : new Trait[]{mOrganismType.getFirstTrait(), mOrganismType.getSecondTrait()};
        return mInheritanceType.getFemaleGenotypes(traits);
    }

    private List<Genotype> findMaleGenotypes() {
        Trait[] traits = (mNumTraits == 1) ? new Trait[]{mOrganismType.getFirstTrait()} : new Trait[]{mOrganismType.getFirstTrait(), mOrganismType.getSecondTrait()};
        return mInheritanceType.getMaleGenotypes(traits);
    }

    private List<Spanned> genotypeToString(List<Genotype> genotypes) {
        List<Spanned> spannedStrings = new ArrayList<Spanned>();
        for (Genotype genotype : genotypes) {
            spannedStrings.add(Html.fromHtml(genotype.toString()));
        }
        return spannedStrings;
    }

    private void setUpGenotypeSpinners() {
        mGenotypeAdapter1 = new ArrayAdapter<Spanned>(this.getActivity(), android.R.layout.simple_spinner_item);
        mGenotypeAdapter2 = new ArrayAdapter<Spanned>(this.getActivity(), android.R.layout.simple_spinner_item);
        mGenotypeSpinner1.setAdapter(mGenotypeAdapter1);
        mGenotypeSpinner2.setAdapter(mGenotypeAdapter2);
        mGenotypeSpinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mFemaleGenotype = findFemaleGenotypes().get(i);
                updateImage();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                mFemaleGenotype = findFemaleGenotypes().get(0);
                updateImage();
            }
        });
        mGenotypeSpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mMaleGenotype = findMaleGenotypes().get(i);
                updateImage();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                mMaleGenotype = findMaleGenotypes().get(0);
                updateImage();
            }
        });
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
                updateView();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                mOrganismSpinner.setSelection(0, true);
            }
        });
    }

    private void setUpButton() {
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mParent.switchToSimulator(gatherInputs());
            }
        });
    }

    private CrossSimulatorArgs gatherInputs() {
        return new CrossSimulatorArgs(new Organism(mOrganismType, mMaleGenotype), new Organism(mOrganismType, mFemaleGenotype));
    }

    private void setUpNumTraitsSwitch() {
        mNumTraitsSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mNumTraits = (b) ? 1 : 2;
                updateView();
            }
        });
    }

    private void setUpInheritanceSwitch() {
        mInheritanceSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mInheritanceType = (b) ? new AutosomalInheritance() : new XlinkedInheritance();
                updateView();
            }
        });
    }

    private void initViewReferences(View root) {
        mNextButton = (Button) root.findViewById(R.id.cs_setup_button_next);
        mOrganismSpinner = (Spinner) root.findViewById(R.id.cs_setup_spinner_organisms);
        mNumTraitsSwitch = (Switch) root.findViewById(R.id.cs_setup_switch_numtraits);
        mInheritanceSwitch = (Switch) root.findViewById(R.id.cs_setup_switch_inheritance);
        mImageView1 = (ImageView) root.findViewById(R.id.cs_setup_imageview1);
        mImageView2 = (ImageView) root.findViewById(R.id.cs_setup_imageview2);
        mGenotypeSpinner1 = (Spinner) root.findViewById(R.id.cs_setup_genotype1);
        mGenotypeSpinner2 = (Spinner) root.findViewById(R.id.cs_setup_genotype2);

        mTrait1Name = (TextView) root.findViewById(R.id.cs_setup_text_trait1name);
        mTrait1Dominant = (TextView) root.findViewById(R.id.cs_setup_text_trait1dominant);
        mTrait1Recessive = (TextView) root.findViewById(R.id.cs_setup_text_trait1recessive);
        mTrait2Name = (TextView) root.findViewById(R.id.cs_setup_text_trait2name);
        mTrait2Dominant = (TextView) root.findViewById(R.id.cs_setup_text_trait2dominant);
        mTrait2Recessive = (TextView) root.findViewById(R.id.cs_setup_text_trait2recessive);
    }

}
