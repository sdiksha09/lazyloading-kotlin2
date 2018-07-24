package com.example.shipra.bookstore_kotlin.utils
import com.example.shipra.bookstore_kotlin.Retrofit.bookStoreAPI
import com.example.shipra.bookstore_kotlin.Retrofit.RetrofitClient


object Common {

    private val BASE_URL = "http://10.0.2.2/mobileapp/"

    val api: bookStoreAPI

        get()= RetrofitClient.getClient(BASE_URL)!!.create(bookStoreAPI::class.java)


}