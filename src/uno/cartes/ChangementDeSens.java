package uno.cartes;

import uno.jeu.Uno;

public class ChangementDeSens extends Carte {
    public ChangementDeSens(Uno u, Couleur c) {
        super(u, c);
    }
    public int getValeur() {
        return 20;
    }

    public int effet() {
        return 2;
    }
    public void appliquerEffet() {
        getUno().changerDeSens();
    }

    @Override
    public boolean peutEtrePoseeSur(Chiffre c) {
        return this.couleur == c.getCouleur();
    }

    @Override
    public boolean peutEtrePoseeSur(Plus2 c) {
        return this.couleur == c.getCouleur();
    }

    @Override
    public boolean peutEtrePoseeSur(Joker c) {
        return this.couleur == c.getCouleur();
    }

    @Override
    public boolean peutEtrePoseeSur(Plus4 c) {
        return true;  // Peut être posé sur n'importe quel Plus4
    }

    @Override
    public boolean peutEtrePoseeSur(PasseTonTour c) {
        return this.couleur == c.getCouleur();
    }

    @Override
    public boolean peutEtrePoseeSur(ChangementDeSens c) {
        return this.couleur == c.getCouleur();
    }

    @Override
    public boolean peutEtreRecouvertePar(Carte c) {
        return c instanceof ChangementDeSens || (c instanceof Chiffre && this.couleur == c.getCouleur()) || (c instanceof Plus2 && this.couleur == c.getCouleur()) || (c instanceof Plus4) || (c instanceof Joker && this.couleur == c.getCouleur()) || (c instanceof PasseTonTour && this.couleur == c.getCouleur());
    }
    public String toString() {
        return "Changement de sens " + getCouleur();
    }
}