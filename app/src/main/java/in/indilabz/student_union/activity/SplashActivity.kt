package `in`.indilabz.student_union.activity

import `in`.indilabz.student_union.R
import `in`.indilabz.yorneeds.utils.INDIPreferences
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        Handler().postDelayed(
            run
        , 3000)
    }

    private val run = Runnable {

        if(INDIPreferences.session()){

            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }
        else{
            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            finish()
        }



    }
}
