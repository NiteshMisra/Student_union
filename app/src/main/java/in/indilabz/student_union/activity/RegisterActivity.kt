@file:Suppress("DEPRECATION")

package `in`.indilabz.student_union.activity

import `in`.indilabz.student_union.rest.RetrofitInstance
import `in`.indilabz.student_union.INDIMaster
import `in`.indilabz.student_union.R
import `in`.indilabz.student_union.databinding.ActivityRegisterBinding
import `in`.indilabz.student_union.extras.DatePickerFragment
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Base64
import android.view.View
import android.widget.*
import androidx.databinding.DataBindingUtil
import java.io.ByteArrayOutputStream
import java.lang.Exception
import java.util.*

class RegisterActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener, DatePickerDialog.OnDateSetListener {

    private lateinit var binding: ActivityRegisterBinding
    private var genderSelected : Boolean = false
    private lateinit var gender : String
    private var dateOfBirth : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_register
        )

        binding.dob.setOnClickListener{
            val datePicker = DatePickerFragment(this)
            datePicker.show(supportFragmentManager,"date picker")
        }

        val adapter = ArrayAdapter.createFromResource(this,R.array.genderArray,android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.genderSpinner.adapter = adapter

        binding.genderSpinner.onItemSelectedListener = this

        if (binding.currentAddress.text!!.isEmpty()){
            binding.addressCheckBox.isEnabled = false
        }

        binding.currentAddress.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                binding.addressCheckBox.isEnabled = false
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.addressCheckBox.isEnabled = binding.currentAddress.text!!.isNotEmpty()
                if (binding.addressCheckBox.isChecked){
                    binding.permanentAddress.text = binding.currentAddress.text
                }
            }

        })

        binding.addressCheckBox.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                if (isChecked){
                    if (binding.currentAddress.text!!.isNotEmpty()){
                        binding.permanentAddress.text = binding.currentAddress.text
                    }
                }else{
                    binding.permanentAddress.setText("")
                }
            }

        })

        binding.submit.setOnClickListener {

            if(binding.phone.editText!!.text.length == 10 &&

                binding.fullName.editText!!.text.isNotEmpty() &&

                genderSelected &&

                binding.course.editText!!.text.isNotEmpty() &&

                binding.email.editText!!.text.isNotEmpty() &&

                binding.year.editText!!.text.isNotEmpty() &&

                binding.fName.editText!!.text.isNotEmpty() &&

                binding.curAddress.editText!!.text.isNotEmpty() &&

                binding.perAddress.editText!!.text.isNotEmpty() &&

                dateOfBirth.isNotEmpty() &&

                binding.college.editText!!.text.isNotEmpty() &&

                binding.password.editText!!.text.length>4)
            {

                executeRegister()
            }
            else{

                Toast.makeText(this, "Invalid form data!", Toast.LENGTH_LONG).show()
            }
        }

        binding.swipe.setOnRefreshListener { binding.swipe.isRefreshing = false }
    }

    private fun executeRegister() {

        binding.swipe.isRefreshing = true

        RetrofitInstance.getRegisterRetrofit(
            INDIMaster.api().register(
                binding.phone.editText!!.text.toString(),
                binding.fullName.editText!!.text.toString(),
                gender,
                binding.course.editText!!.text.toString(),
                binding.email.editText!!.text.toString(),
                binding.year.editText!!.text.toString(),
                binding.fName.editText!!.text.toString(),
                binding.curAddress.editText!!.text.toString(),
                binding.perAddress.editText!!.text.toString(),
                dateOfBirth,
                binding.college.editText!!.text.toString(),
                binding.password.editText!!.text.toString()
            ),
            register
        )
    }

    private val register = { _:Int, bool:Boolean, value:String ->

        binding.swipe.isRefreshing = false

        if(bool){

            Toast.makeText(this, "Registered successfully", Toast.LENGTH_LONG).show()

            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
        }
        else{
            Toast.makeText(this, value, Toast.LENGTH_LONG).show()
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        dateOfBirth = "$year-$month-$dayOfMonth"
        binding.dob.text = dateOfBirth
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (position != 0){
            gender = parent!!.getItemAtPosition(position).toString()
            genderSelected = true
        }else{
            genderSelected = false
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        genderSelected = false
    }

}
