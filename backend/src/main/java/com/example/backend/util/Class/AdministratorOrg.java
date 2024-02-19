package com.example.backend.util.Class;

import jakarta.persistence.*;

@Entity
@Table(name = "organization_admins")

public class AdministratorOrg {

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
    @JoinColumn(name = "organization_id", referencedColumnName = "id")
    private Organizacja organizacja;
    @Column(name = "name")
    private String Nazwa;

    private static int count = 0;
    public AdministratorOrg(String nazwa){
        this.Nazwa = nazwa;
        this.Id = count;
        count++;
    }

    public AdministratorOrg() {

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

}
