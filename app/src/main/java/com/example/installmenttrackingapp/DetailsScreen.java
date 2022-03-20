package com.example.installmenttrackingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.installmenttrackingapp.databinding.ActivityDetailsScreenBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class DetailsScreen extends AppCompatActivity {


    private ActivityDetailsScreenBinding binding;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

reference = FirebaseDatabase.getInstance().getReference("Customers");
String data = getIntent().getStringExtra("id");
 Query query = reference.orderByChild("id").equalTo(data);

 query.addValueEventListener(new ValueEventListener() {
     @Override
     public void onDataChange(@NonNull DataSnapshot snapshot) {
         if (snapshot.exists()) {
             // dataSnapshot is the "issue" node with all children with id 0
             for (DataSnapshot issue : snapshot.getChildren()) {
                 // do something with the individual "issues"
                 Customer customer = issue.getValue(Customer.class);
                 binding.detailName.setText(customer.getName());
                 binding.detailAddress.setText(customer.getAddress());
                 binding.detailPhoneNo.setText(customer.getPhoneNO());
                 binding.detailProductName.setText(customer.getProductName());
                 binding.detailAdvanceAmount.setText(customer.getAdvanceAmount());
                 binding.detailTotalAmount.setText(customer.getTotalAmount());
                 binding.detailDate.setText(customer.getDate());

             }
         }
     }

     @Override
     public void onCancelled(@NonNull DatabaseError error) {

     }
 });





    }
}