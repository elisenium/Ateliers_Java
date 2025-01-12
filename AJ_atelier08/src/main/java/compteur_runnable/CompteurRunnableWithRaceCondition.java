package compteur_runnable;

import sync.Compteur;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class CompteurRunnableWithRaceCondition extends Compteur implements Runnable {

    // 1. ajouter un attribut de classe qui retient l'ordre d'arrivée.
    private static AtomicInteger ordreArrivee = new AtomicInteger(0);

    public CompteurRunnableWithRaceCondition(String nom, int max) {
        super(nom, max);
    }

    @Override
    public void run() {
        count();
    }

    @Override
    public void count() {
        //      2. Quand le compte est terminé, afficher que le compteur a finit de compter
        //         et indiquer son ordre d'arrivée.
        //      3. Veuillez ajouter un délai supplémentaire de 10ms après avoir déterminé l'ordre d'arrivée.
        IntStream.rangeClosed(1, getMax()).forEach(i -> {
            System.out.println(getNom() + " : " + i);
            try {
                Thread.sleep(10);
                if (i == getMax()) {
                    System.out.println(getNom() + " a finit de compter jusqu'à " + getMax() + " et a terminé en position " + ordreArrivee.addAndGet(1));
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
