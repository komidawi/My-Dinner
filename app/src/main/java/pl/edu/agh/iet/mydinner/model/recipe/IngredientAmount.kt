package pl.edu.agh.iet.mydinner.model.recipe

import java.text.DecimalFormat

data class IngredientAmount(
        val ingredient: Ingredient,
        val amount: Double
) {
    companion object {
        private val decimalFormat = DecimalFormat("######.#")
    }

    override fun toString(): String {
        val name = ingredient.name
        val amount = decimalFormat.format(amount)
        val measure = ingredient.measure

        return "$name: $amount [$measure]"
    }
}
