package com.lnyapps.geneticsandevolution.problemsolver;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.lnyapps.geneticsandevolution.MainActivity;
import com.lnyapps.geneticsandevolution.R;

import java.util.HashMap;

/**
 * Created by Jonathan Tseng on 10/31/2014.
 */
public class ProblemSolverFragment extends Fragment {

    private TextView mSolutionTextView;
    private Button mSolveButton;
    private ProblemSolverInputFragment mCurrentFragment;
    private HashMap<String, ProblemSolverInputFragment> mFragmentMapping;

    public ProblemSolverFragment() {
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_problemsolver, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.ps_action_clearinputs) {
            mCurrentFragment.clearInputs();
            clearSolutionTextView();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setUpSelectFunctionSpinner(View root){
        final Spinner spinner = (Spinner) root.findViewById(R.id.ps_spinner_problemtype);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this.getActivity(),
                R.array.problem_solver_functions_array,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long l) {
                for (ProblemSolverInputFragment fragment : mFragmentMapping.values()) {
                    fragment.destroyFragment();
                }

                String item = getResources().getStringArray(R.array.spg_spinner_array)[pos];
                ProblemSolverInputFragment nextFragment = mFragmentMapping.get(item);
                nextFragment.createFragment();

                if (mCurrentFragment == null) {
                    mCurrentFragment = nextFragment;
                }
                if (nextFragment != mCurrentFragment) {
                    mCurrentFragment.clearInputs();
                    mCurrentFragment = nextFragment;
                    clearSolutionTextView();
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
        setUpFragments();
        mSolutionTextView = (TextView) rootView.findViewById(R.id.ps_textview_solution);
        setUpSelectFunctionSpinner(rootView);
        setUpSolveButton(rootView);
        return rootView;
    }

    private void setUpFragments() {
        BreederFragment breederFragment = (BreederFragment) getChildFragmentManager().findFragmentById(R.id.ps_fragment_breeder);
        GeneticCrossMappingFragment crossmapFragment = (GeneticCrossMappingFragment) getChildFragmentManager().findFragmentById(R.id.ps_fragment_crossmapping);
        HardyWeinbergFragment hardyweinbergFragment = (HardyWeinbergFragment) getChildFragmentManager().findFragmentById(R.id.ps_fragment_hardyweinberg);
        PopGrowthFragment popgrowthFragment = (PopGrowthFragment) getChildFragmentManager().findFragmentById(R.id.ps_fragment_popgrowth);

        mFragmentMapping = new HashMap<String, ProblemSolverInputFragment>();
        mFragmentMapping.put(getString(R.string.breeder_eq_heritability), breederFragment);
        mFragmentMapping.put(getString(R.string.hardy_weinberg), hardyweinbergFragment);
        mFragmentMapping.put(getString(R.string.pop_growth), popgrowthFragment);
        mFragmentMapping.put(getString(R.string.genetic_cross_mapping), crossmapFragment);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(getResources().getString(R.string.problem_solver));
    }

    private void setUpSolveButton(View root) {
        mSolveButton = (Button) root.findViewById(R.id.ps_button_solve);
        mSolveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentFragment.clearInputFocus();
                if (mCurrentFragment.canSolve()) {
                    updateSolutionTextView(mCurrentFragment.solve());
                } else {
                    Toast.makeText(getActivity(), "Invalid inputs", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void clearSolutionTextView() {
        mSolutionTextView.setText("");
        mSolutionTextView.setVisibility(View.INVISIBLE);
    }

    public void updateSolutionTextView(String solution) {
        mSolutionTextView.setText(solution);
        mSolutionTextView.setVisibility(View.VISIBLE);
    }

}
