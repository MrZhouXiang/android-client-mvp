package puyuntech.com.beihai.http.httpContor;

import android.widget.Toast;

import java.lang.reflect.InvocationHandler;

import puyuntech.com.beihai.app.APP;
import puyuntech.com.beihai.http.httpContor.base.HttpFactory;
import puyuntech.com.beihai.http.httpContor.base.LoginHttp;


/**
 * @作者 zx
 * @创建时间 2016-04-11 上午 9:46
 * @描述 网路请求工厂 [工厂模式： 凡是出现了大量的产品需要创建，并且具有共同的接口时]
 * @修改时间 2016-04-11 上午 9:46
 * @修改描述
 * @修改者 zx
 **/
public class HttpManager extends HttpFactory {

    private static HttpFactory m_instance = new HttpManager();//饿汉单例模式

    private HttpManager() {
    }

    public static HttpFactory getInstance() {
        return m_instance;
    }

    /**
     * @param c
     * @param <T>
     * @return
     */
    @Override
    public <T> T getHttpByMethod(Class<T> c) throws NotInterFaceException {
        return getHttpByMethod(c, true);//默认需要代理
    }


    @Override
    public <T> T getHttpByMethod(Class<T> c, boolean needProxy) throws NotInterFaceException {
        T mHttp = null;
        if (!c.isInterface()) {
            Toast.makeText(APP.getInstance(), "请用接口来获取http请求类!", Toast.LENGTH_SHORT).show();
            throw new NotInterFaceException("请用接口来获取http请求类!");
        }
//        if (c.equals(LoginHttp.class)) {
//            mHttp = (T) LoginHttpImpl.getMHttpImpl();//获取原始类
//            //是否需要动态代理
//            if (needProxy)
//                mHttp = (T) getProxy((LoginHttp) mHttp);//获取代理类
//        }
//        if (c.equals(RenKouHttp.class)) {
//            mHttp = (T) RenKouHttpImpl.getMHttpImpl();
//            if (needProxy)
//                mHttp = (T) getProxy((RenKouHttp) mHttp);
//        }
//        if (c.equals(RenWuHttp.class)) {
//            mHttp = (T) RenWuHttpImpl.getMHttpImpl();
//            if (needProxy)
//                mHttp = (T) getProxy((RenWuHttp) mHttp);
//        }
//        if (c.equals(ShiJianHttp.class)) {
//            mHttp = (T) ShiJianHttpImpl.getMHttpImpl();
//            if (needProxy)
//                mHttp = (T) getProxy((ShiJianHttp) mHttp);
//        }
//        if (c.equals(TongXunLuHttp.class)) {
//            mHttp = (T) TongXunLuHttpImpl.getMHttpImpl();
//            if (needProxy)
//                mHttp = (T) getProxy((TongXunLuHttp) mHttp);
//        }
//        if (mHttp == null) {
//            Toast.makeText(APP.getInstance(), "传入的接口类型没有匹配!", Toast.LENGTH_SHORT).show();
//            throw new NullPointerException();
//        }
        return mHttp;
    }


    /**
     * 获取http代理接口
     *
     * @param mHttp
     * @param <T>
     * @return
     */
    private <T> T getProxy(T mHttp) {
        InvocationHandler handler = new HttpProxy(mHttp);
        ClassLoader cl = mHttp.getClass().getClassLoader();
        Class[] classes = mHttp.getClass().getInterfaces();
        return (T) DynamicProxy.newProxyInstance(cl, classes, handler);
    }
}
