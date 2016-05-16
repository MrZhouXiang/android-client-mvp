package puyuntech.com.androidclient.app.ActivityBuilder.Impl;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.MenuItem;

import com.nicodelee.utils.ListUtils;

import java.util.List;

import puyuntech.com.androidclient.app.ActivityBuilder.BuildHelper;
import puyuntech.com.androidclient.app.ActivityBuilder.InitBuilder;
import puyuntech.com.androidclient.app.AppDataUtils;
import puyuntech.com.androidclient.app.BaseAct;
import puyuntech.com.androidclient.presenter.BasePresenter;
import puyuntech.com.androidclient.presenter.IUpdateUIListener;
import puyuntech.com.androidclient.ui.adapter.BaseRecAdapter;


/**
 * 作者 zx
 * 创建时间 2016/4/21 0021
 * 描述 抽象activity【导演者】类，所有activity需要继承他
 * 修改时间 2016/4/21 0021
 * 修改描述 基础activity抽象类，所有activity需要实现它
 * 修改者 zx
 **/
public abstract class ActivityDirector extends BaseAct implements BuildHelper, IUpdateUIListener {
    protected boolean isHasMore = true;
    protected int pageSize = AppDataUtils.pageSize;
    private InitBuilder initBuilder;//建造者
    protected BasePresenter mPresenter;//主导器
    public static final int REFRESH_FLAG = 0;
    public static final int LOAD_MORE_FLAG = 1;

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
     * @param loadType     刷新or加载更多
     * @param getList      加载数据
     * @param adapter      需要刷新的adapter
     * @param mSwipeLayout loading界面
     */
    @Override
    public void refreshPage(int loadType, List getList, BaseRecAdapter adapter, SwipeRefreshLayout mSwipeLayout) {
        if (loadType == REFRESH_FLAG) {
            isHasMore = true;
            if (ListUtils.isEmpty(getList) || ListUtils.getSize(getList) < pageSize) {
                isHasMore = false;
            }
            adapter.setDatas(getList);//重置数据并刷新
        } else if (loadType == LOAD_MORE_FLAG) {
            if (ListUtils.isEmpty(getList) || ListUtils.getSize(getList) < pageSize) {
                showToast("全部加载完毕");
                isHasMore = false;
            }
            adapter.addDatas(getList);//重置数据并刷新
        }
        if (mSwipeLayout != null)
            mSwipeLayout.setRefreshing(false);
    }
}
