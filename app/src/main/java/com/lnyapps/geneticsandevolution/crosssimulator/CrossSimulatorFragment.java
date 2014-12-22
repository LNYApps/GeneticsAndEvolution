package com.lnyapps.geneticsandevolution.crosssimulator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.lnyapps.geneticsandevolution.R;
import com.lnyapps.geneticsandevolution.crosssimulator.inheritance.Genotype;
import com.lnyapps.geneticsandevolution.crosssimulator.inheritance.InheritanceType;
import com.lnyapps.geneticsandevolution.crosssimulator.inheritance.chromosome.Chromosome;
import com.lnyapps.geneticsandevolution.crosssimulator.organisms.Organism;
import com.lnyapps.geneticsandevolution.crosssimulator.organisms.OrganismManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonathantseng on 12/17/14.
 */
public class CrossSimulatorFragment extends CrossSimulatorSubFragment {

    private TextView mGenotypeFemale;
    private TextView mGenotypeMale;
    private ImageView mImageFemale;
    private ImageView mImageMale;

    private TableLayout mPunnettTable;
    private TableLayout mRatiosTable;

    private Organism mMale;
    private Organism mFemale;
    private InheritanceType mInheritanceType;
    private List<List<Organism>> mPunnett;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_crosssimulator_simulator, container, false);
        initViewReferences(rootView);
        Button backButton = (Button) rootView.findViewById(R.id.cs_sim_button_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mParent.switchToSetup();
            }
        });
        return rootView;
    }

    private void updatePunnettTable() {
        mPunnettTable.removeAllViews();
        calculatePunnett();
        createPunnettView();
    }

    private void calculatePunnett() {
        List<Chromosome> mMaleChromosomes = mMale.getPossibleChromosomes();
        List<Chromosome> mFemaleChromosomes = mFemale.getPossibleChromosomes();
        mPunnett = new ArrayList<List<Organism>>();
        for (Chromosome femaleChromosome : mFemaleChromosomes) {
            List<Organism> row = new ArrayList<Organism>();
            for (Chromosome maleChromosome : mMaleChromosomes) {
                Organism organism = new Organism(mMale.getType(),
                        new Genotype(femaleChromosome, maleChromosome),
                        mInheritanceType);
                row.add(organism);
            }
            mPunnett.add(row);
        }
    }

    private void createPunnettView() {
        List<Chromosome> mMaleChromosomes = mMale.getPossibleChromosomes();
        List<Chromosome> mFemaleChromosomes = mFemale.getPossibleChromosomes();

        TableRow header = new TableRow(getActivity());
        header.addView(new TextView(getActivity()));
        for (Chromosome c : mMaleChromosomes) {
            TextView cell = new TextView(getActivity());
            cell.setText(c.toString());
            header.addView(cell);
        }
        mPunnettTable.addView(header);
        for (int i = 0; i < mFemaleChromosomes.size(); i++) {
            TableRow row = new TableRow(getActivity());
            TextView label = new TextView(getActivity());
            label.setText(mFemaleChromosomes.get(i).toString());
            row.addView(label);
            for (Organism organism : mPunnett.get(i)) {
                TextView cell = new TextView(getActivity());
                cell.setText(organism.getGenotype().toString());
                row.addView(cell);
            }
            mPunnettTable.addView(row);
        }
    }

    private void updateRatioTable() {

    }

    private void initViewReferences(View root) {
        mGenotypeFemale = (TextView) root.findViewById(R.id.cs_sim_text_genotype_female);
        mGenotypeMale = (TextView) root.findViewById(R.id.cs_sim_text_genotype_male);
        mImageFemale = (ImageView) root.findViewById(R.id.cs_sim_image_female);
        mImageMale = (ImageView) root.findViewById(R.id.cs_sim_image_male);
        mPunnettTable = (TableLayout) root.findViewById(R.id.cs_sim_table_punnett);
        mRatiosTable = (TableLayout) root.findViewById(R.id.cs_sim_table_ratios);
    }

    public void loadCrossSimulatorArgs(CrossSimulatorArgs args) {
        mInheritanceType = args.getInheritanceType();
        mMale = args.getMale();
        mFemale = args.getFemale();

        mImageMale.setImageDrawable(OrganismManager.getDrawableOrganism(mMale));
        mImageFemale.setImageDrawable(OrganismManager.getDrawableOrganism(mFemale));
        mGenotypeMale.setText(mMale.getGenotype().toString());
        mGenotypeFemale.setText(mFemale.getGenotype().toString());

        updatePunnettTable();
        updateRatioTable();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_crosssimsetup, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

}
