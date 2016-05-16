package puyuntech.com.androidclient.presenter;

import android.content.Context;

/**
 * @作者 Administrator
 * @创建时间 2016-05-16 下午 13:41
 * @描述 主导器
 * @修改时间 2016-05-16 下午 13:41
 * @修改描述
 * @修改者 Administrator
 **/
public class MVPPresenter extends BasePresenter {
    public enum ValueGetType {
        NAME,
        PWD
    }

    public MVPPresenter(Context context) {
        super(context);
    }

    public void test() {
        String name = (String) mIUpdateUIListener.getValue(ValueGetType.NAME);
        String pwd = (String) mIUpdateUIListener.getValue(ValueGetType.PWD);
        showShortToast("name:" + name + "password:" + pwd);

    }

}
