package com.example.backend.util.testyFitnesse;

import com.example.backend.util.Class.Dokument;
import com.example.backend.util.Class.Organizacja;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fit.ColumnFixture;
import org.springframework.http.ResponseEntity;

public class FitnesseTest extends ColumnFixture{
    String []nazwa;
    float []kwota;

    public boolean dodajDokument(){
        int liczbaDok = nazwa.length;
        int i=0;
        int udane = 0;
        for(String item : nazwa){
            if(SetUp.organizacja.dodajDokument(new Dokument(item,kwota[i],SetUp.organizacja.getKsiegowy(SetUp.ksiegowyId)))) udane++;
            i++;
        }
        if(liczbaDok==udane) return true;
        return false;
    }

    public int getLiczbaDok() {
        return SetUp.organizacja.getDokumentySize();
    }

}
