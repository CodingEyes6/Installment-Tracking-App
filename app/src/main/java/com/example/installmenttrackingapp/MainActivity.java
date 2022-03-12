package com.example.installmenttrackingapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;


import com.example.installmenttrackingapp.databinding.ActivityMainBinding;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private DatePickerDialog datePickerDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initDatePicker();

        setTitle();

        addNewPerson();

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
                        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
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
                     binding.name.setText(customerName.getText().toString());
                        binding.productNameActivity.setText(customerProductName.getText().toString());
                        binding.name.setText(customerAdvancedAMount.getText().toString());
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