package puyuntech.com.beihai.http.httpContor;

import android.widget.Toast;

import com.nicodelee.utils.L;

import org.xutils.common.util.KeyValue;
import org.xutils.http.RequestParams;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

import puyuntech.com.beihai.app.APP;
import puyuntech.com.beihai.app.AppDataUtils;
import puyuntech.com.beihai.utils.NetWorkUtils;


/**
 * @作者 zx
 * @创建时间 2016-04-19 下午 14:11
 * @描述 Http动态代理
 * @修改时间 2016-04-19 下午 14:11
 * @修改描述
 * @修改者 zx
 **/
public class HttpProxy implements InvocationHandler {
    Class cls = null;

    Object obj = null;

    public HttpProxy(Object _obj) {
        this.obj = _obj;
    }

    public boolean checkHttp() {
        if (NetWorkUtils.getNetworkState(APP.getInstance()) == NetWorkUtils.NETWORN_NONE) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * @param message
     */
    private void showToast(String message) {
        Toast.makeText(APP.getInstance(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (!checkHttp()) {
            //检查网络
            L.e("HttpProxy", "网络断开了");
            showToast("网络已经断开,请检查网络");
            return null;
        }
        //检测网路请求行为:
        beforeHttp(proxy, method, args);
        //反射处理
        Object result = method.invoke(this.obj, args);
        //
        afterHttp(proxy, method, args, result);
        return result;
    }

    private void afterHttp(Object proxy, Method method, Object[] args, Object result) {
        // TODO: 2016/4/26 0026 打印出url
        try {
            if (AppDataUtils.isDeBug) {
                //调试阶段,打印调用接口信息
                RequestParams params = (RequestParams) result;
                List<KeyValue> p = params.getQueryStringParams();
                String url = "" + result;
                for (int i = 0; i < p.size(); i++) {
                    if (i == 0) {
                        url += "?" + p.get(i).key + "=" + p.get(i).getValueStr();
                    } else {
                        url += "&" + p.get(i).key + "=" + p.get(i).getValueStr();
                    }
                }
                L.e("HttpProxy", "发送请求url:" + url);
            }
        } catch (Exception e) {

        }


    }

    /**
     * 发送请求之前
     *
     * @param proxy
     * @param method
     * @param args
     */
    private void beforeHttp(Object proxy, Method method, Object[] args) {
        //todo something you want todo ，like： upload log to server and so on
        if (AppDataUtils.isDeBug) {
            //调试阶段,打印调用接口信息
            printMethodInfo(proxy, method, args);
        }
        //demo 登录时候把账号admin,的密码始终改成3333,也可以做其他操作,比如:加密
//        if (method.getName().equals("login")) {
//            //
//            if (args[0].equals("admin")) {
//                args[1] = "33333";
//            }
//        }
    }

    /**
     * 打印调用接口信息
     *
     * @param method
     * @param args
     */
    private void printMethodInfo(Object proxy, Method method, Object[] args) {
        L.e("HttpProxy", "调用了接口:" + method.getName() + ",传入:");
        for (int i = 0; i < args.length; i++) {
            L.e("HttpProxy", "参数" + (i + 1) + ":" + args[i].toString());
//            L.e("参数" + i + "类型:" + args[i].getClass().getName());
        }
    }
}
