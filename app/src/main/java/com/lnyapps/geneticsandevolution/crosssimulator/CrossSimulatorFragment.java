package com.lnyapps.geneticsandevolution.crosssimulator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lnyapps.geneticsandevolution.R;
import com.lnyapps.geneticsandevolution.crosssimulator.inheritance.AutosomalInheritance;
import com.lnyapps.geneticsandevolution.crosssimulator.inheritance.InheritanceType;
import com.lnyapps.geneticsandevolution.crosssimulator.inheritance.XlinkedInheritance;
import com.lnyapps.geneticsandevolution.crosssimulator.organisms.Organism;
import com.lnyapps.geneticsandevolution.crosssimulator.organisms.OrganismManager;

/**
 * Created by jonathantseng on 12/17/14.
 */
public class CrossSimulatorFragment extends CrossSimulatorSubFragment {

    private TextView mGenotypeFemale;
    private TextView mGenotypeMale;
    private TextView mGenotypeFemaleFirstTrait;
    private TextView mGenotypeFemaleSecondTrait;
    private TextView mGenotypeFemaleThirdTrait;
    private TextView mGenotypeFemaleFourthTrait;

    private TextView mGenotypeMaleFirstTrait;
    private TextView mGenotypeMaleSecondTrait;
    private TextView mGenotypeMaleThirdTrait;
    private TextView mGenotypeMaleFourthTrait;
    private ImageView mImageFemale;
    private ImageView mImageMale;

    private ImageView child11;
    private ImageView child12;
    private ImageView child13;
    private ImageView child14;
    private ImageView child21;
    private ImageView child22;
    private ImageView child23;
    private ImageView child24;
    private ImageView child31;
    private ImageView child32;
    private ImageView child33;
    private ImageView child34;
    private ImageView child41;
    private ImageView child42;
    private ImageView child43;
    private ImageView child44;

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

        mGenotypeFemaleFirstTrait = (TextView) root.findViewById(R.id.cs_sim_text_genotype_female_first_trait);
        mGenotypeFemaleSecondTrait = (TextView) root.findViewById(R.id.cs_sim_text_genotype_female_second_trait);
        mGenotypeFemaleThirdTrait = (TextView) root.findViewById(R.id.cs_sim_text_genotype_female_third_trait);
        mGenotypeFemaleFourthTrait = (TextView) root.findViewById(R.id.cs_sim_text_genotype_female_fourth_trait);

        mGenotypeMaleFirstTrait = (TextView) root.findViewById(R.id.cs_sim_text_genotype_male_first_trait);
        mGenotypeMaleSecondTrait = (TextView) root.findViewById(R.id.cs_sim_text_genotype_male_second_trait);
        mGenotypeMaleThirdTrait = (TextView) root.findViewById(R.id.cs_sim_text_genotype_male_third_trait);
        mGenotypeMaleFourthTrait = (TextView) root.findViewById(R.id.cs_sim_text_genotype_male_fourth_trait);

        child11 = (ImageView) root.findViewById(R.id.cs_sim_image_1_1);
        child12 = (ImageView) root.findViewById(R.id.cs_sim_image_1_2);
        child13 = (ImageView) root.findViewById(R.id.cs_sim_image_1_3);
        child14 = (ImageView) root.findViewById(R.id.cs_sim_image_1_4);
        child21 = (ImageView) root.findViewById(R.id.cs_sim_image_2_1);
        child22 = (ImageView) root.findViewById(R.id.cs_sim_image_2_2);
        child23 = (ImageView) root.findViewById(R.id.cs_sim_image_2_3);
        child24 = (ImageView) root.findViewById(R.id.cs_sim_image_2_4);
        child31 = (ImageView) root.findViewById(R.id.cs_sim_image_3_1);
        child32 = (ImageView) root.findViewById(R.id.cs_sim_image_3_2);
        child33 = (ImageView) root.findViewById(R.id.cs_sim_image_3_3);
        child34 = (ImageView) root.findViewById(R.id.cs_sim_image_3_4);
        child41 = (ImageView) root.findViewById(R.id.cs_sim_image_4_1);
        child42 = (ImageView) root.findViewById(R.id.cs_sim_image_4_2);
        child43 = (ImageView) root.findViewById(R.id.cs_sim_image_4_3);
        child44 = (ImageView) root.findViewById(R.id.cs_sim_image_4_4);

    }

    // TODO initialize the view based on the argument wrapper
    public void loadCrossSimulatorArgs(CrossSimulatorArgs args) {
        mInheritanceType = args.getInheritanceType();
        mMale = args.getMale();
        mFemale = args.getFemale();

        mImageMale.setImageDrawable(OrganismManager.getDrawableOrganism(mMale));
        mImageFemale.setImageDrawable(OrganismManager.getDrawableOrganism(mFemale));
        mGenotypeMale.setText(mMale.getGenotype().toString());
        mGenotypeFemale.setText(mFemale.getGenotype().toString());
        mGenotypeFemaleFirstTrait.setText(mFemale.getGenotype().toString().substring(0,1));
        mGenotypeFemaleSecondTrait.setText(mFemale.getGenotype().toString().substring(2,3));
        mGenotypeMaleFirstTrait.setText(mMale.getGenotype().toString().substring(0,1));
        mGenotypeMaleSecondTrait.setText(mMale.getGenotype().toString().substring(2,3));

        //TODO: update the images and textviews based on the type of inheritance
        if(mInheritanceType instanceof XlinkedInheritance){

        }
        if(mInheritanceType instanceof AutosomalInheritance){

        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_crosssimsetup, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

}
