package pl.edu.agh.iet.mydinner.util

import android.content.Context
import android.widget.Toast

class Utils {
    companion object {
        fun showToast(message: String, context: Context) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
    }
}