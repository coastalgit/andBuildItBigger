package com.udacity.gradle.builditbigger;

/*
 * @author frielb
 * Created on 24/05/2018
 */
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.Log;

import com.bf.javajokelib.JokeStore;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

@SuppressWarnings({"FieldCanBeLocal", "WeakerAccess"})
@SuppressLint("StaticFieldLeak")
@TargetApi(Build.VERSION_CODES.CUPCAKE)
public class FetchJokeTask extends AsyncTask<Void, Integer, String> {

    private static final String TAG = FetchJokeTask.class.getSimpleName();

    @SuppressWarnings("unused")
    private final Context mContext;
    private final boolean mFromCloud;
    MyApi mMyApiService = null;
    private final IOnGetJokeListener mListener;

    public FetchJokeTask(Context mContext, boolean fromCloud, IOnGetJokeListener mListener) {
        this.mContext = mContext;
        this.mFromCloud = fromCloud;
        this.mListener = mListener;
    }

    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
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
                        .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @SuppressWarnings("RedundantThrows")
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });

                mMyApiService = builder.build();
            }

            try {
                return mMyApiService.tellTheJoke().execute().getData();
            } catch (IOException e) {
                Log.e(TAG, "doInBackground: EXCEPTION:[" + e.getLocalizedMessage() + "]");
                return "";
            }

        }

    }

}
