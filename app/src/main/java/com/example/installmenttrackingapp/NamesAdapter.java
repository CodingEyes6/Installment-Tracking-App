package com.example.installmenttrackingapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NamesAdapter extends RecyclerView.Adapter<NamesAdapter.ViewHolder>{


    List<Customer> names;
    Context ctx;

    public NamesAdapter(List<Customer> names, Context ctx) {
        this.names = names;
        this.ctx = ctx;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(ctx).inflate(R.layout.card_names_item,parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Customer list = names.get(position);
        Log.d("holder",list.getName());
holder.name.setText(list.getName());


    }

    @Override
    public int getItemCount() {


        return names.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        ConstraintLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            layout = itemView.findViewById(R.id.group);
        }
    }

}
