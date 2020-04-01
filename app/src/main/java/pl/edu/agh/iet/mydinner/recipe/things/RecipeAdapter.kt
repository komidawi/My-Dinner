package pl.edu.agh.iet.mydinner.recipe.things

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pl.edu.agh.iet.mydinner.R
import pl.edu.agh.iet.mydinner.model.Ingredient

class RecipeAdapter(ingredients: List<Ingredient>) : RecyclerView.Adapter<IngredientsViewHolder>() {
    var data = ingredients
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.text_item_view, parent, false) as TextView
        return IngredientsViewHolder(view)
    }

    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
        holder.textView.text = data[position].toString()
    }

    override fun getItemCount() = data.size
}