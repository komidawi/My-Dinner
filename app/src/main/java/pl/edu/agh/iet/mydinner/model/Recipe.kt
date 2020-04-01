package pl.edu.agh.iet.mydinner.model

data class Recipe(
        var name: String,
        var details: String,
        var ingredientAmounts: List<Ingredient>
)