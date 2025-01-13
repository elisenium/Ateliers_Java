package be.vinci.aj;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Decode {
    public static void main(String[] args) {
        try {
            Class<?> secretsClass = Class.forName("be.vinci.aj.secret.Secrets");
            Object secretsInstance = secretsClass.getDeclaredConstructor().newInstance();

            Field numeroDeCarteBancaireField = secretsClass.getDeclaredField("numeroDeCarteBancaire");
            numeroDeCarteBancaireField.setAccessible(true);
            String numeroDeCarteBancaire = (String) numeroDeCarteBancaireField.get(secretsInstance);
            System.out.println("Numéro de carte bancaire : " + numeroDeCarteBancaire);

            Method getCodeSecretMethod = secretsClass.getDeclaredMethod("getCodeSecret");
            getCodeSecretMethod.setAccessible(true);
            String getCodeSecret = (String) getCodeSecretMethod.invoke(secretsInstance);
            System.out.println("Code secret : " + getCodeSecret);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
