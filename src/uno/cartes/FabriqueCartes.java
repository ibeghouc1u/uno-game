package uno.cartes;


import uno.jeu.Uno;

public class FabriqueCartes {
    private static FabriqueCartes instance = null;

    private FabriqueCartes() {
       
    }

    public static FabriqueCartes getInstance() {
        if (instance == null) {
            instance = new FabriqueCartes();
        }
        return instance;
    }

    public static PaquetDeCartes getPaquetVide() {
        PaquetDeCartes paquet = new PaquetDeCartes();
       
        return paquet;
    }

    public PaquetDeCartes getPaquetDeUno(Uno uno) {
        PaquetDeCartes pdc = new PaquetDeCartes();
        Carte[] cartesBleues = new Carte[25];
        Carte[] cartesRouges = new Carte[25];
        Carte[] cartesJaunes = new Carte[25];
        Carte[] cartesVertes = new Carte[25];
        Joker[] cartesJokers = new Joker[4];
        Plus4[] cartesPlus4 = new Plus4[4];
        for (int i = 0; i < 19; ++i) {
            cartesBleues[i] = new Chiffre(uno, Couleur.BLEU, (i + 1) / 2);
            cartesRouges[i] = new Chiffre(uno, Couleur.ROUGE, (i + 1) / 2);
            cartesVertes[i] = new Chiffre(uno, Couleur.JAUNE, (i + 1) / 2);
            cartesJaunes[i] = new Chiffre(uno, Couleur.VERT, (i + 1) / 2);
        }
        for (int i = 19; i < 21; ++i) {
            cartesBleues[i] = new Plus2(uno, Couleur.BLEU);
            cartesRouges[i] = new Plus2(uno, Couleur.ROUGE);
            cartesVertes[i] = new Plus2(uno, Couleur.JAUNE);
            cartesJaunes[i] = new Plus2(uno, Couleur.VERT);
        }
        for (int i = 21; i < 23; ++i) {
            cartesBleues[i] = new PasseTonTour(uno, Couleur.BLEU);
            cartesRouges[i] = new PasseTonTour(uno, Couleur.ROUGE);
            cartesJaunes[i] = new PasseTonTour(uno, Couleur.JAUNE);
            cartesVertes[i] = new PasseTonTour(uno, Couleur.VERT);
        }
        for (int i = 23; i < 25; ++i) {
            cartesBleues[i] = new ChangementDeSens(uno, Couleur.BLEU);
            cartesRouges[i] = new ChangementDeSens(uno, Couleur.ROUGE);
            cartesJaunes[i] = new ChangementDeSens(uno, Couleur.JAUNE);
            cartesVertes[i] = new ChangementDeSens(uno, Couleur.VERT);
        }
        for (int i = 0; i < 4; ++i) {
            cartesJokers[i] = new Joker(uno);
            cartesPlus4[i] = new Plus4(uno);
        }
        pdc.ajouter(cartesBleues);
        pdc.ajouter(cartesRouges);
        pdc.ajouter(cartesJokers);
        pdc.ajouter(cartesPlus4);
        pdc.ajouter(cartesJaunes);
        pdc.ajouter(cartesVertes);
        return pdc;
    }
}