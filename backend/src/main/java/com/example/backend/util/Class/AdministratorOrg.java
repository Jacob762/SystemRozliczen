package com.example.backend.util.Class;

import jakarta.persistence.*;

@Entity
@Table(name = "organization_admins")

public class AdministratorOrg {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "ID")
    private User currentuser;

    @Column(name = "organization_id")
    private int organization_id;

    private String Nazwa;
    @Id
    @Column(name = "user_id")
    private int Id;
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
}
