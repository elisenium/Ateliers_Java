package domaine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;


import static org.junit.jupiter.api.Assertions.*;

class MoniteurImplTest {
    @Mock

    private Sport sportCompetent;
    private Moniteur moniteur;
    private Stage stageValide;
    @BeforeEach
    void setUp() {
        sportCompetent = Mockito.mock(Sport.class);
        moniteur = new MoniteurImpl("Smith");
        stageValide = Mockito.mock(Stage.class);

        Mockito.when(sportCompetent.contientMoniteur(moniteur)).thenReturn(true);

        Mockito.when(stageValide.getSport()).thenReturn(sportCompetent);
        Mockito.when(stageValide.getMoniteur()).thenReturn(null);
        Mockito.when(stageValide.getNumeroDeSemaine()).thenReturn(8);
    }

    private void amenerALEtat(int etat, Moniteur moniteur) {
        for (int i = 1; i <= etat; i++) {
            Stage stageAjoute = Mockito.mock(Stage.class);

            Mockito.when(stageAjoute.getSport()).thenReturn(sportCompetent);
            Mockito.when(stageAjoute.getMoniteur()).thenReturn(null);
            Mockito.when(stageAjoute.getNumeroDeSemaine()).thenReturn(i);

            moniteur.ajouterStage(stageAjoute);
        }
    }

    @Test
    void testerMoniteurTC1() {
        assertAll(() -> assertTrue(moniteur.ajouterStage(stageValide)),
                () -> assertTrue(moniteur.contientStage(stageValide)),
                () -> assertEquals(1, moniteur.nombreDeStages()),
                () -> Mockito.verify(stageValide).enregistrerMoniteur(moniteur)
        );
    }

    @Test
    void testerMoniteurTC2() {
        amenerALEtat(1, moniteur);
        assertAll(() -> assertTrue(moniteur.ajouterStage(stageValide)),
                () -> assertTrue(moniteur.contientStage(stageValide)),
                () -> assertEquals(2, moniteur.nombreDeStages()),
                () -> Mockito.verify(stageValide).enregistrerMoniteur(moniteur)
        );
    }

    @Test
    void testerMoniteurTC3() {
        amenerALEtat(2, moniteur);
        assertAll(() -> assertTrue(moniteur.ajouterStage(stageValide)),
                () -> assertTrue(moniteur.contientStage(stageValide)),
                () -> assertEquals(3, moniteur.nombreDeStages()),
                () -> Mockito.verify(stageValide).enregistrerMoniteur(moniteur)
        );
    }

    @Test
    void testerMoniteurTC4() {
        amenerALEtat(3, moniteur);
        assertAll(() -> assertTrue(moniteur.ajouterStage(stageValide)),
                () -> assertTrue(moniteur.contientStage(stageValide)),
                () -> assertEquals(4, moniteur.nombreDeStages()),
                () -> Mockito.verify(stageValide).enregistrerMoniteur(moniteur)
        );
    }

    @Test
    void testerMoniteurTC5() {
        amenerALEtat(3, moniteur);
        moniteur.ajouterStage(stageValide);
        assertAll(() -> assertFalse(moniteur.ajouterStage(stageValide)),
                () -> assertEquals(4, moniteur.nombreDeStages()),
                () -> Mockito.verify(stageValide).enregistrerMoniteur(moniteur)
        );
    }

    @Test
    void testerMoniteurTC6() {
        amenerALEtat(4, moniteur);
        Stage stageMemeSemaine = Mockito.mock(Stage.class);
        Mockito.when(stageMemeSemaine.getSport()).thenReturn(sportCompetent);
        Mockito.when(stageMemeSemaine.getMoniteur()).thenReturn(null);
        Mockito.when(stageMemeSemaine.getNumeroDeSemaine()).thenReturn(1);
        assertAll(() -> assertFalse(moniteur.ajouterStage(stageMemeSemaine)),
                () -> assertFalse(moniteur.contientStage(stageMemeSemaine)),
                () -> assertEquals(4, moniteur.nombreDeStages()),
                () -> Mockito.verify(stageMemeSemaine, Mockito.never()).enregistrerMoniteur(moniteur)
        );
    }

    @Test
    void testerMoniteurTC7() {
        amenerALEtat(4, moniteur);
        Moniteur autreMoniteur = new MoniteurImpl("Geoffrey");
        Stage stageAutreMoniteur = Mockito.mock(Stage.class);
        Mockito.when(stageAutreMoniteur.getSport()).thenReturn(sportCompetent);
        Mockito.when(stageAutreMoniteur.getMoniteur()).thenReturn(autreMoniteur);
        Mockito.when(stageAutreMoniteur.getNumeroDeSemaine()).thenReturn(5);

        assertAll(() -> assertFalse(moniteur.ajouterStage(stageAutreMoniteur)),
                () -> assertFalse(moniteur.contientStage(stageAutreMoniteur)),
                () -> assertEquals(4, moniteur.nombreDeStages()),
                () -> Mockito.verify(stageAutreMoniteur, Mockito.never()).enregistrerMoniteur(moniteur)
        );
    }

    @Test
    void testerMoniteurTC8() {
        amenerALEtat(4, moniteur);
        Stage stageBonMoniteur = Mockito.mock(Stage.class);
        Mockito.when(stageBonMoniteur.getSport()).thenReturn(sportCompetent);
        Mockito.when(stageBonMoniteur.getMoniteur()).thenReturn(moniteur);
        Mockito.when(stageBonMoniteur.getNumeroDeSemaine()).thenReturn(5);

        assertAll(() -> assertTrue(moniteur.ajouterStage(stageBonMoniteur)),
                () -> assertTrue(moniteur.contientStage(stageBonMoniteur)),
                () -> assertEquals(5, moniteur.nombreDeStages()),
                () -> Mockito.verify(stageBonMoniteur, Mockito.never()).enregistrerMoniteur(moniteur)
        );

    }

    @Test
    void testerMoniteurTC9() {
        Sport sportPasCompetent = Mockito.mock(Sport.class);
        Mockito.when(sportPasCompetent.contientMoniteur(moniteur)).thenReturn(false);

        Stage stagePasCompetent = Mockito.mock(Stage.class);
        Mockito.when(stagePasCompetent.getSport()).thenReturn(sportPasCompetent);
        Mockito.when(stagePasCompetent.getMoniteur()).thenReturn(null);
        Mockito.when(stagePasCompetent.getNumeroDeSemaine()).thenReturn(1);

        assertAll(() -> assertFalse(moniteur.ajouterStage(stagePasCompetent)),
                () -> assertFalse(moniteur.contientStage(stagePasCompetent)),
                () -> assertEquals(0, moniteur.nombreDeStages()),
                () -> Mockito.verify(stagePasCompetent, Mockito.never()).enregistrerMoniteur(moniteur)
        );
    }
}