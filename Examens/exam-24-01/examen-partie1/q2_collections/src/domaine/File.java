package domaine;

import java.time.LocalDate;
import java.util.*;

public class File {
    private String name;
    private int size;
    private User owner;

    private SortedMap<User, List<Permission>> mapPermissions = new TreeMap<User, List<Permission>>();

    public File(String name, int size, User owner) {
        this.name = name;
        this.size = size;
        this.owner = owner;
    }

    public User getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }




    /**
     *  Vérifie que l'utilisateur a toutes les permissions en paramètre
     * @param user
     * @param read
     * @param write
     * @param execute
     * @return true si l'utilisateur a toutes les permissions, false sinon.
     */
    public boolean hasPermission(User user, boolean read, boolean write, boolean execute) {

        if (user.equals(owner)) {
            return true;
        }

        List<Permission> permissions = mapPermissions.get(user);
        if (permissions == null)
            return false;

        boolean hasRead = false;
        boolean hasWrite = false;
        boolean hasExecute = false;
//        for (Permission permission : permissions) {
//
//            if (permission.getUser().equals(user) && permission.getFile().equals(this) && !permission.isExpired()) {
//                hasRead = hasRead || permission.isRead();
//                hasWrite = hasWrite || permission.isWrite();
//                hasExecute = hasExecute || permission.isExecute();
//            }
//        }

        for (Permission permission : permissions) {
            if (!permission.isExpired()) {
                hasRead = hasRead || permission.isRead();
                hasWrite = hasWrite || permission.isWrite();
                hasExecute = hasExecute || permission.isExecute();
            }
        }

        return ( hasRead || !read ) && ( hasWrite || !write ) && ( hasExecute || !execute );
    }



    /**
     *  Annule toutes les permissions pour l'utilisateur
     * @param user
     * @return true si l'utilisateur avait des permissions, false sinon.
     */
    public boolean revokeAllPermissions(User user) {
        List<Permission> permissions = mapPermissions.get(user);
        boolean revoked = false;
//        Iterator<Permission> iterator = permissions.iterator();
//        while (iterator.hasNext()) {
//            Permission permission = iterator.next();
//            if (permission.getUser().equals(user)) {
//                iterator.remove(); // Suppression sûre
//                revoked = true;
//            }
//        }

        if (permissions == null)
            return false;

        permissions.clear();
        return true;
    }


    /**
     * Ajoute une permission pour l'utilisateur
     * @param user1
     * @param r
     * @param w
     * @param x
     */
    public void addPermission(User user1, boolean r, boolean w, boolean x) {
        List<Permission> permissions = mapPermissions.get(user1);
        if (permissions == null) {
            permissions = new ArrayList<>();
            mapPermissions.put(user1, permissions);
        }
        permissions.add(new Permission(user1, this, r, w, x));
    }

    public void addPermission(User user1, boolean r, boolean w, boolean x, LocalDate expirationDate) {
        List<Permission> permissions = mapPermissions.get(user1);
        if (permissions == null) {
            permissions = new ArrayList<>();
            mapPermissions.put(user1, permissions);
        }
        permissions.add(new Permission(user1, this, r, w, x, expirationDate));
    }
    public void print() {
        System.out.println("File: " + name + " (" + size + " octets)");
    }

    public void printPermissions() {
        System.out.println("Permissions for file: " + name);

        Iterator<List<Permission>> iterator = mapPermissions.values().iterator();
        while (iterator.hasNext()) {
            List<Permission> permissions = iterator.next();
            for (Permission permission : permissions) {
                permission.print();
            }
        }
    }
}
