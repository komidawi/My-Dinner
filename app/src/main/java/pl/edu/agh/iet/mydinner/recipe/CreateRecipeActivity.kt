package pl.edu.agh.iet.mydinner.recipe

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.extensions.jsonBody
import com.github.kittinunf.result.Result
import com.google.gson.Gson
import pl.edu.agh.iet.mydinner.R
import pl.edu.agh.iet.mydinner.config.Env
import pl.edu.agh.iet.mydinner.databinding.ActivityCreateRecipeBinding
import pl.edu.agh.iet.mydinner.model.Ingredient
import pl.edu.agh.iet.mydinner.model.IngredientAmount
import pl.edu.agh.iet.mydinner.model.Measure
import pl.edu.agh.iet.mydinner.model.Recipe
import pl.edu.agh.iet.mydinner.util.Utils

class CreateRecipeActivity : AppCompatActivity() {
    private var ingredientAmounts = mutableListOf<IngredientAmount>()

    private lateinit var binding: ActivityCreateRecipeBinding
    private lateinit var ingredientAmountViewAdapter: RecyclerView.Adapter<IngredientAmountViewHolder>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ingredientAmountViewAdapter = RecipeAdapter(ingredientAmounts)
        binding.apply {
            ingredientMeasureInput.adapter = ArrayAdapter(
                    baseContext, android.R.layout.simple_list_item_1, Measure.values())
            addedIngredientsRecyclerView.adapter = ingredientAmountViewAdapter
        }
    }

    fun onAddIngredientButtonClick(view: View) {
        val name = binding.ingredientNameInput.text.toString()
        val measure = binding.ingredientMeasureInput.selectedItem.toString()
        val amount = binding.ingredientAmountInput.text

        if (amount.isEmpty()) {
            Toast.makeText(this, "Please fill amount", Toast.LENGTH_SHORT).show()
            return
        }

        val ingredient = Ingredient(name, measure)
        val ingredientAmount = IngredientAmount(ingredient, amount.toString().toDouble())

        ingredientAmounts.add(ingredientAmount)
        ingredientAmountViewAdapter.notifyDataSetChanged()
    }

    fun onCreateRecipeButtonClick(view: View) {
        val name = binding.recipeNameInput.text.toString()
        val details = binding.recipeDetailsInput.text.toString()
        val recipe = Recipe(name, details, ingredientAmounts)

        makeCreateRecipeRequest(recipe)
    }

    private fun makeCreateRecipeRequest(recipe: Recipe) {
        val url = "${Env.SERVER_URL}/users/user/recipe/0"

        Fuel.post(url)
                .jsonBody(Gson().toJson(recipe))
                .timeout(5000)
                .response { result ->
                    when (result) {
                        is Result.Success -> handleCreateRecipeSuccess()
                        is Result.Failure -> handleCreateRecipeFailure(result.error.message)
                    }
                }
    }

    private fun handleCreateRecipeSuccess() {
        val message = getString(R.string.recipe_create_message_success)
        Utils.showToast(message, this)
    }

    private fun handleCreateRecipeFailure(error: String?) {
        val message = getString(R.string.recipe_create_message_failure)
        Utils.showToast("$message: $error", this)
    }
}