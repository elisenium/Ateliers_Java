package domaine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CamionTest {
    @Mock
    private Trajet trajet;
    private Camion camion;

    @BeforeEach
    void setUp() {
        trajet = Mockito.mock(Trajet.class);
        camion = new Camion("Hello", 16400, 20);
        Mockito.when(trajet.getDate()).thenReturn(LocalDate.MAX);

        Mockito.when(trajet.calculerPoidsTotal()).thenReturn(200.0);
        Mockito.when(trajet.nbCaisses()).thenReturn(20000);
    }

    @Test
    void peutAjouter() {
        assertFalse(camion.ajouterTrajet(trajet));
    }
}