package com.hash.domagojkopic.hashapp.mvp.main;

import com.hash.domagojkopic.hashapp.room.WebPage;
import com.hash.domagojkopic.hashapp.utils.PersistenceEnum;

/**
 * Created by domagojkopic on 09/09/2017.
 */

public interface MainView {
    void showContent(WebPage webPage);
    void showContent(String url, String hash);
    void showContentPersisted(String url, String hash, PersistenceEnum persistenceEnum);
    void showFailed(String error);
}
