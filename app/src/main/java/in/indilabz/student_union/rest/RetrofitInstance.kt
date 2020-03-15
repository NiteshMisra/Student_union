package `in`.indilabz.student_union.rest

import `in`.indilabz.review_application.rest.ModelAPIError
import `in`.indilabz.student_union.INDIMaster
import `in`.indilabz.student_union.activity.SplashActivity
import `in`.indilabz.student_union.response.*
import android.content.Intent
import android.os.AsyncTask

import java.util.concurrent.TimeUnit

import `in`.indilabz.student_union.utils.Constants
import `in`.indilabz.student_union.utils.Toaster
import `in`.indilabz.yorneeds.utils.INDIPreferences
import android.util.Log
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class RetrofitInstance : Constants {

    private class RetrofitAPI (private val retrofitListener: (Int, Boolean, String) -> Unit?, calls: Call<String>?) : AsyncTask<String, String, String>() {

        init {
            call = calls
        }

        override fun doInBackground(vararg params: String?): String? {

            call!!.clone().enqueue(object : Callback<String> {

                override fun onResponse(call: Call<String>?, response: Response<String>?) {

                    if (response!!.isSuccessful) {

                        try {

                            Log.d("TAG_RETROFIT_RESULT", response.body()!!)

                            retrofitListener.invoke(response.code(), true, response.body()!!)
                        } catch (e: Exception) {

                            Log.d("TAG_RETROFIT_ERROR", e.toString())

                            retrofitListener.invoke(response.code(), false, "Error while getting data")
                        }

                    } else {

                       try {
                            Log.d("TAG_REAL_ERROR", response.errorBody()!!.string())
                        } catch (e: Exception) {

                            Log.d("TAG_REAL_ERROR_EX", e.message!!)
                        }

                        try {

                            //Toaster.longToast(response.errorBody()!!.string())

                            val apiError = INDIMaster.gson.fromJson(response.errorBody()!!.string(), ModelAPIError::class.java)

                            if (apiError.error == "AUTH_ERROR") {

                                if (INDIPreferences.preferenceEditor().clear().commit()) {

                                    INDIMaster.applicationContext().startActivity(Intent(INDIMaster.applicationContext(), SplashActivity::class.java))
                                    INDIPreferences.session(false)
                                    INDIPreferences.backpress(false)
                                }

                            } else {

                                retrofitListener.invoke(response.code(),
                                        false, apiError.error!!)
                            }
                        } catch (e: Exception) {

                           /// Log.d("TAG_EXCEPTION_ERROR", e.toString())

                            try{
                                retrofitListener.invoke(response.code(),
                                        false, "Error while fetching data!")
                            }
                            catch (e : Exception){
                                e.printStackTrace()
                            }
                        }

                    }

                }

                override fun onFailure(call: Call<String>, t: Throwable) {

                    //Log.d("TAG_RETROFIT_THROW", t.message)
                    try{
                        retrofitListener.invoke(404, false, "Please check your internet connection")
                    }catch (e : Exception){
                        e.printStackTrace()
                    }
                    //Toaster.LongToast("Unable to connect to internet");
                }
            })

            return null
        }


        override fun onPostExecute(result: String?) {

        }

        override fun onPreExecute() {

        }

    }

    private class RegisterRetrofitAPI (private val retrofitListener: (Int, Boolean, RegisterResponse) -> Unit?, calls: Call<RegisterResponse>?) : AsyncTask<String, String, String>() {

        init {
            registerCall = calls
        }

        override fun doInBackground(vararg params: String?): String? {

            registerCall!!.clone().enqueue(object : Callback<RegisterResponse> {

                override fun onResponse(call: Call<RegisterResponse>?, response: Response<RegisterResponse>?) {

                    if (response!!.isSuccessful) {

                        try {

                            //Log.d("TAG_RETROFIT_RESULT", response.body()!!)

                            val registerResponse : RegisterResponse = response.body()!!
                            if (registerResponse.success){
                                retrofitListener.invoke(response.code(), true, registerResponse)
                            }else{
                                retrofitListener.invoke(response.code(), false, registerResponse)
                            }
                        } catch (e: Exception) {

                            Log.d("TAG_RETROFIT_ERROR", e.toString())

                            retrofitListener.invoke(
                                response.code(), false,
                                RegisterResponse(false, "", e.toString(), null)
                            )
                        }

                    } else {

                        try {
                            Log.d("TAG_REAL_ERROR", response.errorBody()!!.string())
                        } catch (e: Exception) {

                            Log.d("TAG_REAL_ERROR_EX", e.message!!)
                        }

                        try {

                            //Toaster.longToast(response.errorBody()!!.string())

                            val apiError = INDIMaster.gson.fromJson(response.errorBody()!!.string(), ModelAPIError::class.java)

                            if (apiError.error == "AUTH_ERROR") {

                                if (INDIPreferences.preferenceEditor().clear().commit()) {

                                    INDIMaster.applicationContext().startActivity(Intent(INDIMaster.applicationContext(), SplashActivity::class.java))
                                    INDIPreferences.session(false)
                                    INDIPreferences.backpress(false)
                                }

                            } else {

                                retrofitListener.invoke(
                                    response.code(), false,
                                    RegisterResponse(false, "", apiError.error, null)
                                )
                            }
                        } catch (e: Exception) {

                            /// Log.d("TAG_EXCEPTION_ERROR", e.toString())

                            try{
                                retrofitListener.invoke(
                                    response.code(), false,
                                    RegisterResponse(false, "","Error while fetching data", null)
                                )
                            }
                            catch (e : Exception){
                                e.printStackTrace()
                            }
                        }

                    }

                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {

                    //Log.d("TAG_RETROFIT_THROW", t.message)
                    try{
                        retrofitListener.invoke(
                            404, false,
                            RegisterResponse(false, "", "Please check your internet connection", null)
                        )
                    }catch (e : Exception){
                        e.printStackTrace()
                    }
                    //Toaster.LongToast("Unable to connect to internet");
                }
            })

            return null
        }


        override fun onPostExecute(result: String?) {

        }

        override fun onPreExecute() {

        }

    }

    private class DiscountRetrofitAPI (private val retrofitListener: (Int, Boolean, ShopResponse) -> Unit?, calls: Call<ShopResponse>?) : AsyncTask<String, String, String>() {

        init {
            discountCall = calls
        }

        override fun doInBackground(vararg params: String?): String? {

            discountCall!!.clone().enqueue(object : Callback<ShopResponse> {

                override fun onResponse(call: Call<ShopResponse>?, response: Response<ShopResponse>?) {

                    if (response!!.isSuccessful) {

                        try {

                            //Log.d("TAG_RETROFIT_RESULT", response.body()!!)

                            val discountResponse = response.body()!!
                            if (discountResponse.success){
                                retrofitListener.invoke(response.code(), true, response.body()!!)
                            }else{
                                retrofitListener.invoke(response.code(), false, response.body()!!)
                            }
                        } catch (e: Exception) {

                            Log.d("TAG_RETROFIT_ERROR", e.toString())

                            retrofitListener.invoke(response.code(), false, response.body()!!)
                        }

                    } else {

                        try {
                            Log.d("TAG_REAL_ERROR", response.errorBody()!!.string())
                        } catch (e: Exception) {

                            Log.d("TAG_REAL_ERROR_EX", e.message!!)
                        }

                        try {

                            //Toaster.longToast(response.errorBody()!!.string())

                            val apiError = INDIMaster.gson.fromJson(response.errorBody()!!.string(), ModelAPIError::class.java)

                            if (apiError.error == "AUTH_ERROR") {

                                if (INDIPreferences.preferenceEditor().clear().commit()) {

                                    INDIMaster.applicationContext().startActivity(Intent(INDIMaster.applicationContext(), SplashActivity::class.java))
                                    INDIPreferences.session(false)
                                    INDIPreferences.backpress(false)
                                }

                            } else {

                                retrofitListener.invoke(response.code(), false,response.body()!!)
                            }
                        } catch (e: Exception) {

                            /// Log.d("TAG_EXCEPTION_ERROR", e.toString())

                            try{
                                retrofitListener.invoke(response.code(), false, response.body()!!)
                            }
                            catch (e : Exception){
                                e.printStackTrace()
                            }
                        }

                    }

                }

                override fun onFailure(call: Call<ShopResponse>, t: Throwable) {

                    //Log.d("TAG_RETROFIT_THROW", t.message)
                    try{

                        val discountResponse = ShopResponse(false,t.message,null)
                        retrofitListener.invoke(404, false, discountResponse)
                    }catch (e : Exception){
                        e.printStackTrace()
                    }
                    //Toaster.LongToast("Unable to connect to internet");
                }
            })

            return null
        }


        override fun onPostExecute(result: String?) {

        }

        override fun onPreExecute() {

        }

    }

    private class DiscountInfoRetrofitAPI (private val retrofitListener: (Int, Boolean, DiscountInfoResponse) -> Unit?, calls: Call<DiscountInfoResponse>?) : AsyncTask<String, String, String>() {

        init {
            discountInfoCall = calls
        }

        override fun doInBackground(vararg params: String?): String? {

            discountInfoCall!!.clone().enqueue(object : Callback<DiscountInfoResponse> {

                override fun onResponse(call: Call<DiscountInfoResponse>?, response: Response<DiscountInfoResponse>?) {

                    if (response!!.isSuccessful) {

                        try {

                            //Log.d("TAG_RETROFIT_RESULT", response.body()!!)

                            val discountInfoResponse = response.body()!!
                            if (discountInfoResponse.responseCode == 200){
                                retrofitListener.invoke(response.code(), true, response.body()!!)
                            }else{
                                retrofitListener.invoke(response.code(), false, response.body()!!)
                            }
                        } catch (e: Exception) {

                            Log.d("TAG_RETROFIT_ERROR", e.toString())

                            retrofitListener.invoke(response.code(), false, response.body()!!)
                        }

                    } else {

                        try {
                            Log.d("TAG_REAL_ERROR", response.errorBody()!!.string())
                        } catch (e: Exception) {

                            Log.d("TAG_REAL_ERROR_EX", e.message!!)
                        }

                        try {

                            //Toaster.longToast(response.errorBody()!!.string())

                            val apiError = INDIMaster.gson.fromJson(response.errorBody()!!.string(), ModelAPIError::class.java)

                            if (apiError.error == "AUTH_ERROR") {

                                if (INDIPreferences.preferenceEditor().clear().commit()) {

                                    INDIMaster.applicationContext().startActivity(Intent(INDIMaster.applicationContext(), SplashActivity::class.java))
                                    INDIPreferences.session(false)
                                    INDIPreferences.backpress(false)
                                }

                            } else {

                                retrofitListener.invoke(response.code(), false, response.body()!!)
                            }
                        } catch (e: Exception) {

                            /// Log.d("TAG_EXCEPTION_ERROR", e.toString())

                            try{
                                retrofitListener.invoke(response.code(), false, response.body()!!)
                            }
                            catch (e : Exception){
                                e.printStackTrace()
                            }
                        }

                    }

                }

                override fun onFailure(call: Call<DiscountInfoResponse>, t: Throwable) {

                    //Log.d("TAG_RETROFIT_THROW", t.message)
                    try{

                        val discountInfoResponse = DiscountInfoResponse("0","0","0",404,"Please check your internet connection")
                        retrofitListener.invoke(404, false,discountInfoResponse )

                    }catch (e : Exception){
                        e.printStackTrace()
                    }
                }
            })

            return null
        }


        override fun onPostExecute(result: String?) {

        }

        override fun onPreExecute() {

        }

    }

    private class UpdateRetrofitAPI (private val retrofitListener: (Int, Boolean, String) -> Unit?, calls: Call<UpdateResponse>?) : AsyncTask<String, String, String>() {

        init {
            updateCall = calls
        }

        override fun doInBackground(vararg params: String?): String? {

            updateCall!!.clone().enqueue(object : Callback<UpdateResponse> {

                override fun onResponse(call: Call<UpdateResponse>?, response: Response<UpdateResponse>?) {

                    if (response!!.isSuccessful) {

                        try {

                            //Log.d("TAG_RETROFIT_RESULT", response.body()!!)

                            val updateResponse = response.body()!!
                            if (updateResponse.success){
                                retrofitListener.invoke(response.code(), true, updateResponse.error)
                            }else{
                                retrofitListener.invoke(response.code(), false, updateResponse.error)
                            }
                        } catch (e: Exception) {

                            Log.d("TAG_RETROFIT_ERROR", e.toString())

                            retrofitListener.invoke(response.code(), false, "Error while getting data")
                        }

                    } else {

                        try {
                            Log.d("TAG_REAL_ERROR", response.errorBody()!!.string())
                        } catch (e: Exception) {

                            Log.d("TAG_REAL_ERROR_EX", e.message!!)
                        }

                        try {

                            //Toaster.longToast(response.errorBody()!!.string())

                            val apiError = INDIMaster.gson.fromJson(response.errorBody()!!.string(), ModelAPIError::class.java)

                            if (apiError.error == "AUTH_ERROR") {

                                if (INDIPreferences.preferenceEditor().clear().commit()) {

                                    INDIMaster.applicationContext().startActivity(Intent(INDIMaster.applicationContext(), SplashActivity::class.java))
                                    INDIPreferences.session(false)
                                    INDIPreferences.backpress(false)
                                }

                            } else {

                                retrofitListener.invoke(response.code(),
                                    false, apiError.error!!)
                            }
                        } catch (e: Exception) {

                            /// Log.d("TAG_EXCEPTION_ERROR", e.toString())

                            try{
                                retrofitListener.invoke(response.code(),
                                    false, "Error while fetching data!")
                            }
                            catch (e : Exception){
                                e.printStackTrace()
                            }
                        }

                    }

                }

                override fun onFailure(call: Call<UpdateResponse>, t: Throwable) {

                    //Log.d("TAG_RETROFIT_THROW", t.message)
                    try{
                        retrofitListener.invoke(404, false, "Please check your internet connection")
                    }catch (e : Exception){
                        e.printStackTrace()
                    }
                    //Toaster.LongToast("Unable to connect to internet");
                }
            })

            return null
        }


        override fun onPostExecute(result: String?) {

        }

        override fun onPreExecute() {

        }

    }

    private class LoginRetrofitAPI (private val retrofitListener: (Boolean, LoginResponse) -> Unit?, calls: Call<LoginResponse>?) : AsyncTask<String, String, String>() {

        init {
            loginCall = calls
        }

        override fun doInBackground(vararg params: String?): String? {

            loginCall!!.clone().enqueue(object : Callback<LoginResponse> {

                override fun onResponse(call: Call<LoginResponse>?, response: Response<LoginResponse>?) {

                    if (response!!.isSuccessful) {

                        try {

                            //Log.d("TAG_RETROFIT_RESULT", response.body()!!)

                            val loginResponse = response.body()!!

                            if (loginResponse.success){
                                retrofitListener.invoke(true, loginResponse)
                            }else{
                                retrofitListener.invoke(false, loginResponse)
                            }
                        } catch (e: Exception) {

                            Log.d("TAG_RETROFIT_ERROR", e.toString())
                            retrofitListener.invoke( false, response.body()!!)
                        }

                    } else {

                        //Log.e("login",response.body()!!.toString())

                        try {
                            Log.d("TAG_REAL_ERROR", response.errorBody()!!.string())
                        } catch (e: Exception) {

                            Log.d("TAG_REAL_ERROR_EX", e.message!!)
                        }

                        try {

                            //Toaster.longToast(response.errorBody()!!.string())

                            val apiError = INDIMaster.gson.fromJson(response.errorBody()!!.string(), ModelAPIError::class.java)

                            if (apiError.error == "AUTH_ERROR") {

                                if (INDIPreferences.preferenceEditor().clear().commit()) {

                                    INDIMaster.applicationContext().startActivity(Intent(INDIMaster.applicationContext(), SplashActivity::class.java))
                                    INDIPreferences.session(false)
                                    INDIPreferences.backpress(false)
                                }

                            } else {

                                retrofitListener.invoke(false, response.body()!!)
                            }
                        } catch (e: Exception) {

                            /// Log.d("TAG_EXCEPTION_ERROR", e.toString())

                            try{
                                retrofitListener.invoke(false, response.body()!!)
                            }
                            catch (e : Exception){
                                e.printStackTrace()
                            }
                        }

                    }

                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {

                    //Log.d("TAG_RETROFIT_THROW", t.message)
                    try{

                        val loginResponse2 = LoginResponse(false, t.message,null)
                        retrofitListener.invoke(false, loginResponse2)

                    }catch (e : Exception){
                        e.printStackTrace()
                    }
                    //Toaster.LongToast("Unable to connect to internet");
                }
            })

            return null
        }


        override fun onPostExecute(result: String?) {

        }

        override fun onPreExecute() {

        }

    }

    private class OtpRetrofitAPI (private val retrofitListener: (Int, Boolean, UpdateResponse) -> Unit?, calls: Call<UpdateResponse>?) : AsyncTask<String, String, String>() {

        init {
            updateCall = calls
        }

        override fun doInBackground(vararg params: String?): String? {

            updateCall!!.clone().enqueue(object : Callback<UpdateResponse> {

                override fun onResponse(call: Call<UpdateResponse>?, response: Response<UpdateResponse>?) {

                    if (response!!.isSuccessful) {

                        try {

                            //Log.d("TAG_RETROFIT_RESULT", response.body()!!)

                            val loginResponse = response.body()!!

                            if (loginResponse.success){
                                retrofitListener.invoke(response.code(), true, loginResponse)
                            }else{
                                retrofitListener.invoke(response.code(), false, loginResponse)
                            }
                        } catch (e: Exception) {

                            Log.d("TAG_RETROFIT_ERROR", e.toString())
                            retrofitListener.invoke(response.code(), false, response.body()!!)
                        }

                    } else {

                        Log.e("login",response.body()!!.toString())

                        try {
                            Log.d("TAG_REAL_ERROR", response.errorBody()!!.string())
                        } catch (e: Exception) {

                            Log.d("TAG_REAL_ERROR_EX", e.message!!)
                        }

                        try {

                            //Toaster.longToast(response.errorBody()!!.string())

                            val apiError = INDIMaster.gson.fromJson(response.errorBody()!!.string(), ModelAPIError::class.java)

                            if (apiError.error == "AUTH_ERROR") {

                                if (INDIPreferences.preferenceEditor().clear().commit()) {

                                    INDIMaster.applicationContext().startActivity(Intent(INDIMaster.applicationContext(), SplashActivity::class.java))
                                    INDIPreferences.session(false)
                                    INDIPreferences.backpress(false)
                                }

                            } else {

                                retrofitListener.invoke(response.code(), false, response.body()!!)
                            }
                        } catch (e: Exception) {

                            /// Log.d("TAG_EXCEPTION_ERROR", e.toString())

                            try{
                                retrofitListener.invoke(response.code(), false, response.body()!!)
                            }
                            catch (e : Exception){
                                e.printStackTrace()
                            }
                        }

                    }

                }

                override fun onFailure(call: Call<UpdateResponse>, t: Throwable) {

                    //Log.d("TAG_RETROFIT_THROW", t.message)
                    try{
                        val updateResponse = UpdateResponse(false,"",t.message,null)
                        retrofitListener.invoke(404, false, updateResponse)

                    }catch (e : Exception){
                        e.printStackTrace()
                    }
                    //Toaster.LongToast("Unable to connect to internet");
                }
            })

            return null
        }


        override fun onPostExecute(result: String?) {

        }

        override fun onPreExecute() {

        }

    }

    private class VerifyAPI (private val retrofitListener: (Int, Boolean, UpdateResponse) -> Unit?, calls: Call<UpdateResponse>?) : AsyncTask<String, String, String>() {

        init {
            updateCall = calls
        }

        override fun doInBackground(vararg params: String?): String? {

            updateCall!!.clone().enqueue(object : Callback<UpdateResponse> {

                override fun onResponse(call: Call<UpdateResponse>?, response: Response<UpdateResponse>?) {

                    if (response!!.isSuccessful) {

                        try {

                            //Log.d("TAG_RETROFIT_RESULT", response.body()!!)

                            val loginResponse = response.body()!!

                            if (loginResponse.success){
                                retrofitListener.invoke(response.code(), true, loginResponse)
                            }else{
                                retrofitListener.invoke(response.code(), false, loginResponse)
                            }
                        } catch (e: Exception) {

                            Log.d("TAG_RETROFIT_ERROR", e.toString())
                            retrofitListener.invoke(response.code(), false, response.body()!!)
                        }

                    } else {

                        Log.e("login",response.body()!!.toString())

                        try {
                            Log.d("TAG_REAL_ERROR", response.errorBody()!!.string())
                        } catch (e: Exception) {

                            Log.d("TAG_REAL_ERROR_EX", e.message!!)
                        }

                        try {

                            //Toaster.longToast(response.errorBody()!!.string())

                            val apiError = INDIMaster.gson.fromJson(response.errorBody()!!.string(), ModelAPIError::class.java)

                            if (apiError.error == "AUTH_ERROR") {

                                if (INDIPreferences.preferenceEditor().clear().commit()) {

                                    INDIMaster.applicationContext().startActivity(Intent(INDIMaster.applicationContext(), SplashActivity::class.java))
                                    INDIPreferences.session(false)
                                    INDIPreferences.backpress(false)
                                }

                            } else {

                                retrofitListener.invoke(response.code(), false, response.body()!!)
                            }
                        } catch (e: Exception) {

                            /// Log.d("TAG_EXCEPTION_ERROR", e.toString())

                            try{
                                retrofitListener.invoke(response.code(), false, response.body()!!)
                            }
                            catch (e : Exception){
                                e.printStackTrace()
                            }
                        }

                    }

                }

                override fun onFailure(call: Call<UpdateResponse>, t: Throwable) {

                    //Log.d("TAG_RETROFIT_THROW", t.message)
                    try{
                        val updateResponse = UpdateResponse(false,"",t.message,null)
                        retrofitListener.invoke(404, false, updateResponse)

                    }catch (e : Exception){
                        e.printStackTrace()
                    }
                    //Toaster.LongToast("Unable to connect to internet");
                }
            })

            return null
        }


        override fun onPostExecute(result: String?) {

        }

        override fun onPreExecute() {

        }

    }

    private class CategoryRetrofitAPI (private val retrofitListener: (Int, Boolean, CategoryResponse) -> Unit?, calls: Call<CategoryResponse>?) : AsyncTask<String, String, String>() {

        init {
            categoryCall = calls
        }

        override fun doInBackground(vararg params: String?): String? {

            categoryCall!!.clone().enqueue(object : Callback<CategoryResponse> {

                override fun onResponse(call: Call<CategoryResponse>?, response: Response<CategoryResponse>?) {

                    if (response!!.isSuccessful) {

                        try {

                            //Log.d("TAG_RETROFIT_RESULT", response.body()!!)

                            val categoryResponse = response.body()!!
                            if (categoryResponse.success){
                                retrofitListener.invoke(response.code(), true,categoryResponse)
                            }else{
                                retrofitListener.invoke(response.code(), false, categoryResponse)
                            }
                        } catch (e: Exception) {

                            Log.d("TAG_RETROFIT_ERROR", e.toString())

                            retrofitListener.invoke(response.code(), false, response.body()!!)
                        }

                    } else {

                        try {
                            Log.d("TAG_REAL_ERROR", response.errorBody()!!.string())
                        } catch (e: Exception) {

                            Log.d("TAG_REAL_ERROR_EX", e.message!!)
                        }

                        try {

                            //Toaster.longToast(response.errorBody()!!.string())

                            val apiError = INDIMaster.gson.fromJson(response.errorBody()!!.string(), ModelAPIError::class.java)

                            if (apiError.error == "AUTH_ERROR") {

                                if (INDIPreferences.preferenceEditor().clear().commit()) {

                                    INDIMaster.applicationContext().startActivity(Intent(INDIMaster.applicationContext(), SplashActivity::class.java))
                                    INDIPreferences.session(false)
                                    INDIPreferences.backpress(false)
                                }

                            } else {

                                retrofitListener.invoke(response.code(), false,
                                    CategoryResponse(false,apiError.error,null)
                                )
                            }
                        } catch (e: Exception) {

                            /// Log.d("TAG_EXCEPTION_ERROR", e.toString())

                            try{
                                retrofitListener.invoke(response.code(), false, CategoryResponse(false,e.message,null))
                            }
                            catch (e : Exception){
                                e.printStackTrace()
                            }
                        }

                    }

                }

                override fun onFailure(call: Call<CategoryResponse>, t: Throwable) {

                    //Log.d("TAG_RETROFIT_THROW", t.message)
                    try{
                        retrofitListener.invoke(404, false, CategoryResponse(false,"Please check your internet connection",null))
                    }catch (e : Exception){
                        e.printStackTrace()
                    }
                    //Toaster.LongToast("Unable to connect to internet");
                }
            })

            return null
        }


        override fun onPostExecute(result: String?) {

        }

        override fun onPreExecute() {

        }

    }


    companion object {

        private var retrofit: Retrofit? = null
        private var client: OkHttpClient? = null
        private var call : Call<String>? = null
        private var discountCall : Call<ShopResponse>? = null
        private var updateCall : Call<UpdateResponse>? = null
        private var discountInfoCall : Call<DiscountInfoResponse>? = null
        private var registerCall : Call<RegisterResponse>? = null
        private var loginCall : Call<LoginResponse>? = null
        private var categoryCall: Call<CategoryResponse>? = null

        fun instance(): Retrofit {

            client = OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.MINUTES)
                    .readTimeout(10, TimeUnit.MINUTES)
                    .writeTimeout(10, TimeUnit.MINUTES)
                    .addInterceptor { chain ->
                        val newRequest = chain.request().newBuilder()
                                .build()
                        chain.proceed(newRequest)
                    }.build()


            retrofit = Retrofit.Builder()
                    .client(client!!)
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit as Retrofit
        }

        fun newInstance(): Retrofit {

            client = OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.MINUTES)
                .readTimeout(10, TimeUnit.MINUTES)
                .writeTimeout(10, TimeUnit.MINUTES)
                .addInterceptor { chain ->
                    val newRequest = chain.request().newBuilder()
                        .build()
                    chain.proceed(newRequest)
                }.build()


            retrofit = Retrofit.Builder()
                .client(client!!)
                .baseUrl(Constants.BASE_URL2)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit as Retrofit
        }

        fun session(): Retrofit {

            client = OkHttpClient.Builder()
                    .connectTimeout(5, TimeUnit.MINUTES)
                    .readTimeout(5, TimeUnit.MINUTES)
                    .writeTimeout(5, TimeUnit.MINUTES)
                    .retryOnConnectionFailure(true)
                    .addInterceptor { chain ->
                        val newRequest = chain.request().newBuilder()
                                .addHeader("AUTH-TOKEN", INDIPreferences.token()!!)
                                .build()
                        chain.proceed(newRequest)
                    }.build()


            retrofit = Retrofit.Builder()
                    .client(client!!)
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit as Retrofit
        }

        fun getRetrofit(call: Call<String>?, retrofitListener: (Int, Boolean, String) -> Unit?) {
            RetrofitAPI(retrofitListener, call).execute()
        }

        fun getLoginRetrofit(call: Call<LoginResponse>?, retrofitListener: ( Boolean, LoginResponse) -> Unit?) {
            LoginRetrofitAPI(retrofitListener, call).execute()
        }

        fun getDiscountRetrofit(call: Call<ShopResponse>?, retrofitListener: (Int, Boolean, ShopResponse) -> Unit?) {
            DiscountRetrofitAPI(retrofitListener, call).execute()
        }

        fun getRegisterRetrofit(call: Call<RegisterResponse>?, retrofitListener: (Int, Boolean, RegisterResponse) -> Unit?) {
            RegisterRetrofitAPI(retrofitListener, call).execute()
        }

        fun getOTPRetrofit(call: Call<UpdateResponse>?, retrofitListener: (Int, Boolean, UpdateResponse) -> Unit?) {
            OtpRetrofitAPI(retrofitListener, call).execute()
        }

        fun getDiscountInfoRetrofit(call: Call<DiscountInfoResponse>?, retrofitListener: (Int, Boolean, DiscountInfoResponse) -> Unit?) {
            DiscountInfoRetrofitAPI(retrofitListener, call).execute()
        }

        fun updateRetrofit(call: Call<UpdateResponse>?, retrofitListener: (Int, Boolean, String) -> Unit?) {
            UpdateRetrofitAPI(retrofitListener, call).execute()
        }

        fun verifyRetrofit(call: Call<UpdateResponse>?, retrofitListener: (Int, Boolean, UpdateResponse) -> Unit?) {
            VerifyAPI(retrofitListener, call).execute()
        }

        fun getCategoryRetrofit(call: Call<CategoryResponse>?, retrofitListener: (Int, Boolean, CategoryResponse) -> Unit?) {
            CategoryRetrofitAPI(retrofitListener, call).execute()
        }

    }
}
