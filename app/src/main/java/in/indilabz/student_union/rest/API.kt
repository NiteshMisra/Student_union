package `in`.indilabz.student_union.rest

import `in`.indilabz.student_union.response.*
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by mjdem on 06-05-2018.
 */

interface API {

    @FormUrlEncoded
    @POST("student")
    fun register(
        @Field("phone") contact_number: String,
        @Field("full_name") full_name: String,
        @Field("course") course: String,
        @Field("email") email: String,
        @Field("course_year") year: String,
        @Field("father_name") father_name: String,
        @Field("current_address") current_address: String,
        @Field("permanent_address") paramanent_address: String,
        @Field("dob") dob: String,
        @Field("college") college: String,
        @Field("password") password: String,
        @Field("gender") gender : String,
        @Field("job") job : String,
        @Field("accommodation") accommodation : String
    ): Call<RegisterResponse>


    @PATCH("student/{id}")
    fun verify(
        @Path("id") id : String
    ): Call<UpdateResponse>

    @GET("category/")
    fun getCategory(): Call<CategoryResponse>

    @FormUrlEncoded
    @POST("auth/student")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    @FormUrlEncoded
    @POST("otp/")
    fun sendOtp(
        @Field("phone") contact_number: String
    ): Call<UpdateResponse>

    @GET("discounts/{id}/{page}/{category}")
    fun discount(
        @Path("id") id : String,
        @Path("page") page : Int,
        @Path("category") category: String
    ): Call<ShopResponse>

    @FormUrlEncoded
    @POST("student/update/{id}")
    fun updateName(
        @Path("id") studentId : String,
        @Field("full_name") name : String
    ): Call<UpdateResponse>

    @FormUrlEncoded
    @POST("student/update/{id}")
    fun updatePhone(
        @Path("id") studentId : String,
        @Field("phone") phone: String
    ): Call<UpdateResponse>

    @FormUrlEncoded
    @POST("student/update/{id}")
    fun updateDob(
        @Path("id") studentId : String,
        @Field("dob") dob: String
    ): Call<UpdateResponse>

    @FormUrlEncoded
    @POST("student/update/{id}")
    fun updateCourse(
        @Path("id") studentId : String,
        @Field("course") course : String
    ): Call<UpdateResponse>

    @FormUrlEncoded
    @POST("student/update/{id}")
    fun updateCourseYear(
        @Path("id") studentId : String,
        @Field("course_year") year : String
    ): Call<UpdateResponse>

    @FormUrlEncoded
    @POST("student/update/{id}")
    fun updateCollege(
        @Path("id") studentId : String,
        @Field("college") college : String
    ): Call<UpdateResponse>

    @FormUrlEncoded
    @POST("student/update/{id}")
    fun updateFatherName(
        @Path("id") studentId : String,
        @Field("father_name") fatherName : String
    ): Call<UpdateResponse>

    @FormUrlEncoded
    @POST("student/update/{id}")
    fun updateCurrentAddress(
        @Path("id") studentId : String,
        @Field("current_address") address : String
    ): Call<UpdateResponse>

    @FormUrlEncoded
    @POST("student/update/{id}")
    fun updateGender(
        @Path("id") studentId : String,
        @Field("gender") gender : String
    ): Call<UpdateResponse>

    @FormUrlEncoded
    @POST("student/update/{id}")
    fun updateJob(
        @Path("id") studentId : String,
        @Field("job") job : String
    ): Call<UpdateResponse>

    @FormUrlEncoded
    @POST("student/forgot")
    fun forgotPassword(
        @Field("phone") phone: String,
        @Field("password") password : String
    ): Call<UpdateResponse>

    @FormUrlEncoded
    @POST("student/update/{id}")
    fun updateAccommodation(
        @Path("id") studentId : String,
        @Field("accommodation") accommodation: String
    ): Call<UpdateResponse>

    @GET("api/api/studentdiscountcheck/{id}")
    fun discountInfo(
        @Path("id") id: String
    ): Call<DiscountInfoResponse>
}
