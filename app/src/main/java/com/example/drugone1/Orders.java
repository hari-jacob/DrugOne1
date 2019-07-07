package com.example.drugone1;

import android.net.Uri;
import android.widget.ImageView;

public class Orders {

    private String med_name;
    private String med_detail;
//    private String med_pic;

    public Orders() {
    }

    public Orders(String med_name, String med_detail) {
        this.med_name = med_name;
        this.med_detail = med_detail;
    }

    public String getMed_name() {
        return med_name;
    }

    public String getMed_detail() {
        return med_detail;
    }

//    public String getMed_pic() {
//        return med_pic;
//    }
}
