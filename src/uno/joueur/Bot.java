package uno.joueur;
import uno.cartes.Carte;
import uno.cartes.Couleur;
import uno.cartes.PaquetDeCartes;
import uno.jeu.Uno;
import uno.erreur.ErreurFichier;
import java.util.Random;


public class Bot extends Joueur{
    public Bot(Uno uno) {
        super(uno);
    }
    public boolean estUnJoueurHumain() {
        return false;
    }
    public void jouer(String coup) throws ErreurFichier {
        boolean leBotAJoue = false;
        PaquetDeCartes mainDuBot = getMainDuJoueur();

        for (int i = 0; i < mainDuBot.getNombreDeCartes(); i++) {
            Carte carteActuelle = mainDuBot.getPaquet().get(i);

            if (!leBotAJoue && uno.getTalon().getSommet().peutEtreRecouvertePar(carteActuelle)) {
                leBotAJoue = true;
                mainDuBot.getPaquet().remove(i);
                uno.getTalon().ajouter(carteActuelle);

                if (carteActuelle.estSansCouleur()) {
                    Couleur[] tab = new Couleur[]{Couleur.JAUNE, Couleur.ROUGE, Couleur.VERT, Couleur.BLEU};
                    Random rand = new Random();
                    Couleur c = tab[rand.nextInt(4)];
                    uno.getTalon().getSommet().setCouleur(c);
                }

                uno.getTalon().getSommet().appliquerEffet(); // Appliquer l'effet de la carte que le bot a jouée.
            }
        }

        if (!leBotAJoue) {
            Carte cartePiochee = uno.getPioche().piocher();
            mainDuBot.getPaquet().add(cartePiochee);

            if (uno.getTalon().getSommet().peutEtreRecouvertePar(mainDuBot.getSommet())) {
                uno.getTalon().ajouter(mainDuBot.getSommet());
                mainDuBot.getPaquet().remove(mainDuBot.getNombreDeCartes() - 1);
            }
            uno.getTalon().getSommet().appliquerEffet();

        }
    }

}
