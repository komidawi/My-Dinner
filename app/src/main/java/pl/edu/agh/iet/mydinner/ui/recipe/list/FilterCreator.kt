package pl.edu.agh.iet.mydinner.ui.recipe.list

import android.widget.Filter
import pl.edu.agh.iet.mydinner.model.recipe.Recipe
import java.util.*

class FilterCreator {
    companion object {
        fun createFilter(recipeAdapter: RecipeAdapter): Filter {
            return object : Filter() {

                override fun performFiltering(constraint: CharSequence?): FilterResults {
                    val filtered = prepareFilteredList(constraint)

                    val filterResults = FilterResults()
                    filterResults.values = filtered

                    return filterResults
                }

                private fun prepareFilteredList(constraint: CharSequence?): MutableList<Recipe> {
                    val searchText = constraint.toString()

                    return if (searchText.isEmpty()) {
                        recipeAdapter.recipes
                    } else {
                        filterList(searchText)
                    }
                }

                private fun filterList(searchText: String): MutableList<Recipe> {
                    val resultList = mutableListOf<Recipe>()

                    for (recipe in recipeAdapter.recipes) {
                        val name = recipe.name.toLowerCase(Locale.ROOT)
                        val search = searchText.toLowerCase(Locale.ROOT)

                        if (name.contains(search)) {
                            resultList.add(recipe)
                        }
                    }

                    return resultList
                }

                @Suppress("UNCHECKED_CAST") // Suppressing due to language limitations
                override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                    val resultData = results?.values as MutableList<Recipe>
                    recipeAdapter.data = resultData
                }
            }
        }
    }
}