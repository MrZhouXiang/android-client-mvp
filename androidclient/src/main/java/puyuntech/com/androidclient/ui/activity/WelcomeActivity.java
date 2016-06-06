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
import puyuntech.com.androidclient.presenter.WelComePresenter;
import puyuntech.com.androidclient.ui.adapter.OneItemAdapter;


/**
 * 作者 Administrator
 * 创建时间 2016/5/16 0016
 * 描述 首页
 * 修改时间 2016/5/16 0016
 * 修改描述 首页
 * 修改者 Administrator
 **/
@ContentView(R.layout.activity_welcome)
public class WelcomeActivity extends ActivityDirector {
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
        //初始化主导器
        mPresenter = new WelComePresenter(this);
        data = new ArrayList();
        layoutManager = new LinearLayoutManager(this);
        adapter = new OneItemAdapter(this, R.layout.item_welcome, data);
    }

    @Override
    public void initView() {
        recycler_view.setAdapter(adapter);
        recycler_view.setLayoutManager(layoutManager);
    }

    @Override
    public void setViewClickListener() {
        adapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ListItemModel model = (ListItemModel) adapter.getItem(position);
                WelComePresenter.ShowType type = (WelComePresenter.ShowType) model.type;
                switch (type) {
                    case MVP:
                        skipIntent(MVPActivity.class, false);
                        break;
                    case TITLE:
                        skipIntent(TitleActivity.class, false);
                        break;
                    case LIST:
                        skipIntent(ListActivity.class, false);
                        break;
                    case NET_STATUS:
                        skipIntent(NetStatusActivity.class, false);
                        break;
                    case HTTP:
                        skipIntent(HttpActivity.class, false);
                        break;
                    case ACTIVITY_MANAGER:
                        skipIntent(ManagerActivity.class, false);
                        break;
                    default:
                        showShortToast("点击了第" + (position + 1) + "项:" + model.value);
                        break;
                }

            }
        });
    }

    @Override
    public void getDataLoc() {
    }

    @Override
    public void showTitle() {
        initTitle("欢迎", -1, null, -1);
//        initTitle("欢迎", false);
    }

    @Override
    public void showView() {
        //刷新界面
        refreshPage(REFRESH_FLAG, ((WelComePresenter) mPresenter).getData(), adapter, null, recycler_view);

    }

    @Override
    public void getDataNet() {

    }


}
