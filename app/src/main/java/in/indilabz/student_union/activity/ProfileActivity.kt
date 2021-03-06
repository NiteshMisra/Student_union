@file:Suppress("DEPRECATION")

package `in`.indilabz.student_union.activity

import `in`.indilabz.student_union.INDIMaster
import `in`.indilabz.student_union.R
import `in`.indilabz.student_union.databinding.ActivityProfileBinding
import `in`.indilabz.student_union.extras.DatePickerFragment
import `in`.indilabz.student_union.model.Student
import `in`.indilabz.student_union.response.UpdateResponse
import `in`.indilabz.student_union.rest.RetrofitInstance
import `in`.indilabz.student_union.utils.Toaster
import `in`.indilabz.yorneeds.utils.INDIPreferences
import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.InputType
import android.util.Base64
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dmax.dialog.SpotsDialog
import java.io.ByteArrayOutputStream
import java.lang.Exception

class ProfileActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        dateOfBirth = "${String.format("%02d",dayOfMonth)}-${String.format("%02d",month+1)}-$year"
        updateDob(dateOfBirth)
    }

    private lateinit var binding: ActivityProfileBinding
    private lateinit var dialog: AlertDialog
    private lateinit var profile: Student
    private lateinit var image: String
    private var dateOfBirth : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile)
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        dialog = SpotsDialog.Builder().setContext(this).build()

        binding.changeImage.setOnClickListener {
            selectImage()
        }

        define()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 10 && resultCode == Activity.RESULT_OK && data != null) {
            val path: Uri = data.data!!
            try {
                val bitmap: Bitmap = MediaStore.Images.Media.getBitmap(contentResolver, path)!!
                val byteStream = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteStream)
                val imgBytes = byteStream.toByteArray()
                image = Base64.encodeToString(imgBytes, Base64.DEFAULT)
                binding.profileImage.scaleType = ImageView.ScaleType.FIT_XY
                binding.profileImage.setImageBitmap(bitmap)
                val student = INDIPreferences.user()!!
                student.imageUrl = image
                INDIPreferences.user(student)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun selectImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, 10)
    }

    private fun define() {

        profile = INDIPreferences.user()!!

        binding.uniqueId.text = profile.id.toString()
        binding.fullName.text = profile.full_name
        binding.phone.text = profile.phone
        binding.course.text = profile.course
        binding.address.text = profile.current_address
        binding.gender.text = profile.gender
        binding.fatherName.text = profile.father_name
        val dob = profile.dob
        val newDob = dob.substring(8) + "-" + dob.substring(5,7) + "-" + dob.substring(0,4)
        binding.dob.text = newDob
        binding.college.text = profile.college
        binding.email.text = profile.email
        binding.accomodation.text = profile.accommodation
        binding.courseYear.text = profile.course_year
        binding.partTimeJob.text = profile.job

        if (profile.imageUrl != "") {

            val decodeBytes = Base64.decode(profile.imageUrl, 0)
            val bitmap = BitmapFactory.decodeByteArray(decodeBytes, 0, decodeBytes.size)
            binding.profileImage.scaleType = ImageView.ScaleType.FIT_XY
            binding.profileImage.setImageBitmap(bitmap)
        }

        binding.fullName.setOnClickListener {
            editProfile("Full name", binding.fullName)
        }

        binding.phone.setOnClickListener {
            editProfile("Phone", binding.phone)
        }

        binding.course.setOnClickListener {
            editProfile("Course", binding.course)
        }

        binding.address.setOnClickListener {
            editProfile("Current address", binding.address)
        }

        binding.fatherName.setOnClickListener {
            editProfile("Father name", binding.fatherName)
        }

        binding.courseYear.setOnClickListener {
            editProfile("Course Year", binding.courseYear)
        }

        binding.gender.setOnClickListener {
            selectGender()
        }

        binding.partTimeJob.setOnClickListener {
            selectPartTimeJob()
        }

        binding.dob.setOnClickListener {
            val datePicker = DatePickerFragment(this)
            datePicker.show(supportFragmentManager, "date picker")
        }

        binding.college.setOnClickListener {
            editProfile("College", binding.college)
        }

        binding.accomodation.setOnClickListener {
            selectAccommodation()
        }

    }

    private fun editProfile(title: String, textView: TextView) {

        val taskEditText = EditText(this)

        if (title == "Phone") {
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
            ) { _, _ ->

                if (taskEditText.text.toString().isNotEmpty()){
                    when (title) {
                        "Full name" -> {
                            textView.text = taskEditText.text
                            updateName()
                        }
                        "Phone" -> {
                            textView.text = taskEditText.text
                            updatePhone()
                        }
                        "Course" -> {
                            textView.text = taskEditText.text
                            updateCourse()
                        }
                        "Father name" -> {
                            textView.text = taskEditText.text
                            updateFatherName()
                        }
                        "Current address" -> {
                            textView.text = taskEditText.text
                            updateAddress()
                        }
                        "College" -> {
                            textView.text = taskEditText.text
                            updateCollege()
                        }
                        "Course Year" -> {
                            textView.text = taskEditText.text
                            updateCourseYear()
                        }
                    }
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
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Select gender")
        val gender = arrayOf("Male", "Female")
        builder.setItems(
            gender
        ) { dialog, which -> setGender(gender[which]) }
        val dialog = builder.create()
        dialog.show()
    }

    private fun selectPartTimeJob() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Are you interested in part time job ??")
        val job = arrayOf("Yes", "No")
        builder.setItems(job) { _ , which ->
            binding.partTimeJob.text = job[which]
            updateJob()
        }
        val dialog = builder.create()
        dialog.show()
    }

    private fun selectAccommodation() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Select Accommodation Type")
        val acc = arrayOf("PG", "Hostel","Rent")
        builder.setItems(acc) { _ , which ->
            binding.accomodation.text = acc[which]
            updateAccommodation()
        }
        val dialog = builder.create()
        dialog.show()
    }

    private fun setGender(value: String) {

        binding.gender.text = value
        updateGender()
    }

    private fun updateAccommodation() {

        dialog.show()

        RetrofitInstance.updateRetrofit(
            INDIMaster.newApi().updateAccommodation(
                profile.id.toString(),
                binding.accomodation.text.toString()
            )
        ) { _: Int, bool: Boolean, value: String ->

            dialog.dismiss()
            if (bool) {
                Toaster.longt("Profile updated successfully")
                val user: Student = INDIPreferences.user()!!
                user.accommodation = binding.accomodation.text.toString()
                INDIPreferences.user(user)
            } else {
                Toaster.longt(value)
            }

        }
    }

    private fun updateJob() {

        dialog.show()

        RetrofitInstance.updateRetrofit(
            INDIMaster.newApi().updateJob(
                profile.id.toString(),
                binding.partTimeJob.text.toString()
            )
        ) { _: Int, bool: Boolean, value: String ->

            dialog.dismiss()
            if (bool) {
                Toaster.longt("Profile updated successfully")
                val user: Student = INDIPreferences.user()!!
                user.job = binding.partTimeJob.text.toString()
                INDIPreferences.user(user)
            } else {
                Toaster.longt(value)
            }

        }
    }

    private fun updateCourseYear() {

        dialog.show()

        RetrofitInstance.updateRetrofit(
            INDIMaster.newApi().updateCourseYear(
                profile.id.toString(),
                binding.courseYear.text.toString()
            )
        ) { _: Int, bool: Boolean, value: String ->

            dialog.dismiss()
            if (bool) {
                Toaster.longt("Profile updated successfully")
                val user: Student = INDIPreferences.user()!!
                user.course_year = binding.courseYear.text.toString()
                INDIPreferences.user(user)
            } else {
                Toaster.longt(value)
            }

        }
    }

    private fun updateGender() {

        dialog.show()

        RetrofitInstance.updateRetrofit(
            INDIMaster.newApi().updateGender(
                profile.id.toString(),
                binding.gender.text.toString()
            )
        ) { _: Int, bool: Boolean, value: String ->

            dialog.dismiss()
            if (bool) {
                Toaster.longt("Profile updated successfully")
                val user: Student = INDIPreferences.user()!!
                user.gender = binding.gender.text.toString()
                INDIPreferences.user(user)
            } else {
                Toaster.longt(value)
            }

        }
    }

    private fun updateName() {

        dialog.show()

        RetrofitInstance.updateRetrofit(
            INDIMaster.newApi().updateName(
                profile.id.toString(),
                binding.fullName.text.toString()
            )
        ) { _: Int, bool: Boolean, value: String ->

            dialog.dismiss()
            if (bool) {
                Toaster.longt("Profile updated successfully")
                val user: Student = INDIPreferences.user()!!
                user.full_name = binding.fullName.text.toString()
                INDIPreferences.user(user)
            } else {
                Toaster.longt(value)
            }

        }
    }

    private fun updatePhone() {

        dialog.show()

        RetrofitInstance.getOTPRetrofit(
            INDIMaster.newApi().sendOtp(
                binding.phone.text.toString()
            ),
            otp
        )
    }

    private val otp = { _: Int, bool: Boolean, value: UpdateResponse ->

        if (bool && value.result != null) {

            val serverOtp = value.result.toString()
            dialog.dismiss()

            val intent = Intent(this, ProfileOTP::class.java)
            intent.putExtra("Mobile", binding.phone.text.toString())
            intent.putExtra("Otp", serverOtp)
            startActivity(intent)

        } else {
            dialog.dismiss()
            Toast.makeText(this, value.error, Toast.LENGTH_LONG).show()
        }
    }

    private fun updateCollege() {

        dialog.show()

        RetrofitInstance.updateRetrofit(
            INDIMaster.newApi().updateCollege(
                profile.id.toString(),
                binding.college.text.toString()
            )
        ) { _: Int, bool: Boolean, value: String ->

            dialog.dismiss()
            if (bool) {
                Toaster.longt("Profile updated successfully")
                val user: Student = INDIPreferences.user()!!
                user.college = binding.college.text.toString()
                INDIPreferences.user(user)
            } else {
                Toaster.longt(value)
            }

        }
    }

    private fun updateCourse() {

        dialog.show()

        RetrofitInstance.updateRetrofit(
            INDIMaster.newApi().updateCourse(
                profile.id.toString(),
                binding.course.text.toString()
            )
        ) { _: Int, bool: Boolean, value: String ->

            dialog.dismiss()
            if (bool) {
                Toaster.longt("Profile updated successfully")
                val user: Student = INDIPreferences.user()!!
                user.course = binding.course.text.toString()
                INDIPreferences.user(user)
            } else {
                Toaster.longt(value)
            }

        }
    }

    private fun updateAddress() {

        dialog.show()

        RetrofitInstance.updateRetrofit(
            INDIMaster.newApi().updateCurrentAddress(
                profile.id.toString(),
                binding.address.text.toString()
            )
        ) { _: Int, bool: Boolean, value: String ->

            dialog.dismiss()
            if (bool) {
                Toaster.longt("Profile updated successfully")
                val user: Student = INDIPreferences.user()!!
                user.current_address = binding.address.text.toString()
                INDIPreferences.user(user)
            } else {
                Toaster.longt(value)
            }

        }
    }

    private fun updateDob(dob : String ) {

        dialog.show()

        val newDob = dob.substring(6) + "-" + dob.substring(3,5) + "-" + dob.substring(0,2)
        RetrofitInstance.updateRetrofit(
            INDIMaster.newApi().updateDob(
                profile.id.toString(),
                newDob
            )
        ) { _: Int, bool: Boolean, value: String ->

            dialog.dismiss()
            if (bool) {
                Toaster.longt("Profile updated successfully")
                binding.dob.text = dob
                val user = INDIPreferences.user()
                user!!.dob = dob
                INDIPreferences.user(user)
            } else {
                Toaster.longt(value)
            }

        }
    }

    private fun updateFatherName() {

        dialog.show()

        RetrofitInstance.updateRetrofit(
            INDIMaster.newApi().updateFatherName(
                profile.id.toString(),
                binding.fatherName.text.toString()
            )
        ) { _: Int, bool: Boolean, value: String ->

            dialog.dismiss()
            if (bool) {
                Toaster.longt("Profile updated successfully")
                val user = INDIPreferences.user()
                user!!.father_name = binding.fatherName.text.toString()
                INDIPreferences.user(user)
            } else {
                Toaster.longt(value)
            }

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
