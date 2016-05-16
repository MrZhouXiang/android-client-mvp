package puyuntech.com.androidclient.app.ActivityBuilder.Impl;

import com.nicodelee.utils.L;

import java.util.ArrayList;

import puyuntech.com.androidclient.app.ActivityBuilder.BuildHelper;
import puyuntech.com.androidclient.app.ActivityBuilder.InitBuilder;
import puyuntech.com.androidclient.http.httpContor.Result;
import puyuntech.com.androidclient.http.httpContor.base.HttpAfterExpand;

/**
 * @作者 Administrator
 * @创建时间 2016-04-25 上午 9:44
 * @描述 初始化实现类【建造者】
 * @修改时间 2016-04-25 上午 9:44
 * @修改描述
 * @修改者 zx
 **/
public class InitBuilderImpl implements InitBuilder {

    /**
     * 用于初始化activity
     */
    @Override
    public void initAct(BuildHelper builder) {
        init(builder);
    }

    /**
     * 用于初始化fragment
     */
    @Override
    public void initFragment(BuildHelper builder) {
        // TODO: 2016/4/25 0025 目前和activity初始化方式一样 ,后续可能会修改
        init(builder);
    }

    /**
     * 初始化
     *
     * @param builder
     */
    private void init(BuildHelper builder) {
//        builder.setPageFlag(0);//分页标示 0：刷新 1：更多
//        builder.setPageMods(new ArrayList());//分页数据
        //初始化afterExpand,防止null point exception
//        builder.setAfterExpand(new HttpAfterExpand() {
//            @Override
//            public void afferHttp() {
//            }
//
//            @Override
//            public void afterSuccess(Result resultBean) {
//                L.e("afterSuccess:请初始化回调函数!");
//            }
//
//            @Override
//            public void afterFail(Result resultBean) {
//                L.e("afterSuccess:请初始化回调函数!");
//            }
//
//            @Override
//            public void afterError(Result resultBean) {
//                L.e("afterSuccess:请初始化回调函数!");
//            }
//        });
        builder.initData();//初始化成员变量
        builder.initView();//初始化视图成员变量[]
        builder.setViewClickListener();//设置点击事件
        builder.getDataLoc();//获取本地数据
        builder.showTitle();//显示标题栏
        builder.showView();//展示view
//        builder.initAfterHttp();//初始化网络请求之后的方法
//        builder.getDataNet(builder.getAfterExpand());//网络获取数据
    }
}
