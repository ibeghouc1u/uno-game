package uno.cartes;
import uno.jeu.Uno;

public class Chiffre extends Carte {
    private int valeur;

    public Chiffre(Uno u, Couleur c, int valeur) {
        super(u, c);
        this.valeur = valeur;
    }

    public int getValeur() {
        return valeur;
    }

    public int effet() {
        return 1;
    }
    public void appliquerEffet() {
    }

    public boolean peutEtrePoseeSur(Chiffre c) {
        return this.valeur == c.getValeur() || this.couleur == c.getCouleur();
    }

    public boolean peutEtrePoseeSur(Plus2 c) {
        return this.couleur == c.getCouleur();
    }

    public boolean peutEtrePoseeSur(Plus4 c) {
        return this.couleur == c.getCouleur();
    }
    public boolean peutEtrePoseeSur(Joker c) {
        return this.couleur == c.getCouleur();
    }
    public boolean peutEtrePoseeSur(PasseTonTour c) {
        return this.couleur == c.getCouleur();
    }
    public boolean peutEtrePoseeSur(ChangementDeSens c) {
        return this.couleur == c.getCouleur();
    }
    public boolean peutEtreRecouvertePar(Carte c) {
        if (c instanceof Chiffre) {
            return this.valeur == c.getValeur()|| this.couleur == c.getCouleur();
        } else if (c instanceof Plus2) {
            return this.couleur == c.getCouleur();
        } else if (c instanceof Joker) {
            return true;
        } else if (c instanceof Plus4) {
            return true;
        } else if (c instanceof ChangementDeSens) {
            return this.couleur == c.getCouleur();
        }else if (c instanceof PasseTonTour) {
            return this.couleur == c.getCouleur();
        }
        return false;
    }
    public String toString() {
        return getValeur() + " " + getCouleur();
    }
}