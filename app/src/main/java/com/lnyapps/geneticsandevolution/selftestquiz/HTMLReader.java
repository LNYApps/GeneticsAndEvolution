package com.lnyapps.geneticsandevolution.selftestquiz;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by RussellM on 8/06/2016.
 */


interface HTMLReaderListener {
    void htmlReadFinish(String output,String errCode,String errMessage);

}
public class HTMLReader  extends AsyncTask<URL, Void, String[]> {

    public static final String READ_OK = "0";
    public static final String READ_NULL_STREAM = "-1";
    public static final String READ_EMPTY_STREAM = "-2";
    public static final String READ_IO_EXCEPTION = "-3";
    public static final String READ_CLOSE_ERROR = "-4";

    public static final String CONNECT_OK = "0";
    public static final String CONNECT_ERROR = "-1";



    private URL url;
    public HTMLReaderListener delegate;

    public HTMLReader(String urlString,HTMLReaderListener inDelegate) {
        try {
            url = new URL(urlString);
        }
        catch (java.net.MalformedURLException e) {
            url = null;
        }
        delegate = inDelegate;


    }

    public URL getURL() {
        return url;
    }

    public String[] checkConnection(android.app.Activity act) {
        android.net.ConnectivityManager connMgr = (android.net.ConnectivityManager)
                act.getSystemService(android.content.Context.CONNECTIVITY_SERVICE);

        android.net.NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            // fetch data
            return new String[] {CONNECT_OK,networkInfo.getExtraInfo()};


        } else {
            // display error

            return new String[] {CONNECT_ERROR,networkInfo == null ? "" : networkInfo.getReason()};

        }
    }

    public int  readHTML(android.app.Activity act) {

        if (checkConnection(act)[0].equals("-1")) {
            return Integer.parseInt(CONNECT_ERROR);
        }

        execute(url);
        return Integer.parseInt(CONNECT_OK);

    }


    @Override
    protected String[] doInBackground(URL... urls) {

        String[] ret = {null,"",""};

        // These two need to be declared outside the try/catch
        // so that they can be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        // Will contain the raw JSON response as a string.
        String htmlString = null;

        try {

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                ret[1] = READ_NULL_STREAM;
                ret[2] = "null stream";
                return ret;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                ret[1] = READ_EMPTY_STREAM;
                ret[2] = "empty stream";
                return ret;
            }
            htmlString = buffer.toString();
        } catch (IOException e) {
            Log.e("PlaceholderFragment", "Error ", e);
            // If the code didn't successfully get the data, there's no point in attemping
            // to parse it.
            ret[1] = READ_IO_EXCEPTION;
            ret[2] = e.toString();
            return ret;
        } finally{
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                    ret[0] = htmlString;
                    ret[1] = READ_OK;
                    ret[2] = "";
                } catch (final IOException e) {
                    Log.e("PlaceholderFragment", "Error closing stream", e);
                    ret[1] = READ_CLOSE_ERROR;
                    ret[2] = "Closing error";
                    //return ret;
                }
            }
        }


        return ret;
    }

    @Override
    protected void onPostExecute(String[] result) {

        delegate.htmlReadFinish(result[0],result[1],result[2]);
    }
}




