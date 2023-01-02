package com.example.girlsquotesapp;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SecondRecycleviewAdapter extends RecyclerView.Adapter<SecondRecycleviewAdapter.MyViewHolder2> {
    private Context context;
    private ArrayList<String> quotes2;
    Category_ListI_Interface categoryListIInterface;

    public SecondRecycleviewAdapter(Context context, ArrayList<String> quotes2, Category_ListI_Interface categoryListIInterface) {

        this.context = context;
        this.quotes2 = quotes2;
        this.categoryListIInterface=categoryListIInterface;
    }

    @NonNull
    @Override
    public MyViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.quotes, parent, false);
        MyViewHolder2 myViewHolder2 = new MyViewHolder2(view);

        return myViewHolder2;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder2 holder, int position) {

        holder.txtquotes.setText(quotes2.get(position));
        holder.imgCopy.setOnClickListener(v -> {

            Toast.makeText(context, "Quote Copied in Clipboard.", Toast.LENGTH_SHORT).show();
            String copyQuote = holder.txtquotes.getText().toString();
            ClipboardManager clipboard = (ClipboardManager) context.getSystemService(context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("Text Copty", copyQuote);
            clipboard.setPrimaryClip(clip);
        });
        holder.imgLikeEmpty.setOnClickListener(v ->{
            holder.imgLikeEmpty.setVisibility(View.GONE);
            holder.imgLikeFill.setVisibility(View.VISIBLE);
            Toast.makeText(context, "Added to My Favourite", Toast.LENGTH_SHORT).show();
        });
        holder.imgLikeFill.setOnClickListener(v ->{
            holder.imgLikeEmpty.setVisibility(View.VISIBLE);
            holder.imgLikeFill.setVisibility(View.GONE);
        });
        holder.imgShare.setOnClickListener(v->{
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBody =holder.txtquotes.getText().toString();
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            context.startActivity(Intent.createChooser(sharingIntent, "Share via"));
        });
        holder.imgGallery.setOnClickListener(v->{
            String quotes=holder.txtquotes.getText().toString();
           categoryListIInterface.galleryClicker(position,quotes);
        });
    }

    @Override
    public int getItemCount() {
        return quotes2.size();
    }

    public class MyViewHolder2 extends RecyclerView.ViewHolder {

        TextView txtquotes;
        ImageView imgCopy,imgShare,imgLikeEmpty,imgLikeFill,imgGallery;

        public MyViewHolder2(@NonNull View itemView) {
            super(itemView);
            txtquotes = itemView.findViewById(R.id.txtquotes);
            imgCopy = itemView.findViewById(R.id.imgCopy);
            imgShare = itemView.findViewById(R.id.imgShare);
            imgLikeEmpty = itemView.findViewById(R.id.imgLikeEmpty);
            imgLikeFill = itemView.findViewById(R.id.imgLikeFill);
            imgGallery = itemView.findViewById(R.id.imgGallery);
        }
    }
}
