package com.example.installmenttrackingapp;

public class Customer {


    String name,phoneNO,address,advanceAmount,TotalAmount,ProductName;

  public    Customer(){}

    public Customer(String name, String phoneNO, String address, String advanceAmount, String totalAmount, String productName) {
        this.name = name;
        this.phoneNO = phoneNO;
        this.address = address;
        this.advanceAmount = advanceAmount;
        TotalAmount = totalAmount;
        ProductName = productName;
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
        return TotalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        TotalAmount = totalAmount;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }
}
