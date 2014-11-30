package com.lnyapps.geneticsandevolution.allelefreak;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lnyapps.geneticsandevolution.MainActivity;
import com.lnyapps.geneticsandevolution.R;

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
        ViewPager pager = (ViewPager)rootView.findViewById(R.id.pg_pager);
        pager.setAdapter(new SectionsPagerAdapter(getActivity(), getChildFragmentManager()));
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

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            if (position == 0) {
                return new AlleleGraphInputsFragment();
            } else {
                return new AlleleGraphFragment();
            }
        }

        @Override
        public String getPageTitle(int position) {
            if (position == 0) {
                return getString(R.string.allele_parameters_page);
            } else {
                return getString(R.string.allele_graph_page);
            }
        }
    }
}
