// Generated code from Butter Knife. Do not modify!
package com.hash.domagojkopic.hashapp;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  private View view2131427443;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(final MainActivity target, View source) {
    this.target = target;

    View view;
    target.mEditTextUrl = Utils.findRequiredViewAsType(source, R.id.et_url, "field 'mEditTextUrl'", EditText.class);
    target.tvUrlContent = Utils.findRequiredViewAsType(source, R.id.tv_url_content, "field 'tvUrlContent'", TextView.class);
    target.tvHashContent = Utils.findRequiredViewAsType(source, R.id.tv_hash_content, "field 'tvHashContent'", TextView.class);
    target.tvPersistenceContent = Utils.findRequiredViewAsType(source, R.id.tv_persistence_content, "field 'tvPersistenceContent'", TextView.class);
    target.contentLayout = Utils.findRequiredViewAsType(source, R.id.layout_content, "field 'contentLayout'", RelativeLayout.class);
    view = Utils.findRequiredView(source, R.id.btn_fetch, "method 'onClick'");
    view2131427443 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mEditTextUrl = null;
    target.tvUrlContent = null;
    target.tvHashContent = null;
    target.tvPersistenceContent = null;
    target.contentLayout = null;

    view2131427443.setOnClickListener(null);
    view2131427443 = null;
  }
}
