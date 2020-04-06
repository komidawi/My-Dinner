package pl.edu.agh.iet.mydinner.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import pl.edu.agh.iet.mydinner.databinding.ActivitySignUpBinding
import pl.edu.agh.iet.mydinner.login.credentials.SampleCredentialsStore

class SignUpActivity : AppCompatActivity() {

    private val credentialsStore = SampleCredentialsStore.getInstance()

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun onSignupButtonClicked(view: View?) {
        val isSignupSuccessful = credentialsStore.addCredentials(
                binding.usernameInput.text.toString(),
                binding.passwordInput.text.toString()
        )
        handleSignupResult(isSignupSuccessful)
    }

    private fun handleSignupResult(isSuccessful: Boolean) {
        if (isSuccessful) {
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
        }
    }
}