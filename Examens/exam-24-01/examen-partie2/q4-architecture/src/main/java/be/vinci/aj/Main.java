package be.vinci.aj;

import be.vinci.aj.domain.TrainFactory;
import be.vinci.aj.services.GestionTrains;

public class Main {

    public static void main(String[] args) {
        TrainFactory factory = new TrainFactory();
        GestionTrains gestionTrains = new GestionTrains(factory);
        gestionTrains.demarrerTrains();
    }
}