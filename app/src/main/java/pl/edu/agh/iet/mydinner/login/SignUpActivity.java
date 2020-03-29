package pl.edu.agh.iet.mydinner.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import pl.edu.agh.iet.mydinner.databinding.ActivitySignUpBinding;
import pl.edu.agh.iet.mydinner.login.credentials.CredentialsStore;
import pl.edu.agh.iet.mydinner.login.credentials.SampleCredentialsStore;

public class SignUpActivity extends AppCompatActivity {

    private CredentialsStore credentialsStore = SampleCredentialsStore.getInstance();
    private ActivitySignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    public void onSignupButtonClicked(View view) {
        boolean isSignupSuccessful = credentialsStore.addCredentials(
                binding.usernameInput.getText().toString(),
                binding.passwordInput.getText().toString()
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
