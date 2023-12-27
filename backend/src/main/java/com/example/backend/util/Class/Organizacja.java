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

    public Pracownik getPracownik(int index){
        for(int i=0;i< Pracownicy.size();i++) if(Pracownicy.get(i).getId()==index) return Pracownicy.get(i);
        return null;
    }
    public Dokument getDokument(int index){
        for(int i=0;i< Dokumenty.size();i++) if(Dokumenty.get(i).getId()==index) return Dokumenty.get(i);
        return null;
    }
    public Ksiegowy getKsiegowy(int index) {
        for(int i=0;i< Ksiegowi.size();i++) if(Ksiegowi.get(i).getId()==index) return Ksiegowi.get(i);
        return null;
    }

    public String getNazwa(){
        return this.Nazwa;
    }

    public boolean dodajPracownika(Pracownik pracownik){
        try{
            Pracownicy.add(pracownik);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public void wyswietlPracownikow(){
        int size = Pracownicy.size();
        for(int i=0;i<size;i++) System.out.println(Pracownicy.get(i).getId() + "   " + Pracownicy.get(i).getNazwa());
    }

    public boolean dodajDokument(Dokument dokument){
        try{
            Dokumenty.add(dokument);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
