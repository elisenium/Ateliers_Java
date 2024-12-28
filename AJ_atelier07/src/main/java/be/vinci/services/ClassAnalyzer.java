package be.vinci.services;

import jakarta.json.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.logging.Logger;

/**
 * Class analyzer. It saves a class into attribute, from a constructor, and
 * gives a lot of convenient methods to transform this into a JSON object
 * to print the UML diagram.
 */
public class ClassAnalyzer {

    private Class aClass;

    public ClassAnalyzer(Class aClass) {
        this.aClass = aClass;
    }

    /**
     * Create a JSON Object with all the info of the class.
     * @return
     */
    public JsonObject getFullInfo() {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("name", aClass.getSimpleName());
        objectBuilder.add("fields", getFields());
        objectBuilder.add("methods", getMethods());
        return objectBuilder.build();
    }


    /**
     * From the field descriptor f, create a Json Object with all field data.
     * Example :
     * {
     * name: "firstname",
     * type: "String",
     * visibility : "private"  // public, private, protected, package
     * isStatic: false,
     * }
     * @param f filed descriptor - describe an attribute
     * @return the generated JSON
     */
    public JsonObject getField(Field f) {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();

        objectBuilder.add("name", f.getName());
        if (f.getType().isPrimitive() || f.getType() == List.class) {
                objectBuilder.add("type", f.getAnnotatedType().getType().getTypeName());
        } else {
            objectBuilder.add("type", f.getType().getSimpleName());
        }
        objectBuilder.add("visibility", getFieldVisibility(f));
        objectBuilder.add("isStatic", isFieldStatic(f));
        return objectBuilder.build();
    }

    /**
     * Get fields, and create a Json Array with all fields data.
     * Example :
     * [ {}, {} ]
     * This method rely on the getField() method to handle each field one by one.
     */
    public JsonArray getFields() {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        for (Field field : aClass.getDeclaredFields()) {
            arrayBuilder.add(getField(field));
        }
        return arrayBuilder.build();
    }

    /**
     * Return whether a field is static or not
     *
     * @param f the field to check
     * @return true if the field is static, false else
     */
    private boolean isFieldStatic(Field f) {
        return Modifier.isStatic(f.getModifiers());
    }

    /**
     * Get field visibility in a string form
     *
     * @param f the field to check
     * @return the visibility (public, private, protected, package)
     */
    private String getFieldVisibility(Field f) {
        int modifiers = f.getModifiers();
        if (Modifier.isPublic(modifiers))
            return "public";
        else if (Modifier.isPrivate(modifiers))
            return "private";
        else if (Modifier.isProtected(modifiers))
            return "protected";
        else return "package";
    }
    /**
     * From the method descriptor m, create a Json Object with all method data.
     * Example :
     * {
     * name: "setFirstName",
     * returnType: null,
     * parameters: ["String"]
     * visibility : "public"  // public, private, protected, package
     * isStatic: false,
     * isAbstract: false
     * }
     * @param m method descriptor - describe an method
     * @return the generated JSON
     */
    public JsonObject getMethod(Method m) {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("name", m.getName());
        objectBuilder.add("returnType", m.getReturnType().getSimpleName());
        JsonArrayBuilder parametersArray = Json.createArrayBuilder();
        for (Class<?> parameterType : m.getParameterTypes()) {
            parametersArray.add(parameterType.getSimpleName());
        }
        objectBuilder.add("parameters", parametersArray);
        objectBuilder.add("visibility", getMethodVisibility(m));
        objectBuilder.add("isStatic", isMethodStatic(m));
        objectBuilder.add("isAbstract", isMethodAbstract(m));
        return objectBuilder.build();
    }

    /**
     * Get methods, and create a Json Array with all methods data.
     * Example :
     * [ {}, {} ]
     * This method relies on the getMethod() method to handle each method one by one.
     */
    public JsonArray getMethods() {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Method method : aClass.getDeclaredMethods()) {
            arrayBuilder.add(getMethod(method));
        }
        return arrayBuilder.build();
    }

    /**
     * Get method visibility in a string form
     *
     * @param m the method to check
     * @return the visibility (public, private, protected, package)
     */
    private String getMethodVisibility(Method m) {
        int modifiers = m.getModifiers();
        if (Modifier.isPublic(modifiers))
            return "public";
        else if (Modifier.isPrivate(modifiers))
            return "private";
        else if (Modifier.isProtected(modifiers))
            return "protected";
        else return "package";
    }

    /**
     * Return whether a method is static or not
     *
     * @param m the method to check
     * @return true if the method is static, false else
     */
    private boolean isMethodStatic(Method m) {
        return Modifier.isStatic(m.getModifiers());
    }

    /**
     * Return whether a method is abstract or not
     *
     * @param m the method to check
     * @return true if the method is abstract, false else
     */
    private boolean isMethodAbstract(Method m) {
        return Modifier.isAbstract(m.getModifiers());
    }

}
