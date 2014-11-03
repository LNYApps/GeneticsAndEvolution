/**
 * Created by Alex on 11/2/2014.
 */
package com.lnyapps.geneticsandevolution;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CustomList extends ArrayAdapter<String>{
    private final Context context;
    private final String[] category;
    private final TypedArray imageId;
    private final Activity activity;
    public CustomList(Activity activity, Context context,
                      String[] category, TypedArray imageId) {
        super(context, R.layout.navigation_drawer_single_item, category);
        this.context = context;
        this.category = category;
        this.imageId = imageId;
        this.activity = activity;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.navigation_drawer_single_item, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.text1);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        txtTitle.setText(category[position]);
        imageView.setImageResource(imageId.getResourceId(position, -1));
        return rowView;
    }
}