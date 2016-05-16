package puyuntech.com.androidclient.http.httpContor.base;

/**
 * @作者 Administrator
 * @创建时间 2016-04-19 下午 15:21
 * @描述 BaseHttp
 * @修改时间 2016-04-19 下午 15:21
 * @修改描述
 * @修改者 Administrator
 **/
public interface BaseHttp {
    /**
     * 成功请求之后处理
     *
     * @param result
     * @param afterHttp
     */
    void afterSuccess(String result, HttpAfter afterHttp);
}
