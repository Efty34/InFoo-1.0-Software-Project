package com.example.infoo10;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class MyAdapterTest {

    private MyAdapter myAdapter;
    private List<DataClass> movieList;
    private Context context;

    @Before
    public void setUp() {
        context = getApplicationContext();
        movieList = new ArrayList<>();
        movieList.add(new DataClass("The Shawshank Redemption", "9.3/10", "https://example.com/shawshank.jpg", "1994", "Drama", "Plot"));
        movieList.add(new DataClass("The Godfather", "9.2/10", "https://example.com/godfather.jpg", "1972", "Crime, Drama", "Plot"));
        myAdapter = new MyAdapter(context, movieList, false);
    }

    @Test
    public void testGetItemCount() {
        assertEquals(2, myAdapter.getItemCount());
    }

    @Test
    public void testSetSearchList() {
        List<DataClass> searchList = new ArrayList<>();
        searchList.add(new DataClass("Inception", "8.8/10", "https://example.com/inception.jpg", "2010", "Action, Sci-Fi", "Plot"));
        myAdapter.setSearchList(searchList);

        assertEquals(1, myAdapter.getItemCount());
        assertEquals("Inception", myAdapter.getDataList().get(0).getTitle());
    }

    @Test
    public void testOnCreateViewHolder() {
        RecyclerView recyclerView = new RecyclerView(context);
        RecyclerView.ViewHolder viewHolder = myAdapter.onCreateViewHolder(recyclerView, 0);
        assertNotNull(viewHolder);
    }

    @Test
    public void testOnBindViewHolder() {
        RecyclerView recyclerView = new RecyclerView(context);
        RecyclerView.ViewHolder viewHolder = myAdapter.onCreateViewHolder(recyclerView, 0);
        myAdapter.onBindViewHolder((MyAdapter.MyViewHolder) viewHolder, 0);

        MyAdapter.MyViewHolder myViewHolder = (MyAdapter.MyViewHolder) viewHolder;
        assertEquals("The Shawshank Redemption", myViewHolder.recTitle.getText().toString());
        assertEquals("9.3/10", myViewHolder.recDesc.getText().toString());
    }


}
