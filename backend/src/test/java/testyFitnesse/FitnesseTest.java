package testyFitnesse;

import com.example.backend.util.Class.Dokument;
import fit.ColumnFixture;

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
