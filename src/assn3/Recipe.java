package assn3;
/**
 * The {@code Recipe} class represents a recipe for a type of bread.
 * It includes the ingredients and quantity for making the bread.
 */
public class Recipe {

    /**
     * The name of the recipe.
     */
    private String name;

    /**
     * The amount of sugar (in grams) required for the recipe.
     */
    private float sugar;

    /**
     * The number of eggs required for the recipe.
     */
    private float eggs;

    /**
     * The amount of yeast (in grams) required for the recipe.
     */
    private float yeast;

    /**
     * The amount of butter (in grams) required for the recipe.
     */
    private float butter;

    /**
     * The amount of flour (in grams) required for the recipe.
     */
    private float flour;

    /**
     * The quantity of bread to be made.
     */
    private int quantity;

    /**
     * Constructs a new {@code Recipe} with the specified name and ingredient amounts.
     * The initial quantity is set to zero by default.
     *
     * @param name   the name of the recipe
     * @param sugar  the amount of sugar (in grams)
     * @param eggs   the number of eggs
     * @param flour  the amount of flour (in grams)
     * @param yeast  the amount of yeast (in grams)
     * @param butter the amount of butter (in grams)
     */
    public Recipe(String name, float sugar, float eggs, float flour, float yeast, float butter) {
        this.name = name;
        this.sugar = sugar;
        this.eggs = eggs;
        this.flour = flour;
        this.yeast = yeast;
        this.butter = butter;
        this.quantity = 0; // Default quantity
    }

    /**
     * Gets the name of the recipe.
     *
     * @return the name of the recipe
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the recipe.
     *
     * @param name the new name of the recipe
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the amount of sugar (in grams) required for the recipe.
     *
     * @return the amount of sugar
     */
    public float getSugar() {
        return sugar;
    }

    /**
     * Sets the amount of sugar (in grams) required for the recipe.
     *
     * @param sugar the new amount of sugar
     */
    public void setSugar(float sugar) {
        this.sugar = sugar;
    }

    /**
     * Gets the number of eggs required for the recipe.
     *
     * @return the number of eggs
     */
    public float getEggs() {
        return eggs;
    }

    /**
     * Sets the number of eggs required for the recipe.
     *
     * @param eggs the new number of eggs
     */
    public void setEggs(float eggs) {
        this.eggs = eggs;
    }

    /**
     * Gets the amount of yeast (in grams) required for the recipe.
     *
     * @return the amount of yeast
     */
    public float getYeast() {
        return yeast;
    }

    /**
     * Sets the amount of yeast (in grams) required for the recipe.
     *
     * @param yeast the new amount of yeast
     */
    public void setYeast(float yeast) {
        this.yeast = yeast;
    }

    /**
     * Gets the amount of butter (in grams) required for the recipe.
     *
     * @return the amount of butter
     */
    public float getButter() {
        return butter;
    }

    /**
     * Sets the amount of butter (in grams) required for the recipe.
     *
     * @param butter the new amount of butter
     */
    public void setButter(float butter) {
        this.butter = butter;
    }

    /**
     * Gets the amount of flour (in grams) required for the recipe.
     *
     * @return the amount of flour
     */
    public float getFlour() {
        return flour;
    }

    /**
     * Sets the amount of flour (in grams) required for the recipe.
     *
     * @param flour the new amount of flour
     */
    public void setFlour(float flour) {
        this.flour = flour;
    }

    /**
     * Gets the quantity of bread to be made.
     *
     * @return the quantity of bread
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of bread to be made.
     *
     * @param quantity the new quantity of bread
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Returns a string representation of the recipe.
     *
     * @return a formatted string containing the recipe details
     */
    @Override
    public String toString() {
        return String.format(
            "Recipe: %s\nSugar: %.1f\nEggs: %.1f\nFlour: %.1f\nYeast: %.1f\nButter: %.1f\nQuantity Ordered: %d",
            name, sugar, eggs, flour, yeast, butter, quantity
        );
    }
}
