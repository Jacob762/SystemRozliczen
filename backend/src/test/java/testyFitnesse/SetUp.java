package testyFitnesse;

import com.example.backend.util.Class.Ksiegowy;
import com.example.backend.util.Class.Organizacja;


import fit.Fixture;


public class SetUp extends Fixture{
    static Organizacja organizacja;
    static Ksiegowy ksiegowy;
    static int ksiegowyId;
    static int organizacjaId;


    public SetUp(){
        organizacja = new Organizacja("NazwaTest");
        ksiegowy = new Ksiegowy();
        ksiegowy.setNazwa("Grzesiek");
        organizacjaId = organizacja.getId();
        ksiegowyId = ksiegowy.getId();

    }

}
