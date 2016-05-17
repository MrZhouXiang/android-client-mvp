package puyuntech.com.androidclient.app.ActivityBuilder.Impl;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nicodelee.utils.ListUtils;
import com.nicodelee.utils.StringUtils;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

import puyuntech.com.androidclient.R;
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
    @ViewInject(R.id.toolbar)
    protected Toolbar toolbar;
    @ViewInject(R.id.title_tv)
    protected TextView title_tv;//标题
    @ViewInject(R.id.right_iv)
    protected ImageView right_iv;//右边的image按钮
    @ViewInject(R.id.right_tv)
    protected TextView right_tv;//右边的文字按钮

    protected boolean isHasMore = true;
    protected int pageSize = AppDataUtils.pageSize;
    private InitBuilder initBuilder;//建造者
    protected BasePresenter mPresenter;//主导器
    public static final int REFRESH_FLAG = 0;
    public static final int LOAD_MORE_FLAG = 1;

    @Event({R.id.right_iv, R.id.right_tv})
    private void clickEvent(final View view) {
        switch (view.getId()) {
            case R.id.right_iv:
                rightImgClick();
                break;
            case R.id.right_tv:
                rightTextClick();
                break;
        }
    }

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


    /**
     * 初始化头部
     *
     * @param title
     * @param showLeft 是否显示左边按钮
     */
    @Override
    public void initTitle(String title, boolean showLeft) {
        //标题展示
        initTitle(title, showLeft, -1);
    }

    /**
     * 初始化头部
     *
     * @param title    标题
     * @param showLeft 是否显示左边按钮
     * @param leftId   左边按钮id
     */
    @Override
    public void initTitle(String title, boolean showLeft, int leftId) {
        initTitle(title, showLeft, leftId, -1);
    }

    /**
     * 初始化头部 右边有文字
     *
     * @param title
     * @param showLeft
     * @param leftId
     * @param rightStr 右边文字
     */
    @Override
    public void initTitle(String title, boolean showLeft, int leftId, String rightStr) {
        //标题展示
        if (title_tv != null) {
            title_tv.setText(title);
        }
        //返回按钮展示
        if (toolbar == null)
            return;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setTitle(StringUtils.getNotNullStr(title));
        getSupportActionBar().setDisplayHomeAsUpEnabled(showLeft);// // 给左上角图标的左边加上一个返回的图标 。
        getSupportActionBar().setDisplayShowHomeEnabled(showLeft);//使左上角图标是否显示，如果设成false，则没有程序图标，仅仅就个标题，否则，显示应用程序图标，对应id为Android.R.id.home，对应ActionBar.DISPLAY_SHOW_HOME
        if (showLeft) {
            //需要展示左边图标
            if (leftId != -1) {
                getSupportActionBar().setHomeAsUpIndicator(leftId);
            } else {
                getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_return);
            }
        }
        //需要展示右边文字
        if (rightStr != null) {
            right_tv.setVisibility(View.VISIBLE);
            right_iv.setVisibility(View.GONE);
            right_tv.setText(StringUtils.getNotNullStr(rightStr));
        } else {
            right_tv.setVisibility(View.GONE);
            right_iv.setVisibility(View.GONE);

        }
    }

    /**
     * 初始化头部 右边有图片
     *
     * @param title
     * @param showLeft
     * @param leftId
     * @param rightId  右边图片id
     */
    @Override
    public void initTitle(String title, boolean showLeft, int leftId, int rightId) {
        //标题展示
        if (title_tv != null) {
            title_tv.setText(title);
        }
        //返回按钮展示
        if (toolbar == null)
            return;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setTitle(StringUtils.getNotNullStr(title));
        getSupportActionBar().setDisplayHomeAsUpEnabled(showLeft);// // 给左上角图标的左边加上一个返回的图标 。
        getSupportActionBar().setDisplayShowHomeEnabled(showLeft);//使左上角图标是否显示，如果设成false，则没有程序图标，仅仅就个标题，否则，显示应用程序图标，对应id为Android.R.id.home，对应ActionBar.DISPLAY_SHOW_HOME
        if (showLeft) {
            //需要展示左边图标
            if (leftId != -1) {
                getSupportActionBar().setHomeAsUpIndicator(leftId);
            } else {
                getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_return);
            }
        }
        //需要展示右边图标
        if (rightId != -1) {
            right_iv.setVisibility(View.VISIBLE);
            right_tv.setVisibility(View.GONE);
            right_iv.setImageResource(rightId);
        } else {
            right_iv.setVisibility(View.GONE);
            right_tv.setVisibility(View.GONE);
        }
    }

    @Override
    public void rightImgClick() {

    }

    @Override
    public void rightTextClick() {

    }

    @Override
    public void homeBack() {
        finish();
    }

}
