package com.mobile.weatherforcast.network

import android.util.Log
import com.mobile.weatherforcast.utils.Constant
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

object RetrofitInstance {
    private var retrofit: Retrofit? = null
    private var baseUrl = "http://api.weatherapi.com/v1/"

    fun getRetrofit(): Retrofit? {
        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(createOkHttp())
            .build()
        return retrofit
    }
    private fun createOkHttp(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        httpClient.addInterceptor(interceptor).connectTimeout(25, TimeUnit.SECONDS)
            .writeTimeout(150, TimeUnit.SECONDS)
            .readTimeout(150, TimeUnit.SECONDS)
            .build()
        httpClient.addInterceptor(LoggingInterceptor())
        httpClient.addInterceptor { chain ->
            var newRequest: Request? = null
            try {

                newRequest = chain.request().newBuilder()
                    .addHeader("key", Constant.API_KEY)
                    .build()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            newRequest?.let { chain.proceed(it) }!!
        }
        return httpClient.build()
    }

}
internal class LoggingInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val t1 = System.nanoTime()
        Log.d("OkHttpMy", java.lang.String.format("Sending request %s on %s%n%s",
            request.url, chain.connection(), request.headers
        ))
        val response: Response = chain.proceed(request)
        val t2 = System.nanoTime()
        try{
            Log.d("OkHttpMy", java.lang.String.format("Received response for %s in %.1fms%n%s",
                response.request.url, (t2 - t1) / 1e6, response.headers
            ))
            //Todo Comment Due to crash

//            Log.d("OkHttpMy", "Response Code: ${response.code()}}")
        }catch (e: Exception){
            e.localizedMessage?.let { Log.d("OkHttpMy", it) }
        }
        return response
    }
}
