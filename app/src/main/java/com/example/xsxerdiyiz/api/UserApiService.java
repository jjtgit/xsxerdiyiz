package com.example.xsxerdiyiz.api;

import com.example.xsxerdiyiz.entity.RequestParams;

import java.util.HashMap;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface UserApiService {
    @POST
    @FormUrlEncoded
    Call<ResponseBody> postParams(@Url String url, @FieldMap HashMap<String, String> params);
    @GET
    Call<ResponseBody> getParams(@Url String url);
    @PUT
    Call<ResponseBody> updateNickname(@Header("userId")String id,@Header("sessionId")String sid,@Header("nickName")String name);
}
