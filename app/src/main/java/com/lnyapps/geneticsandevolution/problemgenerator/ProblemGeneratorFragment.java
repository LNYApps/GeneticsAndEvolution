package com.lnyapps.geneticsandevolution.problemgenerator;

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
 * Created by Jonathan Tseng on 11/1/2014.
 */
public class ProblemGeneratorFragment extends Fragment {

    ViewPager mViewPager;
    FragmentPagerAdapter mFragmentPagerAdapter;

    public ProblemGeneratorFragment() {
        Bundle args = new Bundle();
        //args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        setArguments(args);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_problemgenerator, container, false);
        ViewPager pager = (ViewPager)rootView.findViewById(R.id.pg_pager);
        pager.setAdapter(new SectionsPagerAdapter(getActivity(), getChildFragmentManager()));
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(getResources().getString(R.string.problem_generator));
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
                return new SingleProblemGeneratorFragment();
            } else {
                return new MultipleProblemGeneratorFragment();
            }
        }

        @Override
        public String getPageTitle(int position) {
            if (position == 0) {
                return getString(R.string.pg_single_title);
            } else {
                return getString(R.string.pg_multiple_title);
            }
        }

    }


}
