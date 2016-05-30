package puyuntech.com.androidclient.presenter;

import android.content.Context;

import puyuntech.com.androidclient.http.httpContor.HttpManager;
import puyuntech.com.androidclient.http.httpContor.Result;
import puyuntech.com.androidclient.http.httpContor.base.HttpAfterExpand;
import puyuntech.com.androidclient.http.httpContor.base.LoginHttp;

/**
 * @作者 Administrator
 * @创建时间 2016-05-16 下午 13:41
 * @描述 网络请求主导器
 * @修改时间 2016-05-16 下午 13:41
 * @修改描述
 * @修改者 Administrator
 **/
public class HttpPresenter extends BasePresenter {

    public HttpPresenter(Context context) {
        super(context);
    }

    public enum ValueGetType {
        NAME,//获取姓名
        PWD//获取密码
    }

    public enum ShowType {
        TEST,
        PULL_AND_REFRESH
    }

    public void login() {
        try {
            String name = (String) mIUpdateUIListener.getValue(ValueGetType.NAME);//获取姓名
            String pwd = (String) mIUpdateUIListener.getValue(ValueGetType.PWD);//获取密码
            //动态代理获取登录接口
            HttpManager.getInstance().getHttpByMethod(LoginHttp.class).
                    login(name, pwd, new HttpAfterExpand() {
                        @Override
                        public void afferHttp() {
//                                showProgress(false);
                        }

                        @Override
                        public void afterSuccess(Result resultBean) {
                            //todo 登录成功，
                            showShortToast("登录成功");
                        }

                        @Override
                        public void afterFail(Result resultBean) {


                        }

                        @Override
                        public void afterError(Result resultBean) {
                        }
                    });
        } catch (Exception e) {
//                showShortToast("初始化接口失败");
        }
    }
}
