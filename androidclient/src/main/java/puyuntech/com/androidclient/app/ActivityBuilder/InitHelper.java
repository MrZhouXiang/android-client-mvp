package puyuntech.com.androidclient.app.ActivityBuilder;


import android.support.v7.widget.Toolbar;

/**
 * 作者 Administrator
 * 创建时间 2016/4/21 0021
 * 描述
 * 修改时间 2016/4/21 0021
 * 修改描述
 * 修改者 Administrator
 **/
public interface InitHelper {


    /**
     * 成员变量初始化
     */
    void initData();

    /**
     * 初始化视图成员变量
     */
    void initView();

    /**
     * 设置点击事件
     */
    void setViewClickListener();

    /**
     * 初始化成员变量，从本地数据获取
     * 获取act fragment之间的传递数据
     * 获取本地保存的数据
     */
    void getDataLoc();

    /**
     * 展示标题栏
     */
    void showTitle();


    /**
     * 初始化视图
     * 给视图动态赋值
     * 在getDataLoc()之后
     */
    void showView();

    /**
     * 设置网路请求之后回调
     */
//    void initAfterHttp();

    /**
     * 获取网络数据
     */
    void getDataNet();


}
