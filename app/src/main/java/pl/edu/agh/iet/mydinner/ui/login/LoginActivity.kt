package pl.edu.agh.iet.mydinner.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.extensions.jsonBody
import com.github.kittinunf.fuel.gson.responseObject
import com.github.kittinunf.result.Result
import org.json.JSONObject
import pl.edu.agh.iet.mydinner.R
import pl.edu.agh.iet.mydinner.config.Env
import pl.edu.agh.iet.mydinner.databinding.ActivityLoginBinding
import pl.edu.agh.iet.mydinner.login.LoginData
import pl.edu.agh.iet.mydinner.model.login.LoginResponse
import pl.edu.agh.iet.mydinner.ui.recipe.list.RecipeListActivity
import pl.edu.agh.iet.mydinner.util.Utils

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun onSignupActionChosen(view: View) {
        val signupIntent = Intent(this, SignUpActivity::class.java)
        startActivity(signupIntent)
    }

    fun onLoginButtonClicked(view: View) {
        val credentials = prepareCredentials()
        handleLoginRequest(credentials)
    }

    private fun prepareCredentials(): JSONObject {
        val username = binding.usernameInput.text.toString()
        val password = binding.passwordInput.text.toString()

        return LoginUtils.prepareCredentials(username, password)
    }

    private fun handleLoginRequest(credentials: JSONObject) {
        Fuel.post("${Env.SERVER_URL}/users/user/login")
                .jsonBody(credentials.toString())
                .timeout(5000)
                .responseObject<LoginResponse> { _, _, result ->
                    when (result) {
                        is Result.Success -> handleLoginSuccess(result.value)
                        is Result.Failure -> handleLoginFailure(result.error)
                    }
                }
    }

    private fun handleLoginSuccess(response: LoginResponse) {
        LoginData.loggedUserId = response.id
        showLoginSuccessMessage()
        startHomeActivity()
    }

    private fun showLoginSuccessMessage() {
        val message = getString(R.string.log_in_success_message)
        Utils.showToast(message, this)
    }

    private fun startHomeActivity() {
        val intent = Intent(this, RecipeListActivity::class.java)
        startActivity(intent)
    }

    private fun handleLoginFailure(error: FuelError) {
        when (error.response.statusCode) {
            409 -> show409ErrorMessage()
            else -> showGenericErrorMessage(error)
        }
    }

    private fun show409ErrorMessage() {
        val message = getString(R.string.log_in_failure_http_409)
        Utils.showToast(message, this)
    }

    private fun showGenericErrorMessage(error: FuelError) {
        val message = getString(R.string.request_error)
        Utils.showToast("$message: ${error.message}", this)
    }
}