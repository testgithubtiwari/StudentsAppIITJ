package com.example.iitj_app.ui.gallery;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.iitj_app.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class GalleryFragment extends Fragment {


    RecyclerView convoRecycler,techRecycler,cultRecycler,sportRecycler,independenceRecycler,republicRecycler,
    otherRecycler;
    GalleryAdapter adapter;

    DatabaseReference reference;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_gallery, container, false);

        convoRecycler=view.findViewById(R.id.convoRecycler);
        techRecycler=view.findViewById(R.id.techRecycler);
        cultRecycler=view.findViewById(R.id.cultRecycler);
        sportRecycler=view.findViewById(R.id.sportRecycler);
        independenceRecycler=view.findViewById(R.id.independenceRecycler);
        republicRecycler=view.findViewById(R.id.republicRecycler);
        otherRecycler=view.findViewById(R.id.otherRecycler);


        reference= FirebaseDatabase.getInstance().getReference().child("Gallery");

        getConvoImage();
        getTechImage();
        getOtherImage();
        getSportImage();
        getIndeImage();
        getRepImage();
        getCultImage();

        return view;
    }

    private void getCultImage() {
        reference.child("Cultural Fest").addValueEventListener(new ValueEventListener() {
            List<String> imageList=new ArrayList<>();

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1:snapshot.getChildren()){
                    String data=(String) snapshot1.getValue();
                    imageList.add(data);
                }

                adapter=new GalleryAdapter(getContext(),imageList);
                cultRecycler.setLayoutManager(new GridLayoutManager(getContext(),3));
                cultRecycler.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getRepImage() {
        reference.child("Republic Day").addValueEventListener(new ValueEventListener() {
            List<String> imageList=new ArrayList<>();

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1:snapshot.getChildren()){
                    String data=(String) snapshot1.getValue();
                    imageList.add(data);
                }

                adapter=new GalleryAdapter(getContext(),imageList);
                republicRecycler.setLayoutManager(new GridLayoutManager(getContext(),3));
                republicRecycler.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getIndeImage() {
        reference.child("Independence Day").addValueEventListener(new ValueEventListener() {
            List<String> imageList=new ArrayList<>();

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1:snapshot.getChildren()){
                    String data=(String) snapshot1.getValue();
                    imageList.add(data);
                }

                adapter=new GalleryAdapter(getContext(),imageList);
                independenceRecycler.setLayoutManager(new GridLayoutManager(getContext(),3));
                independenceRecycler.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getSportImage() {
        reference.child("Sport's Fest").addValueEventListener(new ValueEventListener() {
            List<String> imageList=new ArrayList<>();

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1:snapshot.getChildren()){
                    String data=(String) snapshot1.getValue();
                    imageList.add(data);
                }

                adapter=new GalleryAdapter(getContext(),imageList);
                sportRecycler.setLayoutManager(new GridLayoutManager(getContext(),3));
                sportRecycler.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getConvoImage() {
        reference.child("Convocation").addValueEventListener(new ValueEventListener() {
            List<String> imageList=new ArrayList<>();

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1:snapshot.getChildren()){
                    String data=(String) snapshot1.getValue();
                    imageList.add(data);
                }

                adapter=new GalleryAdapter(getContext(),imageList);
                convoRecycler.setLayoutManager(new GridLayoutManager(getContext(),3));
                convoRecycler.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getTechImage() {
        reference.child("Technical Fest").addValueEventListener(new ValueEventListener() {
            List<String> imageList=new ArrayList<>();

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1:snapshot.getChildren()){
                    String data=(String) snapshot1.getValue();
                    imageList.add(data);
                }

                adapter=new GalleryAdapter(getContext(),imageList);
                techRecycler.setLayoutManager(new GridLayoutManager(getContext(),3));
                techRecycler.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getOtherImage() {
        reference.child("Others Events").addValueEventListener(new ValueEventListener() {
            List<String> imageList1=new ArrayList<>();

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    String data=(String)snapshot.getValue();
                    imageList1.add(data);
                }

                adapter=new GalleryAdapter(getContext(),imageList1);
                otherRecycler.setLayoutManager(new GridLayoutManager(getContext(),3));
                otherRecycler.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}