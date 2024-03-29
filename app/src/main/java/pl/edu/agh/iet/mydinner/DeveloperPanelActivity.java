package pl.edu.agh.iet.mydinner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import pl.edu.agh.iet.mydinner.ui.recipe.list.RecipeListActivity;
import pl.edu.agh.iet.mydinner.ui.login.LoginActivity;
import pl.edu.agh.iet.mydinner.ui.recipe.create.CreateRecipeActivity;
import pl.edu.agh.iet.mydinner.ui.login.SignUpActivity;

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

    public void startSignUpActivity(View view) {
        Intent signUpActivity = new Intent(this, SignUpActivity.class);
        startActivity(signUpActivity);
    }

    public void startAddRecipeActivity(View view) {
        Intent addRecipeActivity = new Intent(this, CreateRecipeActivity.class);
        startActivity(addRecipeActivity);
    }

    public void startRecipeListActivity(View view) {
        Intent recipeListActivity = new Intent(this, RecipeListActivity.class);
        startActivity(recipeListActivity);
    }
}
