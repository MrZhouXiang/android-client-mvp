package puyuntech.com.beihai.http.httpContor;

import android.widget.Toast;

import com.nicodelee.utils.JsonUtils;

import puyuntech.com.beihai.R;
import puyuntech.com.beihai.app.APP;
import puyuntech.com.beihai.http.httpContor.base.BaseHttp;
import puyuntech.com.beihai.http.httpContor.base.HttpAfter;


/**
 * 作者：zx
 * 时间：2015-11-20 上午 10:31
 * 描述：请求处理层BASE
 */
public class BaseHttpImpl implements BaseHttp {
//    public Context mContext;

    public BaseHttpImpl() {
    }

    /**
     * 获取资源文字
     *
     * @param resId
     * @return
     */
    protected String getString(int resId) {
        return APP.getInstance().getResources().getString(resId);
    }


    /**
     * 弹出错误信息
     *
     * @param resultBean
     */
    protected void showServerErrorMsg(Result resultBean) {
        //服务器崩溃
        showToast(getString(R.string.server_error) + resultBean.getReason());
    }

    /**
     * 弹出服务器忙
     */
    protected void showServerBusyMsg() {
        //服务器链接失败
        showToast(getString(R.string.server_busy));
    }

    /**
     * 未知错误码信息
     *
     * @param resultBean
     */
    protected void showOtherErrorMsg(Result resultBean) {
        //错误码未知
        showToast(resultBean.getReason());
    }


    /**
     * @param message
     */
    protected void showToast(String message) {
        Toast.makeText(APP.getInstance(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 成功请求之后处理
     *
     * @param result
     * @param afterHttp
     */
    public void afterSuccess(String result, HttpAfter afterHttp) {
        final Result resultBean = JsonUtils.readValue(result, Result.class);
        String code = resultBean.getCode();
        switch (Integer.valueOf(code)) {
            case URLUtils.RESULT_SUCCESS:
                afterHttp.afterSuccess(resultBean);
                break;
            case URLUtils.RESULT_FAILED:
                //请求数据失败
                afterHttp.afterFail(resultBean);
                break;
            case URLUtils.RESULT_ERROR:
                afterHttp.afterError(resultBean);
                //服务器崩溃
                showServerErrorMsg(resultBean);
                break;
            case URLUtils.RESULT_LOGIN_AGAIN:
                //重复登录
                afterHttp.afterFail(resultBean);
                break;
            default:
                // 其他情况处理，一般弹出原因
                showOtherErrorMsg(resultBean);
                break;
        }
    }

}
