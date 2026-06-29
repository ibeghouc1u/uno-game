package tests.lescartes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uno.jeu.Uno;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import uno.cartes.PaquetDeCartes;
import java.util.Iterator;
import uno.cartes.*;



import static org.junit.jupiter.api.Assertions.*;
import uno.erreur.ErreurFichier;

class TestePaquetDeCartes {

    private Uno uno;
    private PaquetDeCartes paquet;
    File file = new File("TestEcrire.txt");

    @BeforeEach
    void setUp() {
        uno = new Uno();
        FabriqueCartes fabriqueCartes = FabriqueCartes.getInstance(); // Utilisation de l'instance unique
        paquet = fabriqueCartes.getPaquetDeUno(uno);
    }

    @Test
    void testAjouter() {
        int tailleInitiale = paquet.getNombreDeCartes();
        Carte nouvelleCarte = new Chiffre(uno, Couleur.BLEU, 3);

        paquet.ajouter(nouvelleCarte);

        assertEquals(tailleInitiale + 1, paquet.getNombreDeCartes());

    }

    @Test
    void testEstVide() {
        assertFalse(paquet.estVide());

        paquet = new PaquetDeCartes(); // Nouveau paquet vide

        assertTrue(paquet.estVide());

    }
    @Test
    void testEnlever() {
        Carte carte = paquet.getSommet();
        int tailleInitiale = paquet.getNombreDeCartes();

        paquet.enlever(carte);

        assertEquals(tailleInitiale - 1, paquet.getNombreDeCartes());
        assertFalse(paquet.toString().contains(carte.toString()));
    }
    @Test
    void testMelonger() {
        PaquetDeCartes paquetAvantMelange = new PaquetDeCartes();
        paquetAvantMelange.ajouter(paquet.getSommet());

        paquet.melanger();

        assertNotEquals(paquetAvantMelange.toString(), paquet.toString());
    }

    @Test
    void testRetourner() {
        PaquetDeCartes paquetAvantRetournement = new PaquetDeCartes();
        paquetAvantRetournement.ajouter(paquet.getSommet());

        paquet.retourner();

        assertNotEquals(paquetAvantRetournement.toString(), paquet.toString());
    }
    @Test
    void testGetSommet() {
        assertNotNull(paquet.getSommet());
    }
    @Test
    void testGetNombreDeCartes() {
        assertEquals(108, paquet.getNombreDeCartes());
    }
    @Test
    void testEcrire() throws IOException {
        //On supprime le fichier "ecrireTest.txt" s'il existe avant le test.
        if (file.delete()) {
            System.out.println(file.getName() + " est supprimé.");
        } else {
            System.out.println("Opération de suppression echouée.");
        }
        paquet.ecrire("TestEcrire.txt") ;
    }
    @Test
    void testIterator() {
        // Créer un paquet de cartes avec quelques cartes pour les tests
        Carte carte1 = new Plus2(uno, Couleur.BLEU);
        Carte carte2 = new Plus2(uno, Couleur.BLEU);
        Carte carte3 = new Plus2(uno, Couleur.BLEU);

        PaquetDeCartes paquet = new PaquetDeCartes();
        paquet.ajouter(carte1, carte2, carte3);

        // Obtenez un itérateur à partir du paquet de cartes
        Iterator<Carte> iterator = paquet.iterator();

        // Testez hasNext et next pour chaque carte
        assertTrue(iterator.hasNext());
        assertEquals(carte1, iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals(carte2, iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals(carte3, iterator.next());

        // Il ne devrait plus y avoir d'éléments dans l'itérateur
        assertFalse(iterator.hasNext());

        // next après la fin de l'itérateur devrait lever une exception
        assertThrows(UnsupportedOperationException.class, iterator::next);
    }
    @Test
    void lire() throws FileNotFoundException {
        paquet.lire("lireTest.txt");
        System.out.println(paquet);
    }


}
