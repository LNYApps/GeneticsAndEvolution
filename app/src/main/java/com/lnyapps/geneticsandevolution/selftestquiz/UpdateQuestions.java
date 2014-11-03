package com.lnyapps.geneticsandevolution.selftestquiz;

import android.app.Activity;
import android.os.Environment;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.lnyapps.geneticsandevolution.R;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Alex on 11/2/2014.
 */
public class UpdateQuestions extends Thread{
    private Activity parentActivity;
    private String downloadUrl;
    private static final int DOWNLOAD_BUFFER_SIZE = 4096;


    public UpdateQuestions(Activity activity, String inUrl)
    {
        downloadUrl = "";
        if(inUrl != null)
        {
            downloadUrl = inUrl;
        }
        parentActivity = activity;
    }
    /**
     * Connects to the URL of the file, begins the download, and notifies the
     * AndroidFileDownloader activity of changes in state. Writes the file to
     * the root of the SD card.
     */
    @Override
    public void run()
    {
        URL url;
        URLConnection conn;
        int fileSize, lastSlash;
        String fileName;
        BufferedInputStream inStream;
        BufferedOutputStream outStream;
        File outFile;
        FileOutputStream fileStream;
        Message msg;

        try
        {
            url = new URL(downloadUrl);
            conn = url.openConnection();
            conn.setUseCaches(false);
            fileSize = conn.getContentLength();

            // get the filename
            lastSlash = url.toString().lastIndexOf('/');
            fileName = "GenEvolTerms.json";
            if(lastSlash >=0)
            {
                fileName = url.toString().substring(lastSlash + 1);
            }
            if(fileName.equals(""))
            {
                fileName = "GenEvolTerms.json";
            }

            // notify download start


            // start download
            inStream = new BufferedInputStream(conn.getInputStream());
            outFile = new File(Environment.getExternalStorageDirectory() + "/" + fileName);
            fileStream = new FileOutputStream(outFile);
            outStream = new BufferedOutputStream(fileStream, DOWNLOAD_BUFFER_SIZE);
            byte[] data = new byte[DOWNLOAD_BUFFER_SIZE];
            int bytesRead = 0, totalRead = 0;
            while(!isInterrupted() && (bytesRead = inStream.read(data, 0, data.length)) >= 0)
            {
                outStream.write(data, 0, bytesRead);
            }

            outStream.close();
            fileStream.close();
            inStream.close();
        }
        catch(MalformedURLException e)
        {
            String errMsg = parentActivity.getString(R.string.error_message_bad_url);
            Toast.makeText(parentActivity, errMsg, Toast.LENGTH_SHORT).show();
            Log.e("1st exception", "3");
        }
        catch(FileNotFoundException e)
        {
            String errMsg = parentActivity.getString(R.string.error_message_file_not_found);
            Toast.makeText(parentActivity, errMsg, Toast.LENGTH_SHORT).show();
            Log.e("2rd exception", "3");
        }
        catch(Exception e)
        {
            String errMsg = parentActivity.getString(R.string.error_message_general);
            Log.e("3rd exception", "3", e);
        }
    }
}
