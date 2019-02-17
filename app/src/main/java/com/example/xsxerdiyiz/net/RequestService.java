package com.example.xsxerdiyiz.net;

import com.example.xsxerdiyiz.api.Api;
import com.example.xsxerdiyiz.api.UserApiService;
import com.example.xsxerdiyiz.entity.RequestParams;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Url;

public class RequestService {
    private static RequestService requestService;
    private Retrofit retrofit;
    private RequestService(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();
        retrofit=new Retrofit.Builder()
                .baseUrl(Api.BASE_USER)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }
    public static RequestService getRequestService(){
        if (requestService==null){
            synchronized (RequestService.class){
                if (requestService==null){
                    requestService=new RequestService();
                }
            }
        }
        return requestService;
    }
    public void doPost(String url, HashMap<String,String>params, final RequestCallback callback){
        UserApiService service = retrofit.create(UserApiService.class);
        service.postParams(url,params).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code()==200){
                    String string = response.body().toString();
                    if (callback!=null){
                        callback.Success(string);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (callback!=null){
                    callback.Frailure("网咯不稳定，请稍后再试");
                }
            }
        });
    }
    public void doGet(String url, final RequestCallback callback){
        UserApiService service = retrofit.create(UserApiService.class);
        service.getParams(url).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code()==200){
                    String string = null;
                    try {
                        string = response.body().string();
                        if (callback!=null){
                            callback.Success(string);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (callback!=null){
                    callback.Frailure("网咯不稳定，请稍后再试");
                }
            }
        });
    }
}
