package com.example.drugone1;

public class Medi_Det {

    String det_id;
    String med_name;
    String med_detail;

    public Medi_Det() {

    }

    public Medi_Det(String det_id, String med_name, String med_detail) {
        this.det_id = det_id;
        this.med_name = med_name;
        this.med_detail = med_detail;
    }

    public String getDet_id() {
        return det_id;
    }

    public String getMed_name() {
        return med_name;
    }

    public String getMed_detail() {
        return med_detail;
    }
}
