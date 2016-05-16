package puyuntech.com.beihai.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import puyuntech.com.beihai.utils.NetWorkUtils;

import java.util.ArrayList;

/**
 * 作者：zx
 * 时间：2016-03-14 下午 15:27
 * 描述：
 */
public class NetBroadcastReceiver extends BroadcastReceiver {
    public static ArrayList<netEventHandler> mListeners = new ArrayList<netEventHandler>();
    private static String NET_CHANGE_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(NET_CHANGE_ACTION)) {
            APP.mNetWorkState = NetWorkUtils.getNetworkState(context);
            if (mListeners.size() > 0)// 通知接口完成加载
                for (netEventHandler handler : mListeners) {
                    handler.onNetChange();
                }
        }
    }

    public static abstract interface netEventHandler {

        public abstract void onNetChange();
    }
}
