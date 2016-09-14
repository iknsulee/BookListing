package com.example.android.booklisting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search("");
    }

    private void search(String query) {
        ListView bookListView = (ListView) findViewById(R.id.book_list);

        ArrayList<Book> bookList = new ArrayList<>();
        if (!query.isEmpty()) {
            bookList.add(new Book(query + " author1", "title"));
            bookList.add(new Book(query + " author2", "title"));
            bookList.add(new Book(query + " author3", "title"));
            bookList.add(new Book(query + " author4", "title"));
            bookList.add(new Book(query + " author5", "title"));
            bookList.add(new Book(query + " author6", "title"));
        }
        BookAdapter bookAdapter = new BookAdapter(this, bookList);

        bookListView.setAdapter(bookAdapter);
    }

    public void search(View view) {
        TextView keywordTextView = (TextView) findViewById(R.id.keyword);
        CharSequence text = keywordTextView.getText();

        search(String.valueOf(text));

        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
    }
}
