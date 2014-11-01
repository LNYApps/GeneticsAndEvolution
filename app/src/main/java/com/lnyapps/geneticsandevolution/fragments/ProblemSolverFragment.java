package com.lnyapps.geneticsandevolution.fragments;

import android.support.v4.app.Fragment;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.lnyapps.geneticsandevolution.MainActivity;
import com.lnyapps.geneticsandevolution.R;
import com.lnyapps.geneticsandevolution.fragments.problemsolverfragments.BreederFragment;
import com.lnyapps.geneticsandevolution.fragments.problemsolverfragments.GeneticCrossMappingFragment;
import com.lnyapps.geneticsandevolution.fragments.problemsolverfragments.HardyWeinbergFragment;
import com.lnyapps.geneticsandevolution.fragments.problemsolverfragments.PopGrowthFragment;

/**
 * Created by Jonathan Tseng on 10/31/2014.
 */
public class ProblemSolverFragment extends Fragment {

    public ProblemSolverFragment() {
        Bundle args = new Bundle();
        //args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        setArguments(args);
    }

    private void setUpSelectFunctionSpinner(View root){
        final Spinner spinner = (Spinner) root.findViewById(R.id.problem_solver_function_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this.getActivity(),
                R.array.problem_solver_functions_array,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long l) {
                String item = getResources().getStringArray(R.array.spg_spinner_array)[pos];

                BreederFragment breederFragment = (BreederFragment) getChildFragmentManager().findFragmentById(R.id.ps_fragment_breeder);
                GeneticCrossMappingFragment crossmapFragment = (GeneticCrossMappingFragment) getChildFragmentManager().findFragmentById(R.id.ps_fragment_crossmapping);
                HardyWeinbergFragment hardyweinbergFragment = (HardyWeinbergFragment) getChildFragmentManager().findFragmentById(R.id.ps_fragment_hardyweinberg);
                PopGrowthFragment popgrowthFragment = (PopGrowthFragment) getChildFragmentManager().findFragmentById(R.id.ps_fragment_popgrowth);

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
                Toast.makeText(getActivity().getApplicationContext(), "Nothing was selected", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_problemsolver, container, false);
        setUpSelectFunctionSpinner(rootView);
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(getResources().getString(R.string.problem_solver));
    }

}
