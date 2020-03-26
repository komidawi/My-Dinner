package pl.edu.agh.iet.mydinner.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import pl.edu.agh.iet.mydinner.SampleHomeActivity;
import pl.edu.agh.iet.mydinner.databinding.ActivityLoginBinding;
import pl.edu.agh.iet.mydinner.login.credentials.CredentialsStore;
import pl.edu.agh.iet.mydinner.login.credentials.SampleCredentialsStore;

public class LoginActivity extends AppCompatActivity {

    private CredentialsStore credentialsStore = SampleCredentialsStore.getInstance();
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    public void onSignupActionChosen(View view) {
        Intent signupIntent = new Intent(this, SignUpActivity.class);
        startActivity(signupIntent);
    }

    public void onLoginButtonClicked(View view) {
        boolean areCredentialsValid = credentialsStore.validateCredentials(
                binding.usernameInput.getText().toString(),
                binding.passwordInput.getText().toString()
        );

        onValidatedCredentials(areCredentialsValid);
    }

    private void onValidatedCredentials(boolean areCredentialsValid) {
        if (areCredentialsValid) {
            onLoginSuccess();
        } else {
            Toast.makeText(this, "Incorrect username/password", Toast.LENGTH_SHORT).show();
        }
    }

    private void onLoginSuccess() {
        Intent intent = new Intent(this, SampleHomeActivity.class);
        startActivity(intent);
    }
}
