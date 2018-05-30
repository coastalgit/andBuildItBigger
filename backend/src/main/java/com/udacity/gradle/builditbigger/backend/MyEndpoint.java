package com.udacity.gradle.builditbigger.backend;

import com.bf.javajokelib.JokeStore;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Named;

/** An endpoint class we are exposing */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbigger.gradle.udacity.com",
                ownerName = "backend.builditbigger.gradle.udacity.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    //public MyBean sayHi(@Named("name") String name) {
    @ApiMethod(name = "tellTheJoke")
    public MyBean tellTheJoke() {
        MyBean response = new MyBean();

        JokeStore jokeStore = new JokeStore();
        response.setData(jokeStore.giveMeAJoke());

        return response;
    }

}
