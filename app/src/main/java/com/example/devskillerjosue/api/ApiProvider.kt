package com.example.devskillerjosue.api

import com.google.gson.GsonBuilder
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiVariables {

    private var gsonConverterFactory: GsonConverterFactory? = null

    //Network client for request call
    val client: OkHttpClient
        get() {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            val okHttpClientBuilder = OkHttpClient.Builder()

            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            okHttpClientBuilder.addNetworkInterceptor(httpLoggingInterceptor)

            okHttpClientBuilder.addInterceptor { chain ->
                val request = chain.request()
                val builder = request.newBuilder()
                if (getHeaders().size > 0) {
                    for ((key, value) in getHeaders()) {
                        builder.addHeader(key, value)
                    }
                }
                chain.proceed(builder.build())
            }
            return okHttpClientBuilder.build()
        }

    //convert json in objects
    val gsonConverter: GsonConverterFactory
        get() {
            if (gsonConverterFactory == null) {
                gsonConverterFactory = GsonConverterFactory.create(
                    GsonBuilder()
                        .setLenient()
                        .disableHtmlEscaping()
                        .create())
            }
            return gsonConverterFactory!!
        }

    //general headers of the request
    private fun getHeaders(): HashMap<String, String> {
        val params = HashMap<String, String>()
        params["Access-Control-Allow-Origin"] = "*"
        params["Content-Type"] = "application/json"
        return params
    }
}

//Provider with the base url and retrofit constructor
object ApiProvider {
    var url: HttpUrl = "https://rickandmortyapi.com/".toHttpUrl()

    fun provider(): Api = Retrofit.Builder()
        .baseUrl(url)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(ApiVariables.gsonConverter)
        .client(ApiVariables.client)
        .build()
        .create(Api::class.java)
}