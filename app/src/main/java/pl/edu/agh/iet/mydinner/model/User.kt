package pl.edu.agh.iet.mydinner.model

import java.time.LocalDateTime

data class User(
        val id: Long,
        var username: String,
        var password: String,
        var createdOn: LocalDateTime,
        var recipes: List<Recipe>
)
