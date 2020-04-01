package pl.edu.agh.iet.mydinner.recipe.things

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import pl.edu.agh.iet.mydinner.R
import pl.edu.agh.iet.mydinner.model.Ingredient
import pl.edu.agh.iet.mydinner.model.Measure
import pl.edu.agh.iet.mydinner.model.Recipe

class CreateRecipeActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<IngredientsViewHolder>
    private var ingredients = mutableListOf<Ingredient>()

    private lateinit var recipeNameInput: EditText
    private lateinit var recipeDetailsInput: EditText

    private lateinit var ingredientNameInput: AutoCompleteTextView
    private lateinit var ingredientMeasureInput: Spinner
    private lateinit var ingredientAmountInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_recipe)

        recipeNameInput = findViewById(R.id.recipe_name_input)
        recipeDetailsInput = findViewById(R.id.recipe_details_input)

        ingredientNameInput = findViewById(R.id.ingredient_name_input)
        ingredientMeasureInput = findViewById(R.id.ingredient_measure_input)
        ingredientMeasureInput.adapter = ArrayAdapter(
                this, android.R.layout.simple_list_item_1, Measure.values()
        )
        ingredientAmountInput = findViewById(R.id.ingredient_amount_input)

        recyclerView = findViewById(R.id.added_ingredients_recycler_view)
        viewAdapter = RecipeAdapter(ingredients)
        recyclerView.adapter = viewAdapter
    }

    fun addIngredient(view: View) {
        val name = ingredientNameInput.text.toString()
        val measure = ingredientMeasureInput.selectedItem.toString()
        val amount = ingredientAmountInput.text

        if (amount.isEmpty()) {
            Toast.makeText(this, "Please fill amount", Toast.LENGTH_SHORT).show()
            return
        }

        val ingredient = Ingredient(name, measure, amount.toString().toDouble())

        ingredients.add(ingredient)
        viewAdapter.notifyDataSetChanged()
    }

    fun addRecipe(view: View) {
        val name = recipeNameInput.text.toString()
        val details = recipeDetailsInput.text.toString()
        val recipe = Recipe(name, details, ingredients)

        Toast.makeText(this, "Recipe created: $recipe", Toast.LENGTH_LONG).show()
    }
}