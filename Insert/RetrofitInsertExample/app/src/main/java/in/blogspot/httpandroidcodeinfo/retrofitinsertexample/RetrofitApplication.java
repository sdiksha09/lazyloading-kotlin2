package in.blogspot.httpandroidcodeinfo.retrofitinsertexample;

import android.app.Application;
import android.content.Context;
import android.util.Log;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;


/**

 */
public class RetrofitApplication extends Application{
    private static Context sStaticContext;
    private RegisterAPI taskServiceAPI;
    /**
     * Gets a reference to the application context
     */
    public static Context getStaticContext() {
        if (sStaticContext != null) {
            return sStaticContext;
        }
        //Should NEVER hapen
        throw new RuntimeException("No static context instance");
    }


    @Override
    public void onCreate() {
        super.onCreate();
        sStaticContext = getApplicationContext();
        initLoginApi();
    }

    private void initLoginApi() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(5, TimeUnit.MINUTES);
        client.setReadTimeout(5, TimeUnit.MINUTES);


        final Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(new ToStringConverterFactory())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("http://192.168.43.6:80")
                .client(client)
                .build();
        taskServiceAPI=retrofit.create(RegisterAPI.class);
        Log.i("Application ","call");
    }

    public RegisterAPI getMshoppinpApis() {
        return taskServiceAPI;
    }


}
