package pl.edu.agh.iet.mydinner.ui.recipe.create

import android.content.Intent
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
import pl.edu.agh.iet.mydinner.config.NetworkingConfig
import pl.edu.agh.iet.mydinner.databinding.ActivityCreateRecipeBinding
import pl.edu.agh.iet.mydinner.model.recipe.Ingredient
import pl.edu.agh.iet.mydinner.model.recipe.IngredientAmount
import pl.edu.agh.iet.mydinner.model.recipe.Measure
import pl.edu.agh.iet.mydinner.model.recipe.Recipe
import pl.edu.agh.iet.mydinner.ui.recipe.list.RecipeListActivity
import pl.edu.agh.iet.mydinner.util.Utils

class CreateRecipeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateRecipeBinding
    private lateinit var ingredientAmountViewAdapter: RecyclerView.Adapter<IngredientAmountViewHolder>

    private var ingredientAmounts = mutableListOf<IngredientAmount>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupArrayAdapter()
        setupIngredientAdapter()
    }

    private fun setupArrayAdapter() {
        val arrayAdapter = ArrayAdapter(
                baseContext,
                android.R.layout.simple_list_item_1,
                Measure.values()
        )

        binding.ingredientMeasureInput.adapter = arrayAdapter
    }

    private fun setupIngredientAdapter() {
        ingredientAmountViewAdapter = IngredientAdapter(ingredientAmounts)
        binding.addedIngredientsRecyclerView.adapter = ingredientAmountViewAdapter
    }

    fun onAddIngredientButtonClick(view: View) {
        val amount = binding.ingredientAmountInput.text

        if (amount.isEmpty()) {
            val message = getString(R.string.ingredient_amount_is_empty_message)
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        } else {
            addIngredientAmount()
        }
    }

    private fun addIngredientAmount() {
        val amount = binding.ingredientAmountInput.text.toString().toDouble()
        val name = binding.ingredientNameInput.text.toString()
        val measure = binding.ingredientMeasureInput.selectedItem.toString()

        val ingredient = Ingredient(name, measure)
        val ingredientAmount = IngredientAmount(ingredient, amount)

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
        Fuel.post(NetworkingConfig.CREATE_RECIPE_ENDPOINT_URL)
                .jsonBody(Gson().toJson(recipe))
                .timeout(NetworkingConfig.TIMEOUT_IN_MILLIS)
                .response { result ->
                    when (result) {
                        is Result.Success -> handleCreateRecipeSuccess()
                        is Result.Failure -> showFailureMessage(result.error.message)
                    }
                }
    }

    private fun handleCreateRecipeSuccess() {
        showSuccessMessage()
        startRecipeListActivity()
    }

    private fun showSuccessMessage() {
        val message = getString(R.string.recipe_create_message_success)
        Utils.showToast(message, this)
    }

    private fun startRecipeListActivity() {
        val goToRecipeListIntent = Intent(this, RecipeListActivity::class.java)
        startActivity(goToRecipeListIntent)
    }

    private fun showFailureMessage(error: String?) {
        val message = getString(R.string.recipe_create_message_failure)
        Utils.showToast("$message: $error", this)
    }
}