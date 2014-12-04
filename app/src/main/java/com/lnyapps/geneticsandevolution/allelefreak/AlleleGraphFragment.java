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

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.lnyapps.geneticsandevolution.MainActivity;
import com.lnyapps.geneticsandevolution.R;

import java.util.ArrayList;

/**
 * Created by Alex on 11/30/2014.
 */
public class AlleleGraphFragment extends Fragment {

    private LineChart mChart;
    private ArrayList<ArrayList<Entry>> lines = new ArrayList<ArrayList<Entry>>();

    /**
    public AlleleGraphFragment() {
        Bundle args = new Bundle();
        //args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        setArguments(args);
    }*/

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_allelefreak, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.allele_freak_guide) {
            //TODO: insert pop-up dialog
            AlleleFreakHelpDialog dialog = new AlleleFreakHelpDialog();
            dialog.show(getActivity().getSupportFragmentManager(), "allele freak dialog");
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_allelegraph, container, false);
        //start of graphing code

        mChart = (LineChart) rootView.findViewById(R.id.chart);
        mChart.setNoDataText(getResources().getString(R.string.allele_graph_no_data));
        mChart.setDescription("");
        mChart.setDrawYValues(false);
        mChart.setHighlightIndicatorEnabled(false);
        mChart.setDrawBorder(true);
        mChart.setDrawGridBackground(true);
        mChart.setDrawVerticalGrid(true);
        mChart.setDrawXLabels(true);
        mChart.setDrawYValues(true);
        mChart.setStartAtZero(true);
        mChart.setYRange(0, 1.0f, false);



        //Typeface tf = Typeface.createFromAsset(getActivity().getAssets(),"OpenSans-Light.ttf");

        //end
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(getResources().getString(R.string.allele_freak));
    }

    public void addLine(ArrayList<Entry> line){
        lines.add(line);
    }

    public void graph(){
        ArrayList<LineDataSet> sets = new ArrayList<LineDataSet>();
        for(int i = 0; i<lines.size(); i++){
            //Entry entry = new Entry(lines.get(i), i);
            ArrayList<Entry> entries = lines.get(i);
            int lineNumber = i+1;
            LineDataSet ds = new LineDataSet(entries, "Allele " + lineNumber);
            ds.setLineWidth(2f);
            ds.setDrawCircles(false);
            sets.add(ds);
        }
        int max = findMaxEntries(sets);
        LineData d = new LineData(ChartData.generateXVals(0, max),  sets);
        mChart.setData(d);
        mChart.animateX(3000); //animates in 3000ms
    }

    public void clear(){
        lines.clear();
        mChart.clear();
    }

    public int findMaxEntries(ArrayList<LineDataSet> list){
        int max = list.get(0).getEntryCount();
        for(int i=1; i<list.size(); i++){
            if(list.get(i).getEntryCount()>max){
                max = list.get(i).getEntryCount();
            }
        }
        return max;
    }


    /**
     * Implementing dialog box sub-class
     */
    public class AlleleFreakHelpDialog extends DialogFragment {
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
