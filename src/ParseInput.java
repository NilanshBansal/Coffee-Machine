/*
    Parses JSON file and instantiates inventory and Machine Class
*/

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ParseInput {
    private Map<String, Object> parsedInput;
    private String filePath;

    public ParseInput(String filepath) {
        this.filePath = filepath;
        ObjectMapper mapper = new ObjectMapper();
        try {
            File file = new File(filepath);
            parsedInput = (Map<String, Object>) mapper.readValue(file, Map.class).get("machine");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Integer getOutletCount() {
        Map<String, Integer> num_outlets = (Map<String, Integer>) parsedInput.get("outlets");
        return num_outlets.get("count_n");
    }

    public Inventory addInventory() {
        Map<String, Integer> inputInventory = (Map<String, Integer>) parsedInput.get("total_items_quantity");
        Inventory inventory = new Inventory();
        for (Map.Entry mapElement : inputInventory.entrySet()) {
            String name = (String) mapElement.getKey();
            Integer quantity = (Integer) mapElement.getValue();
            inventory.addIndegrient(name, quantity);
        }
        return inventory;
    }

    public Set<Drink> addDrinks() {
        Map<String, Object> inputBeverages = (Map<String, Object>) parsedInput.get("beverages");
        Set<Drink> drinks = new HashSet<>();
        for (Map.Entry mapElement : inputBeverages.entrySet()) {
            String name = (String) mapElement.getKey();
            Map<String, Integer> indegrients = (Map<String, Integer>) mapElement.getValue();
            Drink drink = new Drink(name, indegrients);
            drinks.add(drink);
        }
        return drinks;
    }
}
