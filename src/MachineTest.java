/*
    Test Cases
*/

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MachineTest {

    private Machine coffeeMachine;
    private Integer count;
    private Inventory inventory;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        ParseInput inputParser = new ParseInput("src/resources/input.json");
        count = inputParser.getOutletCount();
        inventory = inputParser.addInventory();
        coffeeMachine = new Machine(count);
    }

    /*
        Tests if Ingredient is Not Sufficient in Quantity so drink cannot be made
    */
    @Test
    public void testIfIndedgrientIsNotSufficient() throws IllegalArgumentException {

        Map<String, Integer> indegrients = new HashMap<>();
        indegrients.put("hot_water", 1000);
        Drink tea = new Drink("tea", indegrients);

        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            coffeeMachine.isPossibleToMake(tea, inventory);
        });
    }

    /*
        Tests if Ingredient is Not Available so drink cannot be made
    */
    @Test
    public void testIfIndedgrientIsNotAvailable() throws IllegalStateException {
        Map<String, Integer> indegrients = new HashMap<>();
        indegrients.put("unavailable_indegrient", 10);
        Drink tea = new Drink("tea", indegrients);

        IllegalStateException e = Assertions.assertThrows(IllegalStateException.class, () -> {
            coffeeMachine.isPossibleToMake(tea, inventory);
        });
    }

    /*
        Tests if Ingredient is Available and also Sufficient in Quantity so drink can be made
    */
    @Test
    public void testIfIndedgrientIsAvailableAndSufficient(){
        Map<String, Integer> indegrients = new HashMap<>();
        indegrients.put("tea_leaves_syrup", 20);
        Drink tea = new Drink("tea", indegrients);

        boolean status = coffeeMachine.isPossibleToMake(tea, inventory);
        assertTrue(status);
    }
}