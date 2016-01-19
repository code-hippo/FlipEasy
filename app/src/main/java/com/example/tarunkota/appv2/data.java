package com.example.tarunkota.appv2;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by tarun.kota on 06/06/15.
 */
public class data {

    //ct is for browsing.
    dataProvider ct;


    public void init()
    {
        ct = new dataProvider();
        ct.addRoot("Browsing Categories");
        List<String> s = new ArrayList<>();
        s.add("electronics");
        s.add("men");
        s.add("women");
        s.add("babies and kids");
        s.add("home and kitchen");
        s.add("books");
        ct.addChildren("Browsing Categories", s);
       List<String> t = new ArrayList<>();
        t.add("mobiles");
        t.add("laptops");
        ct.addChildren("electronics", t);
        List<String> u = new ArrayList<>();
        u.add("Micromax Canvas");
        u.add("Moto E");
        u.add("Samsung Galaxy Neo");
        ct.addChildren("mobiles", u);

        List<String> f1= new ArrayList<>();
        List<String> f2= new ArrayList<>();
        List<String> f3= new ArrayList<>();

        f1.add("Rupees 8999. Features. Ram 16 Gb ,13 megapixel camera, Android version 4.4.2");
        ct.addChildren("Micromax Canvas", f1);
        f2.add("Rupees 8999. Features. Ram 16 Gb ,13 megapixel camera, Android version 4.4.2");
        ct.addChildren("Moto E", f2);
        f3.add("Rupees 12999. Features. Ram 16 Gb ,13 megapixel camera, Android version 4.4.2");
        ct.addChildren("Samsung Galaxy Neo", f3);


    }
}
