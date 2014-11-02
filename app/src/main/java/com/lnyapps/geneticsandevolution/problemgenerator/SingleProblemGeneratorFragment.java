package com.lnyapps.geneticsandevolution.problemgenerator;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
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
import com.lnyapps.geneticsandevolution.problems.BreederProblem;
import com.lnyapps.geneticsandevolution.problems.CrossMappingProblem;
import com.lnyapps.geneticsandevolution.problems.GenEvolProblem;
import com.lnyapps.geneticsandevolution.problems.HardyWeinbergProblem;
import com.lnyapps.geneticsandevolution.problems.PopGrowthProblem;

/**
 * Created by Jonathan Tseng on 10/31/2014.
 */
public class SingleProblemGeneratorFragment extends Fragment {

    private GenEvolProblem mCurrentProblem;
    private Button mGenerateButton;
    private Button mShowAnswerButton;
    private TextView mGivenText;
    private TextView mSolveText;

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
        mGenerateButton = (Button) rootView.findViewById(R.id.spg_button_generate);
        mShowAnswerButton = (Button) rootView.findViewById(R.id.spg_button_showanswer);
        mGivenText = (TextView) rootView.findViewById(R.id.spg_textview_given);
        mSolveText = (TextView) rootView.findViewById(R.id.spg_textview_solve);
        setUpGenerateButton();
        setUpShowAnswerButton();
        return rootView;
    }

    private void setUpGenerateButton() {
        mGenerateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mShowAnswerButton.setActivated(true);
                mCurrentProblem.randomValues();
                mSolveText.setText(mCurrentProblem.emptySolveString());
                mGivenText.setText(mCurrentProblem.nonEmptyGivenString());
            }
        });
    }

    private void setUpShowAnswerButton() {
        mShowAnswerButton.setActivated(false);
        mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSolveText.setText(mCurrentProblem.solution());
            }
        });
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
                if (item.equals(getString(R.string.breeder_eq_heritability))) {
                    mCurrentProblem = new BreederProblem();
                } else if (item.equals(getString(R.string.hardy_weinberg))) {
                    mCurrentProblem = new HardyWeinbergProblem();
                } else if (item.equals(getString(R.string.pop_growth))) {
                    mCurrentProblem = new PopGrowthProblem();
                } else {
                    mCurrentProblem = new CrossMappingProblem();
                }
                mGivenText.setText(mCurrentProblem.emptyGivenString());
                mSolveText.setText(mCurrentProblem.emptySolveString());
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
