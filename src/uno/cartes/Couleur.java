package uno.cartes;

public enum Couleur {
    JAUNE("Jaune"), VERT("Vert"), BLEU("Bleu"), ROUGE("Rouge");

    private final String nom;

    Couleur(String nom) {
        assert (nom.equals("Jaune") || nom.equals("Vert") || nom.equals("Bleu") || nom.equals("Rouge"))
                : "Couleur non autorisée pour une carte";
        this.nom = nom;
    }

    public String toString() {
        if (nom.equals("Jaune")) {
            return "J\uD83D\uDFE1";
        }
        if (nom.equals("Vert")) {
            return "V\uD83D\uDFE2";
        }
        if (nom.equals("Bleu")) {
            return "B\uD83D\uDD35";
        }
        if (nom.equals("Rouge")) {
            return "R\uD83D\uDD34";
        }
        return nom;
    }
}