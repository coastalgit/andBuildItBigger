package com.bf.jokedisplay;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.text.TextUtils;

import com.udacity.gradle.builditbigger.FetchJokeTask;
import com.udacity.gradle.builditbigger.IOnGetJokeListener;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class FetchJokesTaskTest {

    @Test
    public void testTaskForCloud() throws InterruptedException {
        Context appContext = InstrumentationRegistry.getTargetContext();
        final CountDownLatch latch = new CountDownLatch(1);

        FetchJokeTask jokeTask = new FetchJokeTask(appContext, true, new IOnGetJokeListener() {
            @Override
            public void onFetchJoke_OK(String joke) {
                latch.countDown();
                assertThat(joke, not(isEmptyString()));
            }

            @Override
            public void onFetchJoke_Error(String error) {
                latch.countDown();
                assertThat(error, containsString("No joke"));
            }
        });

        try {
            jokeTask.execute();
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
            fail();
        }
    }
}
