package com.example.backend.util.Class;

public class AdministratorAPK {
    private String Nazwa;
    private int Id;
    private static int count = 0;
    public AdministratorAPK(String nazwa){
        this.Nazwa = nazwa;
        this.Id = count;
        count++;
    }

    public String getNazwa(){
        return this.Nazwa;
    }

    public int getId(){
        return this.Id;
    }
}
