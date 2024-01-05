package com.example.backend.util.Class;

import java.util.Date;

public class Dokument {
    private String Nazwa;
    private int Id;
    private static int count = 0;
    private Date Data;
    private float Kwota;
    private Ksiegowy Autor;
    public Dokument(String nazwa, float kwota, Ksiegowy autor){
        this.Data = new Date();
        this.Nazwa = nazwa;
        this.Kwota = kwota;
        this.Autor = autor;
        this.Id = count;
        count++;
    }

    public static void setCount(int newCount) {
        count = newCount;
    }

    public static int getCount() {
        return count;
    }

    public String getNazwa(){
        return this.Nazwa;
    }

    public int getId(){
        return this.Id;
    }

    public Ksiegowy getAutor(){
        return this.Autor;
    }

    public Date getData(){
        return Data;
    }

    public float getKwota(){
        return this.Kwota;
    }
}
