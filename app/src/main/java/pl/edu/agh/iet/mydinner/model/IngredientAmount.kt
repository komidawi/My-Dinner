package pl.edu.agh.iet.mydinner.model

data class IngredientAmount(
        val id: Long,
        val ingredient: Ingredient,
        var amount: Double,
        var recipes: Set<Recipe>
)
