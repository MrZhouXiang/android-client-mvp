package puyuntech.com.beihai.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;

/**
 * 作者：zx
 * 时间：2016-03-14 下午 15:26
 * 描述：网络状态判断类
 */
public class NetWorkUtils {
    public static final int NETWORN_NONE = 0;
    public static final int NETWORN_WIFI = 1;
    public static final int NETWORN_MOBILE = 2;

    public static int getNetworkState(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        // Wifi
        State state = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                .getState();
        if (state == State.CONNECTED || state == State.CONNECTING) {
            return NETWORN_WIFI;
        }

        // 3G
        state = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                .getState();
        if (state == State.CONNECTED || state == State.CONNECTING) {
            return NETWORN_MOBILE;
        }
        return NETWORN_NONE;
    }

    public static String getNetworkStrByState(int status) {

        // Wifi
        if (status == NETWORN_WIFI) {
            return "Wifi";
        }

        // 3G
        if (status == NETWORN_MOBILE) {
            return "移动网络";
        }
        return "网络已断开";
    }
}

