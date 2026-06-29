package uno.jeu;
import uno.cartes.PaquetDeCartes;
import java.util.ArrayList;
import uno.cartes.FabriqueCartes;
import uno.dialogue.DialogueLigneDeCommande;
import uno.erreur.ErreurFichier;
import uno.joueur.Bot;
import uno.joueur.Joueur;
import uno.joueur.JoueurHumain;

public class Uno {
    private ArrayList<Joueur> Joueurs;
    private int QuiDistribue;
    private int QuiJoue;
    private boolean sensHoraire;
    private PaquetDeCartes pioche;
    private PaquetDeCartes talon;
    private int nbJoueurs;
    private boolean finjeu;
    private DialogueLigneDeCommande dialogue;

    public Uno(){}

    public int getJoueurQuiDistribue() {
        return QuiDistribue;
    }
    public int getNbJoueurs() {
        return nbJoueurs;
    }
    public boolean getSensHoraire() {
        return sensHoraire;
    }
    public PaquetDeCartes getTalon() {
        return talon;
    }
    public PaquetDeCartes getPioche() {
        return pioche;
    }
    public Joueur getJoueur(int nb) {
        return Joueurs.get(nb - 1);
    }
    public void setQuiDistribue(int QuiDistribue) {
        this.QuiDistribue = QuiDistribue;
    }
    public void setTalon(PaquetDeCartes talon) {
        this.talon = talon;
    }
    public void setPioche(PaquetDeCartes pioche) {
        this.pioche = pioche;
    }

    public void changerDeSens() {
        sensHoraire = !sensHoraire;
    }
    public void changerDeJoueur() {
        if (getSensHoraire()) {
            QuiJoue = (QuiJoue == getNbJoueurs()) ? 1 : QuiJoue + 1;
        } else {
            QuiJoue = (QuiJoue == 1) ? getNbJoueurs() : QuiJoue - 1;
        }
    }
    public void creerLesJoueurs(int nb) {
        assert (nb>= 2) : "Le nombre de joueur est insuffisant (<2).";
        assert (nb <= 10) : "Le nombre de joueur est trop élevé (>10).";
        this.nbJoueurs = nb;
        Joueurs = new ArrayList<>(nbJoueurs);
        Joueurs.add(new JoueurHumain(this));
        for (int i = 1; i < nbJoueurs; ++i) {
            Joueurs.add(new Bot(this));
        }
    }
    public void choisirQuiDistribue() {
        QuiDistribue = (int) ((Math.random() * (getNbJoueurs())));
    }
    public void choisirQuiJoue() {
        QuiJoue = getJoueurQuiDistribue() == getNbJoueurs() ? 0 : getJoueurQuiDistribue() + 1;
    }
    public int getQuiJoue() {
        return QuiJoue;
    }
    public Joueur joueurQuiJoue() {
        return getJoueur(getQuiJoue());
    }

    public DialogueLigneDeCommande getDialogue() {
        return dialogue;
    }



    public void distribuerCartes() {
        for (int i = 0; i < 7; ++i) {
            for (int j = 0; j < getNbJoueurs(); ++j) {
                Joueurs.get(j).getMainDuJoueur().ajouter(pioche.piocher());
            }
        }
        talon.ajouter(pioche.piocher());
    }
    public void setDialogue(DialogueLigneDeCommande dialogue) {
        this.dialogue = dialogue;
    }

    public boolean JeuTerminee() {
        return finjeu;
    }
    public void setfinjeu(boolean finjeu) {
        this.finjeu = finjeu;
    }
    public void initialiser(int nb) {
        finjeu = false;
        FabriqueCartes fabriquecarte = FabriqueCartes.getInstance();
        talon = fabriquecarte.getPaquetVide();
        pioche = fabriquecarte.getPaquetDeUno(this);
        pioche.melanger();
        creerLesJoueurs(nb);
        distribuerCartes();
        choisirQuiDistribue();
        choisirQuiJoue();
    }
    public void distribuerCartesJoueurSuivant(int nb) {
        if (getSensHoraire()) {
            if (getQuiJoue() == getNbJoueurs()) {
                for (int i = 0; i < nb; ++i) {
                    if(!getPioche().estVide()){
                        Joueurs.get(0).getMainDuJoueur().ajouter(pioche.piocher());}
                }
            } else {
                for (int i = 0; i < nb; ++i) {
                    if(!getPioche().estVide()){
                        Joueurs.get(getQuiJoue()).getMainDuJoueur().ajouter(pioche.piocher());}
                }
            }
        }
        if (!getSensHoraire()) {
            if (getQuiJoue() == 1) {
                for (int i = 0; i < nb; ++i) {
                    if(!getPioche().estVide()){
                        Joueurs.get(getNbJoueurs() - 1).getMainDuJoueur().ajouter(pioche.piocher());}
                }
            } else {
                for (int i = 0; i < nb; ++i) {
                    if(!getPioche().estVide()){
                        Joueurs.get(getQuiJoue() - 1).getMainDuJoueur().ajouter(pioche.piocher());
                    }}
            }
        }
    }
    public void jeu() {
        DialogueLigneDeCommande dialogue = new DialogueLigneDeCommande(this);
        setDialogue(dialogue);
        while (!JeuTerminee()) {
            getDialogue().reagir();
            if (joueurQuiJoue().estUnJoueurHumain()) {
                boolean coupIncorrect = true;
                while (coupIncorrect) {
                    try {
                        joueurQuiJoue().jouer(getDialogue().tapercoup());
                        coupIncorrect = false;
                    } catch (ErreurFichier e) {
                        getDialogue().getErreur(e.getMessage());
                    } catch (NumberFormatException e) {
                        getDialogue().getErreur("le nombre est grand");
                    }
                }
            } else {
                boolean coupIncorrect = true;
                while (coupIncorrect) {
                    try {
                        joueurQuiJoue().jouer("");
                        coupIncorrect = false;
                    } catch (ErreurFichier e) {
                        e.printStackTrace();
                    }
                }
                getDialogue().waitBot();
            }
            for (int i = 0; i < getNbJoueurs()-1; i++) {
                if ((getJoueur(i + 1).getMainDuJoueur().getNombreDeCartes() == 0 || getPioche().getNombreDeCartes() == 0) && !JeuTerminee()) {
                    for (int j = 1; j < getNbJoueurs(); j++) {
                        getDialogue().afficherScoreBot(j+1, getJoueur(j+1).getMainDuJoueur().getValeur());
                    }
                    getDialogue().afficherMonScore(getJoueur(1).getMainDuJoueur().getValeur());
                    setfinjeu(true);
                }
            }
            changerDeJoueur();
        }
    }





}







