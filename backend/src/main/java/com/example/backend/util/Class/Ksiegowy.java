package com.example.backend.util.Class;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "accountants")

public class Ksiegowy {
    @Id
    @Column(name = "user_id")
    private int ID;

    @OneToOne
    @JoinColumn(name = "user_id")
    @MapsId
    private User currentuser;
    @Column(name = "name")
    private String Nazwa;

    @Column(name = "organization_id", insertable = false, updatable = false)
    private int organization_id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "organization_id", referencedColumnName = "id")
    private Organizacja organizacja;

    @OneToMany(mappedBy = "creator_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dokument> dokumentList;


    private static int count = 0;



    public Ksiegowy() {
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
        return this.ID;
    }
    public int getOrganization_id(){return this.organization_id;}

    public void setNazwa(String nazwa){this.Nazwa = nazwa;}
    public void setUser(User user){this.currentuser = user;}
    public User getUser(){return this.currentuser;}
    public void setOrganization_id(int id){this.organization_id = id;}
    public void setOrganizacja(Organizacja organizacja){this.organizacja = organizacja;}

    public int getDocumentsNumber(){
        if(dokumentList==null) return 0;
        return this.dokumentList.size();
    }
}
