package com.udacity.gradle.builditbigger;

/*
 * @author frielb
 * Created on 24/05/2018
 */

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;

import com.bf.javajokelib.JokeStore;

public class fetchJokeTask extends AsyncTask<Void, Integer, String> {

    private Context mContext;
    private IOnGetJokeListener mListener;

    public fetchJokeTask(Context mContext, IOnGetJokeListener mListener) {
        this.mContext = mContext;
        this.mListener = mListener;
    }

    @Override
    protected void onPostExecute(String newJoke)
    {
        super.onPostExecute(newJoke);
        if (!TextUtils.isEmpty(newJoke))
        {
            // TODO: 24/05/2018

            mListener.onFetchJoke_OK(newJoke);
        }
        else
            mListener.onFetchJoke_Error("Are you kidding!");
    }

    @Override
    protected String doInBackground(Void... voids) {
        JokeStore jokeStore = new JokeStore();
        return jokeStore.giveMeAJoke();
        //return "Q: AAA" + "\n" + "A: BBB";

    }
}
