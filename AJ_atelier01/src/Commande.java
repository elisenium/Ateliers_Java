import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Iterator;

public class Commande implements Iterable<LigneDeCommande> {
    private static int numeroSuivant = 1;
    private int numero;
    private Client client;
    private LocalDateTime date;
    private ArrayList<LigneDeCommande> lignesCommande;

    public Commande(Client client) {
        Util.checkObject(client);

        if (client.getCommandeEnCours() != null)
            throw new IllegalArgumentException("impossible de créer une commande pour un client ayant encore une commande en cours");
        this.client = client;
        this.numero = numeroSuivant++;
        this.date = LocalDateTime.now();
        this.lignesCommande = new ArrayList<>();

        client.enregistrer(this);
    }

    public int getNumero() {
        return numero;
    }

    public Client getClient() {
        return client;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public boolean ajouter(Pizza pizza, int quantite) {
        Util.checkObject(pizza);
        Util.checkStrictlyPositive(quantite);

        if (client.getCommandeEnCours() != this)
            return false;

        for (LigneDeCommande lc : lignesCommande) {
            if (lc.getPizza().equals(pizza)) {
                lc.setQuantite(++quantite);
                return true;
            }
        }
        lignesCommande.add(new LigneDeCommande(pizza, quantite));
        return true;
    }

    public boolean ajouter(Pizza pizza) {
        if (pizza == null)
            throw new IllegalArgumentException();

        return ajouter(pizza, 1);
    }

    public double calculerMontantTotal() {
        double montantTotal = 0;
        for (LigneDeCommande ligneDeCommande : lignesCommande) {
            montantTotal += ligneDeCommande.calculerPrixTotal();
        }
        return montantTotal;
    }

    public String detailler() {
        String details = "";
        for (LigneDeCommande lc : lignesCommande) {
            details += lc.getQuantite() + " " + lc.getPizza().getTitre() + "  à " + lc.getPrixUnitaire() + "\n";
        }
        return details;
    }

    public Iterator<LigneDeCommande> iterator() {
        return lignesCommande.iterator();
    }

    public String toString() {
        DateTimeFormatter formater = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        String encours = "";
        if (client.getCommandeEnCours() == this)
            encours = " (en cours)";
        return "Commande n° " + numero + encours + " du " + client + "\ndate : " + formater.format(date);
    }


    /* Partie optionnelle */

    public boolean retirer(Pizza pizza, int quantite) {
        Util.checkObject(pizza);
        Util.checkStrictlyPositive(quantite);

        if (client.getCommandeEnCours() != this)
            return false;

        for (LigneDeCommande lc : lignesCommande) {
            if (lc.getPizza().equals(pizza)) {
                if (lc.getQuantite() < quantite)
                    return false;
                lc.setQuantite(lc.getQuantite() - quantite);
                return true;
            }
        }
        return false;
    }

    public boolean retirer(Pizza pizza) {
        Util.checkObject(pizza);

        return retirer(pizza, 1);
    }

    public boolean supprimer(Pizza pizza) {
        Util.checkObject(pizza);

        if (client.getCommandeEnCours() != this)
            return false;
        for (LigneDeCommande lc : lignesCommande) {
            if (lc.getPizza().equals(pizza)) {
                lignesCommande.remove(lc);
                return true;
            }
        }
        return false;
    }

}
