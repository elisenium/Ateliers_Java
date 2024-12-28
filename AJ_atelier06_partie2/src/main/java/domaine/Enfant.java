package domaine;

import util.Util;

/**
 * La classe Enfant représente un enfant. Elle connaît son nom et son numéro
 * de téléphone.
 * Cette classe est immuable.
 */
public interface Enfant {

	/**
	 * renvoie le nom de l'enfant
	 */
	String getNom();

	/**
	 * renvoie le téléphone de l'enfant
	 */
	String getTelephone();
}
