package puyuntech.com.beihai.app.ActivityBuilder.Impl;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.nicodelee.utils.ListUtils;

import java.util.ArrayList;
import java.util.List;

import puyuntech.com.beihai.app.ActivityBuilder.BuildHelper;
import puyuntech.com.beihai.app.ActivityBuilder.InitBuilder;
import puyuntech.com.beihai.app.AppDataUtils;
import puyuntech.com.beihai.app.BaseAct;
import puyuntech.com.beihai.http.httpContor.base.HttpAfterExpand;
import puyuntech.com.beihai.presenter.BasePresenter;
import puyuntech.com.beihai.presenter.IUpdateUIListener;
import puyuntech.com.beihai.ui.adapter.BaseRecAdapter;


/**
 * 作者 zx
 * 创建时间 2016/4/21 0021
 * 描述 抽象activity【导演者】类，所有activity需要继承他
 * 修改时间 2016/4/21 0021
 * 修改描述 基础activity抽象类，所有activity需要实现它
 * 修改者 zx
 **/
public abstract class ActivityDirector extends BaseAct implements BuildHelper, IUpdateUIListener {
    private HttpAfterExpand afterExpand;
    protected boolean isHasMore = true;
    protected int pageSize = AppDataUtils.pageSize;
    private InitBuilder initBuilder;//建造者
    protected BasePresenter mPresenter;//主导器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBuilder = new InitBuilderImpl();//建造者实现类
        initBuilder.initAct(this);//初始化方式1:初始化一个activity
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //返回建设置事件
                homeBack();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * @param oldList         原数据
     * @param loadType
     * @param getList        加载数据
     * @param adapter
     * @param mSwipeLayout
     */
    @Override
    public void refreshPage(List oldList, int loadType, List getList, BaseRecAdapter adapter, SwipeRefreshLayout mSwipeLayout) {
        if (loadType == 0) {
            isHasMore = true;
            if (oldList == null) {
                oldList = new ArrayList();
            } else {
                oldList.clear();
            }
            if (ListUtils.isNotEmpty(getList)) {
                oldList.addAll(getList);
            }
            if (ListUtils.isEmpty(getList) || ListUtils.getSize(getList) < pageSize) {
                isHasMore = false;
            }
            adapter.setDatas(oldList);//重置数据并刷新
        } else if (loadType == 1) {
            if (ListUtils.isEmpty(getList) || ListUtils.getSize(getList) < pageSize) {
                showToast("全部加载完毕");
                isHasMore = false;
            }
            int start = ListUtils.getSize(oldList);
            oldList.addAll(getList);
            adapter.setDatas(oldList);//重置数据并刷新
        }
        if (mSwipeLayout != null)
            mSwipeLayout.setRefreshing(false);
    }
}
