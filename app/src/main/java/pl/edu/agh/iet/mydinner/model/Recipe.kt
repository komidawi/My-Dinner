package pl.edu.agh.iet.mydinner.model

data class Recipe(
        val name: String,
        val details: String,
        var ingredientAmounts: List<IngredientAmount>
)