package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bf.jokedisplay.JokeDisplayActivity;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;


@SuppressWarnings({"FieldCanBeLocal", "WeakerAccess"})
public class MainActivity extends AppCompatActivity {

    private static final int DUMMY_DELAY = 2000;

    @BindView(R.id.progbar_loading)
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        displayProgressBar(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Toast.makeText(MainActivity.this, "Udacity rocks!", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void fetchJokeFromJavaLibrary(){
        fetchJoke(false);
    }

    public void fetchJokeFromCloud(){
         //Emulate a delay for realistic progress bar
        displayProgressBar(true);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                fetchJoke(true);
            }
        }, DUMMY_DELAY);
    }

    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    private void fetchJoke(boolean fromCloud){

        new FetchJokeTask(this, fromCloud, new IOnGetJokeListener() {
            @Override
            public void onFetchJoke_OK(String joke) {
                displayProgressBar(false);
                showJokeDisplayActivity(joke);
            }

            @Override
            public void onFetchJoke_Error(String error) {
                displayProgressBar(false);
                Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
            }
        }).execute();
    }

    public void showJokeDisplayActivity(String joke) {
        Intent intent = new Intent(this, JokeDisplayActivity.class);
        intent.putExtra(JokeDisplayActivity.KEY_JOKE, joke);
        startActivity(intent);
    }

    public void displayProgressBar(final boolean showProgress){
        if (mProgressBar != null) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mProgressBar.setVisibility(showProgress ? View.VISIBLE : View.GONE);
                }
            });
        }
    }

}
