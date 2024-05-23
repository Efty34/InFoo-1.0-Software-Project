package com.example.infoo10;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class MovieRepositoryTest {
    private MovieRepository movieRepository;
    private String sampleJson = "{ \"movies\": [ { \"Title\": \"The Shawshank Redemption\", \"Year\": \"1994\", \"Genre\": \"Drama\", \"Plot\": \"Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.\", \"Poster\": \"https://example.com/shawshank.jpg\", \"Ratings\": [ { \"Source\": \"Internet Movie Database\", \"Value\": \"9.3/10\" } ] }, { \"Title\": \"The Godfather\", \"Year\": \"1972\", \"Genre\": \"Crime, Drama\", \"Plot\": \"The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant youngest son.\", \"Poster\": \"https://example.com/godfather.jpg\", \"Ratings\": [ { \"Source\": \"Internet Movie Database\", \"Value\": \"9.2/10\" } ] } ] }";

    @Before
    public void setUp() throws Exception {
        // Use reflection to initialize the private instance variable
        movieRepository = MovieRepository.getInstance(null);
        movieRepository.getMovies().clear();
        loadSampleMovies();
    }

    private void loadSampleMovies() throws Exception {
        movieRepository.loadMoviesFromJson(sampleJson);
    }

    @Test
    public void testLoadMoviesFromAssets() {
        List<DataClass> movies = movieRepository.getMovies();
        assertNotNull(movies);
        assertEquals(2, movies.size());

        DataClass firstMovie = movies.get(0);
        assertEquals("The Shawshank Redemption", firstMovie.getTitle());
        assertEquals("1994", firstMovie.getYear());
        assertEquals("Drama", firstMovie.getGenre());
        assertEquals("9.3/10", firstMovie.getRating());

        DataClass secondMovie = movies.get(1);
        assertEquals("The Godfather", secondMovie.getTitle());
        assertEquals("1972", secondMovie.getYear());
        assertEquals("Crime, Drama", secondMovie.getGenre());
        assertEquals("9.2/10", secondMovie.getRating());
    }

    @Test
    public void testGetRandomMovie() {
        DataClass randomMovie = movieRepository.getRandomMovie();
        assertNotNull(randomMovie);
        assertTrue(randomMovie.getTitle().equals("The Shawshank Redemption") || randomMovie.getTitle().equals("The Godfather"));
    }

    @Test
    public void testAddObserver() {
        TestObserver observer = new TestObserver();
        movieRepository.addObserver(observer);
        movieRepository.testNotifyObservers();  // Use the new method to notify observers
        assertTrue(observer.notified);
    }

    @Test
    public void testLoadMoviesError() {
        movieRepository.setLoadMoviesCallback(message -> assertEquals("Error loading movies", message));
        movieRepository.loadMoviesFromJson(""); // Simulate error by passing an empty JSON string
    }

    private class TestObserver implements MovieDataObserver {
        boolean notified = false;

        @Override
        public void onMovieDataChanged() {
            notified = true;
        }
    }
}
