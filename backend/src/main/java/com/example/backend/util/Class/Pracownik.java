package com.example.backend.util.Class;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")

public class Pracownik {
    @Id
    @Column(name = "user_id")
    private int Id;
    @OneToOne
    @JoinColumn(name = "user_id")
    @MapsId
    private User currentuser;

    @Column(name = "organization_id", insertable = false, updatable = false)
    private int organization_id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "organization_id", referencedColumnName = "id") //problem
    private Organizacja organizacja;
    @Column(name = "name")
    private String Nazwa;
    private static int count = 0;
    public Pracownik(String nazwa){
        this.Nazwa = nazwa;
        this.Id = count++;
    }

    public Pracownik() {
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
    public int getOrganization_id(){return this.organization_id;}
    public User getUser(){return this.currentuser;}
    public void setUser(User user){this.currentuser = user;}
    public void setOrganizacja(Organizacja organizacja){this.organizacja = organizacja;}

    public void setNazwa(String nazwa){this.Nazwa = nazwa;}

}
