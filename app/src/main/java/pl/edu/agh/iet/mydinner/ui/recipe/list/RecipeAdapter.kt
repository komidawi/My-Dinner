package pl.edu.agh.iet.mydinner.ui.recipe.list

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.recipe_item_view.view.*
import pl.edu.agh.iet.mydinner.R
import pl.edu.agh.iet.mydinner.model.recipe.Recipe
import pl.edu.agh.iet.mydinner.ui.recipe.display.DisplayRecipeActivity

class RecipeAdapter(val recipes: MutableList<Recipe>) : RecyclerView.Adapter<RecipeViewHolder>(), Filterable {

    var sortingMode: SortingMode = SortingMode.NO_SORTING
    var data = mutableListOf<Recipe>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    init {
        data = recipes
    }

    fun reverseSorting() {
        data.sortBy { it.name }

        if (sortingMode == SortingMode.NAME_ASCENDING) {
            data.reverse()
            sortingMode = SortingMode.NAME_DESCENDING
        } else {
            sortingMode = SortingMode.NAME_ASCENDING
        }

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.recipe_item_view, parent, false)

        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = data[position]
        holder.bind(recipe)
    }

    override fun getFilter(): Filter {
        return FilterCreator.createFilter(this)
    }

    override fun getItemCount(): Int = data.size
}

class RecipeViewHolder(private val root: View) : RecyclerView.ViewHolder(root) {

    private val name = root.recipe_item_title
    private val description = root.recipe_item_description

    fun bind(recipe: Recipe) {
        name.text = recipe.name
        description.text = recipe.details

        setOnClickListener(recipe)
    }

    private fun setOnClickListener(recipe: Recipe) {
        root.setOnClickListener {
            val context = it.context
            val intent = Intent(context, DisplayRecipeActivity::class.java)
            intent.putExtra(RECIPE_EXTRA_ID, Gson().toJson(recipe))

            context?.startActivity(intent)
        }
    }

    companion object {
        const val RECIPE_EXTRA_ID = "THE_RECIPE"
    }
}
