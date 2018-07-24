package com.example.shipra.bookstore_kotlin

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Base64
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.facebook.accountkit.*
import com.facebook.accountkit.ui.AccountKitActivity
import com.facebook.accountkit.ui.AccountKitConfiguration
import com.facebook.accountkit.ui.LoginType
import com.rengwuxian.materialedittext.MaterialEditText
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

import com.example.shipra.bookstore_kotlin.model.User
import com.example.shipra.bookstore_kotlin.model.CheckUserResponse
import com.example.shipra.bookstore_kotlin.utils.Common
import com.example.shipra.bookstore_kotlin.Retrofit.bookStoreAPI


class MainActivity : AppCompatActivity() {


    private val REQUEST_CODE = 1000
    internal lateinit var button_continue: Button

    internal lateinit var mService: bookStoreAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //PrintKeyHash();
        mService = Common.api

        button_continue = findViewById(R.id.btn_continue) as Button
        button_continue.setOnClickListener { startLoginPage(LoginType.PHONE) }

    }

    private fun startLoginPage(loginType: LoginType) {
        val intent = Intent(this, AccountKitActivity::class.java)
        val builder = AccountKitConfiguration.AccountKitConfigurationBuilder(loginType, AccountKitActivity.ResponseType.TOKEN)

        intent.putExtra(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION, builder.build())

        startActivityForResult(intent, REQUEST_CODE)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            //  AccountKitLoginResult result = data.getParcelableExtra(AccountKitLoginResult.RESULT_KEY);

            val result = data.getParcelableExtra<AccountKitLoginResult>(AccountKitLoginResult.RESULT_KEY)

            if (result.error != null) {

                Toast.makeText(this, "" + result.error!!.errorType.message, Toast.LENGTH_SHORT).show()

            } else if (result.wasCancelled()) {
                Toast.makeText(this, "cancel", Toast.LENGTH_SHORT).show()
            } else {
                if (result.accessToken != null) {

                    //AlertDialog alertDialog= new AlertDialog(MainActivity.this);
                    // final android.app.AlertDialog alertDialog =new SpotsDialog(MainActivity.this);
                    val alertDialog = SpotsDialog(this@MainActivity)
                    alertDialog.show()
                    alertDialog.setMessage("Please Wait")


                    //get user phone no. and check user exist on server or not

                    AccountKit.getCurrentAccount(object : AccountKitCallback<Account> {


                        override fun onSuccess(account: Account) {


                            mService.checkUserExists(account.phoneNumber.toString())

                                    .enqueue(object : Callback<CheckUserResponse> {

                                        override fun onResponse(call: Call<CheckUserResponse>, response: Response<CheckUserResponse>) {


                                            val userResponse = response.body()

                                            if (userResponse!!.isExists) {
                                                // if user already exist just start new activity
                                                alertDialog.dismiss()


                                            } else {
                                                // else need to register new account

                                                alertDialog.dismiss()
                                                showRegisterDialog(account.phoneNumber.toString())
                                            }
                                        }

                                        override fun onFailure(call: Call<CheckUserResponse>, t: Throwable) {

                                            Log.i("Hello", "" + t)
                                            Toast.makeText(this@MainActivity, "Throwable  error   $t", Toast.LENGTH_LONG).show()
                                            alertDialog.dismiss()

                                        }
                                    })
                        }

                        override fun onError(accountKitError: AccountKitError) {

                            Log.d("ERROR", accountKitError.errorType.message)

                        }
                    })

                }
            }
        }
    }

    private fun showRegisterDialog(phone: String) {


        val alertDialog = AlertDialog.Builder(this@MainActivity)
        alertDialog.setTitle("REGISTER")
        val inflater = this.layoutInflater

        val register_layout = inflater.inflate(R.layout.register_layout, null)

        val edt_name = findViewById(R.id.edt_name) as MaterialEditText
        val edit_email = findViewById(R.id.edt_email) as MaterialEditText

        val btn_Register = findViewById(R.id.btn_register) as Button

        btn_Register.setOnClickListener(View.OnClickListener {
            alertDialog.create().dismiss()
            if (TextUtils.isEmpty(edt_name.text.toString())) {
                Toast.makeText(this@MainActivity, "Please enter your name", Toast.LENGTH_SHORT)
                return@OnClickListener
            }
            if (TextUtils.isEmpty(edit_email.text.toString())) {
                Toast.makeText(this@MainActivity, "Please enter your email", Toast.LENGTH_SHORT)
                return@OnClickListener
            }

            val waitingDialog = SpotsDialog(this@MainActivity)
            waitingDialog.show()
            waitingDialog.setMessage("Please Waiting........")

            mService.registerNewUser(phone,
                    edt_name.text.toString(),
                    edit_email.text.toString())
                    .enqueue(object : Callback<User> {
                        override fun onResponse(call: Call<User>, response: Response<User>) {

                            waitingDialog.dismiss()
                            val user = response.body()

                            if (TextUtils.isEmpty(user!!.error_msg)) {

                                Toast.makeText(this@MainActivity, "user register successfully", Toast.LENGTH_SHORT)
                                //start new activity


                            }
                        }

                        override fun onFailure(call: Call<User>, t: Throwable) {

                            waitingDialog.dismiss()

                        }
                    })

            alertDialog.setView(register_layout)
            alertDialog.show()
        })


    }

    private fun PrintKeyHash() {
        try {
            val info = packageManager.getPackageInfo("com.example.shipra.mobileapplication",
                    PackageManager.GET_SIGNATURES)
            for (signature in info.signatures) {

                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                Log.d("KEYHASH", Base64.encodeToString(md.digest(), Base64.DEFAULT))

            }

        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }

    }







  /*  override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    } */
}
