package com.example.iitj_app.ui.about;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.iitj_app.R;

import java.util.ArrayList;
import java.util.List;

public class AboutFragment extends Fragment {

    private ViewPager viewPager;
    private BranchAdapter adapter;
    private List<BranchModel>  list;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_about, container, false);

        list=new ArrayList<>();
        list.add(new BranchModel(R.drawable.ic_video,"Computer Science","Computer Science and Engineering, today, plays a major role" +
                " in transforming every aspect of human life - in addressing social challenges and catalyzing the ongoing wave of the industrial revolution. " +
                "The department is driven by its commitment to excel in next generation technology development and research."));
        list.add(new BranchModel(R.drawable.ic_home,"Mechanical Engineering","The Department of Mechanical Engineering at IIT" +
                " Jodhpur is devoted to quality engineering education and pursuits the excellence in research." +
                " It is dedicated to preparing students to face the emerging challenges faced by society."));

        adapter=new BranchAdapter(getContext(),list);

        viewPager=view.findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);





        return view;
    }
}