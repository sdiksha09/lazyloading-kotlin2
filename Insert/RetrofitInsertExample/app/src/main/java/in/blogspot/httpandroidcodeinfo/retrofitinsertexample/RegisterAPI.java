package in.blogspot.httpandroidcodeinfo.retrofitinsertexample;


import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**

 */
public interface RegisterAPI {

@FormUrlEncoded
@POST("/RetrofitExample/insert.php")
Call<Pojodemo> serverCall(

        @Field("name") String Name,
        @Field("email") String Email,
        @Field("address") String Address,
        @Field("mobile") String Mobile );

}
