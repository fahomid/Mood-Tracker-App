package com.fahomid.moodtracker

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// RetrofitClient object is a singleton that provides a configured Retrofit instance
object RetrofitClient {
    // Base URL for the API
    private const val BASE_URL = "https://zenquotes.io/"

    // Lazy initialization of the ApiService instance using Retrofit
    val instance: ApiService by lazy {
        // Build the Retrofit instance with the specified base URL and Gson converter
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())  // Converter for parsing JSON
            .build()

        // Create and return the ApiService implementation
        retrofit.create(ApiService::class.java)
    }
}
