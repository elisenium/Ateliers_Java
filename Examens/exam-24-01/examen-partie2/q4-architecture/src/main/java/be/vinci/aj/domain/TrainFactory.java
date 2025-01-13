package be.vinci.aj.domain;

public class TrainFactory {

    public LocomotiveImpl getLocomotive(String id, String modele, int poids) {
        return new LocomotiveImpl(id, modele, poids);
    }

    public WagonImpl getWagon(String id, String modele, int poids, int capacite) {
        return new WagonImpl(id, modele, poids, capacite);
    }

    public TrainImpl getTrain(String depart, String arrivee, int tempsParcouru) {
        return new TrainImpl(depart, arrivee, tempsParcouru);
    }
}
