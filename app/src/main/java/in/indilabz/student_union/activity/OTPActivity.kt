package `in`.indilabz.student_union.activity

import `in`.aabhasjindal.otptextview.OTPListener
import `in`.indilabz.student_union.R
import `in`.indilabz.student_union.databinding.ActivityOtpBinding
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil

class OTPActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOtpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_otp)

        binding.otpView.otpListener = listener
    }

    private val listener = object : OTPListener{

        override fun onOTPComplete(otp: String) {

            if(otp == "123456"){

                startActivity(Intent(this@OTPActivity, RegisterActivity::class.java))
                finish()
            }
        }

        override fun onInteractionListener() {

        }
    }
}
