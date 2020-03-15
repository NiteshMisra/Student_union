package `in`.indilabz.student_union.fragment


import `in`.indilabz.student_union.INDIMaster
import `in`.indilabz.student_union.R
import `in`.indilabz.student_union.model.Student
import `in`.indilabz.student_union.model.StudentDetail
import `in`.indilabz.student_union.rest.APIHelper
import `in`.indilabz.student_union.rest.RetrofitInstance
import `in`.indilabz.student_union.utils.Toaster
import `in`.indilabz.yorneeds.utils.INDIPreferences
import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import dmax.dialog.SpotsDialog

class ProfileFragment : Fragment() {

    private lateinit var dialog: AlertDialog
    private lateinit var profile: Student
    private lateinit var uniqueId : TextView
    private lateinit var fullName : TextView
    private lateinit var phone : TextView
    private lateinit var course : TextView
    private lateinit var address : TextView
    private lateinit var gender : TextView
    private lateinit var fatherName : TextView
    private lateinit var dob : TextView
    private lateinit var college : TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog = SpotsDialog.Builder().setContext(this.activity).build()
        define()
    }

    private fun define(){

        profile = INDIPreferences.user()!!

        uniqueId = view!!.findViewById(R.id.uniqueId)
        fullName = view!!.findViewById(R.id.fullName)
        phone = view!!.findViewById(R.id.phone)
        course = view!!.findViewById(R.id.course)
        address = view!!.findViewById(R.id.address)
        gender = view!!.findViewById(R.id.gender)
        fatherName = view!!.findViewById(R.id.fatherName)
        dob =view!!.findViewById(R.id.dob)
        college = view!!.findViewById(R.id.college)

        uniqueId.text = profile.id.toString()
        fullName.text = profile.full_name
        phone.text = profile.phone
        course.text = profile.course
        address.text = profile.current_address
        gender.text = profile.gender
        fatherName.text = profile.father_name
        dob.text = profile.dob
        college.text = profile.college

        fullName.setOnClickListener{
            editProfile("Full name", fullName)
        }

        phone.setOnClickListener{
            editProfile("Phone", phone)
        }

        course.setOnClickListener{
            editProfile("Course", course)
        }

        address.setOnClickListener{
            editProfile("Current address", course)
        }

        fatherName.setOnClickListener{
            editProfile("Father name", address)
        }

        gender.setOnClickListener{
            selectGender()
        }

        dob.setOnClickListener{
            editProfile("Dob", fatherName)
        }

        college.setOnClickListener{
            editProfile("College", fatherName)
        }

    }

    private fun editProfile(title: String, textView: TextView) {

        val taskEditText = EditText(this.activity)

        if (title == "Phone") {
            taskEditText.inputType = InputType.TYPE_CLASS_PHONE
        } else {
            taskEditText.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        }

        val dpi: Float = this.resources.displayMetrics.density
        val dialog: AlertDialog = AlertDialog.Builder(this.activity)
            .setTitle("Edit $title")
            .setView(taskEditText)
            .setPositiveButton(
                "Submit"
            ) { dialog, which ->

                if (title == "Full name"){
                    textView.text = taskEditText.text
                    updateName()
                }else if(title == "Phone"){
                    textView.text = taskEditText.text
                    updatePhone()
                }else if(title == "Course"){
                    textView.text = taskEditText.text
                    updateCourse()
                }else if(title == "Father name"){
                    textView.text = taskEditText.text
                    updateFatherName()
                }else if(title == "Current address"){
                    textView.text = taskEditText.text
                    updateAddress()
                }else if(title == "Dob"){
                    textView.text = taskEditText.text
                    updateDob()
                }else if(title == "College"){
                    textView.text = taskEditText.text
                    updateCollege()
                }
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
        val builder: AlertDialog.Builder = AlertDialog.Builder(this.activity)
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

        gender.text = value
        updateGender()
    }

    private fun updateGender(){

        dialog.show()

        RetrofitInstance.updateRetrofit(
            INDIMaster.newApi().updateGender(
                profile.id.toString(),
                gender.text.toString()
            )
        ) { _:Int, bool:Boolean, value:String ->

            dialog.dismiss()
            if(bool){
                Toaster.longt("Profile updated successfully")
                val user: Student = INDIPreferences.user()!!
                user.gender = gender.text.toString()
                INDIPreferences.user(user)
            }
            else{
                Toaster.longt(value)
            }

        }
    }

    private fun updateName(){

        dialog.show()

        RetrofitInstance.updateRetrofit(
            INDIMaster.newApi().updateName(
                profile.id.toString(),
                fullName.text.toString()
            )
        ) { _:Int, bool:Boolean, value:String ->

            dialog.dismiss()
            if(bool){
                Toaster.longt("Profile updated successfully")
                val user: Student = INDIPreferences.user()!!
                user.full_name = fullName.text.toString()
                INDIPreferences.user(user)
            }
            else{
                Toaster.longt(value)
            }

        }
    }

    private fun updatePhone(){

        dialog.show()

        RetrofitInstance.updateRetrofit(
            INDIMaster.newApi().updatePhone(
                profile.id.toString(),
                phone.text.toString()
            )
        ) { _:Int, bool:Boolean, value:String ->

            dialog.dismiss()
            if(bool){
                Toaster.longt("Profile updated successfully")
                val user: Student = INDIPreferences.user()!!
                user.phone = phone.text.toString()
                INDIPreferences.user(user)
            }
            else{
                Toaster.longt(value)
            }

        }
    }

    private fun updateCollege(){

        dialog.show()

        RetrofitInstance.updateRetrofit(
            INDIMaster.newApi().updateCollege(
                profile.id.toString(),
                college.text.toString()
            )
        ) { _:Int, bool:Boolean, value:String ->

            dialog.dismiss()
            if(bool){
                Toaster.longt("Profile updated successfully")
                val user: Student = INDIPreferences.user()!!
                user.college= college.text.toString()
                INDIPreferences.user(user)
            }
            else{
                Toaster.longt(value)
            }

        }
    }

    private fun updateCourse(){

        dialog.show()

        RetrofitInstance.updateRetrofit(
            INDIMaster.newApi().updateCourse(
                profile.id.toString(),
                course.text.toString()
            )
        ) { _:Int, bool:Boolean, value:String ->

            dialog.dismiss()
            if(bool){
                Toaster.longt("Profile updated successfully")
                val user: Student = INDIPreferences.user()!!
                user.course = course.text.toString()
                INDIPreferences.user(user)
            }
            else{
                Toaster.longt(value)
            }

        }
    }

    private fun updateAddress(){

        dialog.show()

        RetrofitInstance.updateRetrofit(
            INDIMaster.newApi().updateCurrentAddress(
                profile.id.toString(),
                address.text.toString()
            )
        ) { _:Int, bool:Boolean, value:String ->

            dialog.dismiss()
            if(bool){
                Toaster.longt("Profile updated successfully")
                val user: Student = INDIPreferences.user()!!
                user.current_address = address.text.toString()
                INDIPreferences.user(user)
            }
            else{
                Toaster.longt(value)
            }

        }
    }

    private fun updateDob(){

        dialog.show()

        RetrofitInstance.updateRetrofit(
            INDIMaster.newApi().updateDob(
                profile.id.toString(),
                dob.text.toString()
            )
        ) { _:Int, bool:Boolean, value:String ->

            dialog.dismiss()
            if(bool){
                Toaster.longt("Profile updated successfully")
                val user = INDIPreferences.user()
                user!!.dob = dob.text.toString()
                INDIPreferences.user(user)
            }
            else{
                Toaster.longt(value)
            }

        }
    }

    private fun updateFatherName(){

        dialog.show()

        RetrofitInstance.updateRetrofit(
            INDIMaster.newApi().updateFatherName(
                profile.id.toString(),
                fatherName.text.toString()
            )
        ) { _:Int, bool:Boolean, value:String ->

            dialog.dismiss()
            if(bool){
                Toaster.longt("Profile updated successfully")
                val user = INDIPreferences.user()
                user!!.father_name = fatherName.text.toString()
                INDIPreferences.user(user)
            }
            else{
                Toaster.longt(value)
            }

        }
    }


}
