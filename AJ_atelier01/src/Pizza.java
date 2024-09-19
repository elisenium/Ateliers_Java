import java.util.ArrayList;
import java.util.Iterator;

public abstract class Pizza implements Iterable<Ingredient> {
    public static final double PRIX_BASE = 5.0;
    private String titre;
    private String description;
    private ArrayList<Ingredient> ingredients = new ArrayList<>();

    public Pizza(String titre, String description) {
        Util.checkString(titre);
        Util.checkString(description);

        this.titre = titre;
        this.description = description;
    }

    public Pizza(String titre, String description, ArrayList<Ingredient> ingredients) {
        this(titre, description);

        Util.checkString(titre);
        Util.checkString(description);

        if (ingredients.isEmpty())
            throw new IllegalArgumentException();

        this.description = description;
        for (Ingredient i : ingredients) {
            if (this.ingredients.contains(i))
                throw new IllegalArgumentException("Il ne peut pas y avoir deux fois le même ingrédient dans une pizza");
            this.ingredients.add(i);
        }
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public boolean ajouter(Ingredient ingredient) {
        Util.checkObject(ingredients);

        if (ingredients.contains(ingredient))
            return false;
        return ingredients.add(ingredient);
    }

    public boolean supprimer(Ingredient ingredient) {
        Util.checkObject(ingredients);

        if (!ingredients.contains(ingredient))
            return false;
        return ingredients.remove(ingredient);
    }

    public double calculerPrix() {
        double prix = PRIX_BASE;
        for (Ingredient ingredient : ingredients) {
            prix += ingredient.getPrix();
        }
        return prix;
    }

    public Iterator<Ingredient> iterator() {
        return ingredients.iterator();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        String infos = titre + "\n" + description + "\nIngrédients : ";
        for (Ingredient ingredient : ingredients){
            infos +="\n" + ingredient.getNom();
        }
        infos +="\nprix : " + calculerPrix() + " euros";
        return infos;
    }
}
