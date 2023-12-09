package com.example.iitj_app.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.widget.ImageView;





import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.iitj_app.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private ImageView map;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home, container, false);


        map=view.findViewById(R.id.mapimage);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap();
            }
        });

        return  view;
    }

    private void openMap() {
        Uri uri=Uri.parse("geo:0, 0?q=IIT JODHPUR ");
        Intent intent=new Intent(Intent.ACTION_VIEW,uri);
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);

    }
}
