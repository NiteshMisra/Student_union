package `in`.indilabz.student_union.activity

import `in`.indilabz.student_union.rest.RetrofitInstance
import `in`.indilabz.student_union.INDIMaster
import `in`.indilabz.student_union.R
import `in`.indilabz.student_union.databinding.ActivityLoginBinding
import `in`.indilabz.student_union.model.Result
import `in`.indilabz.student_union.model.Student
import `in`.indilabz.student_union.response.RegisterResponse
import `in`.indilabz.student_union.utils.Toaster
import `in`.indilabz.yorneeds.utils.INDIPreferences
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)


        findViewById<Button>(R.id.submit).setOnClickListener {

            if (binding.email.editText!!.text.length > 3) {

                if (binding.password.editText!!.text.length > 4) {

                    RetrofitInstance.getLoginRetrofit(
                        INDIMaster.api().login(
                            binding.email.editText!!.text.toString(),
                            binding.password.editText!!.text.toString()
                        ), login
                    )
                } else {
                    Toaster.longt("Invalid password!")
                }
            } else {
                Toaster.longt("Invalid phone!")
            }

        }

        binding.helpdesk.setOnClickListener {
            startActivity(Intent(this, HelpDesk::class.java))
        }

        findViewById<Button>(R.id.register).setOnClickListener {

            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }

        binding.helpdesk.setOnClickListener {
            startActivity(Intent(this, HelpDesk::class.java))
        }


    }

    private val login = { _: Int, bool: Boolean, value: RegisterResponse ->

        if (bool && value.success) {

            val result: Result? = value.result
            if (result != null) {

                val student2 = Student(
                    result.id,
                    result.fullName,
                    result.contactNumber,
                    result.email,
                    result.password,
                    result.dob,
                    result.course,
                    result.year,
                    result.college,
                    result.fatherName,
                    result.currentAddress,
                    result.permanentAddress,
                    result.createdAt,
                    result.updatedAt
                )

                INDIPreferences.user(student2)
                INDIPreferences.session(true)

                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        } else {
            Toaster.longt(value.message)
        }
    }
}
