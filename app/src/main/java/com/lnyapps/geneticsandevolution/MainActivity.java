package com.lnyapps.geneticsandevolution;

import android.app.ActionBar;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.github.mikephil.charting.data.Entry;
import com.lnyapps.geneticsandevolution.allelefreak.AlleleFreakFragment;
import com.lnyapps.geneticsandevolution.allelefreak.AlleleGraphInputsFragment;
import com.lnyapps.geneticsandevolution.crosssimulator.CrossSimulatorFragment;
import com.lnyapps.geneticsandevolution.problemgenerator.ProblemGeneratorFragment;
import com.lnyapps.geneticsandevolution.problemsolver.ProblemSolverFragment;
import com.lnyapps.geneticsandevolution.selftestquiz.SelfTestQuizFragment;
import com.lnyapps.geneticsandevolution.selftestquiz.UpdateQuestions;

import java.util.ArrayList;


public class MainActivity extends FragmentActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, AlleleGraphInputsFragment.OnGraphSelectedListener{

    public static final int MESSAGE_DOWNLOAD_STARTED = 1000;
    public static final int MESSAGE_DOWNLOAD_COMPLETE = 1001;
    public static final int MESSAGE_DOWNLOAD_CANCELED = 1003;
    public static final int MESSAGE_CONNECTING_STARTED = 1004;

    private Thread downloaderThread;
    public ArrayList<ArrayList<String>> lines = new ArrayList<ArrayList<String>>();

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        downloaderThread = new UpdateQuestions(this, getString(R.string.quiz_questions_url));
        downloaderThread.start();
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        displayView(position);
    }

    public void displayView(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        String fragmentName = getResources().getStringArray(R.array.navigation_drawer_strings)[position];
        Fragment fragment = null;
        if (fragmentName.equals(getResources().getString(R.string.problem_solver))) {
            fragment = new ProblemSolverFragment();
        } else if (fragmentName.equals(getResources().getString(R.string.problem_generator))) {
            fragment = new ProblemGeneratorFragment();
        } else if (fragmentName.equals(getResources().getString(R.string.self_test_quiz))) {
            fragment = new SelfTestQuizFragment();
        } else if (fragmentName.equals(getResources().getString(R.string.allele_freak))) {
            fragment = new AlleleFreakFragment();
        } else if (fragmentName.equals(getResources().getString(R.string.cross_simulator))) {
            fragment = new CrossSimulatorFragment();
        } else if (fragmentName.equals(getResources().getString(R.string.about))) {
            fragment = new AboutFragment();
        }

        if (fragment != null) {
            fragmentManager.beginTransaction()
                    .replace(R.id.container, fragment, "tag")
                    .commit();
        } else {
            // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

    public void onSectionAttached(String title) {
        mTitle = title;
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * This is the Handler for this activity. It will receive messages from the
     * DownloaderThread and make the necessary updates to the UI.
     */
    public Handler activityHandler = new Handler(){
        public void handleMessage(Message msg){
            if(downloaderThread != null){
                downloaderThread.interrupt();
            }
        }
    };

    public void onGraphSelected(ArrayList<Entry> line){
        FragmentManager manager = getSupportFragmentManager();
        AlleleFreakFragment frag =
                (AlleleFreakFragment)manager.findFragmentByTag("tag");
        frag.graphFrag.addLine(line);
        frag.graphFrag.graph();
    }
}
