package pl.edu.agh.iet.mydinner.ui.recipe.display

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import pl.edu.agh.iet.mydinner.databinding.ActivityDisplayRecipeBinding
import pl.edu.agh.iet.mydinner.model.recipe.IngredientAmount
import pl.edu.agh.iet.mydinner.model.recipe.Recipe
import pl.edu.agh.iet.mydinner.ui.recipe.list.RecipeViewHolder

class DisplayRecipeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDisplayRecipeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisplayRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setRecipeData()
    }

    private fun setRecipeData() {
        val recipeJson = intent.getStringExtra(RecipeViewHolder.RECIPE_EXTRA_ID)
        val recipe = Gson().fromJson(recipeJson, Recipe::class.java)

        binding.apply {
            recipe.apply {
                recipeDisplayName.text = name
                recipeDisplayDetails.text = details
                recipeDisplayIngredients.text = ingredientAmounts.joinToString(separator = "\n")
            }
        }
    }
}
