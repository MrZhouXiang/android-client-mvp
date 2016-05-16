package puyuntech.com.beihai.app.ActivityBuilder;


import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import puyuntech.com.beihai.ui.adapter.BaseRecAdapter;

/**
 * 作者 Administrator
 * 创建时间 2016/4/21 0021
 * 描述 刷新接口
 * 修改时间 2016/4/21 0021
 * 修改描述
 * 修改者 Administrator
 **/
public interface RefreshHelper {


    /**
     * 分页标志
     *
     * @return
     */
//    int getPageFlag();

    /**
     * 设置
     *
     * @param pageFlag
     */
//    void setPageFlag(int pageFlag);

    /**
     * 返回分页对象
     *
     * @return
     */
//    List getPageMods();

    /**
     * 设置分页对象
     *
     * @param mMods
     */
//    void setPageMods(List mMods);

    /**
     * 刷新方法
     */
//    void refresh();

    /**
     * 刷新分页方法
     */
//    @Deprecated
//    void refreshPage();

    /**
     * 刷新分页方法
     */
    void refreshPage(List oldList, int loadType, List getList, BaseRecAdapter adapter, SwipeRefreshLayout mSwipeLayout);

}
