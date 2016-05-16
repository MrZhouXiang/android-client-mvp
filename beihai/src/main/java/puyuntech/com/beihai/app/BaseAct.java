package puyuntech.com.beihai.app;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.devspark.appmsg.AppMsg;
import com.nicodelee.utils.StringUtils;
import com.nicodelee.view.LoadingDialog;

import org.xutils.x;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import de.greenrobot.event.EventBus;
import puyuntech.com.beihai.R;
import puyuntech.com.beihai.utils.MyUtils;

public abstract class BaseAct extends AppCompatActivity {
    public static final int OK_CODE = 0x99;//下一级页面操作完成，当前页面需要处理code
    public LoadingDialog loadingDialog;
    public Intent intent;
    private static BaseAct Cot;
    public LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        Cot = this;
        inflater = LayoutInflater.from(this);
        intent = getIntent();
        loadingDialog = new LoadingDialog(this);
        loadingDialog.setMessage(R.string.loading);//设置loading显示文字
    }



    @Override
    public void finish() {
//        AppManager.getAppManager().finishActivity(this);
        super.finish();
    }

    //通知上级页面关闭
    public void finishOK() {
        //通知上级页面关闭
        Intent data = new Intent();
        setResult(RESULT_OK, data);
        finish();
    }

    public <T> T findViewByIdExt(int id) {
        return (T) super.findViewById(id);
    }

//    @Override
//    protected void attachBaseContext(Context newBase) {
//        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
//    }

    public APP getApp() {
        return (APP) getApplication();
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void showShortToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void showInfo(String message) {
        AppMsg.Style style = new AppMsg.Style(1500, R.color.colorPrimary);
        AppMsg appMsg = AppMsg.makeText(this, message, style);
        appMsg.setAnimation(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
        appMsg.setLayoutGravity(Gravity.CENTER);
        appMsg.show();
    }


    public void skipIntent(Class clz, HashMap<String, Object> map,
                           boolean isFinish) {
        Intent intent = new Intent(this, clz);
        if (map != null) {
            Iterator it = map.entrySet().iterator();

            while (it.hasNext()) {

                Map.Entry entry = (Map.Entry) it.next();

                String key = (String) entry.getKey();

                Serializable value = (Serializable) entry.getValue();

                intent.putExtra(key, value);
            }
        }
        startActivity(intent);
        if (isFinish)
            finish();
    }

    public void skipIntent(Class clz, HashMap<String, Object> map, int code) {
        Intent intent = new Intent(this, clz);
        if (map != null) {
            Iterator it = map.entrySet().iterator();

            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                String key = (String) entry.getKey();
                Serializable value = (Serializable) entry.getValue();
                intent.putExtra(key, value);
            }
        }
        startActivityForResult(intent, code);
    }

    public void skipIntent(Class clz, int code, boolean isFinish) {
        Intent intent = new Intent(this, clz);
        startActivityForResult(intent, code);
        if (isFinish)
            finish();
    }

    public void skipIntent(Class clz, boolean isFinish) {
        Intent intent = new Intent(this, clz);
        startActivity(intent);
        if (isFinish)
            finish();
    }

    public Object getExtra(String name) {
        return getIntent().getSerializableExtra(name);
    }

    @Override
    protected void onStart() {
        EventBus.getDefault().registerSticky(this);
        super.onStart();
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    public void onEvent(int event) {
    }

    public void onEvent() {
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
//        WindowManager.LayoutParams winParams = win.getAttributes();
//        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
//        if (on) {
//            winParams.flags |= bits;
//        } else {
//            winParams.flags &= ~bits;
//        }
//        win.setAttributes(winParams);
        win.setFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // Translucent navigation bar
        win.setFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        Window window = getWindow();
//        //透明状态栏
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        //透明导航栏
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    }

    public interface AfterInsert {
        void after(String text);
    }

    public interface AfterSelect {
        void after(int position, String text);
    }

    public interface AfterCheck {
        void after(boolean[] list2);
    }

    public interface AfterTip {
        void afterOK();

        void afterNotOK();
    }

    /**
     * 显示弹出输入框
     *
     * @param hint      提示
     * @param inputType 输入类型
     * @param checktype 检查类型
     * @param after     完成处理
     */
    public void showEdDialog(String hint, int inputType, final int checktype, final AfterInsert after) {
        showEdDialog(hint, null, inputType, checktype, after);
    }

    /**
     * 显示弹出输入框
     *
     * @param hint      提示
     * @param inputType 输入类型
     * @param checktype 检查类型
     * @param after     完成处理
     */
    public void showEdDialog(String hint, String text, int inputType, int checktype, final AfterInsert after) {
//        showEdDialog(hint, text, inputType, checktype, -1, after);
    }

    /**
     * 显示弹出输入框
     *
     * @param hint
     * @param text
     * @param inputType 类型
     * @param checkType 内容check类型
     * @param maxLength 最大输入长度
     * @param after
     */
//    public void showEdDialog(final String hint, String text, int inputType, final int checkType, final int maxLength, final AfterInsert after) {
//        View view = inflater.inflate(R.layout.dialog_edit_ren_kou, null);
//        final EditText text_et = (EditText) view.findViewById(R.id.text_et);
//        final TextInputLayout text_il = (TextInputLayout) view.findViewById(R.id.text_il);
//        String hintMsgAfter = maxLength > 0 ? "(" + 0 + "/" + maxLength + ")" : "";
//        text_il.setHint(hint + hintMsgAfter);//提示信息
//        //设置EditText的显示方式为多行文本输入
//        text_et.setInputType(inputType | InputType.TYPE_TEXT_FLAG_MULTI_LINE);
//        //文本显示的位置在EditText的最上方
//        text_et.setGravity(Gravity.TOP);
//        //改变默认的单行模式
//        text_et.setSingleLine(false);
//        //水平滚动设置为False
//        text_et.setHorizontallyScrolling(false);
//        //设置最大显示高度
//        text_et.setMaxHeight(getResources().getDimensionPixelOffset(R.dimen.dp_250));
//        //设置text变化事件
//        if (maxLength > 0) {
//            text_et.addTextChangedListener(new TextWatcher() {
//                @Override
//                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//                }
//
//                @Override
//                public void onTextChanged(CharSequence s, int start, int before, int count) {
//                    //显示已输入字的个数
//                    String hintMsgAfter = maxLength > 0 ? "(" + s.length() + "/" + maxLength + ")" : "";
//                    text_il.setHint(hint + hintMsgAfter);//提示信息
//                }
//
//                @Override
//                public void afterTextChanged(Editable s) {
//
//                }
//            });
//        }
//
//        if (!StringUtils.isEmpty(text)) {
//            text_et.setText(text);
//            text_et.setSelection(text.length());
//        }
//
//        showSoftInput(text_et);//弹出软键盘
//        new AlertDialog.Builder(this)
////                .setTitle("人口登记")
////                .setMessage("人口登记")
//                .setView(view).setNegativeButton("取消", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                MyUtils.setCanel(dialog, true);//弹出框可以消失
//                dialog.dismiss();
//            }
//        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                String str = text_et.getText().toString();
//                boolean flag = true;
//                String errorMsg = getString(R.string.null_inster);
//                switch (checkType) {
//                    case 0://// TODO: 2016/1/22 0022 检验是否是电话
//                        if (StringUtils.isTel(str)) {
//                            flag = true;
//                        } else {
//                            flag = false;
//                        }
//                        break;
//                    case 1://// TODO: 2016/1/22 0022 检验是否是名族
//                        if (StringUtils.isMingZu(str)) {
//                            flag = true;
//                        } else {
//                            flag = false;
//                        }
//                        break;
//                    default:// TODO: 2016/1/22 0022 检验是否为空
//                        if (StringUtils.isEmpty(str)) {
//                            flag = true;
//                        } else {
//                            flag = false;
//                        }
//                        break;
//                }
//
//                if (flag) {
//                    MyUtils.setCanel(dialog, false);//弹出框不能消失
//                    text_et.setError(errorMsg);
//                    text_et.requestFocus();
//                } else {
//                    if (maxLength > 0 && str.length() > maxLength) {
//                        // TODO: 2016/2/18 0018 判断文字长度
//                        errorMsg = "文字长度不能大于" + maxLength + "位";
//                        MyUtils.setCanel(dialog, false);//弹出框不能消失
//                        text_et.setError(errorMsg);
//                        text_et.requestFocus();
//
//                    } else {
//                        MyUtils.setCanel(dialog, true);//弹出框可以消失
//                        after.after(str);
//                    }
//
//                }
//
//            }
//        }).create().show();
//
//    }

    /**
     * 显示弹出选择
     *
     * @param title
     * @param after
     */
    public void showSLDialog(String title, final String[] list, final AfterSelect after) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setItems(list, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        after.after(which, list[which]);
                    }
                }).create().show();

    }

    /**
     * 显示弹出多选选择
     *
     * @param title
     * @param after
     */
    public void showCHDialog(String title, final String[] list, final boolean[] list2, final AfterCheck after) {
        final List<Integer> positons = new ArrayList<>();
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMultiChoiceItems(list, list2, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        list2[which] = isChecked;

                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean flag = false;//是否有选中
                for (int i = 0; i < list2.length; i++) {
                    if (list2[i]) {
                        //如果有选中
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    MyUtils.setCanel(dialog, true);//弹出框可以消失
                    after.after(list2);
                } else {
                    MyUtils.setCanel(dialog, false);//弹出框不能消失
                    showToast("请选择至少一项");
                }
            }
        }).setNegativeButton("取消", null).create().show();

    }

    /**
     * 显示弹出多选选择
     *
     * @param title
     * @param after
     */
    public void showTipDialog(String title, String msg, final AfterTip after) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        after.afterOK();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                after.afterNotOK();
            }
        }).create().show();

    }

    /**
     * 弹出软键盘
     *
     * @param editText
     */
    public void showSoftInput(final View editText) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
                           public void run() {
                               InputMethodManager inputManager =
                                       (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                               inputManager.showSoftInput(editText, 0);
                           }
                       },
                338);
    }

    /**
     * 判断是否为空，如果不是空就给该控件设置text
     *
     * @param textView
     * @param text
     */
    public void setTextIfNotEmpty(TextView textView, String text) {
        if (!StringUtils.isEmpty(text)) {
            textView.setText(StringUtils.getNotNullStr(text));//
        }
    }

}
