package com.example.drugone1;

public class Med {

    private String Name;
    private String Details;
//    private String Details2;
    private int Photo;

    public Med() {
    }

    public Med(String name, String details, int photo) {
        Name = name;
        Details = details;
//        Details2=details2;
        Photo = photo;
    }

    //Getter


    public String getName() {
        return Name;
    }

    public String getDetails() {
        return Details;
    }

//    public String getDetails2() { return Details2; }

    public int getPhoto() {
        return Photo;
    }


    //Setter


    public void setName(String name) {
        Name = name;
    }

    public void setDetails(String details) {
        Details = details;
    }

//    public void setDetails2(String details2) {
//        Details2 = details2;
//    }

    public void setPhoto(int photo) {
        Photo = photo;
    }

}
