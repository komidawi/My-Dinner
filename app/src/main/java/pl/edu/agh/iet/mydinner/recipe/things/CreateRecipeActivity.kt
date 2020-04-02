package pl.edu.agh.iet.mydinner.recipe.things

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import pl.edu.agh.iet.mydinner.databinding.ActivityCreateRecipeBinding
import pl.edu.agh.iet.mydinner.model.Ingredient
import pl.edu.agh.iet.mydinner.model.Measure
import pl.edu.agh.iet.mydinner.model.Recipe

class CreateRecipeActivity : AppCompatActivity() {
    private var ingredients = mutableListOf<Ingredient>()

    private lateinit var binding: ActivityCreateRecipeBinding
    private lateinit var ingredientsViewAdapter: RecyclerView.Adapter<IngredientsViewHolder>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ingredientsViewAdapter = RecipeAdapter(ingredients)
        binding.apply {
            ingredientMeasureInput.adapter = ArrayAdapter(
                    baseContext, android.R.layout.simple_list_item_1, Measure.values())
            addedIngredientsRecyclerView.adapter = ingredientsViewAdapter
        }
    }

    fun addIngredient(view: View) {
        val name = binding.ingredientNameInput.text.toString()
        val measure = binding.ingredientMeasureInput.selectedItem.toString()
        val amount = binding.ingredientAmountInput.text

        if (amount.isEmpty()) {
            Toast.makeText(this, "Please fill amount", Toast.LENGTH_SHORT).show()
            return
        }

        val ingredient = Ingredient(name, measure, amount.toString().toDouble())

        ingredients.add(ingredient)
        ingredientsViewAdapter.notifyDataSetChanged()
    }

    fun addRecipe(view: View) {
        val name = binding.recipeNameInput.text.toString()
        val details = binding.recipeDetailsInput.text.toString()
        val recipe = Recipe(name, details, ingredients)

        Toast.makeText(this, "Recipe created: $recipe", Toast.LENGTH_LONG).show()
    }
}