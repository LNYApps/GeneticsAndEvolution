package com.lnyapps.geneticsandevolution.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.lnyapps.geneticsandevolution.MainActivity;
import com.lnyapps.geneticsandevolution.R;
import com.lnyapps.geneticsandevolution.fragments.problemsolverfragments.HardyWeinbergFragment;

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
                Toast.makeText(getActivity().getApplicationContext(), "wat", Toast.LENGTH_SHORT).show();
                FragmentManager fragmentManager = getChildFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                if (true) {
                    //Fragment inputFrag = new HardyWeinbergFragment();
                    //transaction.add(getId(), inputFrag);
                    //transaction.commit();
                }
                if (pos == 1) {

                }
                if (pos == 2) {

                }
                if (pos == 3) {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getActivity().getApplicationContext(), "Nothing", Toast.LENGTH_SHORT).show();
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
