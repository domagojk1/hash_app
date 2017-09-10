package com.hash.domagojkopic.hashapp.base;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.afollestad.materialdialogs.MaterialDialog;
import com.hash.domagojkopic.hashapp.R;

/**
 * Created by domagojkopic on 09/09/2017.
 */

public class BaseActivity extends AppCompatActivity implements BaseView {

    private MaterialDialog progressDialog;

    @Override
    public void showProgress() {
        if (progressDialog == null || !progressDialog.isShowing()) {

            progressDialog = new MaterialDialog.Builder(this)
                    .title(R.string.app_name)
                    .content(R.string.loading)
                    .progress(true, 0)
                    .build();

            progressDialog.setCanceledOnTouchOutside(false);
        }
        if (!isFinishing()) {
            progressDialog.show();
        }
    }

    @Override
    public void hideProgress() {
        if (progressDialog != null && progressDialog.isShowing() && !isFinishing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showError(String errorMessage) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(R.string.app_name);
        builder.setPositiveButton(R.string.ok, null);

        if (errorMessage != null) {
            builder.setMessage(errorMessage);
        }

        if (!isFinishing()) {
            builder.show();
        }
    }
}
