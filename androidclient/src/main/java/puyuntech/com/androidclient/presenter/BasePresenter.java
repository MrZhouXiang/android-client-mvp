package puyuntech.com.androidclient.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.widget.Toast;

import com.devspark.appmsg.AppMsg;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import puyuntech.com.androidclient.app.APP;
import puyuntech.com.androidclient.R;

/**
 * @作者 Administrator
 * @创建时间 2016-05-13 上午 11:27
 * @描述 BasePresenter
 * @修改时间 2016-05-13 上午 11:27
 * @修改描述
 * @修改者 Administrator
 **/
public abstract class BasePresenter {
    public IUpdateUIListener mIUpdateUIListener;
    public Context context;

    /**
     * 控件值获取
     */
    public enum RefreshType {
        REFRESH,//刷新
        LOAD_MORE,//加载更多

    }

    public BasePresenter(Context context) {
        this.context = context;
        mIUpdateUIListener = (IUpdateUIListener) context;
    }

    public BasePresenter(Fragment context) {
        this.context = context.getActivity();
        mIUpdateUIListener = (IUpdateUIListener) context;
    }

    public <T> T findViewByIdExt(int id) {
        return (T) ((Activity) context).findViewById(id);
    }


    public APP getApp() {
        return (APP) ((Activity) context).getApplication();
    }

    public void showToast(String message) {
        Toast.makeText(((Activity) context), message, Toast.LENGTH_SHORT).show();
    }

    public void showShortToast(String message) {
        Toast.makeText(((Activity) context), message, Toast.LENGTH_SHORT).show();
    }

    public void showInfo(String message) {
        AppMsg.Style style = new AppMsg.Style(1500, R.color.colorPrimary);
        AppMsg appMsg = AppMsg.makeText(((Activity) context), message, style);
        appMsg.setAnimation(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
        appMsg.setLayoutGravity(Gravity.CENTER);
        appMsg.show();
    }


    public void skipIntent(Class clz, HashMap<String, Object> map,
                           boolean isFinish) {
        Intent intent = new Intent(((Activity) context), clz);
        if (map != null) {
            Iterator it = map.entrySet().iterator();

            while (it.hasNext()) {

                Map.Entry entry = (Map.Entry) it.next();

                String key = (String) entry.getKey();

                Serializable value = (Serializable) entry.getValue();

                intent.putExtra(key, value);
            }
        }
        ((Activity) context).startActivity(intent);
        if (isFinish)
            ((Activity) context).finish();
    }

    public void skipIntent(Class clz, HashMap<String, Object> map, int code) {
        Intent intent = new Intent(context, clz);
        if (map != null) {
            Iterator it = map.entrySet().iterator();

            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                String key = (String) entry.getKey();
                Serializable value = (Serializable) entry.getValue();
                intent.putExtra(key, value);
            }
        }
        ((Activity) context).startActivityForResult(intent, code);
    }

    public void skipIntent(Class clz, int code, boolean isFinish) {
        Intent intent = new Intent(context, clz);
        ((Activity) context).startActivityForResult(intent, code);
        if (isFinish)
            ((Activity) context).finish();
    }

    public void skipIntent(Class clz, boolean isFinish) {
        Intent intent = new Intent(context, clz);
        ((Activity) context).startActivity(intent);
        if (isFinish)
            ((Activity) context).finish();
    }

//    public Object getExtra(String name) {
//        return context.getSerializableExtra(name);
//    }


    protected boolean isHasMore = true;


}
