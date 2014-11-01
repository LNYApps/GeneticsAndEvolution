package com.lnyapps.geneticsandevolution.fragments.problemsolverfragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lnyapps.geneticsandevolution.R;

/**
 * Created by Alex on 10/31/2014.
 */
public class HardyWeinbergFragment extends Fragment {

    public HardyWeinbergFragment() {
        Bundle args = new Bundle();
        //args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        setArguments(args);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_hardy_weinberg, container, false);
       // EditText AA = (EditText) rootView.findViewById(R.id.AA_count);
       // EditText Aa = (EditText) rootView.findViewById(R.id.Aa_count);
       // EditText aa = (EditText) rootView.findViewById(R.id.aa_count);
        return rootView;
    }

    public void destroyFragment() {
        getChildFragmentManager().beginTransaction().hide(this).commit();
    }

    public void createFragment() {
        getChildFragmentManager().beginTransaction().show(this).commit();
    }

}
