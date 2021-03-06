package com.lnyapps.geneticsandevolution.crosssimulator;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        mRatiosTable.removeAllViews();
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
            cell.setText(Html.fromHtml(c.toString()));
            cell.setGravity(Gravity.CENTER_HORIZONTAL);
            cell.setTypeface(null, Typeface.BOLD);
            header.addView(cell);
        }
        mPunnettTable.addView(header);
        for (int i = 0; i < mFemaleChromosomes.size(); i++) {
            TableRow row = new TableRow(getActivity());
            TextView rowLabel = new TextView(getActivity());
            rowLabel.setText(Html.fromHtml(mFemaleChromosomes.get(i).toString()));
            rowLabel.setGravity(Gravity.CENTER);
            rowLabel.setTypeface(null, Typeface.BOLD);
            row.addView(rowLabel);
            for (Organism organism : mPunnett.get(i)) {
                LinearLayout cell = new LinearLayout(getActivity());
                cell.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                cell.setOrientation(LinearLayout.VERTICAL);
                TextView label = new TextView(getActivity());
                label.setGravity(Gravity.CENTER_HORIZONTAL);
                label.setText(Html.fromHtml(organism.getGenotype().toString()));
                ImageView image = new ImageView(getActivity());
                image.setImageDrawable(OrganismManager.getDrawableOrganism(organism));
                image.setScaleType(ImageView.ScaleType.FIT_XY);
                int sideLength = (mPunnett.get(0).size() == 4) ? (int) getResources().getDimension(R.dimen.fragment_cs_sim_punnett_imagesize_small) :
                        (int) getResources().getDimension(R.dimen.fragment_cs_sim_punnett_imagesize_large);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(sideLength, sideLength);
                layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
                image.setLayoutParams(layoutParams);
                cell.addView(image);
                cell.addView(label);
                row.addView(cell);
            }
            mPunnettTable.addView(row);
        }
        mPunnettTable.setColumnStretchable(0, false);
    }

    private void updateRatioTable() {
        TextView title = new TextView(getActivity());
        title.setText("Ratios");
        title.setTypeface(null, Typeface.BOLD);
        mRatiosTable.addView(title);
        Map<Organism, Integer> ratioMap = countPunnettRatios();
        String[] order = new String[] {
                "dd",
                "dr",
                "rd",
                "rr"
        };
        for (String type : order) {
            for (Organism organism : ratioMap.keySet()) {
                if (organism.getGenotype().genotypicDominance().equals(type)) {
                    LinearLayout row = new LinearLayout(getActivity());
                    row.setOrientation(LinearLayout.HORIZONTAL);
                    ImageView view = new ImageView(getActivity());
                    view.setImageDrawable(OrganismManager.getDrawableOrganism(organism));
                    view.setScaleType(ImageView.ScaleType.FIT_XY);
                    int sideLength = (int) getResources().getDimension(R.dimen.fragment_cs_sim_ratio_imagesize);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(sideLength, sideLength);
                    view.setLayoutParams(layoutParams);
                    row.addView(view);
                    TextView label = new TextView(getActivity());
                    label.setText(String.format("%d/%d", ratioMap.get(organism), mPunnett.size() * mPunnett.get(0).size()));
                    row.addView(label);
                    mRatiosTable.addView(row);
                }
            }
        }

    }

    private Map<Organism, Integer> countPunnettRatios() {
        Map<Organism, Integer> ratioMap = new HashMap<Organism, Integer>();
        for (List<Organism> organismList : mPunnett) {
            for (Organism organism : organismList) {
                if (!ratioMap.containsKey(organism)) {
                    ratioMap.put(organism, 0);
                }
                ratioMap.put(organism, ratioMap.get(organism) + 1);
            }
        }
        return ratioMap;
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
        mGenotypeMale.setText(Html.fromHtml(mMale.getGenotype().toString()));
        mGenotypeFemale.setText(Html.fromHtml(mFemale.getGenotype().toString()));

        updatePunnettTable();
        updateRatioTable();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_crosssimsetup, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

}
