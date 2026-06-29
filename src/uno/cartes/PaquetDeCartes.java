package uno.cartes;


import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import uno.erreur.ErreurFichier;
import uno.jeu.Uno;

import java.util.Iterator;


public class PaquetDeCartes implements Iterable<Carte> {
    // Champ pour stocker les cartes dans une ArrayList
    private ArrayList<Carte> cartes;

    // Constructeur par défaut
    public PaquetDeCartes() {
        cartes = new ArrayList<Carte>();
    }
	public void ajouter(Carte... cartes) {
		Collections.addAll(this.cartes, cartes);
	}

	public void ajouter(PaquetDeCartes pdc) {
		this.cartes.addAll(pdc.cartes);
	}
	// Méthode pour obtenir le nombre de cartes dans le paquet
    public int getNombreDeCartes() {
        return cartes.size();
    }
	public ArrayList<Carte> getPaquet() {
		return cartes;
	}
	public boolean estVide(){
		return cartes.isEmpty();
	}
	public int getValeur(){
		int somme = 0;

		for (Carte c : cartes) {
		   somme = somme+c.getValeur(); 
		}

		return somme;
	}
	public String toString(){
		StringBuilder stringB = new StringBuilder();

		for (Carte carte : cartes) {
			stringB.append(carte.toString()).append("\n");
		}

		return stringB.toString();
	}
	public void enlever(Carte carte){
		cartes.remove(carte);
	}
	public void melanger(){
		Collections.shuffle(cartes);
	}
	public void retourner(){
	  Collections.reverse(cartes);
	}
	public Carte getSommet() {
		if (this.estVide()) {
			return null;
		}
		return cartes.get(getNombreDeCartes() - 1);
	}
	public void ecrire(String args) throws IOException {
		PrintWriter flotFiltre;
		FileWriter flot;
		File chemin = new File(args);
		boolean estLa = chemin.exists();
		if (estLa) {
			throw new IOException("Fichier déjà existant !");
		}
		try {
			flot = new FileWriter(args);
			flotFiltre = new PrintWriter(flot);
			int i = 0;
			while (i < this.getNombreDeCartes()) {
				switch (cartes.get(i).effet()) {
					case 1:
						flotFiltre.println("CarteChiffre " + this.cartes.get(i).getCouleur() + " " + this.cartes.get(i).getValeur());
						break;
					case 2:
						flotFiltre.println("CarteChangementDeSens " + this.cartes.get(i).getCouleur());
						break;
					case 3:
						flotFiltre.println("CarteJoker");
						break;
					case 4:
						flotFiltre.println("CartePasseTonTour " + this.cartes.get(i).getCouleur());
						break;
					case 5:
						flotFiltre.println("CartePlus2 " + this.cartes.get(i).getCouleur());
						break;
					case 6:
						flotFiltre.println("CartePlus4");
						break;
				}
				i++;
			}
			flotFiltre.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void lire(String args) throws FileNotFoundException {
		FileReader file = new FileReader(args);
		BufferedReader buffer = new BufferedReader(file);
		String ligne;
		Uno uno = new Uno();
		try {
			while ((ligne = buffer.readLine()) != null) {
				String[] mots = ligne.split(" ");
				Couleur couleur = null;
				if (mots[0].equals("CarteChiffre")) {
					if (mots[1].equals("Bleu")) {
						couleur = Couleur.BLEU;
					}
					if (mots[1].equals("Rouge")) {
						couleur = Couleur.ROUGE;
					}
					if (mots[1].equals("Jaune")) {
						couleur = Couleur.JAUNE;
					}
					if (mots[1].equals("Vert")) {
						couleur = Couleur.VERT;
					}
					this.ajouter(new Chiffre(uno, couleur, Integer.parseInt(mots[2])));
				}
				if (mots[0].equals("CarteChangementDeSens")) {
					if (mots[1].equals("Bleu")) {
						couleur = Couleur.BLEU;
					}
					if (mots[1].equals("Rouge")) {
						couleur = Couleur.ROUGE;
					}
					if (mots[1].equals("Jaune")) {
						couleur = Couleur.JAUNE;
					}
					if (mots[1].equals("Vert")) {
						couleur = Couleur.VERT;
					}
					this.ajouter(new ChangementDeSens(uno, couleur));
				}
				if (mots[0].equals("CarteJoker")) {
					this.ajouter(new Joker(uno));
				}
				if (mots[0].equals("CartePasseTonTour")) {
					if (mots[1].equals("Bleu")) {
						couleur = Couleur.BLEU;
					}
					if (mots[1].equals("Rouge")) {
						couleur = Couleur.ROUGE;
					}
					if (mots[1].equals("Jaune")) {
						couleur = Couleur.JAUNE;
					}
					if (mots[1].equals("Vert")) {
						couleur = Couleur.VERT;
					}
					this.ajouter(new PasseTonTour(uno, couleur));
				}
				if (mots[0].equals("CartePlus2")) {
					if (mots[1].equals("Bleu")) {
						couleur = Couleur.BLEU;
					}
					if (mots[1].equals("Rouge")) {
						couleur = Couleur.ROUGE;
					}
					if (mots[1].equals("Jaune")) {
						couleur = Couleur.JAUNE;
					}
					if (mots[1].equals("Vert")) {
						couleur = Couleur.VERT;
					}
					this.ajouter(new Plus2(uno, couleur));
				}
				if (mots[0].equals("CartePlus4")) {
					this.ajouter(new Plus4(uno));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public Carte piocher() {
		if (!this.estVide()) {
			Carte c = this.cartes.get(0);
			this.cartes.remove(0);
			return c;
		}
		return null;
	}
	public Iterator<Carte> iterator() {
		return new PaquetDeCartesIterator(cartes);
	}
	
}



