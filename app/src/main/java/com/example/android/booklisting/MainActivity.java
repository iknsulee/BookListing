package com.example.android.booklisting;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String GOOGLE_BOOKS_API = "https://www.googleapis.com/books/v1/volumes";
    private String keyword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        updateUi(new ArrayList<Book>());
    }

//    private void setData(String query) {
//        ListView bookListView = (ListView) findViewById(R.id.book_list);
//
//        ArrayList<Book> bookList = new ArrayList<>();
//        if (!query.isEmpty()) {
//            bookList.add(new Book(query + " author1", "title"));
//            bookList.add(new Book(query + " author2", "title"));
//            bookList.add(new Book(query + " author3", "title"));
//            bookList.add(new Book(query + " author4", "title"));
//            bookList.add(new Book(query + " author5", "title"));
//            bookList.add(new Book(query + " author6", "title"));
//        }
//        BookAdapter bookAdapter = new BookAdapter(this, bookList);
//
//        bookListView.setAdapter(bookAdapter);
//    }

    public void search(View view) {
        TextView keywordTextView = (TextView) findViewById(R.id.keyword);
        keyword = String.valueOf(keywordTextView.getText());

        BookAsyncTask bookAsyncTask = new BookAsyncTask();
        bookAsyncTask.execute();

//        Toast.makeText(MainActivity.this, keyword, Toast.LENGTH_SHORT).show();
    }

    private void updateUi(List<Book> books) {
        ListView bookListView = (ListView) findViewById(R.id.book_list);
        BookAdapter bookAdapter = new BookAdapter(this, books);
        bookListView.setAdapter(bookAdapter);
    }

    private class BookAsyncTask extends AsyncTask<URL, Void, List<Book>> {

        @Override
        protected List<Book> doInBackground(URL... urls) {
            List<Book> books = QueryUtils.fetchBookData(GOOGLE_BOOKS_API + "?q=" + keyword + "&maxResults=10");
            return books;
        }

        @Override
        protected void onPostExecute(List<Book> books) {
            if (books == null) {
                return;
            }

            updateUi(books);
        }

    }


}
