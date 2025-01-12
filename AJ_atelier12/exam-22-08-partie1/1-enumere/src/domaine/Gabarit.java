package domaine;

public enum Gabarit {
    DEUX_ESSIEUX(16400, 20),
    TROIS_ESSIEUX(22000, 30),
    QUATRE_ESSIEUX(28600, 40);

    private int chargeMaximale;
    private int nbMaxCaisses;

    Gabarit(int chargeMaximale, int nbMaxCaisses) {
        this.chargeMaximale = chargeMaximale;
        this.nbMaxCaisses = nbMaxCaisses;
    }

    public int getChargeMaximale() {
        return chargeMaximale;
    }

    public int getNbMaxCaisses() {
        return nbMaxCaisses;
    }
}
