package pl.edu.agh.iet.mydinner.model

data class Recipe(
        private val name: String,
        private val details: String,
        private var ingredientAmounts: List<IngredientAmount>
)