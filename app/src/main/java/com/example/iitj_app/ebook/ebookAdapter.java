package com.example.iitj_app.ebook;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iitj_app.R;

import java.util.List;

public class ebookAdapter extends RecyclerView.Adapter<ebookAdapter.EbookViewHolder> {
    private Context context;
    private List<ebookdata> list;

    public ebookAdapter(Context context, List<ebookdata> list) {
        this.context = context;
        this.list = list;
        Log.d("ebookAdapter", "list size: " + list.size());
    }

    @NonNull
    @Override
    public EbookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.ebook_item,parent,false);
        return new EbookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EbookViewHolder holder, int position) {
        holder.ebookname.setText(list.get(position).getPdfTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,PdfViewerActivity.class);
                intent.putExtra("pdfUrl",list.get(position).getPdfUrl());
                context.startActivity(intent);
            }
        });

        holder.ebookDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(list.get(position).getPdfUrl()));
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class EbookViewHolder extends RecyclerView.ViewHolder {

        private TextView ebookname;
        private ImageView ebookDownload;
        public EbookViewHolder(@NonNull View itemView) {
            super(itemView);

            ebookname=itemView.findViewById(R.id.ebookname);
            ebookDownload=itemView.findViewById(R.id.ebookdownload);

        }
    }
}
