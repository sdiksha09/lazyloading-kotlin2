package com.example.shipra.kotlinretrofitapp

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface retrofitApi {

    @FormUrlEncoded
    @POST("register.php")
    abstract fun registerNewUser(@Field("phone") phone: String,
                                 @Field("name") name: String,
                                 @Field("email") email: String): Call<User>


}