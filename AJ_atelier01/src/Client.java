import java.util.ArrayList;

public class Client {
    private static int numeroSuivant = 1;
    private int numero;
    private String nom;
    private String prenom;
    private String telephone;
    private Commande commandeEnCours;
    private ArrayList<Commande> commandesPassees; //Partie optionnelle


    public Client(String nom, String prenom, String telephone) {
        Util.checkString(nom);
        Util.checkString(prenom);
        Util.checkString(telephone);

        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.numero = numeroSuivant;
        this.commandesPassees = new ArrayList<Commande>();
        numeroSuivant++;
    }

    public int getNumero() {
        return numero;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public Commande getCommandeEnCours() {
        return commandeEnCours;
    }

    public boolean enregistrer(Commande commande) {
        Util.checkObject(commande);

        if (commandeEnCours != null || commandesPassees.contains(commande))
            return false;
        if (commande.getClient().numero != this.numero)
            return false;
        commandeEnCours = commande;
        return true;
    }

    public boolean cloturerCommandeEnCours() {
        if (commandeEnCours == null)
            return false;
        commandesPassees.add(commandeEnCours);
        commandeEnCours = null;
        return true;
    }

    @Override
    public String toString() {
        return "client n° " + numero + " (" + prenom  + " " + nom + ", telephone : " + telephone +")";
    }

    /* Partie optionnelle */
    public String historiqueCommandes() {
        String commandesPasseesSt = "";
        for (Commande c : commandesPassees) {
            commandesPasseesSt += c.toString() + "\n";
        }
        return commandesPasseesSt;
    }
}
