package puyuntech.com.androidclient.app.ActivityBuilder.Impl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.nicodelee.utils.ListUtils;

import java.util.ArrayList;
import java.util.List;

import puyuntech.com.androidclient.app.ActivityBuilder.BuildHelper;
import puyuntech.com.androidclient.app.AppDataUtils;
import puyuntech.com.androidclient.app.BaseFragment;
import puyuntech.com.androidclient.presenter.IUpdateUIListener;
import puyuntech.com.androidclient.app.ActivityBuilder.InitBuilder;
import puyuntech.com.androidclient.http.httpContor.base.HttpAfterExpand;
import puyuntech.com.androidclient.presenter.BasePresenter;


/**
 * 作者 Administrator
 * 创建时间 2016/4/25 0025
 * 描述 抽象Fragment【导演者】类，所有Fragment需要继承他
 * 修改时间 2016/4/25 0025
 * 修改描述 实现InitBulder
 * 修改者 Administrator
 **/
public abstract class FragmentDirector extends BaseFragment implements BuildHelper, IUpdateUIListener {
    public static final int REFRESH_FLAG = 0;//刷新
    public static final int LOAD_MORE_FLAG = 1;//更多


    private HttpAfterExpand afterExpand;
    private int pageFlag;//分页标示 0：刷新 1：更多
    private List pageMods;//分页数据
    private InitBuilder initBuilder;//初始化建造者
    protected boolean isHasMore = true;
    protected int pageSize = AppDataUtils.pageSize;
    protected BasePresenter mPresenter;//主导器

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initBuilder = new InitBuilderImpl();
        initBuilder.initFragment(this);//初始化方式2:初始化一个Fragment
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
    public void refreshPage(int loadType, List getList, BaseQuickAdapter adapter, SwipeRefreshLayout mSwipeLayout,RecyclerView recyclerView) {
//        if (loadType == REFRESH_FLAG) {
//            isHasMore = true;
//            if (ListUtils.isEmpty(getList) || ListUtils.getSize(getList) < pageSize) {
//                isHasMore = false;
//            }
//            adapter.setDatas(getList);//重置数据并刷新
//        } else if (loadType == LOAD_MORE_FLAG) {
//            if (ListUtils.isEmpty(getList) || ListUtils.getSize(getList) < pageSize) {
//                showToast("全部加载完毕");
//                isHasMore = false;
//            }
//            adapter.addDatas(getList);//重置数据并刷新
//        }
//        if (mSwipeLayout != null)
//            mSwipeLayout.setRefreshing(false);
    }
}
