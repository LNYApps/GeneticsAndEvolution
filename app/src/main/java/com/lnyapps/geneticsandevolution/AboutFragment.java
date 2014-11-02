package com.lnyapps.geneticsandevolution;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lnyapps.geneticsandevolution.MainActivity;
import com.lnyapps.geneticsandevolution.R;

/**
 * Created by Jonathan Tseng on 10/31/2014.
 */
public class AboutFragment extends Fragment {

    public AboutFragment() {
        Bundle args = new Bundle();
        //args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        setArguments(args);
     }

    private void setUpOnlineDocumentationButton(View root) {
        Button onlineDocumentationButton = (Button) root.findViewById(R.id.about_button_onlinedocumentation);
        onlineDocumentationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://5bcbec0fc7b039fc514de132fc3969fe06e5341a.googledrive.com/host/0B4PMva-Zlc8EUEVJQUZhWHpXa00/GenEvolManual.html";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
    }

    private void setUpCourseWebpageButton(View root) {
        Button courseWebpageButton = (Button) root.findViewById(R.id.about_button_coursewebsite);
        courseWebpageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.coursera.org/course/geneticsevolution";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_about, container, false);
        setUpOnlineDocumentationButton(rootView);
        setUpCourseWebpageButton(rootView);
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(getResources().getString(R.string.about));
    }


}
