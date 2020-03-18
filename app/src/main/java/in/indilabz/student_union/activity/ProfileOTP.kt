package `in`.indilabz.student_union.activity

import `in`.aabhasjindal.otptextview.OTPListener
import `in`.aabhasjindal.otptextview.OtpTextView
import `in`.indilabz.student_union.INDIMaster
import `in`.indilabz.student_union.R
import `in`.indilabz.student_union.rest.RetrofitInstance
import `in`.indilabz.student_union.utils.Toaster
import `in`.indilabz.yorneeds.utils.INDIPreferences
import android.app.AlertDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dmax.dialog.SpotsDialog

class ProfileOTP : AppCompatActivity() {

    private lateinit var serverOtp : String
    private lateinit var otpView : OtpTextView
    private lateinit var dialog: AlertDialog
    private lateinit var mobileNo : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_otp)
        otpView = findViewById(R.id.profile_otp_view)
        otpView.otpListener = listener
        dialog = SpotsDialog.Builder().setContext(this).build()
        serverOtp = intent.extras!!.getString("Otp")!!
        mobileNo = intent.getStringExtra("Mobile")!!
        Toaster.longt(serverOtp)
    }

    private val listener = object : OTPListener {

        override fun onOTPComplete(otp: String) {

            if(otp == serverOtp){

                dialog.setTitle("Please wait...")
                dialog.setMessage("Updating Mobile No...")
                dialog.setCanceledOnTouchOutside(false)
                dialog.show()

                RetrofitInstance.updateRetrofit(
                    INDIMaster.newApi().updatePhone(
                        INDIPreferences.user()!!.id.toString(),
                        mobileNo
                    )
                ) { _:Int, bool:Boolean, _: String ->

                    dialog.dismiss()

                    if(bool){
                        Toaster.longt("Profile updated successfully")
                        val user  = INDIPreferences.user()!!
                        user.phone = mobileNo
                        INDIPreferences.user(user)
                        finish()

                    } else{
                        Toaster.longt("Failed to update profile")
                        finish()
                    }
                }

            }else{
                Toaster.longt("Invalid Otp")
            }
        }

        override fun onInteractionListener() {

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (dialog.isShowing){
            dialog.dismiss()
        }
    }
}
