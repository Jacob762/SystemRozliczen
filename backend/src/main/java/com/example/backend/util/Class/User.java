package com.example.backend.util.Class;


import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int ID;

    @Column(name = "first_name")
    private String imie;

    @Column(name = "last_name")
    private String nazwisko;

    @OneToOne(mappedBy = "currentuser")
    private Ksiegowy ksiegowy;

    public String getImie(){
        return this.imie;
    }
    public void setImie(String imie){
        this.imie = imie;
    }

    public String getNazwisko(){
        return this.nazwisko;
    }

    public void setNazwisko(String nazwisko){
        this.nazwisko = nazwisko;
    }
    public int getID(){return this.ID;}
}
