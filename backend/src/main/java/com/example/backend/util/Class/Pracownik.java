package com.example.backend.util.Class;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")

public class Pracownik {
    private String Nazwa;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "ID")
    private User currentuser;

    @Column(name = "organization_id")
    private int organization_id;
    @Id
    @Column(name = "user_id")
    private int Id;
    private static int count = 0;
    public Pracownik(String nazwa){
        this.Nazwa = nazwa;
        this.Id = count++;
    }

    public Pracownik() {
        this.Nazwa = currentuser.getImie() + " " + currentuser.getNazwisko();
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
