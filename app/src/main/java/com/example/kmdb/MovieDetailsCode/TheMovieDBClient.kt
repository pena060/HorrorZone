package com.example.kmdb.MovieDetailsCode

import com.example.kmdb.API.MakeQueryToTMDB
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val API_KEY = "28d979e940b6fe72b65e85d6eb5ea77f"
const val BASE_URL = "https://api.themoviedb.org/3/"
const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w342"
const val FOREGROUND_BASE_URL = "https://image.tmdb.org/t/p/w1280"
const val FIRST_PAGE = 1
const val POST_PER_PAGE = 20

//TMDB client that will be used specifically to handle the query, requests. and responses for displaying movie details (using interface)
object TheMovieDBClient {
    fun getClient(): MakeQueryToTMDB {
        val requestInterceptor = Interceptor{ chain ->
            val url = chain.request()
                .url()
                .newBuilder()
                .addEncodedQueryParameter("api_key", API_KEY)
                .build()

            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()
            //return value from request
            return@Interceptor chain.proceed(request)
        }
        //initiate okhttp client
        val okhttpClient = OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .connectTimeout(60,TimeUnit.SECONDS)
            .build()


        return Retrofit.Builder()
            .client(okhttpClient)
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MakeQueryToTMDB::class.java)
    }
}