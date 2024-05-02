package com.example.infoo10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    TextView detailRating, detailTitle, detailYear, detailGenre, detailPlot;
    ImageView detailImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Setting up the Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        detailRating = findViewById(R.id.detailRating);
        detailTitle = findViewById(R.id.detailTitle);
        detailImage = findViewById(R.id.detailImage);
        detailYear = findViewById(R.id.detailYear);
//        detailGenre = findViewById(R.id.detailGenre);
        detailPlot = findViewById(R.id.detailPlot);

        DataClass movie = getIntent().getParcelableExtra("MovieData");
        if (movie != null) {
            detailTitle.setText(movie.getTitle());
            detailRating.setText(movie.getRating());
            detailYear.setText(movie.getYear());
//            detailGenre.setText(movie.getGenre());
            detailPlot.setText(movie.getPlot());

            Picasso.get().load(movie.getPosterUrl()).into(detailImage);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
