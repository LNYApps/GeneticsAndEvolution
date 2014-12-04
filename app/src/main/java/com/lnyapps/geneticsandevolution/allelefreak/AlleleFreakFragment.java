package com.lnyapps.geneticsandevolution.allelefreak;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    public AlleleGraphFragment graphFrag;
    public AlleleGraphInputsFragment graphInputsFrag;

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
                graphInputsFrag = new AlleleGraphInputsFragment();
                return graphInputsFrag;
            } else {
                graphFrag = new AlleleGraphFragment();
                return graphFrag;
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
