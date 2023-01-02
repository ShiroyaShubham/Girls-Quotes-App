package com.example.girlsquotesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class QuotesRecycleviewAdapter extends RecyclerView.Adapter<QuotesRecycleviewAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<String> category;
    private Category_ListI_Interface categoryListIInterface;

    public QuotesRecycleviewAdapter(Context context, ArrayList<String> category, Category_ListI_Interface categoryListIInterface) {

        this.context = context;
        this.category = category;
        this.categoryListIInterface = categoryListIInterface;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.quates_tyop, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tctQuoteType.setText(category.get(position));
        holder.tctQuoteType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                categoryListIInterface.clicker(position);

            }
        });

    }

    @Override
    public int getItemCount() {
        return category.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tctQuoteType;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tctQuoteType = itemView.findViewById(R.id.tctQuoteType);
        }
    }
}
