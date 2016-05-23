package puyuntech.com.androidclient.ui.activity;

import android.view.View;
import android.widget.Button;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import puyuntech.com.androidclient.R;
import puyuntech.com.androidclient.app.ActivityBuilder.Impl.ActivityDirector;
import puyuntech.com.androidclient.utils.NetWorkUtils;


/**
 * 作者 Administrator
 * 创建时间 2016/5/23 0023
 * 描述 网络状态监听
 * 修改时间 2016/5/23 0023
 * 修改描述 网络状态监听
 * 修改者 Administrator
 **/
@ContentView(R.layout.activity_net_status)
public class NetStatusActivity extends ActivityDirector {


    @ViewInject(R.id.test_bt)
    Button test_bt;

    @Event(R.id.test_bt)
    private void clickEvent(final View view) {
        switch (view.getId()) {
            case R.id.test_bt:
                showShortToast("发送网络请求");
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
        initTitle("网络状态监听", true);
    }

    @Override
    public void showView() {
        setBtEnable();
    }

    @Override
    public void getDataNet() {

    }

    @Override
    public void onNetChange() {
        super.onNetChange();
        setBtEnable();
    }

    private void setBtEnable() {
        if (NetWorkUtils.getNetworkState(this) == NetWorkUtils.NETWORN_NONE) {
            //todo 网路状态为不可用
            test_bt.setEnabled(false);
            test_bt.setText("网络不可用");
        } else {
            //todo 网络状态可用
            test_bt.setEnabled(true);
            test_bt.setText("点我");

        }
    }
}
