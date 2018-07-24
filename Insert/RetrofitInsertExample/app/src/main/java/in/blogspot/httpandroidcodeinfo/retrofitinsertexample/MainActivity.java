package in.blogspot.httpandroidcodeinfo.retrofitinsertexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText mName,mEmailID,mAddress,mMobile;
    private Button mSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();
    }
           public void init()
             {
                 mName=(EditText)findViewById(R.id.input_name);
                 mEmailID=(EditText)findViewById(R.id.input_email);
                 mAddress=(EditText)findViewById(R.id.input_address);
                 mMobile=(EditText)findViewById(R.id.input_mobile);
                 mSubmit=(Button)findViewById(R.id.input_submit);
                 mSubmit.setOnClickListener(this);
             }

    @Override
    public void onClick(View v) {
     if(v.getId() == R.id.input_submit)
     {
         String s1,s2,s3,s4;
         s1=mName.getText().toString();
         s2=mEmailID.getText().toString();
         s3=mAddress.getText().toString();
         s4=mMobile.getText().toString();
         if(!s1.equalsIgnoreCase("") && !s2.equalsIgnoreCase("") && !s3.equalsIgnoreCase("") && !s4.equalsIgnoreCase(""))
         {
          // Call Retrofit

             insertData(s1,s2,s3,s4);
         }
         else
         {
             Toast.makeText(MainActivity.this,"Enter All Feild",Toast.LENGTH_LONG).show();
         }
     }
    }

     public void insertData(String name,String email,String address,String mobile)
     {
         // call InterFace
         RegisterAPI registerAPI=((RetrofitApplication) getApplication()).getMshoppinpApis();

         // call Inter face method
         Call<Pojodemo> call=registerAPI.serverCall(name,email,address,mobile);
         call.enqueue(new Callback<Pojodemo>() {
             @Override
             public void onResponse(Response<Pojodemo> response, Retrofit retrofit) {
                 // postive responce

                 // get pojo value using  getSuccess and get Body(Body is all responce)
             if(response.body().getSuccess())
             {
                 mAddress.setText("");
                 mMobile.setText("");
                 mEmailID.setText("");
                 mName.setText("");
                 Toast.makeText(MainActivity.this,"Value saved",Toast.LENGTH_LONG).show();
             }
                 else
             {
                 Toast.makeText(MainActivity.this,response.body().getError()+"",Toast.LENGTH_LONG).show();
             }
             }
              // Error Or Any Failure
             @Override
             public void onFailure(Throwable t) {
               Toast.makeText(MainActivity.this,t.getMessage()+"",Toast.LENGTH_LONG).show();
             }
         });
     }

}
