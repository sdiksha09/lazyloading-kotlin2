package com.example.shipra.bookstore_kotlin.Retrofit

import android.telecom.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import com.example.shipra.bookstore_kotlin.model.User
import com.example.shipra.bookstore_kotlin.model.CheckUserResponse

interface bookStoreAPI {

    @FormUrlEncoded
    @POST("checkuser.php")
    abstract fun checkUserExists(@Field("phone") phone: String): retrofit2.Call<CheckUserResponse>


    @FormUrlEncoded
    @POST("register.php")
    abstract fun registerNewUser(@Field("phone") phone: String,
                                 @Field("name") name: String,
                                 @Field("email") email: String):retrofit2.Call<User>
}



