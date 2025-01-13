package test;

import be.vinci.aj.domain.Locomotive;
import be.vinci.aj.domain.Train;
import be.vinci.aj.domain.Vehicule;
import be.vinci.aj.domain.Wagon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TrainTest {
    @Mock
    private Locomotive locomotive;
    private Train train;
    private Wagon wagon;

    @BeforeEach
    void setUp() {
        locomotive = Mockito.mock(Locomotive.class);
        train = new Train("Bruxelles", "Paris", 10800000);
        wagon = Mockito.mock(Wagon.class);
    }

    @Test
    void ajouterVehiculeTU1() {
        Mockito.when(locomotive.getPoids()).thenReturn(2000);
        Mockito.when(locomotive.getPuissance()).thenReturn(6000);

        assertAll(
                () -> assertTrue(train.ajouterVehicule(locomotive)),
                () -> assertEquals(1, train.getVehicules().size()),
                () -> assertEquals(locomotive, train.getVehicules().get(0))
        );
    }

    @Test
    void ajouterVehiculeTU2() {
        Mockito.when(locomotive.getPoids()).thenReturn(2000);
        Mockito.when(locomotive.getPuissance()).thenReturn(6000);
        Mockito.when(wagon.getPoids()).thenReturn(2000);

        train.ajouterVehicule(locomotive);
        assertAll(
                () -> assertTrue(train.ajouterVehicule(wagon)),
                () -> assertEquals(2, train.getVehicules().size()),
                () -> assertEquals(locomotive, train.getVehicules().get(0)),
                () -> assertEquals(wagon, train.getVehicules().get(1))
        );
    }

    @Test
    void ajouterVehiculeTU3() {
        Mockito.when(locomotive.getPoids()).thenReturn(2000);
        Mockito.when(locomotive.getPuissance()).thenReturn(6000);
        Mockito.when(wagon.getPoids()).thenReturn(2000);

        train.ajouterVehicule(locomotive);
        train.ajouterVehicule(wagon);

        Mockito.when(wagon.getPoids()).thenReturn(3000);

        assertAll(
                () -> assertFalse(train.ajouterVehicule(wagon)),
                () -> assertEquals(2, train.getVehicules().size()),
                () -> assertEquals(locomotive, train.getVehicules().get(0))
        );
    }

    @Test
    void ajouterVehiculeTU4() {
        Mockito.when(locomotive.getPoids()).thenReturn(2000);
        Mockito.when(locomotive.getPuissance()).thenReturn(1000);

        assertThrows(IllegalArgumentException.class, () -> {
            train.ajouterVehicule(locomotive);
        });
    }
}