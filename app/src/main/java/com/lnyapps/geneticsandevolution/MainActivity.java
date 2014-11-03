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

import com.lnyapps.geneticsandevolution.problemgenerator.ProblemGeneratorFragment;
import com.lnyapps.geneticsandevolution.problemsolver.ProblemSolverFragment;
import com.lnyapps.geneticsandevolution.selftestquiz.SelfTestQuizFragment;
import com.lnyapps.geneticsandevolution.selftestquiz.UpdateQuestions;


public class MainActivity extends FragmentActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private Thread downloaderThread;

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

        downloaderThread = new UpdateQuestions(this, "https://637a199c47937e7b668851faa0c26911d586837b.googledrive.com/host/0B4PMva-Zlc8Ec0xoOUtiSXJwTUk/GenEvolTerms.json");
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
        } else if (fragmentName.equals(getResources().getString(R.string.about))) {
            fragment = new AboutFragment();
        }

        if (fragment != null) {
            fragmentManager.beginTransaction()
                    .replace(R.id.container, fragment)
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
    public Handler activityHandler = new Handler()
    {
        public void handleMessage(Message msg)
        {
            switch(msg.what)
            {
                case MESSAGE_CONNECTING_STARTED:
                    if(msg.obj != null && msg.obj instanceof String)
                    {
                        String url = (String) msg.obj;
                        // truncate the url
                        if(url.length() > 16)
                        {
                            String tUrl = url.substring(0, 15);
                            tUrl += "...";
                            url = tUrl;
                        }
                    }
                    break;

                case MESSAGE_DOWNLOAD_STARTED:
                    // obj will contain a String representing the file name
                    if(msg.obj != null && msg.obj instanceof String)
                    {
                        int maxValue = msg.arg1;
                        String fileName = (String) msg.obj;



                    }
                    break;
                case MESSAGE_DOWNLOAD_COMPLETE:

                    break;

                                /*
                                 * Handling MESSAGE_DOWNLOAD_CANCELLED:
                                 * 1. Interrupt the downloader thread.
                                 * 2. Remove the progress bar from the screen.
                                 * 3. Display Toast that says download is complete.
                                 */
                case MESSAGE_DOWNLOAD_CANCELED:
                    if(downloaderThread != null)
                    {
                        downloaderThread.interrupt();
                    }
                    break;

                                /*
                                 * Handling MESSAGE_ENCOUNTERED_ERROR:
                                 * 1. Check the obj field of the message for the actual error
                                 *    message that will be displayed to the user.
                                 * 2. Remove any progress bars from the screen.
                                 * 3. Display a Toast with the error message.
                                 */

                default:
                    // nothing to do here
                    break;
            }
        }
    };

    public static final int MESSAGE_DOWNLOAD_STARTED = 1000;
    public static final int MESSAGE_DOWNLOAD_COMPLETE = 1001;
    public static final int MESSAGE_UPDATE_PROGRESS_BAR = 1002;
    public static final int MESSAGE_DOWNLOAD_CANCELED = 1003;
    public static final int MESSAGE_CONNECTING_STARTED = 1004;
    public static final int MESSAGE_ENCOUNTERED_ERROR = 1005;
}
