package com.lnyapps.geneticsandevolution.allelefreak;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.lnyapps.geneticsandevolution.MainActivity;
import com.lnyapps.geneticsandevolution.R;

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
    private Button alleleGraphButton;

    public AlleleGraphInputsFragment() {
        Bundle args = new Bundle();
        //args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        setArguments(args);
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
        alleleGraphButton = (Button) rootView.findViewById(R.id.allele_freak_button_graph);
        setUpGenerateButton();
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(getResources().getString(R.string.allele_freak));
    }

    private void setUpGenerateButton() {
        alleleGraphButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //graph the stuff based off the params
            }
        });
    }


}
