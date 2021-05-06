package org.openjfx.dataModels;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.openjfx.dataCollection.ComponentsCollection;
import org.openjfx.dataValidator.ComponentValidator;
import java.io.Serializable;

/** Model of Pc components. */

public class PCComponents implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String IDKey = "PCComponentID";
    private final int PCComponentID;
    private String componentName;
    private String componentType;
    private String componentSpecs;
    private double componentPrice;

    public PCComponents(int PCComponentID, String componentName, String componentType, String componentSpecs, String componentPrice){
        this.PCComponentID = PCComponentID;
        setComponentName(componentName);
        setComponentType(componentType);
        setComponentSpecs(componentSpecs);
        setComponentPrice(componentPrice);
    }

    public String toString(){
        PCComponents object = new PCComponents(PCComponentID, componentName, componentType, componentSpecs, Double.toString(componentPrice));
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(object);
    }

    public static int createUniqueId(){
        int prevId = ComponentsCollection.getComponentArrayList().size();
        return prevId + 1;
    }

    public void setComponentName(String componentName) {
        ComponentValidator.validateComponentName(componentName);
        this.componentName = ComponentValidator.getComponentName();
    }

    public void setComponentType(String componentType) {
        ComponentValidator.validateComponentType(componentType);
        this.componentType = ComponentValidator.getComponentType();
    }

    public void setComponentSpecs(String componentSpecs) {
        ComponentValidator.validateComponentSpecs(componentSpecs);
        this.componentSpecs = ComponentValidator.getComponentSpecs();
    }

    public void setComponentPrice(String componentPrice) {
        ComponentValidator.validateComponentPrice(componentPrice);
        this.componentPrice = ComponentValidator.getComponentPrice();
    }

    public int getPCComponentID() {
        return PCComponentID;
    }

    public String getComponentName() {
        return componentName;
    }

    public String getComponentType() {
        return componentType;
    }

    public String getComponentSpecs() {
        return componentSpecs;
    }

    public double getComponentPrice() {
        return componentPrice;
    }

    public static String getObjectIDKey(){
        return IDKey;
    }
}
