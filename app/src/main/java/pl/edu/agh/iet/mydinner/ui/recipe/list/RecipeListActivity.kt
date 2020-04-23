package pl.edu.agh.iet.mydinner.ui.recipe.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.result.Result
import com.google.gson.Gson
import com.google.gson.JsonObject
import org.json.JSONObject
import pl.edu.agh.iet.mydinner.R
import pl.edu.agh.iet.mydinner.config.Env
import pl.edu.agh.iet.mydinner.databinding.ActivityRecipeListBinding
import pl.edu.agh.iet.mydinner.model.Recipe
import pl.edu.agh.iet.mydinner.util.Utils
import java.io.StringReader

class RecipeListActivity : AppCompatActivity() {

    private var recipes = mutableListOf<Recipe>()

    private lateinit var binding: ActivityRecipeListBinding
    private lateinit var recipeAdapter: RecyclerView.Adapter<RecipeViewHolder>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recipeAdapter = RecipeAdapter(recipes)
        binding.recipeListRecyclerView.adapter = recipeAdapter

        makeGetRecipesRequest()
    }

    private fun makeGetRecipesRequest() {
        Fuel.get("${Env.SERVER_URL}/recipes")
                .timeout(5000)
                .response { result ->
                    when (result) {
                        is Result.Success -> handleGetRecipesSuccess(result.get())
                        is Result.Failure -> handleGetRecipesFailure(result.error.message)
                    }
                }
    }

    private fun handleGetRecipesSuccess(result: ByteArray) {
        val myRecipes = parseJson(result)

        recipes.addAll(myRecipes)
        recipeAdapter.notifyDataSetChanged()
    }

    private fun handleGetRecipesFailure(error: String?) {
        val message = getString(R.string.recipe_get_all_failure)
        Utils.showToast("$message: $error", this)
    }

    private fun parseJson(result: ByteArray): List<Recipe> {
        val jsonString = String(result)
        val jsonObject = JSONObject(jsonString)
        val recipes = jsonObject.getJSONArray("recipes")

        return Gson().fromJson(recipes.toString(), Array<Recipe>::class.java).toList()
    }
}