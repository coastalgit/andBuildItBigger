package com.bf.jokedisplay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeDisplayActivity extends AppCompatActivity {

    public static String KEY_JOKE = "key_joke";

    TextView mTvJoke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_display);

        String joke = "No jokes today my friend!";
        Intent intent = getIntent();
        if (intent.hasExtra(KEY_JOKE)){
            joke = intent.getStringExtra(JokeDisplayActivity.KEY_JOKE);
        }

        mTvJoke = findViewById(R.id.tv_display_joke);
        mTvJoke.setText(joke);
    }


}
