package puyuntech.com.beihai.http.httpContor.base;

import org.xutils.http.RequestParams;

/**
 * @作者 zx
 * @创建时间 2016-04-19 下午 14:11
 * @描述 登录相关接口
 * @修改时间 2016-04-19 下午 14:11
 * @修改描述
 * @修改者 zx
 **/
public interface LoginHttp {
    /**
     * 缓存登录
     * 离线登录
     *
     * @param account   账号
     * @param password  密码
     * @param afterHttp 回调
     */
    RequestParams loginCath(String account, String password, final HttpAfterExpand afterHttp);

    /**
     * 登录
     *
     * @param account   账号
     * @param password  密码
     * @param afterHttp 回调
     */
    RequestParams login(String account, String password, final HttpAfterExpand afterHttp);
}
