package pl.edu.agh.iet.mydinner.ui.recipe.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recipe_item_view.view.*
import pl.edu.agh.iet.mydinner.R
import pl.edu.agh.iet.mydinner.model.Recipe

class RecipeViewHolder(root: View) : RecyclerView.ViewHolder(root) {
    private val name = root.recipe_item_title
    private val description = root.recipe_item_description

    fun bind(recipe: Recipe) {
        name.text = recipe.name
        description.text = recipe.details
    }
}

class RecipeAdapter(recipes: List<Recipe>) : RecyclerView.Adapter<RecipeViewHolder>() {
    var data = recipes
        set(value) {
            field = value
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

    override fun getItemCount(): Int = data.size
}
