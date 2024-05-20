package com.example.infoo10;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class DataClassFactoryTest {

    @Test
    public void testCreateFromValidJson() throws Exception {
        // Create a sample valid JSON object
        JSONObject sampleMovie = new JSONObject();
        sampleMovie.put("Title", "The Shawshank Redemption");

        JSONArray ratingsArray = new JSONArray();
        JSONObject ratingObject1 = new JSONObject();
        ratingObject1.put("Source", "Internet Movie Database");
        ratingObject1.put("Value", "9.3/10");
        ratingsArray.put(ratingObject1);

        JSONObject ratingObject2 = new JSONObject();
        ratingObject2.put("Source", "Rotten Tomatoes");
        ratingObject2.put("Value", "91%");
        ratingsArray.put(ratingObject2);

        sampleMovie.put("Ratings", ratingsArray);
        sampleMovie.put("Poster", "https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_SX300.jpg");
        sampleMovie.put("Year", "1994");
        sampleMovie.put("Genre", "Drama");
        sampleMovie.put("Plot", "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.");

        // Use the factory to create a DataClass instance
        DataClass dataClass = DataClassFactory.createFromJson(sampleMovie);

        // Assertions to check if the data is correctly parsed and set
        assertNotNull(dataClass);
        assertEquals("The Shawshank Redemption", dataClass.getTitle());
        assertEquals("9.3/10", dataClass.getRating());
        assertEquals("https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_SX300.jpg", dataClass.getPosterUrl());
        assertEquals("1994", dataClass.getYear());
        assertEquals("Drama", dataClass.getGenre());
        assertEquals("Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.", dataClass.getPlot());
    }

    @Test
    public void testCreateFromJsonWithMissingOptionalFields() throws Exception {
        // Create a sample JSON object with missing optional fields
        JSONObject sampleMovie = new JSONObject();
        sampleMovie.put("Title", "The Godfather");

        JSONArray ratingsArray = new JSONArray();
        JSONObject ratingObject = new JSONObject();
        ratingObject.put("Source", "Internet Movie Database");
        ratingObject.put("Value", "9.2/10");
        ratingsArray.put(ratingObject);

        sampleMovie.put("Ratings", ratingsArray);
        sampleMovie.put("Poster", "https://m.media-amazon.com/images/M/MV5BM2MyNjYxNmUtYTAwNi00MTYxLWJmNWYtYzZlODY3ZTk3OTFlXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SX300.jpg");
        // Missing Year, Genre, and Plot fields

        // Use the factory to create a DataClass instance
        DataClass dataClass = DataClassFactory.createFromJson(sampleMovie);

        // Assertions to check if the data is correctly parsed and set
        assertNotNull(dataClass);
        assertEquals("The Godfather", dataClass.getTitle());
        assertEquals("9.2/10", dataClass.getRating());
        assertEquals("https://m.media-amazon.com/images/M/MV5BM2MyNjYxNmUtYTAwNi00MTYxLWJmNWYtYzZlODY3ZTk3OTFlXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SX300.jpg", dataClass.getPosterUrl());
        assertNull(dataClass.getYear());
        assertNull(dataClass.getGenre());
        assertNull(dataClass.getPlot());
    }

    @Test
    public void testCreateFromInvalidJson() {
        try {
            // Create an invalid JSON object
            JSONObject invalidMovie = new JSONObject();
            invalidMovie.put("Title", "Invalid Movie");
            // Ratings array is missing
            invalidMovie.put("Poster", "https://invalidurl.com/poster.jpg");
            invalidMovie.put("Year", "2021");
            invalidMovie.put("Genre", "Action, Adventure");
            invalidMovie.put("Plot", "This is an invalid plot.");

            // Use the factory to create a DataClass instance
            DataClass dataClass = DataClassFactory.createFromJson(invalidMovie);

            // If no exception is thrown, fail the test
            assertNull("Expected an Exception to be thrown", dataClass);
        } catch (Exception e) {
            // Assertions to check if the exception is correctly thrown
            assertEquals("JSONObject[\"Ratings\"] not found.", e.getMessage());
        }
    }
}
