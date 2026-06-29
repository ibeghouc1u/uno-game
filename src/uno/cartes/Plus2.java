package uno.cartes;
import uno.jeu.Uno;

public class Plus2 extends Carte {
    public Plus2(Uno u, Couleur c) {
        super(u, c);
    }
    public int getValeur() {
        return 20;
    }

    public int effet() {
        return 5;
    }
    public void appliquerEffet() {
        getUno().distribuerCartesJoueurSuivant(2);
    }

    public boolean peutEtrePoseeSur(Chiffre c) {
        return this.couleur == c.getCouleur();
    }
    public boolean peutEtrePoseeSur(Plus2 c) {
        return true;
    }
    public boolean peutEtrePoseeSur(Plus4 c) {
        return true;
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
            return this.couleur == c.getCouleur();
        } else if (c instanceof Plus2) {
            return true;
        } else if (c instanceof Joker) {
            return this.couleur == c.getCouleur();
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
        return "+2 " + getCouleur();
    }
}