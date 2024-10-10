package lambda;

import java.util.Arrays;
import java.util.List;

public class TestLambda {

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(153, 22, 4567, 50, 209, 34, 1040);

        List<Integer> result;


        //TODO: Enlever les commentaires et remplacez les ??? par des expressions lambda appropriées

        //Trouve tous les entiers de la liste qui sont plus grands que 200
        result = Lambda.allMatches(list, i -> i > 200);
        System.out.println(result);

        //Trouve tous les entiers pairs de la liste
        result = Lambda.allMatches(list, i -> i % 2 == 0);
        System.out.println(result);

        //Trouve tous les entiers de la liste dont le premier chiffre est 1
        result = Lambda.allMatches(list, i -> i.toString().startsWith("1"));
        System.out.println(result);

        //Retourne une liste contenant les entiers de la liste originale multipliés par 2
        result = Lambda.transformAll(list, i -> i * 2);
        System.out.println(result);

        //Retourne une liste contenant les entiers de la liste originale auxquels on a soustrait 25
        result = Lambda.transformAll(list, i -> i - 25);
        System.out.println(result);


        //TODO: une fois arrivé au point 1.3, enlevez les commentaires et compélétez
        //      en remplaçant les ??? par des expression lambda appropriées

        List<String> list2 = Arrays.asList("hello", "bonjour", "goeiedag", "hallo", "hej");

        //Trouve toutes les String de la liste qui commencent par "h"
        List<String> result2 = Lambda.allMatches(list2, s -> s.startsWith("h"));
        System.out.println(result2);

        //Retourner une liste qui contient la taille de chacune des String de la liste originale
        List<Integer> result3 = Lambda.transformAll(list2, String::length);
        System.out.println(result3);

        System.out.println("\nTESTS filter()");

        List<Integer> list3 = Arrays.asList(183, 22, 9836, 57, 332, 10283, 1109);

        result = Lambda.filter(list3, i -> i > 200);
        System.out.println(result);

        //Trouve tous les entiers pairs de la liste
        result = Lambda.filter(list3, i -> i % 2 == 0);
        System.out.println(result);

        //Trouve tous les entiers de la liste dont le premier chiffre est 1
        result = Lambda.filter(list3, i -> i.toString().startsWith("1"));
        System.out.println(result);

        System.out.println("\nTESTS map()");

        //Retourne une liste contenant les entiers de la liste originale multipliés par 2
        result = Lambda.map(list3, i -> i * 2);
        System.out.println(result);

        //Retourne une liste contenant les entiers de la liste originale auxquels on a soustrait 25
        result = Lambda.map(list3, i -> i - 25);
        System.out.println(result);

        //Retourner une liste qui contient la taille de chacune des String de la liste originale
        result3 = Lambda.map(list3, s -> s.toString().length());
        System.out.println(result3);

    }
}
