package puyuntech.com.androidclient.ui.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import puyuntech.com.androidclient.R;
import puyuntech.com.androidclient.app.ActivityBuilder.Impl.ActivityDirector;
import puyuntech.com.androidclient.presenter.MVPPresenter;

/**
 * @作者 Administrator
 * @创建时间 2016-05-16 下午 15:30
 * @描述 MVP模式实例
 * @修改时间 2016-05-16 下午 15:30
 * @修改描述
 * @修改者 Administrator
 **/
@ContentView(R.layout.activity_mvc)
public class MVPActivity extends ActivityDirector {

    @ViewInject(R.id.name_tv)
    EditText name_tv;

    @ViewInject(R.id.pwd_tv)
    EditText pwd_tv;

    @ViewInject(R.id.name_pwd_show)
    TextView name_pwd_show;

    @Event(R.id.test_bt)
    private void clickEvent(final View view) {
        switch (view.getId()) {
            case R.id.test_bt:
                ((MVPPresenter) mPresenter).test();
                break;
            default:
                break;
        }
    }

    @Override
    public Object getValue(Enum type) {
        MVPPresenter.ValueGetType type1 = (MVPPresenter.ValueGetType) type;
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
        MVPPresenter.UpdateUIType type1 = (MVPPresenter.UpdateUIType) type;
        switch (type1) {
            case SHOW_NAME_PWD:
                //展示输入的用户名密码
                String show = (String) params;
                name_pwd_show.setText(show);
                break;
            default:
                break;
        }
    }

    @Override
    public void initData() {
        //初始化主导器
        mPresenter = new MVPPresenter(this);
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
        initTitle("MVP", true);
    }

    @Override
    public void showView() {

    }

    @Override
    public void getDataNet() {

    }


}
