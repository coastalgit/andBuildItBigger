package com.udacity.gradle.builditbigger;

/*
 * @author frielb
 * Created on 24/05/2018
 */

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import com.bf.javajokelib.JokeStore;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class fetchJokeTask extends AsyncTask<Void, Integer, String> {

    private static final String TAG = fetchJokeTask.class.getSimpleName();


    private Context mContext;
    private boolean mFromCloud = false;
    MyApi mMyApiService = null;
    private IOnGetJokeListener mListener;

    public fetchJokeTask(Context mContext, boolean fromCloud, IOnGetJokeListener mListener) {
        this.mContext = mContext;
        this.mFromCloud = fromCloud;
        this.mListener = mListener;
    }

    @Override
    protected void onPostExecute(String newJoke)
    {
        super.onPostExecute(newJoke);
        if (!TextUtils.isEmpty(newJoke))
            mListener.onFetchJoke_OK(newJoke);
        else
            mListener.onFetchJoke_Error("No jokes today... are you kidding!");
    }

    @Override
    protected String doInBackground(Void... voids) {
        if (!mFromCloud) {
            JokeStore jokeStore = new JokeStore();
            return jokeStore.giveMeAJoke();
        }
        else{
            if (mMyApiService == null) {
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        // options for running against local devappserver
                        // - 10.0.2.2 is localhost's IP address in Android emulator
                        // - turn off compression when running against local devappserver
                        //.setRootUrl("http://10.0.2.2:8080/_ah/api/")
                        .setRootUrl("http://192.168.0.16:8080/_ah/api/") // real devices and Genymotion emulators
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });

                mMyApiService = builder.build();
            }

            try {
                Log.d(TAG, "doInBackground: API call");
                return mMyApiService.tellTheJoke().execute().getData();
            } catch (IOException e) {
                Log.e(TAG, "doInBackground: EXCEPTION:[" + e.getLocalizedMessage() + "]");
                return "";
            }

        }

    }

}
