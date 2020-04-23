package pl.edu.agh.iet.mydinner.ui.login.credentials;

public interface CredentialsStore {
    boolean addCredentials(String username, String password);

    boolean validateCredentials(String username, String password);
}
