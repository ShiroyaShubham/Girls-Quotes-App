package com.example.girlsquotesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ThirdRecycleviewAdapter extends RecyclerView.Adapter<ThirdRecycleviewAdapter.MyViewHolder> {
    Context context;
    ArrayList<String> quoteList2;

    public ThirdRecycleviewAdapter(Context context, ArrayList<String> quotesList2) {
        this.context = context;
        this.quoteList2 = quotesList2;
    }

    @NonNull
    @Override
    public ThirdRecycleviewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.horizontal_quotes_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ThirdRecycleviewAdapter.MyViewHolder holder, int position) {
        holder.txtHorizontalQuotes.setText(quoteList2.get(position));
    }

    @Override
    public int getItemCount() {
        return quoteList2.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtHorizontalQuotes;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtHorizontalQuotes = itemView.findViewById(R.id.txtHorizontalQuotes);
        }
    }
}
