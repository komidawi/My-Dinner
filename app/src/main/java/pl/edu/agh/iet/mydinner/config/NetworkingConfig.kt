package pl.edu.agh.iet.mydinner.config

import pl.edu.agh.iet.mydinner.login.LoginData

class NetworkingConfig {
    companion object {
        const val TIMEOUT_IN_MILLIS: Int = 5000
        const val USER_ENDPOINT_URL: String = "${Env.SERVER_URL}/users/user"
        const val LOGIN_ENDPOINT_URL: String = "${USER_ENDPOINT_URL}/login"
        var CREATE_RECIPE_ENDPOINT_URL: String = "$USER_ENDPOINT_URL/recipe/${LoginData.loggedUserId}"

        const val RECIPE_ENDPOINT_URL: String = "${Env.SERVER_URL}/recipes"

    }
}