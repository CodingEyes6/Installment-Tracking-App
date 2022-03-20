package com.example.installmenttrackingapp;

public class Customer {


    String id,name,phoneNO,address,advanceAmount,totalAmount,productName,date;

  public    Customer(){}

    public Customer(String id,String name, String phoneNO, String address, String advanceAmount, String totalAmount, String productName,String date) {
        this.name = name;
        this.phoneNO = phoneNO;
        this.address = address;
        this.advanceAmount = advanceAmount;
        this.date = date;
        this.totalAmount = totalAmount;
        this.productName = productName;
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNO() {
        return phoneNO;
    }

    public void setPhoneNO(String phoneNO) {
        this.phoneNO = phoneNO;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAdvanceAmount() {
        return advanceAmount;
    }

    public void setAdvanceAmount(String advanceAmount) {
        this.advanceAmount = advanceAmount;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
