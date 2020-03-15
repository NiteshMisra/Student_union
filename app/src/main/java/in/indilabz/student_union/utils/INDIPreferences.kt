package `in`.indilabz.yorneeds.utils

import `in`.indilabz.student_union.INDIMaster
import `in`.indilabz.student_union.model.Student
import `in`.indilabz.student_union.utils.Constants
import android.app.Activity
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class INDIPreferences : Constants {
    companion object {

        private val preferences: SharedPreferences
            get() = INDIMaster.applicationContext()!!.getSharedPreferences(Constants.APP_NAME, Activity.MODE_PRIVATE)

        fun preferenceEditor(): SharedPreferences.Editor {
            return INDIMaster.applicationContext()!!.getSharedPreferences(Constants.APP_NAME, Activity.MODE_PRIVATE).edit()
        }

        //// Add following lines to store and retrieve String

        fun session(value: Boolean) {
            val editor = preferences.edit()
            editor.putBoolean("APP_SESSION", value)
            editor.commit()
        }

        fun session(): Boolean {
            val mSharedPreferences = preferences
            return mSharedPreferences.getBoolean("APP_SESSION", false)
        }

        fun otp(value: String) {
            val editor = preferences.edit()
            editor.putString("USER_OTP", value)
            editor.commit()
        }

        fun otp(): String? {
            val mSharedPreferences = preferences
            return mSharedPreferences.getString("USER_OTP", "")
        }

        fun token(): String? {
            val mSharedPreferences = preferences
            return mSharedPreferences.getString("USER_TOKEN", "bd53e7b8-2c5f-11ea-babf-44a84246acd5")
        }

        fun token(value: String) {
            val editor = preferences.edit()
            editor.putString("USER_TOKEN", value)
            editor.commit()
        }

        fun phone(): String? {
            val mSharedPreferences = preferences
            return mSharedPreferences.getString("phone", "")
        }

        fun phone(value: String) {
            val editor = preferences.edit()
            editor.putString("phone", value)
            editor.commit()
        }

        fun backpress(value: Boolean) {
            val editor = preferences.edit()
            editor.putBoolean("BACK_PRESS", value)
            editor.commit()
        }

        fun backpress(): Boolean {
            val mSharedPreferences = preferences
            return mSharedPreferences.getBoolean("BACK_PRESS", false)
        }
        
       ////////////////////////////// USER

        fun user(questions: Student) {

            val editor = preferences.edit()
            val gson = Gson()
            val jsonDatum = gson.toJson(questions)
            editor.putString("user", jsonDatum)
            editor.commit()
        }

        fun user(): Student? {

            val settings = preferences

            if (settings.contains("user")) {
                val jsonDatum = settings.getString("user", "{}")
                val gson = Gson()
                val datum = gson.fromJson(jsonDatum,
                    Student::class.java)

               return datum
            } else
                return null
        }

        ////////////////////////////// Category

        fun category(categories : ArrayList<String>) {

            val editor = preferences.edit()
            val gson = Gson()
            val jsonDatum = gson.toJson(categories)
            editor.putString("category", jsonDatum)
            editor.commit()
        }

        fun category(): ArrayList<String>? {

            val settings = preferences

            return if (settings.contains("category")) {
                val jsonDatum = settings.getString("category", "")
                val gson = Gson()
                val type = object : TypeToken<List<String>>(){}.type!!
                val datum : ArrayList<String> = gson.fromJson(jsonDatum, type)
                datum
            } else
                null
        }


        ////////////////////////////// FRAGMENT NAME
        fun fragmentName(value: String) {
            val editor = preferences.edit()
            editor.putString("FRAGMENT_NAME", value)
            editor.commit()
        }

        fun fragmentName(): String? {
            val mSharedPreferences = preferences
            return mSharedPreferences.getString("FRAGMENT_NAME", "")
        }
        
        fun email(value: String?) {
            val editor = preferences.edit()
            editor.putString("EMAIL", value)
            editor.commit()
        }

        fun email(): String? {
            val mSharedPreferences = preferences
            return mSharedPreferences.getString("EMAIL", "")
        }


        fun totalTestTime(value: String) {
            val editor =  preferences.edit()
            editor.putString("test_time", value)
            editor.commit()
        }

        fun totalTestTime(): String? {
            val mSharedPreferences =  preferences
            return mSharedPreferences.getString("test_time", "")
        }

        fun exam(value: String) {
            val editor =  preferences.edit()
            editor.putString("exam", value)
            editor.commit()
        }

        fun exam(): String? {
            val mSharedPreferences =  preferences
            return mSharedPreferences.getString("exam", "")
        }

        fun logout(): Boolean{

            return preferences.edit().clear().commit()
        }

        fun questionId(value: String) {
            val editor =  preferences.edit()
            editor.putString("question", value)
            editor.commit()
        }

        fun questionId(): String? {
            val mSharedPreferences =  preferences
            return mSharedPreferences.getString("question", "")
        }
    }
}