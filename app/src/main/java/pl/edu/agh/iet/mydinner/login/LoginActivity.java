package pl.edu.agh.iet.mydinner.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import pl.edu.agh.iet.mydinner.R;
import pl.edu.agh.iet.mydinner.SampleHomeActivity;
import pl.edu.agh.iet.mydinner.login.credentials.CredentialsStore;
import pl.edu.agh.iet.mydinner.login.credentials.SampleCredentialsStore;

public class LoginActivity extends AppCompatActivity {

    private CredentialsStore credentialsStore = SampleCredentialsStore.getInstance();
    private EditText usernameInput;
    private EditText passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameInput = findViewById(R.id.username_input);
        passwordInput = findViewById(R.id.password_input);
    }

    public void onSignupActionChosen(View view) {
        Intent signupIntent = new Intent(this, SignUpActivity.class);
        startActivity(signupIntent);
    }

    public void onLoginButtonClicked(View view) {
        boolean areCredentialsValid = credentialsStore.validateCredentials(
                usernameInput.getText().toString(),
                passwordInput.getText().toString()
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
