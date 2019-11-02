package com.pieroashady.biodataapps.ApiService;

import com.pieroashady.biodataapps.Model.AllData.Status;
import com.pieroashady.biodataapps.Model.ById.StatusId;
import com.pieroashady.biodataapps.Model.DeleteData.StatusDelete;
import com.pieroashady.biodataapps.Model.PostData.StatusPost;
import com.pieroashady.biodataapps.Model.PutData.StatusUpdate;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface APIInterfaceRest {


    @GET("biodata")
    Call<Status> getData();

    @GET("biodata/{id}")
    Call<StatusId> getDataById(@Path("id") int id);

    @FormUrlEncoded
    @POST("biodata")
    Call<StatusPost> saveData(@Field("nim") String nim,
                              @Field("nama") String nama,
                              @Field("alamat") String alamat);

    @FormUrlEncoded
    @PUT("biodata/{id}")
    Call<StatusUpdate> updateData(@Field("nim") String nim,
                                  @Field("nama") String nama,
                                  @Field("alamat") String alamat);

    @DELETE("biodata/{id}")
    Call<StatusDelete> deleteData(@Path("id") int id);

}
