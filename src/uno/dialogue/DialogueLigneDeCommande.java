package uno.dialogue;

import uno.jeu.Uno;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.List;

public class DialogueLigneDeCommande {
    private final Uno uno;
    public Uno getUno() {
        return uno;
    }
    public DialogueLigneDeCommande(Uno uno) {
        boolean choixJoueurCorrect = false;
        Scanner scanner = new Scanner(System.in);
        this.uno = uno;
        int nbJoueurs = 0;
        while (!choixJoueurCorrect) {
            try {
                System.out.println("Bienvenue dans cette partie de Uno !");
                System.out.println("choisir le nombres de joueurs ?");
                System.out.print("Entre 2 et 10 : ");
                nbJoueurs = scanner.nextInt();
                if (nbJoueurs >= 2 && nbJoueurs <= 10) {
                    choixJoueurCorrect = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("\nLe nombre de joueurs que vous avez choisi nest pas valide\n");
                scanner = new Scanner(System.in);
            }
        }


        this.uno.initialiser(nbJoueurs);
    }
    public void reagir() {
        System.out.println("Il reste " + getUno().getPioche().getNombreDeCartes() + " cartes dans la pioche.\n");
        for (int i = 2; i <= uno.getNbJoueurs(); ++i) {
            System.out.println("Il reste " + getUno().getJoueur(i).getMainDuJoueur().getNombreDeCartes() + " cartes dans la main du joueur " + i + ".");
        }
        System.out.println("\nLa carte en haut du talon est : " + getUno().getTalon().getSommet());
        System.out.println("\nVotre main est la suivante :");
        for (int i = 0; i < getUno().getJoueur(1).getMainDuJoueur().getNombreDeCartes(); ++i) {
            System.out.println(i + " : " + getUno().getJoueur(1).getCarteNumero(i));
        }
        System.out.println();
    }
    public String choisirCouleurJoker() {
        List<String> couleurs = List.of("r", "v", "b", "j");
        Scanner scanner = new Scanner(System.in);
        System.out.println("C'est un joker, choisi une couleur pour le jouer ?");
        String choix = "";
        while (!couleurs.contains(choix)) {
            System.out.print("(r, v, b ou j) : ");
            choix = scanner.nextLine();
        }
        return choix;
    }

    public String choisirCouleurPlus4() {
        List<String> couleurs = List.of("r", "v", "b", "j");
        Scanner scanner = new Scanner(System.in);
        System.out.println("C'est un Plus4, choisi une couleur pour le jouer ?");
        String choix = "";
        while (!couleurs.contains(choix)) {
            System.out.print("(r, v, b ou j) : ");
            choix = scanner.nextLine();
        }
        return choix;
    }
    public String tapercoup() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nC'est votre tour ! Que voulez-vous jouer ?");
        System.out.print("(Le numéro de votre carte,si vous jouer une carte Joker ou Plus4 ajouté la première lettre de la couleur souhaitée en miniscule) : ");
        return scanner.nextLine();
    }
    public void afficherMonScore(int nb) {
        System.out.println("Votre score est de : " + nb);
    }
    public void afficherScoreBot(int n, int nb) {
        System.out.println("Le joueur " + n + " à un score de : " + nb);
    }
    public void getErreur(String e) {
        System.out.println(e);
    }
    public void waitBot() {
        System.out.println("le bot réfléchit, patientez!!...\n");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }





}
