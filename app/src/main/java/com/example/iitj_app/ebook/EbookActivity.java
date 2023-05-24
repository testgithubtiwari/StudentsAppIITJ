package com.example.iitj_app.ebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
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

public class EbookActivity extends AppCompatActivity {

    private RecyclerView ebookRecycler;
    private DatabaseReference reference;
    private List<ebookdata> list;
    private ebookAdapter adapter;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ebook);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ebookRecycler=findViewById(R.id.ebookRecycler);
        progressBar=findViewById(R.id.progressbar);
        reference= FirebaseDatabase.getInstance().getReference().child("pdf");
        getData();

    }

    private void getData() {
        progressBar.setVisibility(View.VISIBLE);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ebookdata data = snapshot.getValue(ebookdata.class);
                    list.add(data);
                }
                adapter = new ebookAdapter(EbookActivity.this, list);
                ebookRecycler.setLayoutManager(new LinearLayoutManager(EbookActivity.this));
                ebookRecycler.setAdapter(adapter);
                progressBar.setVisibility(View.GONE); // Move this line here
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(EbookActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE); // Move this line here too
            }
        });
    }
}