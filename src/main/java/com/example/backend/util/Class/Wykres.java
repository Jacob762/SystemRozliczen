package com.example.backend.util.Class;

public class Wykres {
    private String Nazwa;
    private int Id;
    private static int count = 0;

    public Wykres(String nazwa){
        this.Nazwa = nazwa;
        this.Id = count;
        count++;
    }

    public int getId(){
        return this.Id;
    }

    public String getNazwa(){
        return this.Nazwa;
    }

    public void wyswietlWykres(){

    }

    public void setNazwa(String nazwa){
        this.Nazwa = nazwa;
    }
}
