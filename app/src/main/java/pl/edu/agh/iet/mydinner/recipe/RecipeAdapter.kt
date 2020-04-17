package pl.edu.agh.iet.mydinner.recipe

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pl.edu.agh.iet.mydinner.R
import pl.edu.agh.iet.mydinner.model.IngredientAmount

class IngredientAmountViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

class RecipeAdapter(ingredientAmounts: List<IngredientAmount>) : RecyclerView.Adapter<IngredientAmountViewHolder>() {
    var data = ingredientAmounts
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientAmountViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.text_item_view, parent, false) as TextView
        return IngredientAmountViewHolder(view)
    }

    override fun onBindViewHolder(holder: IngredientAmountViewHolder, position: Int) {
        holder.textView.text = data[position].toString()
    }

    override fun getItemCount() = data.size
}