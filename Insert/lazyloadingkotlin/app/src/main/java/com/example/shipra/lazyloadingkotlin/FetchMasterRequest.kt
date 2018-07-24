package com.example.shipra.lazyloadingkotlin

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import java.util.HashMap


open class FetchMasterRequest : RetroRequestBuilder() {

    fun getService(): FetchMasterRequest.FetchNetworkService {
        return super.build().create(FetchMasterRequest.FetchNetworkService::class.java)
    }

    interface FetchNetworkService {

        @Headers("token:1234567890")
        @POST("/api/vehicle-details")
        fun vehicleMaster(@Body body: HashMap<String, String>): Call<CarMasterResponse>

    }
}