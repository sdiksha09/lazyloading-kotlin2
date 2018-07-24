package com.example.shipra.kotlinretrofitapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class register : AppCompatActivity() {

    internal lateinit var button_register: Button
    internal lateinit var phone: EditText
    internal lateinit var name: EditText
    internal lateinit var email: EditText
    internal var mService: retrofitApi? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        phone = findViewById(R.id.edt_phone) as EditText
        name = findViewById(R.id.edt_name) as EditText
        email = findViewById(R.id.edt_email) as EditText
        button_register = findViewById(R.id.btn_register) as Button

        button_register.setOnClickListener {
            /*   OkHttpClient client = new OkHttpClient.Builder()
        .connectTimeout(100, TimeUnit.SECONDS)
        .readTimeout(100,TimeUnit.SECONDS).build();
*/


            val client = OkHttpClient()

            val gson = GsonBuilder()

                    .setLenient()
                    .create()

            val retrofit = Retrofit.Builder()
                    .baseUrl("http://10.0.6.33/mobileapp/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()

            val service = retrofit.create<retrofitApi>(retrofitApi::class.java!!)
            val user = User()
            user.setPhone(phone.text.toString())
            user.setName(name.text.toString())
            user.setEmail(email.text.toString())
            val call = service.registerNewUser(user.getPhone(), user.getName(), user.getEmail())



            call.enqueue(object : Callback<User> {


                override fun onResponse(call: Call<User>, response: Response<User>) {

                    Toast.makeText(this@register, "response$response", Toast.LENGTH_LONG).show()

                    phone.setText("")
                    name.setText("")
                    email.setText("")

                }

                override fun onFailure(call: Call<User>, t: Throwable) {


                    Toast.makeText(this@register, "Throwable  error   $t", Toast.LENGTH_LONG).show()

                }
            })
        }


    }
}
