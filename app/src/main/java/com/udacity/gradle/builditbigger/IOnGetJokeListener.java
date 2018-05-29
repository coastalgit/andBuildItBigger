package com.udacity.gradle.builditbigger;

/*
 * @author frielb
 * Created on 24/05/2018
 */

public interface IOnGetJokeListener
{
    void onFetchJoke_OK(String joke);
    void onFetchJoke_Error(String error);
}
