/*
    Machine Class for the Coffee Machine having number of outlets
 */

import java.util.Map;
import java.util.Set;

public class Machine {
    private int num_outlets;
    private String errorMessage;

    public Machine(int num_outlets) {
        this.num_outlets = num_outlets;
    }

    /*
        Prints system status returns exception if drink not possible
    */

    void printSystemStatus(Set<Drink> drinks, Inventory inventory) {
        int drinksPossible = num_outlets;
        for(Drink drink:drinks) {
            if(drinksPossible > 0) {
                try{
                    isPossibleToMake(drink, inventory);
                    makeDrink(drink, inventory);
                    drinksPossible -= 1;
                } catch (IllegalStateException | IllegalArgumentException e ) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    /*
        makeDrink function used for creating a drink and deducting the ingredients
        quantity from inventory
    */

    public void makeDrink(Drink drink, Inventory inventory) {
        Map<String, Integer> requiredIndegrients = drink.getRequiredIndegrients();
        for (Map.Entry mapElement : requiredIndegrients.entrySet()) {
            String indegrient = (String) mapElement.getKey();
            Integer quantity = (Integer) mapElement.getValue();
            inventory.addIndegrient(indegrient, -1 * quantity);
        }
        System.out.println(drink.getName() + " is prepared");
    }

    /*
        Boolean function isPossibleToMake returns if drink can be made or not
    */

    public boolean isPossibleToMake(Drink drink, Inventory inventory) {
        Map<String, Integer> requiredIndegrients = drink.getRequiredIndegrients();
        for (Map.Entry mapElement : requiredIndegrients.entrySet()) {
            String indegrient = (String) mapElement.getKey();
            Integer quantity = (Integer) mapElement.getValue();

            Integer availableQuantity = inventory.getQuantity(indegrient);
            if (availableQuantity == -1) {
                errorMessage = drink.getName() + " cannot be prepared because " + indegrient + " is not available";
                throw new IllegalStateException(errorMessage);
            } else if (quantity > availableQuantity) {
                errorMessage = drink.getName() + " cannot be prepared because " + indegrient + " is not sufficient";
                throw new IllegalArgumentException(errorMessage);
            }
        }
        return true;
    }
}
