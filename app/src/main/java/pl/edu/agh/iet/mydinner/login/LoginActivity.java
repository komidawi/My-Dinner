package pl.edu.agh.iet.mydinner.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import pl.edu.agh.iet.mydinner.R;
import pl.edu.agh.iet.mydinner.SampleHomeActivity;

public class LoginActivity extends AppCompatActivity {

    private LoginManager loginManager = new SampleLoginManager();
    private EditText usernameInput;
    private EditText passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameInput = findViewById(R.id.username_input);
        passwordInput = findViewById(R.id.password_input);
    }

    public void onLoginButtonPress(View view) {
        boolean areCredentialsValid = loginManager.validateCredentials(
                usernameInput.getText().toString(),
                passwordInput.getText().toString()
        );

        handleCredentials(areCredentialsValid);
    }

    private void handleCredentials(boolean areCredentialsValid) {
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
