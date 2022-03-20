package com.example.installmenttrackingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.installmenttrackingapp.databinding.ActivityMainBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Constants;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private DatePickerDialog datePickerDialog;

 //   private NamesAdapter adapter;
    private NamesAdapter adapter;
    private DatabaseReference reference;
    private List<Customer> customers;
    private List<String> keys;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        customers = new ArrayList<>();
        keys = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference("Customers");
        GridLayoutManager manager = new GridLayoutManager(getApplicationContext(), 2, LinearLayoutManager.VERTICAL, false);
        binding.namesRV.setLayoutManager(manager);
        binding.mainProgress.setVisibility(View.VISIBLE);




        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    for (DataSnapshot npsnapshot : snapshot.getChildren()) {
                        Customer customer = npsnapshot.getValue(Customer.class);

                        Log.d("msg", npsnapshot.getKey());
                        if(!isExist(npsnapshot.getKey())) {
                            customers.add(customer);
                            keys.add(npsnapshot.getKey());
                        }

                    }



                    adapter = new NamesAdapter(customers, getApplicationContext());


                    binding.namesRV.setAdapter(adapter);
                    binding.mainProgress.setVisibility(View.INVISIBLE);



                }
                else{
                    Toast.makeText(MainActivity.this, "No data exist", Toast.LENGTH_SHORT).show();
                    binding.mainProgress.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "failed", Toast.LENGTH_SHORT).show();
            }
        });



//            FirebaseRecyclerOptions<Customer> options = new FirebaseRecyclerOptions.Builder<Customer>()
//                    .setQuery(reference,Customer.class).build();
//            adapter = new CustomerAdapter(options);






        initDatePicker();

        setTitle();

        addNewPerson();

       // adapter = new NamesAdapter(names,this);




    }

    public boolean isExist(String key) {

        for (int i = 0; i < keys.size(); i++) {
            if (keys.get(i).equals(key)) {
                return true;
            }
        }

        return false;
    }

    private void initDatePicker() {

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener(){

            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1 = i1 + 1;
                String date = makeDateString(i,i1,i2);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);





    }

    private String makeDateString(int i, int i1, int i2) {
        return i + " " + i2 + " " + i2;
    }

    private void addNewPerson() {



        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                 @SuppressLint("InflateParams") View mView = getLayoutInflater().inflate(R.layout.custom_dialog,null);
                TextInputEditText customerName = mView.findViewById(R.id.personName);
                TextInputEditText customerPhoneNo = mView.findViewById(R.id.personPhoneNo);
                TextInputEditText customerAddress = mView.findViewById(R.id.personAddress);
                TextInputEditText customerAdvancedAMount = mView.findViewById(R.id.advanceAmount);
                TextInputEditText customerTotalAMount = mView.findViewById(R.id.totalAmount);
                TextInputEditText customerProductName = mView.findViewById(R.id.productName);
                ProgressBar progressBar = mView.findViewById(R.id.customerProgressBAr);
                Button addCustomerBtn = mView.findViewById(R.id.addCustomer);
                Button cancelDialog = mView.findViewById(R.id.cancelDialog);
                TextView dateDialog = mView.findViewById(R.id.date);


                Date c = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
                String formattedDate = df.format(c);
                dateDialog.setText(formattedDate);

                alert.setView(mView);

                final AlertDialog alertDialog = alert.create();
                alertDialog.setCanceledOnTouchOutside(false);


                dateDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Calendar cal = Calendar.getInstance();
                        int year = cal.get(Calendar.YEAR);
                        int month = cal.get(Calendar.MONTH);
                        int day = cal.get(Calendar.DAY_OF_MONTH);

                        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,R.style.dialogSTyleStyle, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                dateDialog.setText(i + "/" + (i1 + 1) + "/" + i2);
                            }
                        },year,month,day);
                        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                        datePickerDialog.show();
                    }
                });

                addCustomerBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        progressBar.setVisibility(View.VISIBLE);
if(customerName.getText().toString().isEmpty()){
    Toast.makeText(MainActivity.this, "Please enter name", Toast.LENGTH_SHORT).show();
    progressBar.setVisibility(View.INVISIBLE);
}
else if(customerPhoneNo.getText().toString().isEmpty()){
    Toast.makeText(MainActivity.this, "Please enter Phone No", Toast.LENGTH_SHORT).show();
    progressBar.setVisibility(View.INVISIBLE);
}
else if(customerAddress.getText().toString().isEmpty()){
    Toast.makeText(MainActivity.this, "Please enter address", Toast.LENGTH_SHORT).show();
    progressBar.setVisibility(View.INVISIBLE);
}
else if(customerAdvancedAMount.getText().toString().isEmpty()){
    Toast.makeText(MainActivity.this, "Please enter advance Amount", Toast.LENGTH_SHORT).show();
    progressBar.setVisibility(View.INVISIBLE);
}
else if(customerTotalAMount.getText().toString().isEmpty()){
    Toast.makeText(MainActivity.this, "Please enter Total Amount", Toast.LENGTH_SHORT).show();
    progressBar.setVisibility(View.INVISIBLE);
}
else if(customerProductName.getText().toString().isEmpty()){
    Toast.makeText(MainActivity.this, "Please enter product name", Toast.LENGTH_SHORT).show();
    progressBar.setVisibility(View.INVISIBLE);
}
else if(formattedDate.equals(dateDialog.getText().toString())){
    Toast.makeText(MainActivity.this, "Please enter address", Toast.LENGTH_SHORT).show();
    progressBar.setVisibility(View.INVISIBLE);
}
else {
    String key = reference.push().getKey();
    Customer customer = new Customer(key,customerName.getText().toString(), customerPhoneNo.getText().toString(), customerAddress.getText().toString(), customerAdvancedAMount.getText().toString(), customerTotalAMount.getText().toString(), customerProductName.getText().toString(), dateDialog.getText().toString());
    reference.child(key).setValue(customer).addOnCompleteListener(new OnCompleteListener<Void>() {
        @Override
        public void onComplete(@NonNull Task<Void> task) {
            if (task.isSuccessful()) {
                progressBar.setVisibility(View.INVISIBLE);
                alertDialog.dismiss();
                Toast.makeText(MainActivity.this, "data added", Toast.LENGTH_SHORT).show();
            } else {

                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(MainActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
            }
        }
    });
}
//                   names.add(customerName.getText().toString());
//                   adapter.notifyDataSetChanged();
                    }
                });

                cancelDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
alertDialog.show();
            }
        });

    }

    private void setTitle() {
        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);
    }
}