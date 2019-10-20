package com.example.weatherAppFiveStates;

import retrofit2.Call;
import retrofit2.http.GET;

public interface weatherPojo {


    //Montreal
    @GET("3534")
    Call<Montreal> getWeather();


    //London
    @GET("44418")
    Call<Montreal> getWeatherLondon();

    //Perth
    @GET("1098081")
    Call<Montreal> getWeatherPerth();


    //New Delhi
    @GET("28743736")
    Call<Montreal> getWeatherNewDelhi();


    //Spain
    @GET("766273")
    Call<Montreal> getWeatherSpain();
}
