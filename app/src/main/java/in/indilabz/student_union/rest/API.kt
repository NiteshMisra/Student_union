package `in`.indilabz.student_union.rest

import `in`.indilabz.student_union.response.*
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by mjdem on 06-05-2018.
 */

interface API {

    @FormUrlEncoded
    @POST("student/")
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
        @Field("password") password: String
    ): Call<RegisterResponse>

    @FormUrlEncoded
    @POST("auth/student/")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<RegisterResponse>

    @GET("api/api/discount/{student_id}")
    fun discount(
        @Path("student_id") contact_number: String
    ): Call<DiscountResponse>

    @GET("api/api/student/{student_id}")
    fun profile(
        @Path("student_id") contact_number: String
    ): Call<String>

    @FormUrlEncoded
    @PUT("student/{student_id}")
    fun updateName(
        @Path("student_id") studentId : String,
        @Field("full_name") id: String
    ): Call<UpdateResponse>

    @GET("api/api/studentdiscountcheck/{id}")
    fun discountInfo(
        @Path("id") id: String
    ): Call<DiscountInfoResponse>
}
