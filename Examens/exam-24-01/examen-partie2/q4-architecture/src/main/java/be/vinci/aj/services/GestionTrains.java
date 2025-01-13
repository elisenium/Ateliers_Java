package be.vinci.aj.services;

import be.vinci.aj.domain.TrainFactory;
import be.vinci.aj.domain.TrainImpl;

public class GestionTrains {

    private TrainFactory factory;

    public GestionTrains(TrainFactory factory) {
        this.factory = factory;
    }

    public void demarrerTrains() {
        TrainImpl train1 = new TrainImpl("Bruxelles", "Namur", 1500);
        TrainImpl train2 = new TrainImpl("Bruxelles", "Ostende", 2000);
        TrainImpl train3 = new TrainImpl("Bruxelles", "Louvain", 1000);
        train1.demarrer();
        train2.demarrer();
        train3.demarrer();
    }

}
