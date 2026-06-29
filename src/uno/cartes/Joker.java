package uno.cartes;
import uno.jeu.Uno;

public class Joker extends Carte {
    public Joker(Uno u) {
        super(u, null);
    }
    public int getValeur() {
        return 50;
    }

    public int effet() {
        return 3;
    }
    public void appliquerEffet() {
    }

    public boolean peutEtrePoseeSur(Chiffre c) {
        return true;
    }

    public boolean peutEtrePoseeSur(Plus2 c) {
        return true;
    }

    public boolean peutEtrePoseeSur(Plus4 c) {
        return true;
    }

    public boolean peutEtrePoseeSur(Joker c) {
        return true;
    }

    public boolean peutEtrePoseeSur(PasseTonTour c) {
        return true;
    }

    public boolean peutEtrePoseeSur(ChangementDeSens c) {
        return true;  // Peut être posé sur n'importe quel ChangementDeSens
    }
    public boolean peutEtreRecouvertePar(Carte c) {
        if (c instanceof Chiffre) {
            return this.couleur == c.getCouleur();
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
        if (getCouleur() != null) {
            return "Joker " + getCouleur();
        }
        return "Joker";
    }
}