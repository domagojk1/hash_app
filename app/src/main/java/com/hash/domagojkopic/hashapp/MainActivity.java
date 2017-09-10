package com.hash.domagojkopic.hashapp;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hash.domagojkopic.hashapp.base.BaseActivity;
import com.hash.domagojkopic.hashapp.mvp.main.MainPresenter;
import com.hash.domagojkopic.hashapp.mvp.main.MainView;
import com.hash.domagojkopic.hashapp.mvp.main.impl.MainPresenterImpl;
import com.hash.domagojkopic.hashapp.room.WebPage;
import com.hash.domagojkopic.hashapp.utils.PersistenceEnum;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MainView {

    @BindView(R.id.et_url) EditText mEditTextUrl;
    @BindView(R.id.tv_url_content) TextView tvUrlContent;
    @BindView(R.id.tv_hash_content) TextView tvHashContent;
    @BindView(R.id.tv_persistence_content) TextView tvPersistenceContent;
    @BindView(R.id.layout_content) RelativeLayout contentLayout;

    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainPresenter = new MainPresenterImpl(this);
    }

    @OnClick(R.id.btn_fetch)
    protected void onClick() {
        if (mEditTextUrl.getText().length() > 0) {
            hideContent();
            MainActivity.this.showProgress();
            mainPresenter.checkIfExists(mEditTextUrl.getText().toString());
            mEditTextUrl.setText("");
        }
    }

    @Override
    public void showContent(WebPage webPage) {
        hideProgress();
        setAndShow(webPage.getUrl(), webPage.getHash(), PersistenceEnum.Database);
    }

    @Override
    public void showContent(String url, String hash) {
        hideProgress();
        setAndShow(url, hash, PersistenceEnum.SharedPrefs);
    }

    @Override
    public void showContentPersisted(String url, String hash, PersistenceEnum persistenceEnum) {
        hideProgress();
        setAndShow(url, hash, persistenceEnum);
    }

    @Override
    public void showFailed(String error) {
        hideProgress();
        hideContent();
        showError(error);
    }

    private void setAndShow(String url, String hash, PersistenceEnum persistenceEnum) {
        hideKeyboard();
        contentLayout.setVisibility(View.VISIBLE);
        tvHashContent.setText(hash);
        tvUrlContent.setText(url);

        if (persistenceEnum == PersistenceEnum.Database) {
            tvPersistenceContent.setText(R.string.database);
        }
        else {
            tvPersistenceContent.setText(R.string.preferences);
        }
    }

    private void hideContent() {
        contentLayout.setVisibility(View.INVISIBLE);
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
