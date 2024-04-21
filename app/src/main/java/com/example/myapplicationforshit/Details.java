package com.example.myapplicationforshit;

import java.util.ArrayList;
import java.util.List;

public class Details {
    private String text;
    private String address;

    public Details() {
        text="none";
        address="none";
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public static List<Details> GenerateDetails(){
        List<Details> details=new ArrayList<Details>();
        return  details;
    }
}
