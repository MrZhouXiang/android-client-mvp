package puyuntech.com.androidclient.presenter;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * @作者 Administrator
 * @创建时间 2016-05-16 下午 13:41
 * @描述 WelComePresenter
 * @修改时间 2016-05-16 下午 13:41
 * @修改描述
 * @修改者 Administrator
 **/
public class WelComePresenter extends BasePresenter {

    public WelComePresenter(Context context) {
        super(context);
    }

    public List getData() {
        List data = new ArrayList();
        data.add("test1");
        data.add("test2");
        data.add("test3");
        return data;
    }
}
