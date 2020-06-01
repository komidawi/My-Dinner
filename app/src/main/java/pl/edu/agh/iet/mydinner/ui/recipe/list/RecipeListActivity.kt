package pl.edu.agh.iet.mydinner.ui.recipe.list

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.result.Result
import com.google.gson.Gson
import org.json.JSONObject
import pl.edu.agh.iet.mydinner.R
import pl.edu.agh.iet.mydinner.config.Env
import pl.edu.agh.iet.mydinner.databinding.ActivityRecipeListBinding
import pl.edu.agh.iet.mydinner.model.recipe.Recipe
import pl.edu.agh.iet.mydinner.ui.recipe.create.CreateRecipeActivity
import pl.edu.agh.iet.mydinner.util.Utils

class RecipeListActivity : AppCompatActivity() {

    private var recipes = mutableListOf<Recipe>()

    private lateinit var binding: ActivityRecipeListBinding
    private lateinit var recipeAdapter: RecipeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeRecyclerView()
        initializeRecipeSearch()

        makeGetRecipesRequest()
    }

    private fun initializeRecyclerView() {
        recipeAdapter = RecipeAdapter(recipes)
        binding.recipeListRecyclerView.adapter = recipeAdapter
    }

    private fun initializeRecipeSearch() {
        binding.recipeSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                recipeAdapter.filter.filter(newText)
                return false
            }
        })
    }

    fun onSortButtonClick(view: View) {
        recipeAdapter.reverseSorting()
    }

    fun startCreateRecipeActivity(view: View) {
        val intent = Intent(this, CreateRecipeActivity::class.java)
        startActivity(intent)
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