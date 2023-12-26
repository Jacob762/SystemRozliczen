package com.example.backend.util.Class;

import java.util.ArrayList;
import java.util.List;

public class Organizacja {
    private List<Dokument> Dokumenty;
    private List<AdministratorOrg> Administratorzy;
    private List<Ksiegowy> Ksiegowi;
    private List<Pracownik> Pracownicy;
    private String Nazwa;
    private int Id;
    private static int count = 0;
    private List<Wykres> Wykresy;
    private List<Raport> Raporty;

    public Organizacja(String nazwa){
        this.Nazwa = nazwa;
        this.Id = count++;
        Pracownicy = new ArrayList<>();
        Ksiegowi = new ArrayList<>();
        Administratorzy = new ArrayList<>();
        Dokumenty = new ArrayList<>();
        Wykresy = new ArrayList<>();
        Raporty = new ArrayList<>();
    }

    public int getId(){
        return this.Id;
    }

    public String getNazwa(){
        return this.Nazwa;
    }

    public boolean dodajUzytkownika(String rodzaj, String nazwa){
        try{
            switch (rodzaj){
                case "Ksiegowy":
                    Ksiegowi.add(new Ksiegowy(nazwa));
                    return true;
                case "Pracownik":
                    Pracownicy.add(new Pracownik(nazwa));
                    return true;
                case "Admin":
                    Administratorzy.add(new AdministratorOrg(nazwa));
                    return true;
            }
        } catch (Exception e){
            return false;
        }
        return false;
    }

}
