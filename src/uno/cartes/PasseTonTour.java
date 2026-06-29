package uno.cartes;
import uno.jeu.Uno;
public class PasseTonTour extends Carte {
    public PasseTonTour(Uno u, Couleur c) {
        super(u, c);
    }
    public int getValeur() {
        return 20;
    }

    public int effet() {
        return 4;
    }
    public void appliquerEffet() {
        getUno().changerDeJoueur();
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
        return c instanceof PasseTonTour || (c instanceof Chiffre && this.couleur == c.getCouleur()) || (c instanceof Plus2 && this.couleur == c.getCouleur()) || (c instanceof Plus4) || (c instanceof Joker && this.couleur == c.getCouleur()) || (c instanceof ChangementDeSens && this.couleur == c.getCouleur());
    }
    public String toString() {
        return "Passe ton tour " + getCouleur();
    }
}