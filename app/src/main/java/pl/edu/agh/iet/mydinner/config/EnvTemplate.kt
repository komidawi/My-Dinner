package pl.edu.agh.iet.mydinner.config

/**
 * Class for containing configuration.
 * Usage:
 * Specify required properties and change name of this file
 * to "Env.kt" and class to "Env"
 */
class EnvTemplate {
    companion object {
        private const val SERVER_IP = "127.0.0.1"
        private const val SERVER_PORT = "2020"
        const val SERVER_URL = "http://${SERVER_IP}:${SERVER_PORT}"
    }
}