package uno.cartes;
import uno.jeu.Uno;

public abstract class Carte {
    protected Uno uno;
    protected Couleur couleur ;

    public Carte(Uno u) {
        this.uno = u;
    }
    public Carte(Uno u, Couleur c) {
        this.uno = u;
        this.couleur = c;
    }
    public abstract boolean peutEtreRecouvertePar(Carte c);

    public abstract int getValeur();
    public abstract int effet();

    public Couleur getCouleur() {
        return couleur;
    }
    public Uno getUno() {
        return uno;
    }

    public void setCouleur(Couleur c) {
        this.couleur = c;
    }
    public boolean estSansCouleur() {
        return couleur == null;
    }
    public abstract void appliquerEffet();
    public boolean estDeCouleurCompatibleAvec(Carte c) {
        return couleur == c.getCouleur();
    }
    public abstract boolean peutEtrePoseeSur(Chiffre c);

    public abstract boolean peutEtrePoseeSur(Plus2 c);

    public abstract boolean peutEtrePoseeSur(Plus4 c);

    public abstract boolean peutEtrePoseeSur(Joker c);

    public abstract boolean peutEtrePoseeSur(PasseTonTour c);

    public abstract boolean peutEtrePoseeSur(ChangementDeSens c);

}