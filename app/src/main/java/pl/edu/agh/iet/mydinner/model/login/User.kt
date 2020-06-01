package pl.edu.agh.iet.mydinner.model.login

import pl.edu.agh.iet.mydinner.model.recipe.Recipe
import java.time.LocalDateTime

data class User(
        val id: Long,
        var username: String,
        var password: String,
        var createdOn: LocalDateTime,
        var recipes: List<Recipe>
)
