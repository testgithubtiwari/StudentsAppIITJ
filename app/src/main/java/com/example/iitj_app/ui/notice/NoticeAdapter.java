package com.example.iitj_app.ui.notice;


import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iitj_app.R;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeViewAdapter> {
    private Context context;
    private ArrayList<NoticeData> list;

    public NoticeAdapter(Context context, ArrayList<NoticeData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public NoticeViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.newsfeed_item_layout, parent, false);

        return new NoticeViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeViewAdapter holder, int position) {
        NoticeData currItem = list.get(position);

        holder.NoticeTitle.setText(currItem.getTitle());
        holder.date.setText(currItem.getDate());
        holder.time.setText(currItem.getTime());

        try {
            if (currItem.getImage() != null) {
                Picasso.get().load(currItem.getImage()).into(holder.NoticeImage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class NoticeViewAdapter extends RecyclerView.ViewHolder {


        private TextView NoticeTitle, date, time;
        private ImageView NoticeImage;

        public NoticeViewAdapter(@NonNull View itemView) {
            super(itemView);
            NoticeTitle = itemView.findViewById(R.id.NoticeTitle);
            NoticeImage = itemView.findViewById(R.id.NoticeImage);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);

        }
    }
}
