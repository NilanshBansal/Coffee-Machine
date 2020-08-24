/*
    Inventory Class used for storing available Ingredients and their quantity
 */

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private Map<String, Integer> availableIndegrients;

    public Inventory() {
        this.availableIndegrients = new HashMap<>();
    }

    protected void addIndegrient(String name, int quantity) {
        if(availableIndegrients.containsKey(name)) {
            int newQuantity = availableIndegrients.get(name) + quantity;
            availableIndegrients.replace(name, newQuantity);
        } else {
            availableIndegrients.put(name, quantity);
        }
    }

    protected int getQuantity(String indegrientName) {
        return availableIndegrients.getOrDefault(indegrientName, -1);
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "availableIndegrients=" + availableIndegrients +
                '}';
    }
}
