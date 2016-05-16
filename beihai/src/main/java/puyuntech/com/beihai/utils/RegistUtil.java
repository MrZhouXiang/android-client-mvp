package puyuntech.com.beihai.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2015/12/13 0013.
 */
public class RegistUtil {
    public final static String USERNAME_KEY = "username";// 用户名
    public final static String PHONE_KEY = "phone";// 用户名
    public final static String PASSWORD_KEY = "password";// 密码
    public final static String ISFIRSTOPEN = "ISFIRSTOPEN";// 是否首次打开

    // 定义SharedPreferences对象
    private static SharedPreferences sp;

    public static SharedPreferences getSharedPreferences(Context con) {
        if (RegistUtil.sp == null) {
            //MODE_PRIVATE权限是指只能够本应用所读写
            RegistUtil.sp = con.getSharedPreferences("registpref", Context.MODE_PRIVATE);
        }
        return sp;
    }

    /**
     * 保存用户名密码
     *
     * @see [类、类#方法、类#成员]
     */
    public static void saveRegistPWD(Context con, boolean iSRemPassWord, String userName, String password) {
        //让getsharedPreferences方法处于编辑状态
        SharedPreferences.Editor editor = getSharedPreferences(con).edit();
        // 修改数据
        if (iSRemPassWord) {
            editor.putString(RegistUtil.USERNAME_KEY, userName);
            //   editor.putString(RegistUtil.PHONE_KEY, phone);
            editor.putString(RegistUtil.PASSWORD_KEY, password);
            editor.commit();
        } else {
            editor.putString(RegistUtil.USERNAME_KEY, userName);
            //  editor.putString(RegistUtil.PHONE_KEY, phone);
            editor.putString(RegistUtil.PASSWORD_KEY, "");
            editor.commit();
        }
    }

    /**
     * 保存用户名密码
     *
     * @see [类、类#方法、类#成员]
     */
    public static void removeRegistPWD(Context con) {
        //让getsharedPreferences方法处于编辑状态
        SharedPreferences.Editor editor = getSharedPreferences(con).edit();
        editor.putString(RegistUtil.PASSWORD_KEY, "");
        editor.commit();
    }

    public interface AfterGetUser {
        void after(String name, String pwd);
    }

    /**
     * 获取本地保存的登录信息
     */
    public static void getUserInfoLoca(Context con, AfterGetUser after) {
        SharedPreferences sharedPreferences = RegistUtil.getSharedPreferences(con);
// 使用getString方法获得value，注意第2个参数是value的默认值
        String name = sharedPreferences.getString(RegistUtil.USERNAME_KEY, "");
        String pwd = sharedPreferences.getString(RegistUtil.PASSWORD_KEY, "");
//        if (!StringUtils.isEmpty(name))
        after.after(name, pwd);
    }

}
