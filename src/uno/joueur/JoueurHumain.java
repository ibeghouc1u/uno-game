package uno.joueur;
import uno.cartes.Carte;
import uno.cartes.Couleur;
import uno.erreur.ErreurFichier;
import uno.jeu.Uno;
import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JoueurHumain extends Joueur{
    public JoueurHumain(Uno uno) {
        super(uno);
    }
    public boolean estUnJoueurHumain() {
        return true;
    }
    public Carte carteChoisie(String coup) throws ErreurFichier {
        Matcher matcher = Pattern.compile("\\d+").matcher(coup);
        // On cherche un nombre.
        if (!matcher.find()) {
            throw new ErreurFichier("Erreur lors de l'identification du coup.");
        }
        String nombre = matcher.group(0);
        int numeroCarteChoisie = Integer.parseInt(nombre);
        if (numeroCarteChoisie >= getMainDuJoueur().getNombreDeCartes()) {
            throw new ErreurFichier("Erreur, la carte n'existe pas, recommencez.");
        }
        coup = coup.replace(nombre, "");
        // On vérifie la couleur fournie.
        List<String> couleurs = List.of("r", "v", "b", "j");
        if (!(couleurs.contains(coup) || coup.equals(""))) {
            throw new ErreurFichier("Erreur, la couleur n'est pas bonne, recommencez.");
        }
        int typeDeCarteChoisie = getCarteNumero(numeroCarteChoisie).effet();
        boolean estUneCartePlus4OuJoker = (typeDeCarteChoisie == 3 || typeDeCarteChoisie == 6);
        if (estUneCartePlus4OuJoker && coup.equals("")) {
            throw new ErreurFichier("Erreur, la carte doit prendre une couleur, recommencez.");
        }
        if (!estUneCartePlus4OuJoker && couleurs.contains(coup)) {
            throw new ErreurFichier("Erreur, la carte ne prends pas de couleur, recommencez.");
        }
        return getMainDuJoueur().getPaquet().get(numeroCarteChoisie);
    }
    public void jouer(String coup) throws ErreurFichier {
        Matcher matcher = Pattern.compile("\\d+").matcher(coup);
        String nombre = "nonTrouve";
        int numeroCarteChoisie = -1;

        if (matcher.find()) {
            nombre = matcher.group(0);
            numeroCarteChoisie = Integer.parseInt(nombre);
        }

        // Si le joueur indique p, on le fait piocher.
        if (coup.equals("p")) {
            Carte cartePiocher = getUno().getPioche().piocher();
            // Si la carte piochée peut être jouée, on la joue.
            if (getUno().getTalon().getSommet().peutEtreRecouvertePar(cartePiocher)) {
                // Si c'est une carte qui doit prendre une couleur, on la demande au joueur
                if (cartePiocher.effet() == 3) {
                    switch (getUno().getDialogue().choisirCouleurJoker().charAt(0)) {
                        case 'r':
                            cartePiocher.setCouleur(Couleur.ROUGE);
                            break;
                        case 'v':
                            cartePiocher.setCouleur(Couleur.VERT);
                            break;
                        case 'b':
                            cartePiocher.setCouleur(Couleur.BLEU);
                            break;
                        case 'j':
                            cartePiocher.setCouleur(Couleur.JAUNE);
                            break;
                    }
                } else if (cartePiocher.effet() == 6) {
                    switch (getUno().getDialogue().choisirCouleurPlus4().charAt(0)) {
                        case 'r':
                            cartePiocher.setCouleur(Couleur.ROUGE);
                            break;
                        case 'v':
                            cartePiocher.setCouleur(Couleur.VERT);
                            break;
                        case 'b':
                            cartePiocher.setCouleur(Couleur.BLEU);
                            break;
                        case 'j':
                            cartePiocher.setCouleur(Couleur.JAUNE);
                            break;
                    }
                }
                getUno().getTalon().ajouter(cartePiocher);
                cartePiocher.appliquerEffet(); // Appliquer l'effet après avoir ajouté la carte au talon.
            } else {
                // Sinon, on se contente de l'ajouter à notre main.
                getMainDuJoueur().ajouter(cartePiocher);
            }
        } else {
            // Si on a indiqué une carte à jouer.
            if (!nombre.equals("nonTrouve")) {
                Carte carteAJouer = carteChoisie(coup);
                // Si elle peut être jouée.
                if (getUno().getTalon().getSommet().peutEtreRecouvertePar(carteAJouer)) {
                    getMainDuJoueur().getPaquet().remove(numeroCarteChoisie);
                    // Si la carte nécessite de choisir une couleur, on la demande au joueur
                    if (carteAJouer.effet() == 3 || carteAJouer.effet() == 6) {
                        switch (getUno().getDialogue().choisirCouleurJoker().charAt(0)) {
                            case 'r':
                                carteAJouer.setCouleur(Couleur.ROUGE);
                                break;
                            case 'v':
                                carteAJouer.setCouleur(Couleur.VERT);
                                break;
                            case 'b':
                                carteAJouer.setCouleur(Couleur.BLEU);
                                break;
                            case 'j':
                                carteAJouer.setCouleur(Couleur.JAUNE);
                                break;
                        }
                    }
                    getUno().getTalon().ajouter(carteAJouer);
                    carteAJouer.appliquerEffet(); // Appliquer l'effet après avoir ajouté la carte au talon.
                }
                // Sinon, déclencher l'exception coupIncorrect.
                else {
                    throw new ErreurFichier("La carte ne peut être jouée sur le talon !");
                }
            }
            // Sinon, on passe son tour.
        }
    }

}
