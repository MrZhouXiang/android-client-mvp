package puyuntech.com.androidclient.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import puyuntech.com.androidclient.R;
import puyuntech.com.androidclient.app.ActivityBuilder.Impl.ActivityDirector;
import puyuntech.com.androidclient.model.ListItemModel;
import puyuntech.com.androidclient.presenter.ListPresenter;
import puyuntech.com.androidclient.ui.activity.list.PullToRefreshUseActivity;
import puyuntech.com.androidclient.ui.adapter.OneItemAdapter;

/**
 * 作者 Administrator
 * 创建时间 2016/5/23 0023
 * 描述 列表统一处理
 * 修改时间 2016/5/23 0023
 * 修改描述 列表统一处理
 * 修改者 Administrator
 **/
@ContentView(R.layout.activity_list)
public class ListActivity extends ActivityDirector implements BaseQuickAdapter.OnRecyclerViewItemClickListener {
    List data;
    BaseQuickAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    @ViewInject(R.id.recycler_view)
    RecyclerView recycler_view;

    @Override
    public Object getValue(Enum type) {
        return null;
    }

    @Override
    public void updateUI(Object params, Enum type) {

    }

    @Override
    public void initData() {
        mPresenter = new ListPresenter(this);
        data = new ArrayList();
        layoutManager = new LinearLayoutManager(this);
        adapter = new OneItemAdapter(this, R.layout.item_welcome, data);
    }

    @Override
    public void initView() {
        //
        recycler_view.setAdapter(adapter);
        recycler_view.setLayoutManager(layoutManager);
    }

    @Override
    public void setViewClickListener() {
        adapter.setOnRecyclerViewItemClickListener(this);
    }

    @Override
    public void getDataLoc() {

    }

    @Override
    public void showTitle() {
        initTitle("列表的统一处理", true);

    }

    @Override
    public void showView() {
        //刷新列表
        refreshPage(REFRESH_FLAG, ((ListPresenter) mPresenter).getData(), adapter, null);
    }

    @Override
    public void getDataNet() {

    }

    @Override
    public void onItemClick(View view, int position) {
        ListItemModel model = (ListItemModel) adapter.getItem(position);
        ListPresenter.ShowType type = (ListPresenter.ShowType) model.type;
        switch (type) {
            case PULL_AND_REFRESH:
                //刷新与分页
                skipIntent(PullToRefreshUseActivity.class, false);
                break;
            default:
                showShortToast("点击了第" + (position + 1) + "项:" + model.value);
                break;

        }
    }
}
