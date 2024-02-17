package com.example.backend.util.Class;

import jakarta.persistence.*;

@Entity
@Table(name = "app_admin")
public class AdministratorAPK {
    private String Nazwa;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "ID")
    private User currentUser;
    private String Imie;
    private String Nazwisko;
    @Id
    @Column(name = "user_id")
    private int Id;
    private static int count = 0;
    public AdministratorAPK(String nazwa){
        this.Nazwa = nazwa;
        this.Id = count;
        count++;
    }

    public AdministratorAPK() {
        this.Imie = currentUser.getImie();
        this.Nazwisko = currentUser.getNazwisko();
        this.Nazwa = Imie + " " + Nazwisko;
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
