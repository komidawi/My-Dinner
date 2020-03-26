package pl.edu.agh.iet.mydinner.login.credentials;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SampleCredentialsStore implements CredentialsStore {

    private static final CredentialsStore instance = new SampleCredentialsStore();

    private Map<String, String> credentials = new HashMap<>();

    private SampleCredentialsStore() {
        addCredentials("admin", "admin");
    }

    public static CredentialsStore getInstance() {
        return instance;
    }

    @Override
    public boolean addCredentials(String username, String password) {
        if (!credentials.containsKey(username)) {
            credentials.put(username, password);
            return true;
        }

        return false;
    }

    @Override
    public boolean validateCredentials(String username, String password) {
        if (credentials.containsKey(username)) {
            return Objects.equals(credentials.get(username), password);
        }

        return false;
    }
}
