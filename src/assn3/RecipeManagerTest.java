package assn3;
import java.io.IOException;
import java.util.Scanner;

/**
 * Main driver class for the Recipe Manager application.
 * <p>
 * This class provides a console-based interface for managing recipes, creating shopping lists,
 * and saving them to a file. Users can:
 * <ul>
 *   <li>View available recipes</li>
 *   <li>Create a shopping list by specifying quantities for recipes</li>
 *   <li>Save and print the shopping list</li>
 *   <li>Exit the application</li>
 * </ul>
 */
public class RecipeManagerTest {

    /**
     * Main entry point for the Recipe Manager application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        RecipeManager manager = new RecipeManager(); // Create RecipeManager instance
        Scanner scanner = new Scanner(System.in);   // Scanner for user input

        // Step 1: Load recipes from the file
        try {
            manager.loadRecipes("recipelist.txt");
            System.out.println("Recipes successfully loaded!");
        } catch (IOException e) {
            System.err.println("Error loading recipes: " + e.getMessage());
            return; // Exit if the recipes cannot be loaded
        }

        // Main menu loop
        while (true) {
            // Display the main menu
            System.out.println("\nWelcome to Zahra Recipe Manager!");
            System.out.println("1. Show available recipes.");
            System.out.println("2. Create shopping list.");
            System.out.println("3. Print and save shopping list.");
            System.out.println("4. Quit program.");
            System.out.println("0. to reprint this menu.");
            int choice = getValidInput(scanner, "Please enter your choice: ", 0, 4);

            switch (choice) {
                case 0 -> {
                    // No additional logic needed; menu will naturally loop
                }
                case 1 -> showRecipes(manager);
                case 2 -> createShoppingList(manager, scanner);
                case 3 -> printAndSaveShoppingList(manager, scanner);
                case 4 -> {
                    System.out.println("Goodbye!");
                    scanner.close();
                    return; // Exit the program
                }
            }
        }
    }

    /**
     * Displays the list of available recipes in the Recipe Manager.
     *
     * @param manager The {@link RecipeManager} instance.
     */
    private static void showRecipes(RecipeManager manager) {
        System.out.println("\nAvailable Recipes:");
        int index = 1;
        for (Recipe recipe : manager.getRecipes()) {
            System.out.printf("%d. %s\n", index++, recipe.getName());
        }
    }

    /**
     * Allows the user to create a shopping list by adding or reducing bread quantities
     * for a specific recipe.
     *
     * @param manager The {@link RecipeManager} instance.
     * @param scanner The {@link Scanner} for user input.
     */
    private static void createShoppingList(RecipeManager manager, Scanner scanner) {
        System.out.println("\nAvailable Recipes:");
        int index = 1;
        for (Recipe recipe : manager.getRecipes()) {
            System.out.printf("%d. %s\n", index++, recipe.getName());
        }

        int recipeIndex = getValidInput(scanner, "Which bread would you like? ", 1, manager.getRecipes().size()) - 1;
        Recipe recipe = manager.getRecipes().get(recipeIndex);

        System.out.print("How much of this bread would you like? ");
        while (!scanner.hasNextInt()) {
            System.out.println("Please only type digits.");
            System.out.print("How much of this bread would you like? ");
            scanner.nextLine(); // Clear invalid input
        }
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Clear the buffer

        if (quantity < 0) {
            int currentQuantity = recipe.getQuantity();
            if (currentQuantity == 0) {
                System.out.println("Invalid quantity. Cannot reduce quantity of " + recipe.getName()
                                   + " because no loaves have been ordered.");
            } else {
                int newQuantity = currentQuantity + quantity;
                if (newQuantity < 0) {
                    recipe.setQuantity(0);
                    System.out.println("Quantity reduced to 0 for " + recipe.getName() + ". No loaves remaining.");
                } else {
                    recipe.setQuantity(newQuantity);
                    System.out.println("Quantity reduced by " + Math.abs(quantity) + " for " + recipe.getName()
                                       + ". Total: " + newQuantity + " loaf/loaves.");
                }
            }
        } else if (quantity > 0) {
            recipe.setQuantity(recipe.getQuantity() + quantity);
            System.out.println(quantity + " loaf/loaves of " + recipe.getName() + " added. Total: "
                               + recipe.getQuantity() + " loaf/loaves.");
        } else {
            System.out.println("Invalid quantity. Please enter a positive number to add or a negative number to reduce.");
        }
    }

    /**
     * Prints and optionally saves the shopping list to a file.
     *
     * @param manager The {@link RecipeManager} instance.
     * @param scanner The {@link Scanner} for user input.
     */
    private static void printAndSaveShoppingList(RecipeManager manager, Scanner scanner) {
        try {
            manager.saveShoppingList("shoppinglist.txt", scanner);
        } catch (IOException e) {
            System.err.println("Error saving shopping list: " + e.getMessage());
        }
    }

    /**
     * Gets a valid integer input from the user within a specified range.
     *
     * @param scanner Scanner for user input
     * @param prompt  The message to display to the user
     * @param min     The minimum valid value
     * @param max     The maximum valid value
     * @return A valid integer input within the specified range
     */
    private static int getValidInput(Scanner scanner, String prompt, int min, int max) {
        int input;
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                scanner.nextLine(); // Clear the buffer
                if (input >= min && input <= max) {
                    return input; // Valid input
                }
            } else {
                scanner.nextLine(); // Clear invalid input
            }
            System.out.println("Please only type digits.");
            System.out.printf("Valid input are digits from %d to %d.%n", min, max);
        }
    }
}
