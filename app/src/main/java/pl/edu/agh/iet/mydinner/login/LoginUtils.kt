package pl.edu.agh.iet.mydinner.login

import org.json.JSONObject
import java.security.MessageDigest

class LoginUtils {
    companion object {
        fun prepareCredentials(username: String, password: String): JSONObject {
            val credentials = JSONObject()
            val encryptedPassword = hash(password)

            credentials.put("username", username)
            credentials.put("password", encryptedPassword)

            return credentials
        }

        private fun hash(plainTextPassword: String): String {
            val passwordByteArray = plainTextPassword.toByteArray(Charsets.UTF_8)
            val messageDigest = MessageDigest.getInstance("SHA-256")
            val result = messageDigest.digest(passwordByteArray)

            val sb = StringBuilder()
            for (byte in result) {
                sb.append(String.format("%02X", byte))
            }

            return sb.toString()
        }
    }
}