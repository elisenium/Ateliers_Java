package main;

import domaine.*;
import domaine.Plat.Cout;
import domaine.Plat.Difficulte;

import java.util.Iterator;

public class Main {

	public static void main(String[] args) {

		Plat plat = null;
		plat = new Plat("Waterzooi", 4, Difficulte.XX, Cout.$$$, Plat.Type.PLAT);

		Instruction instruction = new Instruction("Couper les légumes", 15);
		try {
			plat.insererInstruction(0,instruction);
		} catch(IllegalArgumentException iae){}
		plat.ajouterInstruction(instruction);
		instruction = new Instruction("Faire revenir les légumes", 5);
		plat.ajouterInstruction(instruction);
		instruction = new Instruction("Laisser mijoter jusqu'à cuisson du poulet",50);
		try {
			plat.insererInstruction(4,instruction);
		} catch(IllegalArgumentException iae){}
		plat.ajouterInstruction(instruction);
		instruction = new Instruction("Laisser légèrement refroidir", 3);
		plat.ajouterInstruction(instruction);
		instruction = new Instruction("Ajouter la crème et servir", 0);
		plat.ajouterInstruction(instruction);
		instruction = new Instruction("Laisser mijoter jusqu'à cuisson du poulet", 67);
		plat.remplacerInstruction(3,instruction);
		instruction = new Instruction("Ajouter le poulet", 0);
		plat.insererInstruction(3,instruction);
		plat.supprimerInstruction(5);

		Iterator<Instruction> instructionIterator = plat.instructions();
		while(instructionIterator.hasNext()){
			instruction = instructionIterator.next();
		}
		try{
			instructionIterator.remove();
		} catch (UnsupportedOperationException uoe){}

		Ingredient ing = new Ingredient("Blanc de poulet");
		plat.ajouterIngredient(ing,400, Unite.GRAMME);
		ing = new Ingredient("Céleri");
		plat.ajouterIngredient(ing,200, Unite.GRAMME);
		ing = new Ingredient("Carottes");
		plat.ajouterIngredient(ing, 2);
		ing = new Ingredient("jus de citron");
		plat.ajouterIngredient(ing,10,Unite.MILLILITRE);
		ing = new Ingredient("Sel");
		plat.ajouterIngredient(ing,1, Unite.PINCEE);
		ing = new Ingredient("Crème fraiche");
		plat.ajouterIngredient(ing,10, Unite.CENTILITRE);
		plat.modifierIngredient(new Ingredient("Blanc de poulet"), 600,Unite.GRAMME);
		plat.supprimerIngredient(new Ingredient("jus de citron"));
		IngredientQuantifie ingQuantifie = plat.trouverIngredientQuantifie(new Ingredient("Blanc de poulet"));
		System.out.println("Quantité de blanc de poulet nécessaire : " + ingQuantifie.getQuantite() + " " + ingQuantifie.getUnite()+"\n");
		System.out.println(plat);

//		Livre livre = new Livre();
//		livre.ajouterPlat(plat);
//		System.out.println(livre);
//		livre.supprimerPlat(plat);
//		System.out.println(livre);

		Livre livre = new Livre();
		livre.ajouterPlat(plat);
		livre.ajouterPlat(new Plat("Croquettes au fromage", 4, Difficulte.XXX,
				Cout.$$, Plat.Type.ENTREE));
//		System.out.println(livre);
		livre.supprimerPlat(new Plat("Toasts aux champignons", 5, Difficulte.XXX,
				Cout.$$$, Plat.Type.ENTREE));
//		System.out.println(livre);

		plat = new Plat("Artichaut vinaigrette", 4, Difficulte.XX, Cout.$$, Plat.Type.ENTREE);
		livre.ajouterPlat(plat);
		plat = new Plat("Noix de St Jacques à la crème", 4, Difficulte.XXX, Cout.$$$, Plat.Type.ENTREE);
		livre.ajouterPlat(plat);
		plat = new Plat("Pâtes Carbonara", 7, Difficulte.X, Cout.$, Plat.Type.PLAT);
		livre.ajouterPlat(plat);
		plat = new Plat("Gnocchis faits maison", 2, Difficulte.XXX, Cout.$, Plat.Type.PLAT);
		livre.ajouterPlat(plat);
		plat = new Plat("Pancakes", 8, Difficulte.X, Cout.$, Plat.Type.DESSERT);
		livre.ajouterPlat(plat);

		/* RECETTE DESSERT DAME BLANCHE */
		plat = new Plat("Dame blanche", 6, Difficulte.X,
				Cout.$, Plat.Type.DESSERT);
		livre.ajouterPlat(plat);

		instruction = new Instruction("Faites fondre le chocolat au bain-marie (surtout pas de beurre!!!).", 4);
		plat.ajouterInstruction(instruction);
		instruction = new Instruction("Mettez les boules de glace à la vanille dans chaque coupe.", 1);
		plat.ajouterInstruction(instruction);
		instruction = new Instruction("Dans 4 coupes disposez les 4 petites meringues (vous pouvez les concasser si vous préférez).", 2);
		plat.ajouterInstruction(instruction);
		instruction = new Instruction("Nappez du chocolat fondu au bain-marie.", 1);
		plat.ajouterInstruction(instruction);
		instruction = new Instruction("Décorez avec de la crème chantilly (pas trop quand même!!!).", 2);
		plat.ajouterInstruction(instruction);
		instruction = new Instruction("Savourez !!!!!!!!!!!", 0);
		plat.ajouterInstruction(instruction);

		plat.ajouterIngredient(new Ingredient("Chocolat noir pâtissier"), 225, Unite.GRAMME);
		plat.ajouterIngredient(new Ingredient("Boules de glace vanille"), 6);
		plat.ajouterIngredient(new Ingredient("Crème chantilly sucrée"), 1);
		plat.ajouterIngredient(new Ingredient("Meringue"), 6);

//		System.out.println(livre);
//		System.out.println(livre);
		System.out.println(livre.contient(plat));
		System.out.println(livre.contient(new Plat("Crêpes", 4, Difficulte.XX, Cout.$$, Plat.Type.DESSERT)));
		livre.ajouterPlat(plat);

		System.out.println("UNIQUEMENT LES PLATS");
		System.out.println("_______________________________________");
		System.out.println(livre.getPlatsParType(Plat.Type.PLAT));
		System.out.println("_______________________________________");

		System.out.println("TOUS LES PLATS DU LIVRE");
		System.out.println(livre.tousLesPlats());
		System.out.println("_______________________________________");
		System.out.println(livre);
		System.out.println(livre.supprimerPlat(new Plat("Crêpes", 4, Difficulte.XX, Cout.$$, Plat.Type.DESSERT)));


	}

}
