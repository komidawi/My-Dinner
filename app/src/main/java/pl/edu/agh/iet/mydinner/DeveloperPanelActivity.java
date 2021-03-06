package pl.edu.agh.iet.mydinner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import pl.edu.agh.iet.mydinner.login.LoginActivity;

/**
 * Activity for developing purposes.
 * Acts as a starting point for all app activities.
 */
public class DeveloperPanelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer_panel);
    }

    public void startLoginActivity(View view) {
        Intent loginActivity = new Intent(this, LoginActivity.class);
        startActivity(loginActivity);
    }

    public void startHomeActivity(View view) {
        Intent homeActivity = new Intent(this, SampleHomeActivity.class);
        startActivity(homeActivity);
    }
}
