package com.tenTwenty.testapp.webServices

import com.google.gson.GsonBuilder
import com.tenTwenty.testapp.appUtil.AppConstant
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitSingleTon {



    var client: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.MINUTES) // connect timeout
        .writeTimeout(1, TimeUnit.MINUTES) // write timeout
        .readTimeout(1, TimeUnit.MINUTES) // read timeout
        .build()


    val gson = GsonBuilder()
        .setLenient()
        .create()
    private val retrofit = Retrofit.Builder()
        .baseUrl(AppConstant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    private val retrofitGson = Retrofit.Builder()
        .baseUrl(AppConstant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .build()


    fun <T> buildService(service: Class<T>): T{
        return retrofit.create(service)
    }

    fun <T> buildGSONService(service: Class<T>): T{
        return retrofitGson.create(service)
    }


}