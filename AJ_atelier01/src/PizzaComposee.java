import java.util.ArrayList;

public class PizzaComposee extends Pizza {
    public static final int REMISE = 15;

    public PizzaComposee(String titre, String description, ArrayList<Ingredient> ingredients) {
        super(titre, description, ingredients);

        Util.checkString(titre);
        Util.checkString(description);
        if (ingredients.isEmpty())
            throw new IllegalArgumentException();
    }

    @Override
    public boolean ajouter(Ingredient ingredient) {
        throw new UnsupportedOperationException("Les ingrédients d'une pizza composée ne peuvent pas être modifiées");
    }

    @Override
    public boolean supprimer(Ingredient ingredient) {
        throw new UnsupportedOperationException("Les ingrédients d'une pizza composée ne peuvent pas être modifiées");
    }

    @Override
    public double calculerPrix() {
        double prixPizza = super.calculerPrix();
        return Math.ceil(prixPizza - (prixPizza * REMISE * 0.01)); // ceil() : arrondir à l'unité supérieure
    }
}
