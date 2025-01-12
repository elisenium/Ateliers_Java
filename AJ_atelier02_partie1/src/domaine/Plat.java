package domaine;

import util.Util;

import java.time.Duration;
import java.util.*;

public class Plat {
    public enum Difficulte {
        X, XX, XXX, XXXX, XXXXX;

        @Override
        public String toString() {
            return super.toString().replace("X", "*");
        }
    }

    public enum Cout {
        $, $$, $$$, $$$$, $$$$$;

        @Override
        public String toString() {
            return super.toString().replace("$", "€");
        }
    }
    private String nom;
    private int nbPersonnes;
    private Difficulte niveauDifficulte;
    private Cout cout;
    private Duration dureeEnMinutes = Duration.ofMinutes(0);
    private List<Instruction> recette = new ArrayList<>();
    private Set<IngredientQuantifie> ingredients = new HashSet<>();

    public Plat(String nom, int nbPersonnes, Difficulte niveauDifficulte, Cout cout) {
        this.nom = nom;
        this.nbPersonnes = nbPersonnes;
        this.niveauDifficulte = niveauDifficulte;
        this.cout = cout;

    }

    public String getNom() {
        return nom;
    }

    public int getNbPersonnes() {
        return nbPersonnes;
    }

    public Difficulte getNiveauDifficulte() {
        return niveauDifficulte;
    }

    public Cout getCout() {
        return cout;
    }

    public Duration getDureeEnMinutes() {
        return dureeEnMinutes;
    }

    public List<Instruction> getRecette() {
        return recette;
    }

    public Set<IngredientQuantifie> getIngredients() {
        return ingredients;
    }

    //insère l’instruction à la position précisée, position commençant à 1.
    public void insererInstruction(int position, Instruction instruction) {
        Util.checkStrictlyPositive(position);
        Util.checkObject(instruction);
        if (position > recette.size() + 1)
            throw new IllegalArgumentException();

        recette.add(position-1, instruction);
        dureeEnMinutes = dureeEnMinutes.plus(instruction.getDureeEnMinute());

    }

    //ajoute l’instruction en dernier.
    public void ajouterInstruction (Instruction instruction) {
        Util.checkObject(instruction);

        recette.add(instruction);
        dureeEnMinutes = dureeEnMinutes.plus(instruction.getDureeEnMinute());

    }

    //remplace l’instruction à la position précisée par celle en paramètre.
    //renvoie l’instruction qui a été remplacée
    public Instruction remplacerInstruction (int position, Instruction instruction) {
        Util.checkObject(instruction);
        Util.checkStrictlyPositive(position);
        if (position > recette.size())
            throw new IllegalArgumentException();

        Instruction remplacee = recette.set(position-1, instruction);
        dureeEnMinutes = dureeEnMinutes.minus(remplacee.getDureeEnMinute());
        dureeEnMinutes = dureeEnMinutes.plus(instruction.getDureeEnMinute());
        return remplacee;

    }

    //supprimer l’instruction à la position précisée
    //renvoie l’instruction qui a été supprimée
    public Instruction supprimerInstruction (int position) {
        Util.checkStrictlyPositive(position);
        if (position > recette.size())
            throw new IllegalArgumentException();

        Instruction suprimee = recette.remove(position-1);
        dureeEnMinutes = dureeEnMinutes.minus(suprimee.getDureeEnMinute());
        return suprimee;
    }

    // fournit une collection non-modifiable contenant les instructions du plat considéré.

    public List<Instruction> instructions() {
        return Collections.unmodifiableList(recette);
    }

    @Override
    public String toString() {
        String hms = String.format("%d h %02d m", dureeEnMinutes.toHours(), dureeEnMinutes.toMinutesPart());
        String infos = nom + "\n\n" +
                "Pour " + nbPersonnes + " personnes\n" +
                "Difficulté : " + niveauDifficulte + "\n" +
                "Coût : " + cout + "\n" +
                "Durée : " + hms + "\n\n\n";

        String recetteString = "";
        int i = 1;
        for (Instruction instruction : this.recette) {
            String instuctionString = i + ". " + instruction + "\n";
            recetteString += instuctionString;
            i++;
        }

        return infos + recetteString;
    }
}
