package com.lnyapps.geneticsandevolution.allelefreak;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.github.mikephil.charting.data.Entry;
import com.lnyapps.geneticsandevolution.MainActivity;
import com.lnyapps.geneticsandevolution.R;

import java.util.ArrayList;

/**
 * Created by Alex on 11/30/2014.
 */
public class AlleleGraphInputsFragment extends Fragment {

    private EditText alleleInitFreq;
    private EditText alleleFitAA;
    private EditText alleleFitAa;
    private EditText alleleFitaa;
    private EditText allelePop;
    private EditText alleleInbreeding;
    private EditText alleleGenerations;
    private Button alleleGraphButton;
    private Button alleleClearButton;

    OnGraphSelectedListener mListener;



    public interface OnGraphSelectedListener {
        public void onGraphSelected(ArrayList<Entry> line);

        public void onClearSelected();
    }

    public AlleleGraphInputsFragment() {
        Bundle args = new Bundle();
        //args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        setArguments(args);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_allelefreak, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.allele_freak_guide) {
            //TODO: Test
            AlleleFreakHelpDialog dialog = new AlleleFreakHelpDialog();
            dialog.show(getActivity().getSupportFragmentManager(), "allele freak dialog");
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_allelegraphinputs, container, false);
        alleleInitFreq = (EditText) rootView.findViewById(R.id.allele_freak_edittext_intial_freq);
        alleleFitAA = (EditText) rootView.findViewById(R.id.allele_freak_edittext_fit_AA);
        alleleFitAa = (EditText) rootView.findViewById(R.id.allele_freak_edittext_fit_Aa);
        alleleFitaa = (EditText) rootView.findViewById(R.id.allele_freak_edittext_fit_aa);
        allelePop = (EditText) rootView.findViewById(R.id.allele_freak_edittext_pop);
        alleleInbreeding = (EditText) rootView.findViewById(R.id.allele_freak_edittext_inbreeding);
        alleleGenerations = (EditText) rootView.findViewById(R.id.allele_freak_edittext_generations);
        alleleGraphButton = (Button) rootView.findViewById(R.id.allele_freak_button_graph);
        alleleClearButton = (Button) rootView.findViewById(R.id.allele_freak_button_clear);


        //setting default values of inputs
        alleleInitFreq.setText("0.5");
        alleleFitAA.setText("1.0");
        alleleFitAa.setText("1.0");
        alleleFitaa.setText("1.0");
        allelePop.setText("0");
        alleleInbreeding.setText("0.0");
        alleleGenerations.setText("1000");
        setUpGenerateButton();
        setUpClearButton();
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(getResources().getString(R.string.allele_freak));
        try {
            mListener = (OnGraphSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnGraphSelectedListener");
        }
    }

    private void setUpGenerateButton() {
        alleleGraphButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //graph the stuff based off the params
                float initFreq = Float.parseFloat(alleleInitFreq.getText().toString());
                float AAfit = Float.parseFloat(alleleFitAA.getText().toString());
                float Aafit = Float.parseFloat(alleleFitAa.getText().toString());
                float aafit = Float.parseFloat(alleleFitaa.getText().toString());
                int pop = Integer.parseInt(allelePop.getText().toString());
                float inbreed = Float.parseFloat(alleleInbreeding.getText().toString());
                int gens = Integer.parseInt(alleleGenerations.getText().toString());

                GraphLine allele = new GraphLine(initFreq, AAfit, Aafit, aafit, pop, inbreed, gens);
                ArrayList<Entry> line = allele.createData();
                mListener.onGraphSelected(line);
            }
        });
    }

    private void setUpClearButton() {
        alleleClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onClearSelected();
            }
        });
    }

    /**
     * Implementing dialog box sub-class
     */
    public static class AlleleFreakHelpDialog extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(R.string.allele_freak_help)
                    .setTitle(R.string.allele_param_about);
            // Create the AlertDialog object and return it
            return builder.create();
        }
    }

}
