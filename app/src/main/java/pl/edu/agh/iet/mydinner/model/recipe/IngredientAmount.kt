package pl.edu.agh.iet.mydinner.model.recipe

import java.text.DecimalFormat

data class IngredientAmount(
        val ingredient: Ingredient,
        val amount: Double
) {
    override fun toString(): String {
        val df = DecimalFormat("######.#")
        return "${ingredient.name}: ${df.format(amount)} [${ingredient.measure}]"
    }
}
