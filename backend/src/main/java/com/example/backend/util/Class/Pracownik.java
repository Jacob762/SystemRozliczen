package com.example.backend.util.Class;

public class Pracownik {
    private String Nazwa;
    private int Id;
    private static int count = 0;
    public Pracownik(String nazwa){
        this.Nazwa = nazwa;
        this.Id = count++;
    }

    public String getNazwa(){
        return this.Nazwa;
    }

    public int getId(){
        return this.Id;
    }
}
