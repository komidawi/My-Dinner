package pl.edu.agh.iet.mydinner.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import pl.edu.agh.iet.mydinner.R;
import pl.edu.agh.iet.mydinner.login.credentials.CredentialsStore;
import pl.edu.agh.iet.mydinner.login.credentials.SampleCredentialsStore;

public class SignUpActivity extends AppCompatActivity {

    private CredentialsStore credentialsStore = SampleCredentialsStore.getInstance();
    private EditText usernameInput;
    private EditText passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        usernameInput = findViewById(R.id.username_input);
        passwordInput = findViewById(R.id.password_input);
    }

    public void onSignupButtonClicked(View view) {
        boolean isSignupSuccessful = credentialsStore.addCredentials(
                usernameInput.getText().toString(),
                passwordInput.getText().toString()
        );

        handleSignupResult(isSignupSuccessful);
    }

    private void handleSignupResult(boolean isSuccessful) {
        if (isSuccessful) {
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }
}
