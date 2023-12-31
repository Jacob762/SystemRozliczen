package com.example.backend.util.Class;

import java.util.*;

public class Organizacja {
    public List<Dokument> Dokumenty;
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

    public static void setCount(int newCount) {
        count = newCount;
    }

    public static int getCount() {
        return count;
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

    public AdministratorOrg getAdmin(int index){
        for(AdministratorOrg admin : Administratorzy) if(admin.getId()==index) return admin;
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

    public boolean dodajAdministratora(AdministratorOrg administratorOrg){
        try{
            Administratorzy.add(administratorOrg);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public boolean dodajKsiegowego(Ksiegowy ksiegowy) {
        try {
            Ksiegowi.add(ksiegowy);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean usunKsiegowego(int idKsieg) {
        for (int i = 0; i < Ksiegowi.size(); i++)
        {
            if (Ksiegowi.get(i).getId() == idKsieg) {
                Ksiegowi.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean dodajDokument(Dokument dokument){
        try{
            Dokumenty.add(dokument);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public boolean usunDokument(Dokument dokument){
        try{
            Dokumenty.remove(dokument);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public double totalStatystyka(){
        double wynik = 0.0;
        for(Dokument dokument : Dokumenty){
            wynik+= dokument.getKwota();
        }
        return wynik;
    }

    public double okresowaStatystyka(Date poczatek, Date koniec){
        double wynik = 0.0;
        for(Dokument dokument : Dokumenty){
            if(dokument.getData().after(poczatek)&&dokument.getData().before(koniec)) wynik+=dokument.getKwota();
        }
        return wynik;
    }

    public void sortujDokumenty(){
        Collections.sort(Dokumenty, Comparator.comparing(Dokument::getKwota));
    }
}
