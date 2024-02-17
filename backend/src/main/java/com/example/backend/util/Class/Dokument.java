package com.example.backend.util.Class;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import org.aspectj.weaver.ast.Or;

import java.util.Date;
@Entity
@Table(name = "documents")
public class Dokument {
    private String Nazwa;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int ID;
    @Column(name = "organization_id", insertable = false, updatable = false)///todo fk na organizacje
    private int organization_id;
    @Column(name = "creator_id", insertable = false, updatable = false)
    private int creator_id;
    private static int count = 0;
    @Column(name = "creation_date")
    private Date Data;
    @Column(name = "kwota")
    private float Kwota;
    @ManyToOne(fetch = FetchType.LAZY) ///todo dowiedziec sie czym sa fetchtype
    @JoinColumn(name = "creator_id", referencedColumnName = "user_id")
    private Ksiegowy Autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id", referencedColumnName = "id", nullable = false)
    private Organizacja organizacja;

    public Dokument(String nazwa, float kwota, Ksiegowy autor){
        this.Data = new Date();
        this.Nazwa = nazwa;
        this.Kwota = kwota;
        this.Autor = autor;
        this.ID = count;
        count++;
    }

    public Dokument() {
        this.Data = new Date();
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

    public Ksiegowy getAutor(){
        return this.Autor;
    }

    public Date getData(){
        return Data;
    }

    public float getKwota(){
        return this.Kwota;
    }
    public void setKwota(float kwota){this.Kwota = kwota;}
    public void setNazwa(String nazwa){this.Nazwa=nazwa;}
    public void setAutor(Ksiegowy ksiegowy){
        this.Autor = ksiegowy;
    }
    public int getOrganization_id(){return this.organization_id;}

    public void setOrganizacja(Organizacja organizacja){
        this.organizacja = organizacja;
        this.organization_id = organizacja.getId();
    }

}
