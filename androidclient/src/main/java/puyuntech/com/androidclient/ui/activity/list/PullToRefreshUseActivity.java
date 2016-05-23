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

import puyuntech.com.androidclient.R;
import puyuntech.com.androidclient.app.ActivityBuilder.Impl.ActivityDirector;
import puyuntech.com.androidclient.presenter.DataServer;
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
    private static final int TOTAL_COUNTER = 100;
    private int delayMillis = 1000;
    private int mCurrentCounter = 0;

    @Override
    public void onLoadMoreRequested() {
        mRecyclerView.post(new Runnable() {
            @Override
            public void run() {
                if (mCurrentCounter >= TOTAL_COUNTER) {
                    refreshPage(LOAD_MORE_FLAG, DataServer.getSampleData(5), mQuickAdapter, mSwipeRefreshLayout);

                } else {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            refreshPage(LOAD_MORE_FLAG, DataServer.getSampleData(pageSize), mQuickAdapter, mSwipeRefreshLayout);
                            mCurrentCounter = mQuickAdapter.getItemCount();
                        }
                    }, delayMillis);
                }
            }


        });
    }


    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mQuickAdapter.setNewData(DataServer.getSampleData(pageSize));
                mQuickAdapter.openLoadMore(pageSize, true);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, delayMillis);
    }


    @Override
    public Object getValue(Enum type) {
        return null;
    }

    @Override
    public void updateUI(Object params, Enum type) {

    }

    @Override
    public void initData() {
        initAdapter();
    }

    @Override
    public void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
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
        addHeadView();

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
