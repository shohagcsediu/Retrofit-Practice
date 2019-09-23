package com.shohagapp.retrofitpractice.newsresponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsApiService {

    @GET("v2/top-headlines?country=us&apiKey=a02060f5a916404485827c2a2be2e860")
    Call<NewsResponse> getNewsResponse();
}
