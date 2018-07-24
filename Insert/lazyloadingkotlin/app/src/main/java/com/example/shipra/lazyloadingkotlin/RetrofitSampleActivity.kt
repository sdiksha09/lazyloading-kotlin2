package com.example.shipra.lazyloadingkotlin

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_retrofit_sample.*
import com.example.shipra.lazyloadingkotlin.CarMasterController.callback
import kotlinx.android.synthetic.main.vehicle_data_layout.*
import kotlinx.android.synthetic.main.vehicle_data_layout.view.*


abstract class RetrofitSampleActivity : AppCompatActivity(), callback {

    lateinit var vehicle_list:RecyclerView

    // Step:1  creating a array list
    var vehicles:MutableList<String> = ArrayList()





    // Step:1  creating a array list
    var vehicles:MutableList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit_sample)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // this means the item will be one below other

        rvRetrofit.layoutManager = LinearLayoutManager(this)
        rvRetrofit.adapter = vehicleAdapter(vehicles.this)

        CarMasterController(this, this).serviceCall()


        loadData()                           //load the data







    }




    class vehicleAdapter(item:List<String>,ctx:Context):RecyclerView.Adapter<vehicleAdapter.ViewHolder>()//   creating a constructor inside the Adapter class as parameter
    {

        var List=item
        var context=ctx

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            //set the view holder
            return  ViewHolder(LayoutInflater.from(context).inflate(R.layout.vehicle_data_layout,parent,false))


        }

        override fun getItemCount(): Int {

            // this has to return ItemCount

            return  List.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            // to bind this view need to set the text

            holder?.Make_ID?.text=List.get(position)       //puting a ?mark after holder and name to check it is null or not
            holder?.Make_Name?.text=List.get(position)
            holder?.Model_ID?.text=List.get(position)
            holder?.Model_Name?.text=List.get(position)
            holder?.Variant_ID?.text=List.get(position)
            holder?.Variant_Name?.text=List.get(position)
            holder?.Model_ID1?.text=List.get(position)
            holder?.Cubic_Capacity.text=List.get(position)
            holder?.CreatedOn?.text=List.get(position)
            holder?.Fuel_ID?.text=List.get(position)
            holder?.Seating_Capacity?.text=List.get(position)
            holder?.ExShoroomPrice?.text=List.get(position)
            holder?.IsActive?.text=List.get(position)
            holder?.Make_ID?.text=List.get(position)
            holder?.Make_ID?.text=List.get(position)
            holder?.Make_ID?.text=List.get(position)
            holder?.Make_ID?.text=List.get(position)
            holder?.Make_ID?.text=List.get(position)
            holder?.Make_ID?.text=List.get(position)
            holder?.Make_ID?.text=List.get(position)





        }
        //Creating a view Holder inside this class

        //step: 4 creating a constructon with view as a parameter and extand the parent class RecyclerView
        class ViewHolder(v:View) :RecyclerView.ViewHolder(v){

            val  Make_ID= v.txt_Make_ID
            val  Make_Name=v.txt_Make_Name
            val  Model_ID=v.txt_Model_ID
            val  Model_Name=v.txt_Model_Name
            val  Variant_ID =v.txt_Variant_ID
            val  Variant_Name=v.txt_Variant_Name
            val  Model_ID1=v.txt_Model_ID1
            val  Cubic_Capacity=v.txt_Cubic_Capacity
            val  Fuel_ID=v.txt_Fuel_ID
            val  Seating_Capacity=v.txt_Seating_Capacity
            val  ExShoroomPrice=v.txt_ExShoroomPrice
            val  IsActive =v.txt_IsActive
            val  CreatedOn=v.txt_CreatedOn
            val  ModifyOn=v.txt_ModifyOn
            val  Product_Id_New=v.txt_Product_Id_New
            val  Make_Name1 =v.txt_Make_Name1
            val  Model_Name1=v.txt_Model_Name1
            val  Fuel_Name=v.txt_Fuel_Name
            val  Description=v.txt_Description
            val  Make_ID1=v.txt_Make_ID1

            //inside this creating a variable




        }
    }



    /*override fun callbackResponse(response: CarMasterResponse) {
       data_holder.text=response.MasterData.toString()



        Toast.makeText(this, ""+ data_holder , Toast.LENGTH_LONG).show()




    }
    */




}
