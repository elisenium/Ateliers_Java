package be.vinci.aj.services;

import be.vinci.aj.domain.Train;

public class GestionTrains {

    public void demarrerTrains() {
        Train train1 = new Train("Bruxelles", "Namur", 1500);
        Train train2 = new Train("Bruxelles", "Ostende", 2000);
        Train train3 = new Train("Bruxelles", "Louvain", 1000);
        train1.demarrer();
        train2.demarrer();
        train3.demarrer();
    }

}
