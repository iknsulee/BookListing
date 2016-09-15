package com.example.android.booklisting;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.net.URL;
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

    public void search(View view) {
        TextView keywordTextView = (TextView) findViewById(R.id.keyword);
        keyword = String.valueOf(keywordTextView.getText());

        BookAsyncTask bookAsyncTask = new BookAsyncTask();
        bookAsyncTask.execute();
    }

    private void updateUi(List<Book> books) {
        ListView bookListView = (ListView) findViewById(R.id.book_list);
        BookAdapter bookAdapter = new BookAdapter(this, books);
        bookListView.setEmptyView(findViewById(R.id.empty_list_view));
        bookListView.setAdapter(bookAdapter);
    }

    private class BookAsyncTask extends AsyncTask<URL, Void, List<Book>> {

        @Override
        protected List<Book> doInBackground(URL... urls) {
            List<Book> books = QueryUtils.fetchBookData(GOOGLE_BOOKS_API + "?q=" + keyword + "&maxResults=20");
            return books;
        }

        @Override
        protected void onPostExecute(List<Book> books) {
            if (books == null) {
                updateUi(new ArrayList<Book>());
            } else {
                updateUi(books);

            }
        }

    }


}
