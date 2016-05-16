package puyuntech.com.androidclient.presenter;

import android.content.Context;

import org.xutils.common.util.MD5;

/**
 * @作者 Administrator
 * @创建时间 2016-05-16 下午 13:41
 * @描述 主导器
 * @修改时间 2016-05-16 下午 13:41
 * @修改描述
 * @修改者 Administrator
 **/
public class MVPPresenter extends BasePresenter {
    /**
     * 数据获取类型
     */
    public enum ValueGetType {
        NAME,//获取姓名
        PWD//获取密码
    }

    public enum UpdateUIType {
        SHOW_NAME_PWD,//显示用户名密码
    }

    public MVPPresenter(Context context) {
        super(context);
    }

    public void test() {
        // 获取数据,从activity中获取数据，同样也可以从其他地方获取，如网络，数据库
        String name = (String) mIUpdateUIListener.getValue(ValueGetType.NAME);//获取姓名
        String pwd = (String) mIUpdateUIListener.getValue(ValueGetType.PWD);//获取密码
        // 处理数据,这边只是做了一个对密码的加密，更多复杂算法都可以在此处理
        String showTest = "name:" + name + ",\n加密后的password:\n" + md5(pwd);
        // 更新UI
        mIUpdateUIListener.updateUI(showTest, UpdateUIType.SHOW_NAME_PWD);
    }

    /**
     * md5加密
     *
     * @param pwd
     * @return
     */
    private String md5(String pwd) {
        String md5Str = MD5.md5(pwd);
        return md5Str;
    }
}
