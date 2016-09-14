package com.example.android.booklisting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void search(View view) {
        TextView keywordTextView = (TextView) findViewById(R.id.keyword);
        CharSequence text = keywordTextView.getText();

        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
    }
}
