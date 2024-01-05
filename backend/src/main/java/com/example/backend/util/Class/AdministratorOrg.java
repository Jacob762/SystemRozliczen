package com.example.backend.util.Class;

public class AdministratorOrg {
    private String Nazwa;
    private int Id;
    private static int count = 0;
    public AdministratorOrg(String nazwa){
        this.Nazwa = nazwa;
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
}
