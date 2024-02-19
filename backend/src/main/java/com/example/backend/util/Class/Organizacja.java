package com.example.backend.util.Class;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;

import java.util.*;

@Entity
@Table(name = "organizations")
//@Transactional
public class Organizacja {
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "organization_id", cascade = CascadeType.ALL)
    public List<Dokument> Dokumenty;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "organization_id", cascade = CascadeType.ALL)
    private List<AdministratorOrg> Administratorzy;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "organization_id", cascade = CascadeType.ALL)
    private List<Ksiegowy> Ksiegowi;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "organization_id", cascade = CascadeType.ALL)
    private List<Pracownik> Pracownicy;
    @Column(name = "name")
    private String Nazwa;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int ID;
    private static int count = 0;
//    private List<Wykres> Wykresy;
//    private List<Raport> Raporty;

    public Organizacja(String nazwa){
        this.Nazwa = nazwa;
        this.ID = count++;
        Pracownicy = new ArrayList<>();
        Ksiegowi = new ArrayList<>();
        Administratorzy = new ArrayList<>();
        Dokumenty = new ArrayList<>();
//        Wykresy = new ArrayList<>();
//        Raporty = new ArrayList<>();
    }

    public Organizacja() {
        Pracownicy = new ArrayList<>();
        Ksiegowi = new ArrayList<>();
        Administratorzy = new ArrayList<>();
        Dokumenty = new ArrayList<>();
    }

    public static void setCount(int newCount) {
        count = newCount;
    }

    public static int getCount() {
        return count;
    }

    public int getId(){
        return this.ID;
    }

    public Pracownik getPracownik(int index){
        for(int i=0;i< Pracownicy.size();i++) if(Pracownicy.get(i).getId()==index) return Pracownicy.get(i);
        return null;
    }

    public int getAdministratorSize() {
        return Administratorzy.size();
    }

    public int getKsiegowiSize(){
        return Ksiegowi.size();
    }

    public int getPracownicySize(){
        return Pracownicy.size();
    }
    public int getDokumentySize(){return Dokumenty.size();}
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
    public void setNazwa(String nazwa) {this.Nazwa = nazwa;}

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
        String []items = {"/","<",">","|"};
        try{
            if(dokument.getKwota()<=0) return false;
            for(String item : items) if (dokument.getNazwa().contains(item)) return false;
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

    public float totalStatystyka(){
        float wynik = 0.0F;
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
