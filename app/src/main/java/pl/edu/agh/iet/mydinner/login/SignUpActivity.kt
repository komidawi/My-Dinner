package pl.edu.agh.iet.mydinner.login

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

    fun makeSignUpRequest(view: View) {
        val createUserUrl = Env.SERVER_URL + "/users/user"

        val body = JSONObject()
        body.put("username", binding.usernameInput.text.toString())
        body.put("password", binding.passwordInput.text.toString())

        fireSignUpRequest(createUserUrl, body)
    }

    private fun fireSignUpRequest(url: String, body: JSONObject) {
        Fuel.post(url)
                .jsonBody(body.toString())
                .response { result ->
                    when (result) {
                        is Result.Success -> handleSignupSuccess()
                        is Result.Failure -> handleSignupFailure(result.error.message)
                    }
                }
    }

    private fun handleSignupSuccess() {
        val message = getString(R.string.signup_message_success)
        Utils.showToast(message, this)
    }

    private fun handleSignupFailure(error: String?) {
        val message = getString(R.string.signup_message_failure)
        Utils.showToast("$message: $error", this)
    }
}