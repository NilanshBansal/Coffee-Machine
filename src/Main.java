import java.util.Set;

public class Main {

    public static void main(String[] args) {

        ParseInput inputParser = new ParseInput("src/resources/input.json");

        Integer count = inputParser.getOutletCount();
        Inventory inventory = inputParser.addInventory();
        Set<Drink> drinks = inputParser.addDrinks();

        Machine coffeeMachine = new Machine(count);
        coffeeMachine.printSystemStatus(drinks, inventory);

    }
}
