package pl.edu.agh.iet.mydinner.model.recipe

import pl.edu.agh.iet.mydinner.model.recipe.Ingredient

data class IngredientAmount(
        val ingredient: Ingredient,
        val amount: Double
)
