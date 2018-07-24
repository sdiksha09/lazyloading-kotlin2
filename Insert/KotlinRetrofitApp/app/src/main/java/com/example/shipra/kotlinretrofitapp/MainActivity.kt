package com.example.shipra.kotlinretrofitapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    internal lateinit var button_continue: Button
    internal var mService: retrofitApi? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val phone: String? = null



        button_continue = findViewById<View>(R.id.btn_continue) as Button

        button_continue.setOnClickListener {
            // Start NewActivity.class
            val myIntent = Intent(this@MainActivity,
                    register::class.java)
            startActivity(myIntent)
        }


    }

}
