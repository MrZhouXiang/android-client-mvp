package puyuntech.com.androidclient.ui.activity.list;

import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

import puyuntech.com.androidclient.R;
import puyuntech.com.androidclient.app.ActivityBuilder.Impl.ActivityDirector;
import puyuntech.com.androidclient.presenter.list.PullToRefreshPresenter;
import puyuntech.com.androidclient.ui.adapter.QuickAdapter;


/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
@ContentView(R.layout.activity_pull_to_refresh)
public class PullToRefreshUseActivity extends ActivityDirector implements BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    @ViewInject(R.id.recycler_view)
    private RecyclerView mRecyclerView;
    private QuickAdapter mQuickAdapter;
    @ViewInject(R.id.swipeLayout)
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private int delayMillis = 1000;
    private int mCurrentCounter = 0;

    @Override
    public void onLoadMoreRequested() {
        //加载更多
        ((PullToRefreshPresenter) mPresenter).loadMore();
    }


    @Override
    public void onRefresh() {
        //刷新
        ((PullToRefreshPresenter) mPresenter).refresh();

    }


    @Override
    public Object getValue(Enum type) {
        PullToRefreshPresenter.ValueGetType type1 = (PullToRefreshPresenter.ValueGetType) type;
        switch (type1) {
            case CURRENT_PAGE_SIZE:
                //获取的值
                return mQuickAdapter.getItemCount();
            default:
                break;
        }
        return null;
    }

    @Override
    public void updateUI(final Object params, Enum type) {
        PullToRefreshPresenter.UpdateUIType type1 = (PullToRefreshPresenter.UpdateUIType) type;
        switch (type1) {
            case LOAD_MORE:
                //加载更多
                mRecyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        List l = (List) params;
                        refreshPage(LOAD_MORE_FLAG, l, mQuickAdapter, mSwipeRefreshLayout, mRecyclerView);
                    }
                });
                break;
            case REFRESH:
                //刷新
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        List l = (List) params;
                        refreshPage(REFRESH_FLAG, l, mQuickAdapter, mSwipeRefreshLayout, mRecyclerView);
                    }
                }, delayMillis);
                break;
            default:
                break;
        }

    }

    @Override
    public void initData() {
        mPresenter = new PullToRefreshPresenter(this);
        initAdapter();
    }

    @Override
    public void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mQuickAdapter);
    }

    @Override
    public void setViewClickListener() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mQuickAdapter.setOnLoadMoreListener(this);
        mQuickAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(PullToRefreshUseActivity.this, Integer.toString(position), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void getDataLoc() {

    }

    @Override
    public void showTitle() {
        initTitle("刷新和分页", true);

    }

    @Override
    public void showView() {

    }

    @Override
    public void getDataNet() {

    }

    private void initAdapter() {
        mQuickAdapter = new QuickAdapter(PullToRefreshUseActivity.this, pageSize);
        mQuickAdapter.openLoadAnimation();
        mCurrentCounter = mQuickAdapter.getItemCount();
        mQuickAdapter.openLoadMore(pageSize, true);//or call mQuickAdapter.setPageSize(PAGE_SIZE);  mQuickAdapter.openLoadMore(true);
//        addHeadView();
        setEmpty(mQuickAdapter, mRecyclerView);
    }

    protected void setEmpty(BaseQuickAdapter adapter, RecyclerView recyclerView) {
        View emptyView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) recyclerView.getParent(), false);
        adapter.setEmptyView(emptyView);
        recyclerView.setAdapter(adapter);
    }

    private void addHeadView() {
        View headView = getLayoutInflater().inflate(R.layout.head_view, null);
        headView.setLayoutParams(new DrawerLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        headView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PullToRefreshUseActivity.this, "click HeadView", Toast.LENGTH_LONG).show();
            }
        });
        mQuickAdapter.addHeaderView(headView);
    }
}
