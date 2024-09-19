public class MainPizzeria {

    public static void main(String[] args) {
        Client emmeline = new Client("Leconte", "Emmeline", "0488/98.23.85");
        Client stephanie = new Client("Ferneeuw", "Stéphanie", "0475/51.30.80");
        Commande commandeEmmeline = new Commande(emmeline);
        System.out.println("Commande en cours d'Emmeline : " +emmeline.getCommandeEnCours());
        System.out.println();
        try {
            new Commande(emmeline);
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        System.out.println();
        commandeEmmeline.ajouter(MenuPizzeria.PIZZA_DUCHEF, 2);
        PizzaComposable pizzaComposable = new PizzaComposable(emmeline);
        pizzaComposable.ajouter(Ingredients.AUBERGINES);
        pizzaComposable.ajouter(Ingredients.TOMATE);
        pizzaComposable.ajouter(Ingredients.GORGONZOLA);
        emmeline.cloturerCommandeEnCours();
        System.out.println(commandeEmmeline);
        System.out.println(commandeEmmeline.detailler());
        System.out.println();
        System.out.println("Commande en cours d'Emmeline : " +emmeline.getCommandeEnCours());
        System.out.println();
        System.out.println("ajout d'une pizza à une commande clôturée : " +commandeEmmeline.ajouter(MenuPizzeria.PIZZA_4FROMAGES) );
        System.out.println();
        Commande commandeStephanie = new Commande(stephanie);
        commandeStephanie.ajouter(MenuPizzeria.PIZZA_MARGARITA, 1);
        commandeStephanie.ajouter(MenuPizzeria.PIZZA_MARGARITA);
        commandeStephanie.ajouter(MenuPizzeria.PIZZA_MARINIERE, 3);
        System.out.println(commandeStephanie);
        System.out.println(commandeStephanie.detailler());
        System.out.println("Montant de la commande de Stéphanie : " + commandeStephanie.calculerMontantTotal());
        System.out.println();
        Commande commandeEmmeline2 = new Commande(emmeline);
        pizzaComposable = new PizzaComposable(emmeline);
        pizzaComposable.ajouter(Ingredients.JAMBON);
        pizzaComposable.ajouter(Ingredients.TOMATE);
        pizzaComposable.ajouter(Ingredients.OLIVES);
        pizzaComposable.ajouter(Ingredients.MOZZARELLA);
        System.out.println(commandeEmmeline2);

        /* TESTS PARTIE OPTIONNELLE */
//        System.out.println("TEST COMMANDES PASSÉES : ");
//        System.out.println(stephanie.getCommandeEnCours());
//        System.out.println();
//        stephanie.cloturerCommandeEnCours();
//        Commande commandeStephanie2 = new Commande(stephanie);
//        commandeStephanie2.ajouter(MenuPizzeria.PIZZA_4SAISONS, 4);;
//        stephanie.cloturerCommandeEnCours();
//        System.out.println(stephanie.historiqueCommandes());
//        commandeStephanie2.retirer(MenuPizzeria.PIZZA_4SAISONS);
//        Commande commandeStephanie3 = new Commande(stephanie);
//        commandeStephanie3.ajouter(MenuPizzeria.PIZZA_MARGARITA, 2);
//        commandeStephanie3.ajouter(MenuPizzeria.PIZZA_DUCHEF, 5);
//        commandeStephanie3.retirer(MenuPizzeria.PIZZA_DUCHEF, 2);
//        commandeStephanie3.retirer(MenuPizzeria.PIZZA_MARGARITA);
//        System.out.println(commandeStephanie3.detailler());
//        commandeStephanie3.supprimer(MenuPizzeria.PIZZA_MARGARITA);
//        System.out.println(commandeStephanie3.detailler());
//        commandeStephanie3.supprimer(MenuPizzeria.PIZZA_4FROMAGES);
//        System.out.println(commandeStephanie3.detailler());

//        Client c = new Client("", "", "");
//        Client c1 = new Client(null, null, null);
//        Commande com = new Commande(null);
//        Ingredient i = new Ingredient(null, 0);
//        Ingredient i1 = new Ingredient("", 0);
//        LigneDeCommande ldc = new LigneDeCommande(null, -1);
//        PizzaComposable pc = new PizzaComposable(null);
//        PizzaComposee pizzaComposee = new PizzaComposee("", "", null);
//        PizzaComposee pizzaComposee1 = new PizzaComposee(null, null, null);
//        PizzaComposee pizzaComposee2 = new PizzaComposee(null, null, ingredientArrayList);
//        ArrayList<Ingredient> ingredientArrayList = new ArrayList<>();
//        PizzaComposee pizzaComposee3 = new PizzaComposee("a", "b", ingredientArrayList);

    }

}
