package puyuntech.com.androidclient.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import puyuntech.com.androidclient.R;
import puyuntech.com.androidclient.app.ActivityBuilder.Impl.ActivityDirector;
import puyuntech.com.androidclient.http.httpContor.base.HttpAfterExpand;
import puyuntech.com.androidclient.presenter.WelComePresenter;
import puyuntech.com.androidclient.ui.adapter.BaseRecAdapter;
import puyuntech.com.androidclient.ui.adapter.WelcomeAdapter;

@ContentView(R.layout.activity_welcome)
public class WelcomeActivity extends ActivityDirector {
    List data;
    BaseRecAdapter adapter;
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
        data = new ArrayList();
        mPresenter = new WelComePresenter(this);
        adapter = new WelcomeAdapter(this, data, R.layout.item_welcome) {
            @Override
            public void onItemClick(View v, int position) {
                showShortToast("点击了第" + position + "项:" + data.get(position).toString());
            }
        };
        layoutManager = new LinearLayoutManager(this);
    }

    @Override
    public void initView() {
        recycler_view.setAdapter(adapter);
        recycler_view.setLayoutManager(layoutManager);
    }

    @Override
    public void setViewClickListener() {

    }

    @Override
    public void getDataLoc() {
    }

    @Override
    public void showTitle() {

    }

    @Override
    public void showView() {
        //刷新界面
        refreshPage(REFRESH_FLAG, ((WelComePresenter) mPresenter).getData(), adapter, null);

    }

    @Override
    public void getDataNet(HttpAfterExpand afterExpand) {

    }

    @Override
    public void homeBack() {

    }

}
