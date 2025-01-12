package domaine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoniteurImplTest {
    private Sport sportCompetent;
    private Moniteur moniteur;
    private Stage stageValide;

    @BeforeEach
    void setUp() {
        sportCompetent = new SportStub(true);
        moniteur = new MoniteurImpl("Joris");
        stageValide = new StageStub(8, sportCompetent, null);

    }

    private void amenerALEtat(int etat, Moniteur moniteur) {
        for (int i = 1; i <= etat; i++) {
            moniteur.ajouterStage(new StageStub(i, sportCompetent, null));
        }

    }

    @Test
    void testerMoniteurTC1() {
        assertAll(() -> assertTrue(moniteur.ajouterStage(stageValide)),
                () -> assertTrue(moniteur.contientStage(stageValide)),
                () -> assertEquals(1, moniteur.nombreDeStages())
                );
    }

    @Test
    void testerMoniteurTC2() {
        amenerALEtat(1, moniteur);
        assertAll(() -> assertTrue(moniteur.ajouterStage(stageValide)),
                () -> assertTrue(moniteur.contientStage(stageValide)),
                () -> assertEquals(2, moniteur.nombreDeStages()));
    }

    @Test
    void testerMoniteurTC3() {
        amenerALEtat(2, moniteur);
        assertAll(() -> assertTrue(moniteur.ajouterStage(stageValide)),
                () -> assertTrue(moniteur.contientStage(stageValide)),
                () -> assertEquals(3, moniteur.nombreDeStages()));
    }

    @Test
    void testerMoniteurTC4() {
        amenerALEtat(3, moniteur);
        assertAll(() -> assertTrue(moniteur.ajouterStage(stageValide)),
                () -> assertTrue(moniteur.contientStage(stageValide)),
                () -> assertEquals(4, moniteur.nombreDeStages()));
    }

    @Test
    void testerMoniteurTC5() {
        amenerALEtat(3, moniteur);
        moniteur.ajouterStage(stageValide);
        assertAll(() -> assertTrue(moniteur.supprimerStage(stageValide)),
                () -> assertFalse(moniteur.contientStage(stageValide)),
                () -> assertEquals(3, moniteur.nombreDeStages()));
    }

    @Test
    void testerMoniteurTC6() {
        amenerALEtat(2, moniteur);
        moniteur.ajouterStage(stageValide);
        assertAll(() -> assertTrue(moniteur.supprimerStage(stageValide)),
                () -> assertFalse(moniteur.contientStage(stageValide)),
                () -> assertEquals(2, moniteur.nombreDeStages()));
    }

    @Test
    void testerMoniteurTC7() {
        amenerALEtat(1, moniteur);
        moniteur.ajouterStage(stageValide);
        assertAll(() -> assertTrue(moniteur.supprimerStage(stageValide)),
                () -> assertFalse(moniteur.contientStage(stageValide)),
                () -> assertEquals(1, moniteur.nombreDeStages()));
    }

    @Test
    void testerMoniteurTC8() {
        moniteur.ajouterStage(stageValide);
        assertAll(() -> assertTrue(moniteur.supprimerStage(stageValide)),
                () -> assertFalse(moniteur.contientStage(stageValide)),
                () -> assertEquals(0, moniteur.nombreDeStages()));
    }

    @Test
    void testerMoniteurTC9() {
        amenerALEtat(3, moniteur);
        moniteur.ajouterStage(stageValide);
        assertAll(() -> assertFalse(moniteur.ajouterStage(stageValide)),
                () -> assertEquals(4, moniteur.nombreDeStages()));
    }

    @Test
    void testerMoniteurTC10() {
        amenerALEtat(4, moniteur);
        Stage stageMemeSemaine = new StageStub(1, sportCompetent, null);
        assertAll(() -> assertFalse(moniteur.ajouterStage(stageMemeSemaine)),
                () -> assertFalse(moniteur.contientStage(stageMemeSemaine)),
                () -> assertEquals(4, moniteur.nombreDeStages()));
    }

    @Test
    void testerMoniteurTC11() {
        amenerALEtat(4, moniteur);
        assertAll(() -> assertFalse(moniteur.supprimerStage(stageValide)),
                () -> assertEquals(4, moniteur.nombreDeStages()));
    }

    @Test
    void testerMoniteurTC12() {
        amenerALEtat(4, moniteur);
        Stage stageAutreMoniteur = new StageStub(6, sportCompetent, new MoniteurImpl("Jack"));
        assertAll(() -> assertFalse(moniteur.ajouterStage(stageAutreMoniteur)),
                () -> assertFalse(moniteur.contientStage(stageAutreMoniteur)),
                () -> assertEquals(4, moniteur.nombreDeStages()));
    }

    @Test
    void testerMoniteurTC13() {
        amenerALEtat(4, moniteur);
        Stage stageBonMoniteur = new StageStub(8, sportCompetent, moniteur);
        assertAll(() -> assertTrue(moniteur.ajouterStage(stageBonMoniteur)),
                () -> assertTrue(moniteur.contientStage(stageBonMoniteur)),
                () -> assertEquals(5, moniteur.nombreDeStages()));
    }

    @Test
    void testerMoniteurTC14() {
        amenerALEtat(4, moniteur);
        Sport sportPasCompetent = new SportStub(false);
        Stage stageMauvaisSport = new StageStub(8, sportPasCompetent, moniteur);
        assertAll(() -> assertFalse(moniteur.ajouterStage(stageMauvaisSport)),
                () -> assertFalse(moniteur.contientStage(stageMauvaisSport)),
                () -> assertEquals(4, moniteur.nombreDeStages()));
    }
}