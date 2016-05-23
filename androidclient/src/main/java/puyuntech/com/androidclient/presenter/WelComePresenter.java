package puyuntech.com.androidclient.presenter;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import puyuntech.com.androidclient.model.ListItemModel;

/**
 * @作者 Administrator
 * @创建时间 2016-05-16 下午 13:41
 * @描述 首页主导器
 * @修改时间 2016-05-16 下午 13:41
 * @修改描述
 * @修改者 Administrator
 **/
public class WelComePresenter extends BasePresenter {

    public WelComePresenter(Context context) {
        super(context);
    }

    public enum ShowType {
        MVP,//("MVP模式"),//
        TITLE,//("头部统一处理"),//
        LIST,//("列表的统一处理"),
        NET_STATUS,//("网络状态监听");
        HTTP,//("网络请求统一处理"),
    }

    public List getData() {
        List<ListItemModel> data = new ArrayList();
        data.add(new ListItemModel(ShowType.MVP, "MVP模式"));
        data.add(new ListItemModel(ShowType.TITLE, "头部统一处理"));
        data.add(new ListItemModel(ShowType.LIST, "列表的统一处理"));
        data.add(new ListItemModel(ShowType.NET_STATUS, "网络状态监听"));
        data.add(new ListItemModel(ShowType.HTTP, "网络请求统一处理"));
        return data;
    }
}
