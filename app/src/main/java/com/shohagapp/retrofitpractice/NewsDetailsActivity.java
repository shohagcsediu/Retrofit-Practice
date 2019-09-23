package com.shohagapp.retrofitpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.shohagapp.retrofitpractice.newsresponse.Article;
import com.squareup.picasso.Picasso;

public class NewsDetailsActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        imageView = findViewById(R.id.newsImage);
        Article article = (Article) getIntent().getSerializableExtra("article");
        if (article != null){
            String imageUrl = article.getUrlToImage();
            Picasso.get().load(imageUrl).into(imageView);
        }
    }
}
