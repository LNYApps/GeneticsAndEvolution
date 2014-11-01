package com.lnyapps.geneticsandevolution.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.lnyapps.geneticsandevolution.MainActivity;
import com.lnyapps.geneticsandevolution.R;
import com.lnyapps.geneticsandevolution.fragments.problemgeneratorfragments.BreederGeneratorFragment;
import com.lnyapps.geneticsandevolution.fragments.problemgeneratorfragments.GeneticCrossMappingGeneratorFragment;
import com.lnyapps.geneticsandevolution.fragments.problemgeneratorfragments.HardyWeinbergGeneratorFragment;
import com.lnyapps.geneticsandevolution.fragments.problemgeneratorfragments.PopGrowthGeneratorFragment;

/**
 * Created by Jonathan Tseng on 10/31/2014.
 */
public class SingleProblemGeneratorFragment extends Fragment {

    public SingleProblemGeneratorFragment() {
        Bundle args = new Bundle();
        //args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        setArguments(args);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_singleproblemgenerator, container, false);
        setUpSpinner(rootView);
        return rootView;
    }

    private void setUpSpinner(View root) {
        final Spinner spinner = (Spinner) root.findViewById(R.id.spg_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this.getActivity(),
                R.array.spg_spinner_array,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long l) {
                String item = getResources().getStringArray(R.array.spg_spinner_array)[pos];

                BreederGeneratorFragment breederFragment = (BreederGeneratorFragment) getChildFragmentManager().findFragmentById(R.id.spg_fragment_breeder);
                GeneticCrossMappingGeneratorFragment crossmapFragment = (GeneticCrossMappingGeneratorFragment) getChildFragmentManager().findFragmentById(R.id.spg_fragment_crossmapping);
                HardyWeinbergGeneratorFragment hardyweinbergFragment = (HardyWeinbergGeneratorFragment) getChildFragmentManager().findFragmentById(R.id.spg_fragment_hardyweinberg);
                PopGrowthGeneratorFragment popgrowthFragment = (PopGrowthGeneratorFragment) getChildFragmentManager().findFragmentById(R.id.spg_fragment_popgrowth);

                breederFragment.destroyFragment();
                crossmapFragment.destroyFragment();
                hardyweinbergFragment.destroyFragment();
                popgrowthFragment.destroyFragment();

                if (item.equals(getString(R.string.breeder_eq_heritability))) {
                    breederFragment.createFragment();
                } else if (item.equals(getString(R.string.hardy_weinberg))) {
                    hardyweinbergFragment.createFragment();
                } else if (item.equals(getString(R.string.pop_growth))) {
                    popgrowthFragment.createFragment();
                } else if (item.equals(getString(R.string.genetic_cross_mapping))) {
                    crossmapFragment.createFragment();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getActivity().getApplicationContext(), "Nothing Selected", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(getResources().getString(R.string.problem_generator));
    }

}
