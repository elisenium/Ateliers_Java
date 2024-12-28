package domaine;

import exceptions.QuantiteNonAutoriseeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class PrixTest {



    private Prix prixAucune;
    private Prix prixPub;
    private Prix prixSolde;

    @BeforeEach
    void setUp() {
        prixAucune = new Prix();
        prixPub = new Prix(TypePromo.PUB, 20);
        prixSolde = new Prix(TypePromo.SOLDE, 50);

        prixAucune.definirPrix(1, 20);
        prixAucune.definirPrix(10, 10);

        prixPub.definirPrix(3, 15);
    }

    // Tests getters

    @Test
    @DisplayName("Test de la valeur renvoyée par getTypePromo")
    void getTypePromo() {
        assertAll(() -> assertNull(prixAucune.getTypePromo()),
                  () -> assertSame(TypePromo.PUB, prixPub.getTypePromo()),
                  () -> assertSame(TypePromo.SOLDE, prixSolde.getTypePromo()));
    }

    @Test
    @DisplayName("Test de la valeur renvoyée par getValeurPromo")
    void getValeurPromo() {
        assertAll(() -> assertEquals(0, prixAucune.getValeurPromo()),
                  () -> assertEquals(20, prixPub.getValeurPromo()),
                  () -> assertEquals(50, prixSolde.getValeurPromo()));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1,0})
    @DisplayName("Test de la valeur renvoyée par definirPrix")
    void definirPrix1(int quantite) {
        assertThrows(IllegalArgumentException.class, () -> prixPub.definirPrix(quantite, 15));
    }
    @ParameterizedTest
    @ValueSource(ints = {-7,0})
    @DisplayName("Test de la valeur renvoyée par definirPrix")
    void definirPrix2(double prix) {
        assertThrows(IllegalArgumentException.class, () -> prixPub.definirPrix(12, prix));
    }

    @Test
    @DisplayName("Test de la valeur renvoyée par definirPrix")
    void definirPrix3() {
        prixAucune.definirPrix(20,5);
        assertEquals(5, prixAucune.getPrix(20));
    }

    // Test constructeur

    @ParameterizedTest
    @ValueSource(ints = {-4, 0})
    @DisplayName("Test de la valeur renvoyée par getPrix")
    void getPrix1(int quantite) {
        assertThrows(IllegalArgumentException.class, () -> prixPub.getPrix(quantite));
    }

    @ParameterizedTest
    @ValueSource(ints = {10,15,20,35})
    @DisplayName("Test de la valeur renvoyée par getPrix")
    void getPrix2(int quantite) {
        assertEquals(15, prixPub.getPrix(quantite));
    }

    @Test
    @DisplayName("Test de getPrix avec une quantite < à la plus petite quantité minimale")
    void testGetPrix3() throws QuantiteNonAutoriseeException {
        assertThrows(QuantiteNonAutoriseeException.class,()->prixPub.getPrix(2));
    }

    @Test
    @DisplayName("Test de getPrix s'il n'y a pas de prix défini")
    void testGetPrix4() {
        assertThrows(QuantiteNonAutoriseeException.class,()->prixSolde.getPrix(1));
    }
}