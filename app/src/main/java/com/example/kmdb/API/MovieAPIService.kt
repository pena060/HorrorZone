package com.example.kmdb.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//Main API services, using main Base URL instantiation (this will be used in all query as a main url)
class MovieAPIService {
    companion object{
        private const val BASE_URL = "https://api.themoviedb.org"
        private var retrofit : Retrofit? = null

        fun getInstance(): Retrofit{
            if(retrofit == null){
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }

    }


}