package pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.util.TypedValue
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.R

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        var a: TypedValue = TypedValue()
        if (this.theme.resolveAttribute(android.R.attr.windowBackground, a, true)){
            Log.i("WindowBGColor", a.toString())
        }
        Handler(Looper.getMainLooper()).postDelayed({
            startMainActivity()
        }, 5000)
    }

    fun startMainActivity(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}