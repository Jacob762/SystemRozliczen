package com.example.backend.util.testyFitnesse;

import com.example.backend.util.Class.Organizacja;
import fit.ColumnFixture;

public class FitnesseTest extends ColumnFixture{
    String nazwa;

    public boolean dodajOrg() {
        try{
            SetUp.organizacja = new Organizacja(nazwa);
            return true;
        } catch (Exception e){
        }
        return false;
    }

    public String getNazwa(){
        return SetUp.organizacja.getNazwa();
    }
}
