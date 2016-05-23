package puyuntech.com.androidclient.presenter.list;

import android.content.Context;
import android.os.Handler;

import puyuntech.com.androidclient.presenter.BasePresenter;
import puyuntech.com.androidclient.presenter.DataServer;

/**
 * @作者 Administrator
 * @创建时间 2016-05-16 下午 13:41
 * @描述 列表主导器
 * @修改时间 2016-05-16 下午 13:41
 * @修改描述
 * @修改者 Administrator
 **/
public class PullToRefreshPresenter extends BasePresenter {
    private static final int TOTAL_COUNTER = 100;//最大页数

    /**
     * 数据获取类型
     */
    public enum ValueGetType {
        CURRENT_PAGE_SIZE,//当前列表长度
    }

    public enum UpdateUIType {
        REFRESH,//刷新
        LOAD_MORE,//加载更多}
    }

    public PullToRefreshPresenter(Context context) {
        super(context);
    }

    public void refresh() {
        mIUpdateUIListener.updateUI(DataServer.getSampleData(pageSize), UpdateUIType.REFRESH);
    }

    public void loadMore() {
        int currentPageSize = (int) mIUpdateUIListener.getValue(ValueGetType.CURRENT_PAGE_SIZE);
        if (currentPageSize >= TOTAL_COUNTER) {
            mIUpdateUIListener.updateUI(DataServer.getSampleData(5), UpdateUIType.LOAD_MORE);
        } else {
            mIUpdateUIListener.updateUI(DataServer.getSampleData(pageSize), UpdateUIType.LOAD_MORE);
        }
    }


}