package `in`.indilabz.student_union.rest

import `in`.indilabz.student_union.response.*
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by mjdem on 06-05-2018.
 */

interface API {

    @FormUrlEncoded
    @POST("api/api/student")
    fun register(
        @Field("contact_number") contact_number: String,
        @Field("full-name") full_name: String,
        @Field("gender") gender: String,
        @Field("course") course: String,
        @Field("email") email: String,
        @Field("year") year: String,
        @Field("father_name") father_name: String,
        @Field("current_address") current_address: String,
        @Field("paramanent_address") paramanent_address: String,
        @Field("dob") dob: String,
        @Field("college") college: String,
        @Field("password") password: String
    ): Call<RegisterResponse>

    @FormUrlEncoded
    @POST("admin")
    fun login(
        @Field("username") contact_number: String,
        @Field("password") password: String,
        @Field("from") from: String,
        @Field("user_type") user_type: String
    ): Call<LoginResponse>

    @GET("api/api/discount/{student_id}")
    fun discount(
        @Path("student_id") contact_number: String
    ): Call<DiscountResponse>

    @GET("api/api/student/{student_id}")
    fun profile(
        @Path("student_id") contact_number: String
    ): Call<String>

    @FormUrlEncoded
    @POST("api/api/studentupdate")
    fun update(
        @Field("id") id: String,
        @Field("name") fullName: String,
        @Field("contact_number") phone: String,
        @Field("course") course: String,
        @Field("current_address") address: String,
        @Field("gender") gender: String,
        @Field("father_name") fatherName: String
    ): Call<UpdateResponse>

    @GET("api/api/studentdiscountcheck/{id}")
    fun discountInfo(
        @Path("id") id: String
    ): Call<DiscountInfoResponse>
}
