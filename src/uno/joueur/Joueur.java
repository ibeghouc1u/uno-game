package uno.joueur;

import uno.erreur.ErreurFichier;
import uno.cartes.Carte;
import uno.jeu.Uno;
import uno.cartes.FabriqueCartes;
import uno.cartes.PaquetDeCartes;

public abstract class Joueur {
    private final PaquetDeCartes mainDuJoueur;
    final Uno uno;

    public Joueur(Uno uno) {
        FabriqueCartes singleton = FabriqueCartes.getInstance();
        mainDuJoueur = singleton.getPaquetVide();
        this.uno = uno;
    }
    public Uno getUno() {
        return uno;
    }
    public abstract boolean estUnJoueurHumain();
    public PaquetDeCartes getMainDuJoueur() {
        return mainDuJoueur;
    }

    public Carte getCarteNumero(int n) {
        return getMainDuJoueur().getPaquet().get(n);
    }
    public abstract void jouer(String coup) throws ErreurFichier;
}
