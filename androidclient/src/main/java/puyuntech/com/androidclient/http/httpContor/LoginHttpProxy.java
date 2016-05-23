package puyuntech.com.androidclient.http.httpContor;

import org.xutils.http.RequestParams;

import puyuntech.com.androidclient.app.APP;
import puyuntech.com.androidclient.http.httpContor.base.HttpAfterExpand;
import puyuntech.com.androidclient.http.httpContor.base.LoginHttp;
import puyuntech.com.androidclient.utils.NetWorkUtils;

/**
 * @作者 zx
 * @创建时间 2016-04-19 下午 14:11
 * @描述 登录相关接口代理
 * @修改时间 2016-04-19 下午 14:11
 * @修改描述
 * @修改者 zx
 **/
@Deprecated
public class LoginHttpProxy extends BaseHttpImpl implements LoginHttp, puyuntech.com.androidclient.http.httpContor.base.HttpProxy {
    LoginHttp m_LoginHttp = null;

    public LoginHttpProxy(LoginHttp loginHttp) {
        this.m_LoginHttp = loginHttp;
    }

    @Override
    public RequestParams loginCath(String account, String password, HttpAfterExpand afterHttp) {
        if (!checkHttp()) {
//            L.e("网络已经断开,请检查网路");
            showToast("网络已经断开,请检查网络");
            return null;
        }
        return this.m_LoginHttp.loginCath(account, password, afterHttp);
    }

    @Override
    public RequestParams login(String account, String password, HttpAfterExpand afterHttp) {
        if (!checkHttp()) {
            showToast("网络已经断开,请检查网络");
            return null;
        }
        return this.m_LoginHttp.login(account, password, afterHttp);

    }

    @Override
    public boolean checkHttp() {
        if (NetWorkUtils.getNetworkState(APP.getInstance()) == NetWorkUtils.NETWORN_NONE) {
            return false;
        } else {
            return true;
        }
    }
}
