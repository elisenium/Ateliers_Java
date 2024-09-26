package domaine;

import util.Util;

import java.util.*;

public class Livre {
    private TreeMap<Plat.Type, SortedSet<Plat>> plats = new TreeMap<>();

    /**
     * Ajoute un plat dans le livre, s'il n'existe pas déjà dedans.
     * Il faut ajouter correctement le plat en fonction de son type.
     * @param plat le plat à ajouter
     * @return true si le plat a été ajouté, false sinon.
     */
    public boolean ajouterPlat(Plat plat) {
        Util.checkObject(plat);

        SortedSet<Plat> set = this.plats.get(plat.getType());
        if (set == null) {
            set = new TreeSet<>(new Comparator<Plat>() {
                @Override
                public int compare(Plat o1, Plat o2) {
                    int compare = Integer.compare(o1.getNiveauDeDifficulte().ordinal(), o2.getNiveauDeDifficulte().ordinal());

                    if (compare != 0)
                        return compare;

                    return o1.getNom().compareToIgnoreCase(o2.getNom());
                }
            });
            this.plats.put(plat.getType(), set);
        }
        return set.add(plat);
    }

    /**
     * Supprime un plat du livre, s'il est dedans.
     * Si le plat supprimé est le dernier de ce type de plat, il faut supprimer
     ce type de
     * plat de la Map.
     * @param plat le plat à supprimer
     * @return true si le plat a été supprimé, false sinon.
     */
    public boolean supprimerPlat (Plat plat) {
        Util.checkObject(plat);

        SortedSet<Plat> set = plats.get(plat.getType()); // on localise le type du plat à supprimer
        if (set != null && set.remove(plat)) {
            if (set.isEmpty()) {
                plats.remove(plat.getType()); // s'il n'y a plus de plats pour ce type, on supprime le type de plat
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Plat.Type type : plats.keySet()) {
            stringBuilder.append(type.getNom().toUpperCase()).append("\n"); // type en majuscule
            stringBuilder.append("====\n");

            for (Plat plat : plats.get(type)) {
                stringBuilder.append(plat.getNom()).append("\n");
            }
            stringBuilder.append("\n");
        }
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    /**
     * Renvoie un ensemble contenant tous les plats d'un certain type.
     * L'ensemble n'est pas modifable.
     * @param type le type de plats souhaité
     * @return l'ensemble des plats
     */
    public SortedSet<Plat> getPlatsParType(Plat.Type type) {
        // L'ensemble renvoyé ne doit pas être modifiable !
        SortedSet<Plat> platsParType = this.plats.get(type);

        if (platsParType == null) {

            platsParType = new TreeSet<>(new Comparator<Plat>() {
                @Override
                public int compare(Plat o1, Plat o2) {
                    int typeCompare = o1.getType().compareTo(o2.getType());
                    if (typeCompare != 0)
                        return typeCompare;

                    int niveauDiffCompare = o1.getNiveauDeDifficulte().ordinal() - o2.getNiveauDeDifficulte().ordinal();
                    if (niveauDiffCompare != 0)
                        return niveauDiffCompare;

                    return o1.getNom().compareToIgnoreCase(o2.getNom());
                }
            });
        }

        return Collections.unmodifiableSortedSet(platsParType);
    }

    /**
     * Renvoie true si le livre contient le plat passé en paramètre. False sinon.
     * Pour cette recherche, un plat est identique à un autre si son type, son niveau de
     * difficulté et son nom sont identiques.
     * @param plat le plat à rechercher
     * @return true si le livre contient le plat, false sinon.
     */
    public boolean contient(Plat plat) {
        // Ne pas utiliser 2 fois la méthode get() de la map, et ne pas déclarer de variable locale !

        for (Plat plats : this.plats.get(plat.getType())) {
            if (plats.getNiveauDeDifficulte() == plat.getNiveauDeDifficulte() && plats.getNom().equals(plat.getNom()))
                return true;
        }
        return false;
    }

    /**
     * Renvoie un ensemble contenant tous les plats du livre. Ils ne doivent pas être triés.
     * @return l'ensemble de tous les plats du livre.
     */
    public Set<Plat> tousLesPlats() {
        // Ne pas utiliser la méthode keySet() ou entrySet() ici !
        Set<Plat> tousLesPlats = new TreeSet<>(new Comparator<Plat>() {
            @Override
            public int compare(Plat o1, Plat o2) {
                int typeCompare = o1.getType().compareTo(o2.getType());
                if (typeCompare != 0)
                    return typeCompare;

                int niveauDiffCompare = o1.getNiveauDeDifficulte().ordinal() - o2.getNiveauDeDifficulte().ordinal();
                if (niveauDiffCompare != 0)
                    return niveauDiffCompare;

                return o1.getNom().compareToIgnoreCase(o2.getNom());
            }
        });

        for (SortedSet<Plat> platsParType : plats.values()) {
            tousLesPlats.addAll(platsParType);
        }

        return tousLesPlats;
    }
}
