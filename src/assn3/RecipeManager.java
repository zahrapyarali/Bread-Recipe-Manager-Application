package assn3;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Manages recipes by loading them from a file, storing them, and generating shopping lists.
 * Provides functionality to manage a collection of recipes and generate a shopping list
 * based on the quantities of recipes specified by the user.
 */
public class RecipeManager {

    /**
     * A list to store the collection of recipes.
     */
    private List<Recipe> recipes;

    /**
     * Constructs a new {@code RecipeManager} with an empty list of recipes.
     */
    public RecipeManager() {
        recipes = new ArrayList<>();
    }

    /**
     * Loads recipes from a file and adds them to the recipe list.
     * Each recipe includes its name and required ingredients (sugar, eggs, flour, yeast, butter).
     *
     * @param fileName the name of the file containing recipe data
     * @throws IOException if an error occurs while reading the file
     */
    public void loadRecipes(String fileName) throws IOException {
        Scanner scanner = new Scanner(new File(fileName));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.startsWith("Recipe")) {
                String recipeName = line.substring(7).trim();
                float sugar = 0, eggs = 0, flour = 0, yeast = 0, butter = 0;

                for (int i = 0; i < 5; i++) {
                    String[] parts = scanner.nextLine().trim().split(" ");
                    String ingredient = parts[0];
                    float amount = Float.parseFloat(parts[1]);

                    switch (ingredient.toLowerCase()) {
                        case "sugar" -> sugar = amount;
                        case "eggs" -> eggs = amount;
                        case "flour" -> flour = amount;
                        case "yeast" -> yeast = amount;
                        case "butter" -> butter = amount;
                    }
                }

                recipes.add(new Recipe(recipeName, sugar, eggs, flour, yeast, butter));
            }
        }
        scanner.close();
    }

    /**
     * Retrieves the list of recipes stored in this manager.
     *
     * @return a {@code List} of {@code Recipe} objects
     */
    public List<Recipe> getRecipes() {
        return recipes;
    }

    /**
     * Generates a shopping list based on the quantities of recipes specified by the user
     * and optionally saves the list to a file.
     *
     * @param fileName the name of the file where the shopping list will be saved
     * @param scanner  a {@code Scanner} object to handle user input
     * @throws IOException if an error occurs while writing to the file
     */
    public void saveShoppingList(String fileName, Scanner scanner) throws IOException {
        StringBuilder shoppingList = new StringBuilder();
        shoppingList.append("Shopping List:\n");

        float totalSugar = 0, totalEggs = 0, totalFlour = 0, totalYeast = 0, totalButter = 0;

        for (Recipe recipe : recipes) {
            int quantity = recipe.getQuantity();
            if (quantity > 0) {
                shoppingList.append(String.format("%d %s loaf/loaves.\n", quantity, recipe.getName()));
                totalSugar += recipe.getSugar() * quantity;
                totalEggs += recipe.getEggs() * quantity;
                totalFlour += recipe.getFlour() * quantity;
                totalYeast += recipe.getYeast() * quantity;
                totalButter += recipe.getButter() * quantity;
            }
        }

        shoppingList.append("\nYou will need a total of:\n");
        if (totalYeast > 0) shoppingList.append(String.format("%.1f grams of yeast\n", totalYeast));
        if (totalFlour > 0) shoppingList.append(String.format("%.1f grams of flour\n", totalFlour));
        if (totalSugar > 0) shoppingList.append(String.format("%.1f grams of sugar\n", totalSugar));
        if (totalEggs > 0) shoppingList.append(String.format("%.1f egg(s)\n", totalEggs));
        if (totalButter > 0) shoppingList.append(String.format("%.1f grams of butter\n", totalButter));

        System.out.println(shoppingList);

        System.out.print("Do you want to save this list (Y/n)? ");
        String response = scanner.nextLine().trim().toLowerCase();

        if (response.equals("y") || response.equals("")) {
            PrintWriter writer = new PrintWriter(new File(fileName));
            writer.print(shoppingList);
            writer.close();
            System.out.println("Shopping list saved to " + fileName + "!");
        } else {
            System.out.println("Shopping list was not saved.");
        }
    }
}
