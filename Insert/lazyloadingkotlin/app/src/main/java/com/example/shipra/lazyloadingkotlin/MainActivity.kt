package com.example.shipra.lazyloadingkotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListener()
    }

    private fun setListener() {
        btnRecycler.setOnClickListener(this)

        btnRetrofit.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {




            R.id.btnRetrofit -> {
                Toast.makeText(this, "Retrofit click", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, RetrofitSampleActivity::class.java)
                startActivity(intent)
            }
        }
    }
}