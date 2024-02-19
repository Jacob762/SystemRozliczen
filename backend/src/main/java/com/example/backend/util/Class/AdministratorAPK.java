package com.example.backend.util.Class;

import jakarta.persistence.*;

@Entity
@Table(name = "app_admin")
public class AdministratorAPK {
    @Id
    @Column(name = "user_id")
    private int Id;
    @OneToOne
    @JoinColumn(name = "user_id")
    @MapsId
    private User currentuser;
    @Column(name = "name")
    private String Nazwa;
    private static int count = 0;
    public AdministratorAPK(String nazwa){
        this.Nazwa = nazwa;
        this.Id = count;
        count++;
    }

    public AdministratorAPK() {

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

    public User getUser(){return this.currentuser;}
}
