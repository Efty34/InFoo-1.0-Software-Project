package com.example.infoo10;

import static org.junit.Assert.*;

import android.os.Parcel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
public class DataClassTest {

    private DataClass dataClass;

    @Before
    public void setUp() {
        dataClass = new DataClass("Title", "8.5", "http://example.com/poster.jpg", "2024", "Action", "Sample plot");
    }

    @Test
    public void testGetTitle() {
        assertEquals("Title", dataClass.getTitle());
    }

    @Test
    public void testGetRating() {
        assertEquals("8.5", dataClass.getRating());
    }

    @Test
    public void testGetPosterUrl() {
        assertEquals("http://example.com/poster.jpg", dataClass.getPosterUrl());
    }

    @Test
    public void testGetYear() {
        assertEquals("2024", dataClass.getYear());
    }

    @Test
    public void testGetGenre() {
        assertEquals("Action", dataClass.getGenre());
    }

    @Test
    public void testGetPlot() {
        assertEquals("Sample plot", dataClass.getPlot());
    }

    @Test
    public void testParcelable() {
        Parcel parcel = Parcel.obtain();
        dataClass.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);

        DataClass createdFromParcel = DataClass.CREATOR.createFromParcel(parcel);
        assertEquals(dataClass.getTitle(), createdFromParcel.getTitle());
        assertEquals(dataClass.getRating(), createdFromParcel.getRating());
        assertEquals(dataClass.getPosterUrl(), createdFromParcel.getPosterUrl());
        assertEquals(dataClass.getYear(), createdFromParcel.getYear());
        assertEquals(dataClass.getGenre(), createdFromParcel.getGenre());
        assertEquals(dataClass.getPlot(), createdFromParcel.getPlot());
        parcel.recycle();
    }

}