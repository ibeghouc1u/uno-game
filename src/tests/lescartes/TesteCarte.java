package tests.lescartes;

import uno.cartes.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uno.jeu.Uno;

import static org.junit.jupiter.api.Assertions.*;

class TesteCarte {

    Uno uno;
    Chiffre carteBleu1;
    Chiffre carteBleu2;
    Chiffre carteRouge1;
    Chiffre carteRouge2;
    Chiffre carteJaune1;
    Chiffre carteJaune2;
    Chiffre carteVert1;
    Chiffre carteVert2;
    Plus2 carteBleuPlus2;
    Plus2 carteRougePlus2;
    ChangementDeSens carteBleuChangementDeSens;
    ChangementDeSens carteRougeChangementDeSens;
    PasseTonTour carteBleuPasseTonTour;
    PasseTonTour carteRougePasseTonTour;
    Joker carteJoker;
    Plus4 cartePlus4;

    @BeforeEach
    void init() {
        uno = new Uno();
        carteBleu1 = new Chiffre(uno, Couleur.BLEU, 1);
        carteBleu2 = new Chiffre(uno, Couleur.BLEU, 2);
        carteRouge1 = new Chiffre(uno, Couleur.ROUGE, 1);
        carteRouge2 = new Chiffre(uno, Couleur.ROUGE, 2);
        carteJaune1 = new Chiffre(uno, Couleur.JAUNE, 1);
        carteJaune2 = new Chiffre(uno, Couleur.JAUNE, 2);
        carteVert1 = new Chiffre(uno, Couleur.VERT, 1);
        carteVert2 = new Chiffre(uno, Couleur.VERT, 2);
        carteBleuPlus2 = new Plus2(uno, Couleur.BLEU);
        carteRougePlus2 = new Plus2(uno, Couleur.ROUGE);
        carteBleuChangementDeSens = new ChangementDeSens(uno, Couleur.BLEU);
        carteRougeChangementDeSens = new ChangementDeSens(uno, Couleur.ROUGE);
        carteBleuPasseTonTour = new PasseTonTour(uno, Couleur.BLEU);
        carteRougePasseTonTour = new PasseTonTour(uno, Couleur.ROUGE);
        carteJoker = new Joker(uno);
        cartePlus4 = new Plus4(uno);
    }

    @Test
    void getValeur() {
        assertEquals(1, carteBleu1.getValeur());
        assertEquals(2, carteBleu2.getValeur());
        assertEquals(1, carteRouge1.getValeur());
        assertEquals(2, carteRouge2.getValeur());
        assertEquals(1, carteJaune1.getValeur());
        assertEquals(2, carteJaune2.getValeur());
        assertEquals(1, carteVert1.getValeur());
        assertEquals(2, carteVert2.getValeur());
        assertEquals(20, carteBleuPlus2.getValeur());
        assertEquals(20, carteRougePlus2.getValeur());
        assertEquals(20, carteBleuChangementDeSens.getValeur());
        assertEquals(20, carteRougeChangementDeSens.getValeur());
        assertEquals(20, carteBleuPasseTonTour.getValeur());
        assertEquals(20, carteRougePasseTonTour.getValeur());
        assertEquals(50, carteJoker.getValeur());
        assertEquals(50, cartePlus4.getValeur());
    }

    @Test
    void effet() {
        assertEquals(1, carteBleu1.effet());
        assertEquals(1, carteBleu2.effet());
        assertEquals(1, carteRouge1.effet());
        assertEquals(1, carteRouge2.effet());
        assertEquals(1, carteJaune1.effet());
        assertEquals(1, carteJaune2.effet());
        assertEquals(1, carteVert1.effet());
        assertEquals(1, carteVert2.effet());
        assertEquals(5, carteBleuPlus2.effet());
        assertEquals(5, carteRougePlus2.effet());
        assertEquals(2, carteBleuChangementDeSens.effet());
        assertEquals(2, carteRougeChangementDeSens.effet());
        assertEquals(4, carteBleuPasseTonTour.effet());
        assertEquals(4, carteRougePasseTonTour.effet());
        assertEquals(3, carteJoker.effet());
        assertEquals(6, cartePlus4.effet());
    }

    @Test
    void getCouleur() {
        assertEquals(Couleur.BLEU, carteBleu1.getCouleur());
        assertEquals(Couleur.ROUGE, carteRouge1.getCouleur());
        assertEquals(Couleur.JAUNE, carteJaune1.getCouleur());
        assertEquals(Couleur.VERT, carteVert1.getCouleur());
        assertEquals(Couleur.BLEU, carteBleuPlus2.getCouleur());
        assertEquals(Couleur.ROUGE, carteRougePlus2.getCouleur());
        assertEquals(Couleur.BLEU, carteBleuChangementDeSens.getCouleur());
        assertEquals(Couleur.ROUGE, carteRougeChangementDeSens.getCouleur());
        assertEquals(Couleur.BLEU, carteBleuPasseTonTour.getCouleur());
        assertEquals(Couleur.ROUGE, carteRougePasseTonTour.getCouleur());
        assertNull(carteJoker.getCouleur());
        assertNull(cartePlus4.getCouleur());
    }

    @Test
    void setCouleur() {
        carteBleu1.setCouleur(Couleur.JAUNE);
        carteRouge1.setCouleur(Couleur.JAUNE);
        carteJaune1.setCouleur(Couleur.JAUNE);
        carteVert1.setCouleur(Couleur.JAUNE);
        carteBleuPlus2.setCouleur(Couleur.JAUNE);
        carteBleuChangementDeSens.setCouleur(Couleur.JAUNE);
        carteBleuPasseTonTour.setCouleur(Couleur.JAUNE);
        assertEquals(Couleur.JAUNE, carteBleu1.getCouleur());
        assertEquals(Couleur.JAUNE, carteRouge1.getCouleur());
        assertEquals(Couleur.JAUNE, carteJaune1.getCouleur());
        assertEquals(Couleur.JAUNE, carteVert1.getCouleur());
        assertEquals(Couleur.JAUNE, carteBleuPlus2.getCouleur());
        assertEquals(Couleur.JAUNE, carteBleuChangementDeSens.getCouleur());
        assertEquals(Couleur.JAUNE, carteBleuPasseTonTour.getCouleur());
    }

    @Test
    void estSansCouleur() {
        assertFalse(carteBleu1.estSansCouleur());
        assertFalse(carteBleu2.estSansCouleur());
        assertFalse(carteRouge1.estSansCouleur());
        assertFalse(carteRouge2.estSansCouleur());
        assertFalse(carteJaune1.estSansCouleur());
        assertFalse(carteJaune2.estSansCouleur());
        assertFalse(carteVert1.estSansCouleur());
        assertFalse(carteVert2.estSansCouleur());
        assertFalse(carteBleuPlus2.estSansCouleur());
        assertFalse(carteRougePlus2.estSansCouleur());
        assertFalse(carteBleuChangementDeSens.estSansCouleur());
        assertFalse(carteRougeChangementDeSens.estSansCouleur());
        assertFalse(carteBleuPasseTonTour.estSansCouleur());
        assertFalse(carteRougePasseTonTour.estSansCouleur());
        assertTrue(carteJoker.estSansCouleur());
        assertTrue(cartePlus4.estSansCouleur());
    }


}

