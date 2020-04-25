package pl.edu.agh.iet.mydinner.ui.login

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.extensions.jsonBody
import com.github.kittinunf.result.Result
import org.json.JSONObject
import pl.edu.agh.iet.mydinner.R
import pl.edu.agh.iet.mydinner.config.Env
import pl.edu.agh.iet.mydinner.databinding.ActivitySignUpBinding
import pl.edu.agh.iet.mydinner.util.Utils

@Suppress("SameParameterValue")
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
        val credentials = JSONObject()
        credentials.put("username", binding.usernameInput.text.toString())
        credentials.put("password", binding.passwordInput.text.toString())

        return credentials
    }

    private fun fireSignUpRequest(body: JSONObject) {
        Fuel.post("${Env.SERVER_URL}/users/user")
                .jsonBody(body.toString())
                .timeout(5000)
                .response { result ->
                    when (result) {
                        is Result.Success -> showSignupSuccessMessage()
                        is Result.Failure -> showSignupFailureMessage(result.error.message)
                    }
                }
    }

    private fun showSignupSuccessMessage() {
        val message = getString(R.string.signup_message_success)
        Utils.showToast(message, this)
    }

    private fun showSignupFailureMessage(error: String?) {
        val message = getString(R.string.signup_message_failure)
        Utils.showToast("$message: $error", this)
    }
}