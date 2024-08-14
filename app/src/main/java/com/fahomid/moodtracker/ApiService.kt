package com.fahomid.moodtracker

import retrofit2.Call
import retrofit2.http.GET

// ApiService interface defines the endpoints for network requests using Retrofit
interface ApiService {

    // getRandomQuote method makes a GET request to the "/api/random" endpoint
    // It returns a Call object that contains a list of Quote objects
    @GET("/api/random")
    fun getRandomQuote(): Call<List<Quote>>
}
