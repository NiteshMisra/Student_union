@file:Suppress("DEPRECATION")

package `in`.indilabz.student_union.activity

import `in`.indilabz.student_union.INDIMaster
import `in`.indilabz.student_union.R
import `in`.indilabz.student_union.databinding.ActivityRegisterBinding
import `in`.indilabz.student_union.extras.DatePickerFragment
import `in`.indilabz.student_union.model.Register
import `in`.indilabz.student_union.response.UpdateResponse
import `in`.indilabz.student_union.rest.RetrofitInstance
import `in`.indilabz.student_union.utils.Toaster
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.gson.Gson
import dmax.dialog.SpotsDialog
import java.util.*

class RegisterActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener,
    DatePickerDialog.OnDateSetListener {

    private lateinit var binding: ActivityRegisterBinding
    private var genderSelected: Boolean = false
    private var jobSelected: Boolean = false
    private var accSelected: Boolean = false
    private lateinit var gender: String
    private lateinit var job : String
    private lateinit var accommodation : String
    private var dateOfBirth: String = ""
    private var serverOtp : String ?= null
    private lateinit var jobAdapter : ArrayAdapter<CharSequence>
    private lateinit var genderAdapter : ArrayAdapter<CharSequence>
    private lateinit var accAdapter: ArrayAdapter<CharSequence>
    private lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_register
        )

        dialog = SpotsDialog.Builder().setContext(this).build()
        binding.dob.setOnClickListener {
            val datePicker = DatePickerFragment(this)
            datePicker.show(supportFragmentManager, "date picker")
        }

        binding.termsCondition.setOnClickListener {
            startActivity(Intent(this,TermsAndCondition::class.java))
        }

        genderAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.genderArray,
            android.R.layout.simple_spinner_item
        )
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.genderSpinner.adapter = genderAdapter
        binding.genderSpinner.onItemSelectedListener = this

        jobAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.jobArray,
            android.R.layout.simple_spinner_item
        )
        jobAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.job.adapter = jobAdapter
        binding.job.onItemSelectedListener = this

        accAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.accomodationArray,
            android.R.layout.simple_spinner_item
        )
        accAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.accomodation.adapter = accAdapter
        binding.accomodation.onItemSelectedListener = this

        if (binding.currentAddress.text!!.isEmpty()) {
            binding.addressCheckBox.isEnabled = false
        }

        binding.currentAddress.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                binding.addressCheckBox.isEnabled = false
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.addressCheckBox.isEnabled = binding.currentAddress.text!!.isNotEmpty()
                if (binding.addressCheckBox.isChecked) {
                    binding.permanentAddress.text = binding.currentAddress.text
                }
            }

        })

        binding.addressCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                if (binding.currentAddress.text!!.isNotEmpty()) {
                    binding.permanentAddress.text = binding.currentAddress.text
                }
            } else {
                binding.permanentAddress.setText("")
            }
        }

        binding.submit.setOnClickListener {

            if (binding.phone.editText!!.text.length != 10){
                Toaster.longt("Enter valid phone no.")
                return@setOnClickListener
            }

            if (binding.fullName.editText!!.text.isEmpty()){
                Toaster.longt("Enter your name")
                return@setOnClickListener
            }

            if (!genderSelected){
                Toaster.longt("Select your gender")
                return@setOnClickListener
            }

            if (!jobSelected){
                Toaster.longt("Select the job")
                return@setOnClickListener
            }

            if (binding.year.editText!!.text.isEmpty()){
                Toaster.longt("Enter the year of joining the course")
                return@setOnClickListener
            }

            if (binding.email.editText!!.text.isEmpty()){
                Toaster.longt("Enter your email Id")
                return@setOnClickListener
            }

            if (binding.course.editText!!.text.isEmpty()){
                Toaster.longt("Enter the course name")
                return@setOnClickListener
            }

            if (!accSelected){
                Toaster.longt("Select the accommodation")
                return@setOnClickListener
            }

            if (binding.password.editText!!.text.length <= 4){
                Toaster.longt("Password must be greater than 4 digit")
                return@setOnClickListener
            }

            if (binding.college.editText!!.text.isEmpty()){
                Toaster.longt("Enter your college name")
                return@setOnClickListener
            }

            if (dateOfBirth.isEmpty()){
                Toaster.longt("Enter your Date of Birth")
                return@setOnClickListener
            }

            if (binding.perAddress.editText!!.text.isEmpty()){
                Toaster.longt("Enter your Permanent Address")
                return@setOnClickListener
            }

            if (binding.curAddress.editText!!.text.isEmpty()){
                Toaster.longt("Enter your Current Address")
                return@setOnClickListener
            }

            if (binding.fName.editText!!.text.isEmpty()){
                Toaster.longt("Enter your Father's Name")
                return@setOnClickListener
            }

            if (binding.password.editText!!.text.toString().toLowerCase(Locale.getDefault()) != binding.confirmPassword.editText!!.text.toString().toLowerCase(
                    Locale.getDefault())){
                Toaster.longt("Password not matching")
                return@setOnClickListener
            }

            executeRegister()
        }

        binding.swipe.setOnRefreshListener { binding.swipe.isRefreshing = false }
    }

    private fun executeRegister() {

        dialog.setTitle("Please wait...")
        dialog.setMessage("Sending OTP...")
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()

        RetrofitInstance.getOTPRetrofit(
            INDIMaster.newApi().sendOtp(
                binding.phone.editText!!.text.toString()
            ),
            otp
        )
    }

    private val otp = { _: Int, bool: Boolean, value: UpdateResponse ->

        if (bool && value.result != null) {

            dialog.dismiss()
            serverOtp = value.result.toString()

            val newDob = dateOfBirth.substring(6) + "-" + dateOfBirth.substring(3,5) + "-" + dateOfBirth.substring(0,2)

            val register = Register(
                binding.phone.editText!!.text.toString(),
                binding.fullName.editText!!.text.toString(),
                binding.course.editText!!.text.toString(),
                binding.email.editText!!.text.toString(),
                binding.year.editText!!.text.toString(),
                binding.fName.editText!!.text.toString(),
                binding.curAddress.editText!!.text.toString(),
                binding.perAddress.editText!!.text.toString(),
                newDob,
                binding.college.editText!!.text.toString(),
                binding.password.editText!!.text.toString(),
                gender,
                job,
                accommodation
            )

            val intent = Intent(this,OTPActivity::class.java)
            intent.putExtra("GSON", Gson().toJson(register))
            intent.putExtra("Otp",serverOtp)
            startActivity(intent)

        } else {
            dialog.dismiss()
            Toast.makeText(this, value.message, Toast.LENGTH_LONG).show()
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        dateOfBirth = "${String.format("%02d",dayOfMonth)}-${String.format("%02d",month+1)}-$year"
        binding.dob.text = dateOfBirth
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        if (parent!!.adapter == genderAdapter){
            if (position != 0) {
                gender = parent.getItemAtPosition(position).toString()
                genderSelected = true
            } else {
                genderSelected = false
            }
        }else if (parent.adapter == jobAdapter){
            if (position != 0) {
                job = parent.getItemAtPosition(position).toString()
                jobSelected = true
            } else {
                jobSelected = false
            }
        }else if (parent.adapter == accAdapter){
            if (position != 0) {
                accommodation = parent.getItemAtPosition(position).toString()
                accSelected = true
            } else {
                accSelected = false
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        when {
            parent!!.adapter == jobAdapter -> jobSelected = false
            parent.adapter == genderAdapter -> genderSelected = false
            parent.adapter == accAdapter -> accSelected = false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (dialog.isShowing){
            dialog.dismiss()
        }
    }

}
