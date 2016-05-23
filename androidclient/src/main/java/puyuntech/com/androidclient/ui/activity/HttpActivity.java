package puyuntech.com.androidclient.ui.activity;

import android.view.View;
import android.widget.EditText;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import puyuntech.com.androidclient.R;
import puyuntech.com.androidclient.app.ActivityBuilder.Impl.ActivityDirector;
import puyuntech.com.androidclient.presenter.HttpPresenter;
import puyuntech.com.androidclient.presenter.MVPPresenter;

/**
 * 作者 Administrator
 * 创建时间 2016/5/23 0023
 * 描述 网络请求
 * 修改时间 2016/5/23 0023
 * 修改描述 网络请求
 * 修改者 Administrator
 **/
@ContentView(R.layout.activity_http)
public class HttpActivity extends ActivityDirector {

    @ViewInject(R.id.name_tv)
    EditText name_tv;
    @ViewInject(R.id.pwd_tv)
    EditText pwd_tv;

    @Event(R.id.login_bt)
    private void clickEvent(final View view) {
        switch (view.getId()) {
            case R.id.login_bt:
                ((HttpPresenter) mPresenter).login();
                break;
            default:
                break;
        }
    }

    @Override
    public Object getValue(Enum type) {
        HttpPresenter.ValueGetType type1 = (HttpPresenter.ValueGetType) type;
        switch (type1) {
            case NAME:
                //获取name控件的值
                return name_tv.getText().toString();
            case PWD:
                //获取pwd控件的值
                return pwd_tv.getText().toString();
            default:
                break;
        }
        return null;
    }

    @Override
    public void updateUI(Object params, Enum type) {

    }

    @Override
    public void initData() {
        mPresenter = new HttpPresenter(this);
    }

    @Override
    public void initView() {

    }

    @Override
    public void setViewClickListener() {

    }

    @Override
    public void getDataLoc() {

    }

    @Override
    public void showTitle() {
        initTitle("网络请求统一处理", true);
    }

    @Override
    public void showView() {

    }

    @Override
    public void getDataNet() {

    }
}
