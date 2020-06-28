package pl.edu.agh.iet.mydinner.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.gson.jsonBody
import com.github.kittinunf.fuel.gson.responseObject
import com.github.kittinunf.result.Result
import org.json.JSONObject
import pl.edu.agh.iet.mydinner.R
import pl.edu.agh.iet.mydinner.config.NetworkingConfig
import pl.edu.agh.iet.mydinner.databinding.ActivitySignUpBinding
import pl.edu.agh.iet.mydinner.login.LoginData
import pl.edu.agh.iet.mydinner.model.login.LoginResponse
import pl.edu.agh.iet.mydinner.ui.recipe.list.RecipeListActivity
import pl.edu.agh.iet.mydinner.util.Utils

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun onSignUpButtonClick(view: View) {
        if (passwordsAreEqual()) {
            handleSignUpRequest()
        } else {
            showPasswordsAreNotEqualMessage()
        }
    }

    private fun passwordsAreEqual(): Boolean {
        val password = binding.passwordInput.text.toString()
        val passwordRepeat = binding.passwordInputRepeat.text.toString()

        return password == passwordRepeat
    }

    private fun showPasswordsAreNotEqualMessage() {
        val message = getString(R.string.password_are_not_equal_message)
        Utils.showToast(message, this)
    }

    private fun handleSignUpRequest() {
        val credentials = prepareCredentials()
        fireSignUpRequest(credentials)
    }

    private fun prepareCredentials(): JSONObject {
        val username = binding.usernameInput.text.toString()
        val password = binding.passwordInput.text.toString()

        return LoginUtils.prepareCredentials(username, password)
    }

    private fun fireSignUpRequest(body: JSONObject) {
        Fuel.post(NetworkingConfig.USER_ENDPOINT_URL)
                .jsonBody(body.toString())
                .timeout(NetworkingConfig.TIMEOUT_IN_MILLIS)
                .responseObject<LoginResponse> { _, _, result ->
                    when (result) {
                        is Result.Success -> handleSignupSuccess(result.value)
                        is Result.Failure -> showSignupFailureMessage(result.error.message)
                    }
                }
    }

    private fun handleSignupSuccess(response: LoginResponse) {
        LoginData.loggedUserId = response.id
        showSignupSuccessMessage()
        startHomeActivity()
    }

    private fun showSignupSuccessMessage() {
        val message = getString(R.string.signup_message_success)
        Utils.showToast(message, this)
    }

    private fun startHomeActivity() {
        val intent = Intent(this, RecipeListActivity::class.java)
        startActivity(intent)
    }

    private fun showSignupFailureMessage(error: String?) {
        val message = getString(R.string.signup_message_failure)
        Utils.showToast("$message: $error", this)
    }
}