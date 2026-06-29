package tests.uno;

import uno.cartes.FabriqueCartes;

import uno.jeu.Uno;
import uno.joueur.Joueur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UnoTest {

    private Uno jeuUno;
    FabriqueCartes sing = FabriqueCartes.getInstance();

    @BeforeEach
    void setUp() {
        jeuUno = new Uno();
        jeuUno.creerLesJoueurs(4);
    }

    @Test
    void testGetJoueurQuiDistribue() {
        assertEquals(0, jeuUno.getJoueurQuiDistribue(), "La méthode getJoueurQuiDistribue ne fonctionne pas correctement.");
    }


    @Test
    void testGetSensHoraire() {
        assertFalse(jeuUno.getSensHoraire(), "La méthode getSensHoraire ne fonctionne pas correctement.");
    }

    @Test
    void testGetTalon() {
        assertNull(jeuUno.getTalon(), "La méthode getTalon ne fonctionne pas correctement.");
    }

    @Test
    void testGetPioche() {
        assertNull(jeuUno.getPioche(), "La méthode getPioche ne fonctionne pas correctement.");
    }

    @Test
    void testChangerDeSens() {
        jeuUno.changerDeSens();
        assertTrue(jeuUno.getSensHoraire(), "La méthode changerDeSens ne fonctionne pas correctement.");
    }

    @Test
    void changerDeJoueur() {
        jeuUno.creerLesJoueurs(3);
        jeuUno.setQuiDistribue(2);
        jeuUno.choisirQuiJoue();
        assertEquals(3, jeuUno.getQuiJoue());
    }




    @Test
    void testCreerLesJoueurs() {
        jeuUno.creerLesJoueurs(4);
        assertEquals(4, jeuUno.getNbJoueurs(), "La méthode creerLesJoueurs ne fonctionne pas correctement.");
    }

    @Test
    void testChoisirQuiDistribue() {
        jeuUno.creerLesJoueurs(5);
        jeuUno.choisirQuiDistribue();
        assertTrue(jeuUno.getJoueurQuiDistribue() >= 0 && jeuUno.getJoueurQuiDistribue() < jeuUno.getNbJoueurs(),
                "La méthode choisirQuiDistribue ne fonctionne pas correctement.");
    }

    @Test
    void testChoisirQuiJoue() {
        jeuUno.creerLesJoueurs(3);
        jeuUno.choisirQuiDistribue();
        jeuUno.choisirQuiJoue();
        assertNotEquals(jeuUno.getJoueurQuiDistribue(), jeuUno.getQuiJoue(),
                "La méthode choisirQuiJoue ne fonctionne pas correctement.");
    }

    @Test
    void distribuerCartes() {
        jeuUno.creerLesJoueurs(2);
        jeuUno.setPioche(sing.getPaquetDeUno(jeuUno));
        jeuUno.setTalon(sing.getPaquetVide());
        jeuUno.distribuerCartes();
        assertEquals(jeuUno.getJoueur(1).getMainDuJoueur().getNombreDeCartes(), 7);
        assertEquals(jeuUno.getJoueur(2).getMainDuJoueur().getNombreDeCartes(), 7);
        assertEquals(jeuUno.getPioche().getNombreDeCartes(), 93);
    }

    @Test
    void testInitialiser() {
        jeuUno.initialiser(4);
        assertNotNull(jeuUno.getPioche(), "La méthode initialiser ne fonctionne pas correctement (pioche nulle).");
        assertNotNull(jeuUno.getTalon(), "La méthode initialiser ne fonctionne pas correctement (talon nul).");
        assertEquals(4, jeuUno.getNbJoueurs(), "La méthode initialiser ne fonctionne pas correctement (nombre de joueurs).");
        assertNotNull(jeuUno.getJoueurQuiDistribue(), "La méthode initialiser ne fonctionne pas correctement (joueur qui distribue nul).");
        assertNotNull(jeuUno.getQuiJoue(), "La méthode initialiser ne fonctionne pas correctement (joueur qui joue nul).");
    }
}

