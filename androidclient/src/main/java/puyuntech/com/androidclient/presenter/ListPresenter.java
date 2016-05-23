package puyuntech.com.androidclient.presenter;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import puyuntech.com.androidclient.model.ListItemModel;

/**
 * @作者 Administrator
 * @创建时间 2016-05-16 下午 13:41
 * @描述 列表主导器
 * @修改时间 2016-05-16 下午 13:41
 * @修改描述
 * @修改者 Administrator
 **/
public class ListPresenter extends BasePresenter {

    public ListPresenter(Context context) {
        super(context);
    }

    public enum ShowType {
        TEST,
        PULL_AND_REFRESH
    }

    public List getData() {
        List<ListItemModel> data = new ArrayList();
        data.add(new ListItemModel(ShowType.PULL_AND_REFRESH, "刷新与分页"));
        data.add(new ListItemModel(ShowType.TEST, "2"));
        data.add(new ListItemModel(ShowType.TEST, "3"));
        data.add(new ListItemModel(ShowType.TEST, "4"));
        data.add(new ListItemModel(ShowType.TEST, "5"));
        return data;
    }
}
