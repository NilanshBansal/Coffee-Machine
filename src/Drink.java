/*
    Drink Class used for Instantiating drinks, stores name of drink
    and contains a list of ingredients required
*/

import java.util.Map;

public class Drink {
    private final String name;
    private Map<String, Integer> requiredIndegrients;


    public Drink(String name) {
        this.name = name;
    }

    public Drink(String name, Map<String, Integer> requiredIndegrients) {
        this.name = name;
        this.requiredIndegrients = requiredIndegrients;
    }

    public Map<String, Integer> getRequiredIndegrients() {
        return requiredIndegrients;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Drink{" +
                "name='" + name + '\'' +
                ", requiredIndegrients=" + requiredIndegrients +
                '}';
    }
}
