package `in`.indilabz.student_union.activity

import `in`.indilabz.student_union.INDIMaster
import `in`.indilabz.student_union.R
import `in`.indilabz.student_union.databinding.ActivityProfileBinding
import `in`.indilabz.student_union.model.StudentDetail
import `in`.indilabz.student_union.rest.APIHelper
import `in`.indilabz.student_union.rest.RetrofitInstance
import `in`.indilabz.student_union.utils.Toaster
import `in`.indilabz.yorneeds.utils.INDIPreferences
import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dmax.dialog.SpotsDialog


class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var dialog: AlertDialog
    private lateinit var profile: StudentDetail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile)
        setSupportActionBar(binding.toolbar)

        dialog = SpotsDialog.Builder().setContext(this).build()

        binding.toolbar.title = "Profile"

        getData()
    }

    private fun define(profile: StudentDetail){

        this.profile = profile

        binding.uniqueId.text = profile.student_id
        binding.fullName.text = profile.name
        binding.phone.text = profile.contact_number
        binding.username.text = profile.username
        binding.course.text = profile.course
        binding.address.text = profile.current_address
        binding.gender.text = profile.gender
        binding.fatherName.text = profile.father_name

        binding.fullName.setOnClickListener{
            editProfile("full name", binding.fullName)
        }

        binding.phone.setOnClickListener{
            editProfile("phone", binding.phone)
        }

        binding.username.setOnClickListener{
            editProfile("email", binding.username)
        }

        binding.course.setOnClickListener{
            editProfile("course", binding.course)
        }

        binding.address.setOnClickListener{
            editProfile("address", binding.address)
        }

        binding.gender.setOnClickListener{
            selectGender()
        }

        binding.fatherName.setOnClickListener{
            editProfile("father's name", binding.fatherName)
        }

    }

    private fun getData(){

        dialog.show()

        RetrofitInstance.getRetrofit(
            INDIMaster.api().profile(
                INDIPreferences.user()!!.student_p_id
            )
        , result)
    }

    private fun editProfile(title: String, textView: TextView) {

        val taskEditText = EditText(this)

        if (title.equals("phone")) {
            taskEditText.inputType = InputType.TYPE_CLASS_PHONE
        } else {
            taskEditText.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        }

        val dpi: Float = this.resources.displayMetrics.density
        val dialog: AlertDialog = AlertDialog.Builder(this)
            .setTitle("Edit $title")
            .setView(taskEditText)
            .setPositiveButton(
                "Submit"
            ) { dialog, which ->

                textView.text = taskEditText.text
                update()
            }
            .setNegativeButton("Cancel", null)
            .create()
        dialog.setView(
            taskEditText, (19.toFloat() * dpi).toInt(),
            (5.toFloat() * dpi).toInt(),
            (14.toFloat() * dpi).toInt(),
            (5.toFloat() * dpi).toInt()
        )
        dialog.show()
    }

    private fun selectGender() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Select gender")
        val gender = arrayOf("Male", "Female")
        builder.setItems(
            gender
        ) { dialog, which -> setGender(gender[which]) }
        val dialog = builder.create()
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
    }

    private fun setGender(value: String){

        binding.gender.text = value

        update()
    }

    private fun update(){

        dialog.show()

        RetrofitInstance.updateRetrofit(
            INDIMaster.api().update(
                profile.id,
                binding.fullName.text.toString(),
                binding.phone.text.toString(),
                binding.course.text.toString(),
                binding.address.text.toString(),
                binding.gender.text.toString(),
                binding.fatherName.text.toString()
            )
            , updateResult)
    }

    private val result = {_:Int, bool:Boolean, value:String ->

        dialog.dismiss()

        if(bool){

            val result = APIHelper.result(value)

            val datumList:Array<StudentDetail> = INDIMaster.gson.fromJson(
                result,
                Array<StudentDetail>::class.java)

            define(datumList[0])
        }
        else{
            Toaster.longt("Error while getting data")
        }
    }


    private val updateResult = { _:Int, bool:Boolean, value:String ->

        dialog.dismiss()

        if(bool){
            Toaster.longt("Profile updated successfully")
            getData()
        }
        else{
            Toaster.longt("Failed to update profile")
        }
    }

    override fun onBackPressed() {
        this.finish()
    }
}
