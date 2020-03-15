package `in`.indilabz.student_union.activity

import `in`.aabhasjindal.otptextview.OTPListener
import `in`.indilabz.student_union.INDIMaster
import `in`.indilabz.student_union.R
import `in`.indilabz.student_union.databinding.ActivityOtpBinding
import `in`.indilabz.student_union.model.Register
import `in`.indilabz.student_union.model.Result
import `in`.indilabz.student_union.model.Student
import `in`.indilabz.student_union.response.RegisterResponse
import `in`.indilabz.student_union.rest.RetrofitInstance
import `in`.indilabz.student_union.utils.Toaster
import `in`.indilabz.yorneeds.utils.INDIPreferences
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.gson.Gson
import dmax.dialog.SpotsDialog

class OTPActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOtpBinding
    private lateinit var serverOtp: String
    private lateinit var registerObject: Register
    private lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_otp)
        val value: String = intent.getStringExtra("GSON")!!
        dialog = SpotsDialog.Builder().setContext(this).build()
        registerObject = Gson().fromJson(value, Register::class.java)
        binding.otpView.otpListener = listener
        serverOtp = intent.extras!!.getString("Otp")!!
        Toaster.longt(serverOtp)
    }

    private val listener = object : OTPListener {

        override fun onOTPComplete(otp: String) {

            if (otp == serverOtp) {

                dialog.setTitle("Please wait...")
                dialog.setMessage("Creating new user!!...")
                dialog.setCanceledOnTouchOutside(false)
                dialog.show()

                RetrofitInstance.getRegisterRetrofit(
                    INDIMaster.newApi().register(
                        registerObject.phone,
                        registerObject.fullname,
                        registerObject.course,
                        registerObject.email,
                        registerObject.year,
                        registerObject.fName,
                        registerObject.curAddress,
                        registerObject.perAddress,
                        registerObject.dob,
                        registerObject.college,
                        registerObject.password,
                        registerObject.gender,
                        registerObject.job,
                        registerObject.accommodation
                    ),
                    register
                )

            } else {
                Toaster.longt("Invalid Otp")
            }
        }

        override fun onInteractionListener() {

        }
    }

    private val register = { _: Int, bool: Boolean, value: RegisterResponse ->


        if (bool && value.success) {

            Toast.makeText(this, "Registered successfully", Toast.LENGTH_LONG).show()
            val result: Result? = value.result
            if (result != null) {

                val student2 = Student(
                    result.id,
                    result.fullName,
                    result.contactNumber,
                    result.email,
                    "",
                    result.password,
                    result.dob,
                    result.course,
                    result.year,
                    result.college,
                    result.fatherName,
                    result.currentAddress,
                    result.permanentAddress,
                    result.createdAt,
                    result.updatedAt,
                    result.gender,
                    result.job,
                    result.accommodation
                )

                INDIPreferences.user(student2)
                INDIPreferences.session(true)

                dialog.dismiss()
                val intent = Intent(this@OTPActivity, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)

            }
        } else {
            dialog.dismiss()
            Toast.makeText(this, value.message, Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (dialog.isShowing){
            dialog.dismiss()
        }
    }
}
