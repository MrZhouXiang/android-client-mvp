package puyuntech.com.androidclient.ui.activity;

import android.view.View;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

import puyuntech.com.androidclient.R;
import puyuntech.com.androidclient.app.ActivityBuilder.Impl.ActivityDirector;


/**
 * 作者 Administrator
 * 创建时间 2016/5/17 0017
 * 描述 头部统一处理
 * 修改时间 2016/5/17 0017
 * 修改描述 头部统一处理
 * 修改者 Administrator
 **/
@ContentView(R.layout.activity_title)
public class TitleActivity extends ActivityDirector {


    @Override
    public Object getValue(Enum type) {
        return null;
    }

    @Override
    public void updateUI(Object params, Enum type) {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void setViewClickListener() {

    }

    @Override
    public void getDataLoc() {

    }

    @Event({R.id.no_bt, R.id.left_bt, R.id.left_change_img_bt, R.id.right_text_bt, R.id.right_img_bt})
    private void ClickEvent(View view) {
        switch (view.getId()) {
            case R.id.no_bt:
//                initTitle("无按钮", false);
                initTitle("无按钮", -1, null, -1);
                break;
            case R.id.left_bt:
//                initTitle("左边按钮", true);
                initTitle("左边按钮", R.mipmap.ic_return, null, -1);

                break;
            case R.id.left_change_img_bt:
//                initTitle("左边按钮替换图片", true, R.mipmap.ic_downarrow);
                initTitle("左边按钮替换图片", R.mipmap.ic_downarrow, null, -1);
//                initTitle("左边按钮替换图片", true, R.mipmap.ic_downarrow);
                break;
            case R.id.right_text_bt:
//                initTitle("右边文字按钮", true, -1, "OK");
                initTitle("右边文字按钮", R.mipmap.ic_downarrow, "OK", -1);
                break;
            case R.id.right_img_bt:
//                initTitle("右边图片按钮", true, -1, R.mipmap.ic_search);
                initTitle("右边图片按钮", R.mipmap.ic_downarrow, null, R.mipmap.ic_search);
                break;
            default:
                break;
        }
    }

    @Override
    public void showTitle() {
        initTitle("头部显示统一处理", -1, null, -1);
//        initTitle("头部显示统一处理", true);
    }

    @Override
    public void showView() {

    }

    @Override
    public void getDataNet() {

    }

    @Override
    public void homeBack() {
    }

    @Override
    public void leftImgClick() {
        showShortToast("你点击了头部左边的按钮");
        finish();
    }

    public void rightImgClick() {
        showShortToast("你点击了头部右边的图片按钮");
    }

    @Override
    public void rightTextClick() {
        showShortToast("你点击了头部右边的文字按钮");
    }
}
