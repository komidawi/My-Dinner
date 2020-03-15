package pl.edu.agh.iet.mydinner.login;

public class SampleLoginManager implements LoginManager{
    @Override
    public boolean validateCredentials(String username, String password) {
        return username.equals("admin") && password.equals("admin");
    }
}
