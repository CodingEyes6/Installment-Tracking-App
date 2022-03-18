package com.example.installmenttrackingapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class CustomerAdapter extends FirebaseRecyclerAdapter<Customer,CustomerAdapter.ViewHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public CustomerAdapter(@NonNull FirebaseRecyclerOptions<Customer> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Customer model) {
holder.name.setText(model.getName());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_names_item,parent,false);
        return new CustomerAdapter.ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
        }
    }
}
