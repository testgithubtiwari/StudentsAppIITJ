package com.example.iitj_app.ui.faculty;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.iitj_app.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class FacultyFragment extends Fragment {

    private RecyclerView csDepartment,eeDepartment,meDepartment,chDepartment,cieDepartment,mtDepartment,bbDepartment,physicsDepartment,chemistryDepartment,otherDepartment;
    private LinearLayout csNodata,eeNodata,meNodata,chNodata,cieNodata,mtNodata,bbNodata,pNodata,cNodata,oNodata;
    private List<TeacherData> list1,list2,list3,list4,list5,list6,list7,list8,list9,list10;
    private DatabaseReference reference,dbRef;
    private TeacherAdapter adapter;
    private ProgressBar progressBar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_faculty, container, false);


                csDepartment=view.findViewById(R.id.csDepartment);
                eeDepartment=view.findViewById(R.id.eeDepartment);
                meDepartment=view.findViewById(R.id.meDepartment);
                chDepartment=view.findViewById(R.id.chDepartment);
                cieDepartment=view.findViewById(R.id.cieDepartment);
                mtDepartment=view.findViewById(R.id.mtDepartment);
                bbDepartment=view.findViewById(R.id.bbDepartment);
                physicsDepartment=view.findViewById(R.id.physicsDepartment);
                chemistryDepartment=view.findViewById(R.id.chemistryDepartment);
                otherDepartment=view.findViewById(R.id.otherDepartment);
                progressBar=view.findViewById(R.id.progressbar);

                csNodata=view.findViewById(R.id.csNodata);
                eeNodata=view.findViewById(R.id.eeNodata);
                meNodata=view.findViewById(R.id.meNodata);
                chNodata=view.findViewById(R.id.chNodata);
                cieNodata=view.findViewById(R.id.cieNodata);
                mtNodata=view.findViewById(R.id.mtNodata);
                bbNodata=view.findViewById(R.id.bbNodata);
                pNodata=view.findViewById(R.id.physicsNodata);
                cNodata=view.findViewById(R.id.chemistryNodata);
                oNodata=view.findViewById(R.id.otherNodata);
                reference= FirebaseDatabase.getInstance().getReference().child("Teacher's");

                csDepartment();
                eeDepartment();
                meDepartment();
                chDepartment();
                cieDepartment();
                mtDepartment();
                bbDepartment();
                physicsDepartment();
                chemistryDepartment();
                otherDepartment();
                return view;
    }

    private void otherDepartment() {
        dbRef=reference.child("Other");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list10=new ArrayList<>();
                if(!dataSnapshot.exists())
                {
                    oNodata.setVisibility(View.VISIBLE);
                    otherDepartment.setVisibility(View.GONE);
                }else {
                    oNodata.setVisibility(View.GONE);
                    otherDepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot:dataSnapshot.getChildren())
                    {
                        TeacherData data=snapshot.getValue(TeacherData.class);
                        list10.add(data);
                    }
                    otherDepartment.setHasFixedSize(true);
                    otherDepartment.setLayoutManager( new LinearLayoutManager(getContext()));
                    adapter=new TeacherAdapter(list10,getContext());
                    otherDepartment.setAdapter(adapter);
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void chemistryDepartment() {
        dbRef=reference.child("Chemistry");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list9=new ArrayList<>();
                if(!dataSnapshot.exists())
                {
                    cNodata.setVisibility(View.VISIBLE);
                    chemistryDepartment.setVisibility(View.GONE);
                }else {
                    cNodata.setVisibility(View.GONE);
                    chemistryDepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot:dataSnapshot.getChildren())
                    {
                        TeacherData data=snapshot.getValue(TeacherData.class);
                        list9.add(data);
                    }
                    chemistryDepartment.setHasFixedSize(true);
                    chemistryDepartment.setLayoutManager( new LinearLayoutManager(getContext()));
                    adapter=new TeacherAdapter(list9,getContext());
                    chemistryDepartment.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void physicsDepartment() {
        dbRef=reference.child("Physics");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list8=new ArrayList<>();
                if(!dataSnapshot.exists())
                {
                    pNodata.setVisibility(View.VISIBLE);
                    physicsDepartment.setVisibility(View.GONE);
                }else {
                    pNodata.setVisibility(View.GONE);
                    physicsDepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot:dataSnapshot.getChildren())
                    {
                        TeacherData data=snapshot.getValue(TeacherData.class);
                        list8.add(data);
                    }
                    physicsDepartment.setHasFixedSize(true);
                    physicsDepartment.setLayoutManager( new LinearLayoutManager(getContext()));
                    adapter=new TeacherAdapter(list8,getContext());
                    physicsDepartment.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void csDepartment() {
        dbRef=reference.child("Computer Science");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list1=new ArrayList<>();
                if(!dataSnapshot.exists())
                {
                    csNodata.setVisibility(View.VISIBLE);
                    csDepartment.setVisibility(View.GONE);
                }else {
                    csNodata.setVisibility(View.GONE);
                    csDepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot:dataSnapshot.getChildren())
                    {
                        TeacherData data=snapshot.getValue(TeacherData.class);
                        list1.add(data);
                    }
                    csDepartment.setHasFixedSize(true);
                    csDepartment.setLayoutManager( new LinearLayoutManager(getContext()));
                    adapter=new TeacherAdapter(list1,getContext());
                    csDepartment.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void eeDepartment() {

        dbRef=reference.child("Electrical Engineering");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list2=new ArrayList<>();
                if(!dataSnapshot.exists())
                {
                    eeNodata.setVisibility(View.VISIBLE);
                    eeDepartment.setVisibility(View.GONE);
                }else {
                    eeNodata.setVisibility(View.GONE);
                    eeDepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot:dataSnapshot.getChildren())
                    {
                        TeacherData data=snapshot.getValue(TeacherData.class);
                        list2.add(data);
                    }
                    eeDepartment.setHasFixedSize(true);
                    eeDepartment.setLayoutManager( new LinearLayoutManager(getContext()));
                    adapter=new TeacherAdapter(list2,getContext());
                    eeDepartment.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void meDepartment() {
        dbRef=reference.child("Mechanical Engineering");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list3=new ArrayList<>();
                if(!dataSnapshot.exists())
                {
                    meNodata.setVisibility(View.VISIBLE);
                    meDepartment.setVisibility(View.GONE);
                }else {
                    meNodata.setVisibility(View.GONE);
                    meDepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot:dataSnapshot.getChildren())
                    {
                        TeacherData data=snapshot.getValue(TeacherData.class);
                        list3.add(data);
                    }
                    meDepartment.setHasFixedSize(true);
                    meDepartment.setLayoutManager( new LinearLayoutManager(getContext()));
                    adapter=new TeacherAdapter(list3,getContext());
                    meDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void chDepartment() {
        dbRef=reference.child("Chemical Engineering");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list4=new ArrayList<>();
                if(!dataSnapshot.exists())
                {
                    chNodata.setVisibility(View.VISIBLE);
                    chDepartment.setVisibility(View.GONE);
                }else {
                    chNodata.setVisibility(View.GONE);
                    chDepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot:dataSnapshot.getChildren())
                    {
                        TeacherData data=snapshot.getValue(TeacherData.class);
                        list4.add(data);
                    }
                    chDepartment.setHasFixedSize(true);
                    chDepartment.setLayoutManager( new LinearLayoutManager(getContext()));
                    adapter=new TeacherAdapter(list4,getContext());
                    chDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void cieDepartment() {
        dbRef=reference.child("Civil Engineering");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list5=new ArrayList<>();
                if(!dataSnapshot.exists())
                {
                    cieNodata.setVisibility(View.VISIBLE);
                    cieDepartment.setVisibility(View.GONE);
                }else {
                    cieNodata.setVisibility(View.GONE);
                    cieDepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot:dataSnapshot.getChildren())
                    {
                        TeacherData data=snapshot.getValue(TeacherData.class);
                        list5.add(data);
                    }
                    cieDepartment.setHasFixedSize(true);
                    cieDepartment.setLayoutManager( new LinearLayoutManager(getContext()));
                    adapter=new TeacherAdapter(list5,getContext());
                    cieDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void mtDepartment() {
        dbRef=reference.child("Material Engineering");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list6=new ArrayList<>();
                if(!dataSnapshot.exists())
                {
                    mtNodata.setVisibility(View.VISIBLE);
                    mtDepartment.setVisibility(View.GONE);
                }else {
                    mtNodata.setVisibility(View.GONE);
                    mtDepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot:dataSnapshot.getChildren())
                    {
                        TeacherData data=snapshot.getValue(TeacherData.class);
                        list6.add(data);
                    }
                    mtDepartment.setHasFixedSize(true);
                    mtDepartment.setLayoutManager( new LinearLayoutManager(getContext()));
                    adapter=new TeacherAdapter(list6,getContext());
                    mtDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void bbDepartment() {
        dbRef=reference.child("BioEngineering");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list7=new ArrayList<>();
                if(!dataSnapshot.exists())
                {
                    bbNodata.setVisibility(View.VISIBLE);
                    bbDepartment.setVisibility(View.GONE);
                }else {
                    bbNodata.setVisibility(View.GONE);
                    bbDepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot:dataSnapshot.getChildren())
                    {
                        TeacherData data=snapshot.getValue(TeacherData.class);
                        list7.add(data);
                    }
                    bbDepartment.setHasFixedSize(true);
                    bbDepartment.setLayoutManager( new LinearLayoutManager(getContext()));
                    adapter=new TeacherAdapter(list7,getContext());
                    bbDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }



}