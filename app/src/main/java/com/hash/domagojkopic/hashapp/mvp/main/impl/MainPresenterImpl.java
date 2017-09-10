package com.hash.domagojkopic.hashapp.mvp.main.impl;

import android.content.Context;
import android.util.Log;

import com.hash.domagojkopic.hashapp.R;
import com.hash.domagojkopic.hashapp.base.BaseActivity;
import com.hash.domagojkopic.hashapp.mvp.main.MainInteractor;
import com.hash.domagojkopic.hashapp.mvp.main.MainPresenter;
import com.hash.domagojkopic.hashapp.mvp.main.MainView;
import com.hash.domagojkopic.hashapp.room.WebPage;
import com.hash.domagojkopic.hashapp.utils.HashCalculator;
import com.hash.domagojkopic.hashapp.utils.PersistenceEnum;
import com.hash.domagojkopic.hashapp.utils.PreferenceHelper;

import java.lang.ref.WeakReference;

/**
 * Created by domagojkopic on 09/09/2017.
 */

public class MainPresenterImpl implements MainPresenter, MainInteractor.FetchHashListener {
    private WeakReference<MainView> mainView;
    private MainInteractor mainInteractor;
    private String url;
    private Context context;
    private byte[] hash;

    public MainPresenterImpl(MainView view) {
        this.mainView = new WeakReference<>(view);

        if (mainView.get() != null && mainView.get() instanceof BaseActivity) {
            context = ((BaseActivity) mainView.get());
            mainInteractor = new MainInteractorImpl(context);
        }
    }

    @Override
    public void fetchWebContent() {
        mainInteractor.fetchContent(url, this);
    }

    @Override
    public void checkIfExists(final String url) {
        this.url = url;
        if (mainInteractor.preferencesContains(url)) {
            if (mainView.get() != null) {
                final String hash = new PreferenceHelper(context).getString(url);
                showUI(new Runnable() {
                    @Override
                    public void run() {
                        mainView.get().showContentPersisted(url, hash, PersistenceEnum.SharedPrefs);
                    }
                });
            }
        }
        else {
            mainInteractor.databaseContains(url, new MainInteractor.DatabaseListener() {
                @Override
                public void onSuccess(final WebPage webPage) {
                    if (mainView.get() != null) {
                        showUI(new Runnable() {
                            @Override
                            public void run() {
                                mainView.get().showContentPersisted(webPage.getUrl(), webPage.getHash(), PersistenceEnum.Database);
                            }
                        });
                    }
                }

                @Override
                public void onFailed() {
                    MainPresenterImpl.this.fetchWebContent();
                }
            });
        }
    }

    @Override
    public void onFetchHashSuccess(byte[] hash) {
        this.hash = hash;
        save();
    }

    @Override
    public void save() {
        if (HashCalculator.isFirstByteEven(hash)) {
            mainInteractor.saveToDatabase(url, hash, new MainInteractor.DatabaseListener() {
                @Override
                public void onSuccess(final WebPage webPage) {
                    if (mainView.get() != null) {
                        showUI(new Runnable() {
                            @Override
                            public void run() {
                                mainView.get().showContent(webPage);
                            }
                        });
                    }
                }

                @Override
                public void onFailed() {
                    if (mainView.get() != null) {
                        showUI(new Runnable() {
                            @Override
                            public void run() {
                                mainView.get().showFailed(context.getString(R.string.database_error));
                            }
                        });
                    }
                }
            });
        }
        else {
            mainInteractor.saveToPreferences(url, hash);
            if (mainView.get() != null) {
                showUI(new Runnable() {
                    @Override
                    public void run() {
                        mainView.get().showContent(url, HashCalculator.getHexString(hash));
                    }
                });
            }
        }
    }

    @Override
    public void onFetchHashError() {
        if (mainView.get() != null) {
            showUI(new Runnable() {
                @Override
                public void run() {
                    mainView.get().showFailed(context.getString(R.string.download_failed));
                }
            });
        }
    }

    private void showUI(Runnable runnable) {
        if (mainView.get() instanceof BaseActivity) {
            ((BaseActivity) mainView.get()).runOnUiThread(runnable);
        }
    }
}
