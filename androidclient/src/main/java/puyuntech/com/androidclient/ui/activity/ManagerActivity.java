package puyuntech.com.androidclient.ui.activity;

import android.view.View;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

import puyuntech.com.androidclient.R;
import puyuntech.com.androidclient.app.ActivityBuilder.Impl.ActivityDirector;
import puyuntech.com.androidclient.app.MyActivityManager;

/**
 * @作者 Administrator
 * @创建时间 2016-05-23 下午 14:41
 * @描述 ManagerActivity
 * @修改时间 2016-05-23 下午 14:41
 * @修改描述
 * @修改者 Administrator
 **/
@ContentView(R.layout.activity_manager)
public class ManagerActivity extends ActivityDirector {
    @Event(R.id.next_act)
    private void clickEvent(View v) {
        switch (v.getId()) {
            case R.id.next_act:
                //打开自己
                skipIntent(ManagerActivity.class, false);
                break;
            default:
                break;
        }
    }

    @Override
    public Object getValue(Enum type) {
        return null;
    }

    @Override
    public void updateUI(Object params, Enum type) {

    }

    @Override
    public void initData() {

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
        initTitle("activity统一管理", true);
    }

    @Override
    public void showView() {

    }

    @Override
    public void getDataNet() {

    }

    @Override
    public void homeBack() {
        //退出所有activity
        MyActivityManager.getInstance().finishAllActivity();
        skipIntent(WelcomeActivity.class, true);
    }
}
