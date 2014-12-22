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
import android.widget.TextView;

import com.lnyapps.geneticsandevolution.R;
import com.lnyapps.geneticsandevolution.crosssimulator.inheritance.InheritanceType;
import com.lnyapps.geneticsandevolution.crosssimulator.organisms.Organism;
import com.lnyapps.geneticsandevolution.crosssimulator.organisms.OrganismManager;

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
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_crosssimsetup, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

}
