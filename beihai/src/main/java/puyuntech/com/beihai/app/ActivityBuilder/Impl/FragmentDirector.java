package puyuntech.com.beihai.app.ActivityBuilder.Impl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.nicodelee.utils.ListUtils;

import java.util.ArrayList;
import java.util.List;

import puyuntech.com.beihai.app.ActivityBuilder.BuildHelper;
import puyuntech.com.beihai.app.ActivityBuilder.InitBuilder;
import puyuntech.com.beihai.app.AppDataUtils;
import puyuntech.com.beihai.app.BaseFragment;
import puyuntech.com.beihai.http.httpContor.base.HttpAfterExpand;
import puyuntech.com.beihai.presenter.BasePresenter;
import puyuntech.com.beihai.presenter.IUpdateUIListener;


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
    public HttpAfterExpand getAfterExpand() {
        return afterExpand;
    }

    @Override
    public int getPageFlag() {
        return pageFlag;
    }

    @Override
    public void setPageFlag(int pageFlag) {
        this.pageFlag = pageFlag;
    }

    @Override
    public List getPageMods() {
        return pageMods;
    }

    @Override
    public void setPageMods(List pageMods) {
        this.pageMods = pageMods;
    }

    /**
     * 过时
     */
    @Override
    @Deprecated
    public void initAfterHttp() {

    }

    /**
     * 设置网路请求之后回调
     *
     * @param afterExpand
     */
    @Override
    public void setAfterExpand(HttpAfterExpand afterExpand) {
        this.afterExpand = afterExpand;
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


    @Deprecated
    @Override
    public void refreshPage() {

    }

    /**
     * @param list         原数据
     * @param loadType
     * @param mMods        加载数据
     * @param adapter
     * @param mSwipeLayout
     */
    @Override
    public void refreshPage(List list, int loadType, List mMods, RecyclerView.Adapter adapter, SwipeRefreshLayout mSwipeLayout) {
        if (loadType == REFRESH_FLAG) {
            isHasMore = true;
            if (list == null) {
                list = new ArrayList();
            } else {
                list.clear();
            }
            if (ListUtils.isNotEmpty(mMods)) {
                list.addAll(mMods);
            }
            if (ListUtils.isEmpty(mMods) || ListUtils.getSize(mMods) < pageSize) {
                isHasMore = false;
            }
            adapter.notifyDataSetChanged();
        } else if (loadType == LOAD_MORE_FLAG) {
            if (ListUtils.isEmpty(mMods) || ListUtils.getSize(mMods) < pageSize) {
                showToast("全部加载完毕");
                isHasMore = false;
            }

            int start = ListUtils.getSize(list);
            list.addAll(mMods);
            adapter.notifyItemRangeChanged(start, mMods.size());
        }
        if (mSwipeLayout != null)
            mSwipeLayout.setRefreshing(false);
    }
}
