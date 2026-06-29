package uno.cartes;
import uno.jeu.Uno;

public class Plus4 extends Carte {
    public Plus4(Uno u) {
        super(u, null);
    }

    public int getValeur() {
        return 50;
    }

    public int effet() {
        return 6;
    }
    public void appliquerEffet() {
        getUno().distribuerCartesJoueurSuivant(4);
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
        return true;
    }
    public boolean peutEtreRecouvertePar(Carte c) {
        if (c instanceof Chiffre) {
            return false;
        } else if (c instanceof Plus2) {
            return true;
        } else if (c instanceof Joker) {
            return false;
        } else if (c instanceof Plus4) {
            return true;
        } else if (c instanceof ChangementDeSens) {
            return false;
        }else if (c instanceof PasseTonTour) {
            return false;
        }
        return false;
    }
    public String toString() {
        if (getCouleur() != null) {
            return "+4 " + getCouleur();
        }
        return "+4";
    }

}