package `in`.indilabz.student_union.activity

import `in`.indilabz.student_union.INDIMaster
import `in`.indilabz.student_union.R
import `in`.indilabz.student_union.databinding.ForgotPasswordBinding
import `in`.indilabz.student_union.model.Student
import `in`.indilabz.student_union.rest.RetrofitInstance
import `in`.indilabz.student_union.utils.Toaster
import `in`.indilabz.yorneeds.utils.INDIPreferences
import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import dmax.dialog.SpotsDialog

class ForgotPassword : AppCompatActivity() {

    private lateinit var binding : ForgotPasswordBinding
    private lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.forgot_password)

        dialog = SpotsDialog.Builder().setContext(this).build()
        binding.changePassword.setOnClickListener {
            if(binding.mobile.text.toString().length != 10 ){
                Toaster.longt("Enter valid Mobile No.")
                return@setOnClickListener
            }
            if (binding.newPassword.text.toString().length <= 4){
                Toaster.longt("Password must be greater than 4 digit")
                return@setOnClickListener
            }
            changePassword()
        }

    }

    private fun changePassword() {

        dialog.setTitle("Please wait...")
        dialog.setMessage("Searching user...")
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()

        RetrofitInstance.updateRetrofit(
            INDIMaster.newApi().forgotPassword(
                binding.mobile.text.toString(),
                binding.newPassword.text.toString()
            )
        ) { _: Int, bool: Boolean, value: String ->

            dialog.dismiss()
            if (bool) {
                Toaster.longt("Password updated successfully")
                finish()
            } else {
                Toaster.longt("Mobile no. is not registered")
            }

        }

    }
}
