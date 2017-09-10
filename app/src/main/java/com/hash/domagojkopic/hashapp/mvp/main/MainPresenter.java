package com.hash.domagojkopic.hashapp.mvp.main;

/**
 * Created by domagojkopic on 09/09/2017.
 */

public interface MainPresenter {
    void fetchWebContent();
    void checkIfExists(String url);
    void save();
}