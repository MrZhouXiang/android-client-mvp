package puyuntech.com.androidclient.app.ActivityBuilder;


import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;


/**
 * 作者 Administrator
 * 创建时间 2016/4/21 0021
 * 描述 界面展示接口
 * 修改时间 2016/4/21 0021
 * 修改描述
 * 修改者 Administrator
 **/
public interface ViewShowHelper {


    /**
     * 通用方法初始化头部
     *
     * @param title
     * @param showReturn
     */
    void initTitle(String title, boolean showReturn);

    /**
     * @param title
     * @param showLeft
     * @param leftId
     */
    void initTitle(String title, boolean showLeft, int leftId);

    /**
     * @param title
     * @param showLeft
     * @param leftId
     * @param rightId
     */
    void initTitle(String title, boolean showLeft, int leftId,  int rightId);

    /**
     * @param title
     * @param showLeft
     * @param leftId
     * @param rightText 右边文字
     */
    void initTitle(String title, boolean showLeft, int leftId, String rightText);

    /**
     * 刷新分页方法
     */
    void refreshPage(int loadType, List getList, BaseQuickAdapter adapter, SwipeRefreshLayout mSwipeLayout, RecyclerView recyclerView);

}
