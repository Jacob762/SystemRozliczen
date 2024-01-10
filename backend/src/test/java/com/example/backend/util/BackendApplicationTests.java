package com.example.backend.util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BackendApplicationTests {

    @Test
    void contextLoads() {
    } ///todo na jedna osobe jedna funkcjonalnosc, jedna funkcja nie oznacza jednego testu, testow ma byc kilka testujacych skrajne przypadki
        // kazda wartosc osobna funkcja testujaca, funkcje maja miec wlasne osobne nazwy, pokazujace co testuja
        // pelne funkcje zamockowane, przynajmniej dwie funkcje bez mocka dwie z mockiem, przynajmniej 4 funkcje przetestowac
        // w jednym lancuszku, na nastepnych zajeciach fitness uzywany, za pomoca fitnessa ze dwie funkcjonalnosci na grupe
        // teraz jmock + junit
}
