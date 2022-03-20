package com.example.installmenttrackingapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

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

        Customer temp = names.get(position);


holder.name.setText(temp.getName());

holder.materialCardView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(ctx,DetailsScreen.class);
        intent.putExtra("id",temp.getId());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
ctx.startActivity(intent);


    }
});


    }

    @Override
    public int getItemCount() {


        return names.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        ConstraintLayout layout;
        MaterialCardView materialCardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            layout = itemView.findViewById(R.id.group);
            materialCardView = itemView.findViewById(R.id.cardView);
        }
    }

}
