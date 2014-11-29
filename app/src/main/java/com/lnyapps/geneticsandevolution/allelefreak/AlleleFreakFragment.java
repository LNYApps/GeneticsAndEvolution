package com.lnyapps.geneticsandevolution.allelefreak;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;

import com.lnyapps.geneticsandevolution.MainActivity;
import com.lnyapps.geneticsandevolution.R;
import com.lnyapps.geneticsandevolution.problemgenerator.MultipleProblemGeneratorFragment;
import com.lnyapps.geneticsandevolution.problemgenerator.SingleProblemGeneratorFragment;

/**
 * Created by Alex on 11/24/2014.
 */
public class AlleleFreakFragment extends Fragment {

    ViewPager mViewPager;
    FragmentPagerAdapter mFragmentPagerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_allelefreak, container, false);
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(getResources().getString(R.string.allele_freak));
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        Context mContext;

        public SectionsPagerAdapter(Context context, android.support.v4.app.FragmentManager fragmentManager) {
            super(fragmentManager);
            mContext = context;
        }

        @Override
        public int getCount() {
            return 2;
        }

        //TODO: Change the fragments below in getItem
        //Look up graphing API and implement in new classes (need to create the two fragments)
        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            if (position == 0) {
                return new SingleProblemGeneratorFragment();
            } else {
                return new MultipleProblemGeneratorFragment();
            }
        }

        @Override
        public String getPageTitle(int position) {
            if (position == 0) {
                return getString(R.string.allele_graph_page);
            } else {
                return getString(R.string.allele_parameters_page);
            }
        }
    }
}
