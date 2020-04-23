package pl.edu.agh.iet.mydinner.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import pl.edu.agh.iet.mydinner.databinding.ActivityLoginBinding
import pl.edu.agh.iet.mydinner.ui.recipe.list.RecipeListActivity

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
        onValidatedCredentials(true)
    }

    private fun onValidatedCredentials(areCredentialsValid: Boolean) {
        if (areCredentialsValid) {
            onLoginSuccess()
        } else {
            Toast.makeText(this, "Incorrect username/password", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onLoginSuccess() {
        val intent = Intent(this, RecipeListActivity::class.java)
        startActivity(intent)
    }
}