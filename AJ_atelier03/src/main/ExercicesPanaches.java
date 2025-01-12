
package main;

import domaine.Trader;
import domaine.Transaction;

import java.util.*;
import java.util.stream.Collectors;

public class ExercicesPanaches {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        ExercicesPanaches main = new ExercicesPanaches(transactions);
        main.run();
    }

    private List<Transaction> transactions;

    public ExercicesPanaches(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void run() {
        // Complete the methods below based on the exercise descriptions
        exercice1();
        exercice2();
        exercice3();
        exercice4();
        exercice5();
        exercice6();
    }

    private void exercice1() {
        // Filter transactions of Cambridge, map to their values, and find max.
        System.out.println("exercice1");
        Optional<Integer> s = transactions
                .stream()
                .filter(e -> e.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .reduce((a, b) ->
                        a > b ? a : b)
                ;

        if (s.isPresent())
            System.out.println(s.get());
        else
            System.out.println("Aucune transaction trouvée");
    }

    private void exercice2() {
        // Filter transactions for traders in Cambridge, group by trader, and count their transactions.
        System.out.println("exercice2");
        Map<String, Long> traderNameList = transactions
                .stream()
                .map(Transaction::getTrader)
                .filter(e -> e.getCity().equals("Cambridge"))
                .collect(Collectors.groupingBy(Trader::getName, Collectors.counting()));

        System.out.println(traderNameList);
    }

    private void exercice3() {
        // Filter transactions over 500, map trader names, sort by name length, find the longest.
        System.out.println("exercice3");
        var s = transactions
                .stream()
                .filter(e -> e.getValue() > 500)
                .map(Transaction::getTrader)
                .map(Trader::getName)
                .distinct()
                .sorted((a,b) ->
                        Integer.compare(b.length(), a.length()))
                .findFirst()
                .orElse("No trader found");

        System.out.println(s);
    }

    private void exercice4() {
        // Group transactions by city, map to transaction values, and compute the average.
        System.out.println("exercice4");
        Map<String, Double> s = transactions
                .stream()
                .collect(Collectors.groupingBy(transaction ->
                        transaction.getTrader().getCity(), Collectors.averagingDouble(Transaction::getValue)
                ));

        System.out.println(s);
    }

    private void exercice5() {
        // Filter transactions in Milan, map to values, find the min, and handle empty results.
        System.out.println("exercice5");
        Optional<Integer> minVal = transactions
                .stream()
                .filter(e -> e.getTrader().getCity().equals("Milan"))
                .map(Transaction::getValue)
                .min(Integer::compareTo);

        if (minVal.isPresent())
            System.out.println(minVal.get());
        else
            System.out.println("Min non trouvé");


    }
    private void exercice6() {
        // group transaction by year
        System.out.println("exercice6");
        Map<Integer, List<Transaction>> s = transactions
                .stream()
                .collect(Collectors.groupingBy(Transaction::getYear));

        System.out.println(s);
    }
}
