package com.shohagapp.retrofitpractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.shohagapp.retrofitpractice.newsresponse.Article;
import com.shohagapp.retrofitpractice.newsresponse.NewsApiService;
import com.shohagapp.retrofitpractice.newsresponse.NewsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "https://newsapi.org/";

    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;
    private LinearLayoutManager llm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.newsRecyclerView);
        llm = new LinearLayoutManager(this);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NewsApiService apiService = retrofit.create(NewsApiService.class);
        
        apiService.getNewsResponse().enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {

                if (response.code() == 200){
                    NewsResponse newsResponse = response.body();
                    List<Article> articles = newsResponse.getArticles();
                    newsAdapter = new NewsAdapter(MainActivity.this, articles);
                    recyclerView.setLayoutManager(llm);
                    recyclerView.setAdapter(newsAdapter);
                    Toast.makeText(MainActivity.this, ""+articles.size(), Toast.LENGTH_SHORT).show();

                }else {
                    //
                }

                }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {

            }
        });
    }
}
