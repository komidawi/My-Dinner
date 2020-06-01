package pl.edu.agh.iet.mydinner.model.recipe

import pl.edu.agh.iet.mydinner.model.recipe.IngredientAmount

data class Recipe(
        val name: String,
        val details: String,
        var ingredientAmounts: List<IngredientAmount>
)