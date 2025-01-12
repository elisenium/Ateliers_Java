package domaine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TrajetTest {
    Trajet trajet;
    Caisse caisse;
    LocalDate date, dateLimite;

    @BeforeEach
    void setUp() {
        date = LocalDate.of(LocalDate.now().getYear()+1, 1, 24);
        dateLimite = LocalDate.of(LocalDate.now().getYear()+1, 4, 24);
        trajet = new Trajet("1", date, "Bruxelles", "Arlon");
    }

    @Test
    void peutAjouterCaisseNull() {
        assertThrows(IllegalArgumentException.class, () -> {
           trajet.ajouter(null);
        });
    }

    @Test
    void peutAjouterMauvaiseCaisse() {
        caisse = new Caisse("28H90S", dateLimite, "Paris", "Arlon", 1000);
        assertFalse(trajet.ajouter(caisse));
    }

    @Test
    void peutAjouterBonneCaisse() {
        caisse = new Caisse("72P52C", dateLimite, "Bruxelles", "Arlon", 1000);
        assertTrue(trajet.ajouter(caisse));
    }
}