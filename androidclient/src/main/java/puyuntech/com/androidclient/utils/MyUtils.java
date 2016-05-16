package puyuntech.com.androidclient.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;

import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import puyuntech.com.androidclient.R;

/**
 * 作者：zx
 * 时间：2016-01-22 上午 10:23
 * 描述：
 */
public class MyUtils {
    /**
     * 设置弹出框是否可以消失
     *
     * @param dialog
     * @param flag
     */
    public static void setCanel(DialogInterface dialog, boolean flag) {
        try {
            Field field = dialog.getClass()
                    .getSuperclass().getDeclaredField(
                            "mShowing");
            field.setAccessible(true);
            //   将mShowing变量设为false，表示对话框已关闭
            field.set(dialog, flag);
            dialog.dismiss();

        } catch (Exception e) {

        }
    }

//    private void uploadUserIcon(String path, final String zhenfan) {
//        final String img = zhenfan;
//        // 参数
//        final Map<String, String> params = new HashMap<String, String>();
//        params.put("type", "2");
//        // 文件
//        final Map<String, File> files = new HashMap<String, File>();
//        files.put("file1", new File(path));
//
//        final String url = path;
//
//        final Handler handler = new Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//                show_image(imgmod.getRealpath(), img);
//                super.handleMessage(msg);
//            }
//        };
//        // final String resultTemp;
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    String resultTemp = FileUploadUtils.uploadMultiFile(URLUtils.UPLOAD_PHOTO, params, files);
//                    LogUitl.e("data-->" + resultTemp);
//
//                    final Result resultBean = JsonUtils.readValue(resultTemp, Result.class);
//                    switch (Integer.valueOf(resultBean.getCode())) {
//                        case URLUtils.RESULT_SUCCESS:
//                            Object o = resultBean.getResult();
//                            imgmod = JsonUtils
//                                    .getObjectMapper().convertValue(
//                                            o,
//                                            UploadImgResultMod.class);
//                            //对缓存图片进行删除
//                            File file = new File(url);
//                            if (file.exists()) {
//                                file.delete();
//                            }
//
//
//                            break;
//                        case URLUtils.RESULT_FAILED:
//
//                            //请求数据失败
//                            showToast(resultBean.getReason());
//                            break;
//                        case URLUtils.RESULT_ERROR:
//
//                            //服务器崩溃
//                            showToast(getString(R.string.server_error) + resultBean.getReason());
//                            break;
//                        default:
//                            // 其他情况处理，一般弹出原因
//                            break;
//                    }
//
//
//                } catch (Exception e) {
//                    // bundle.putString("result", "failed");
//                    e.printStackTrace();
//                }
//                Message msg = new Message();
//                // msg.setData(bundle);
//                msg.what = 1;
//                // Object handler;
//                handler.sendMessage(msg);
//            }
//        }).start();
//    }

    public static Drawable getErrorIcon(Context context, int res) {
        //获取到自定义图标
        Drawable errorIcon = context.getResources().getDrawable(res);
        // 设置图片大小
        errorIcon.setBounds(new Rect(0, 0, errorIcon.getIntrinsicWidth(),
                errorIcon.getIntrinsicHeight()));
        return errorIcon;
    }

    // a integer to xx:xx:xx
    public static String secToTime(int time) {
        String timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (time <= 0)
            return "00:00";
        else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
                timeStr = unitFormat(minute) + ":" + unitFormat(second);
            } else {
                hour = minute / 60;
                if (hour > 99)
                    return "99:59:59";
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
            }
        }
        return timeStr;
    }

    public static String unitFormat(int i) {
        String retStr = null;
        if (i >= 0 && i < 10)
            retStr = "0" + Integer.toString(i);
        else
            retStr = "" + i;
        return retStr;
    }

    /**
     * 第一帧显示
     *
     * @param url
     * @param width
     * @param height
     * @return
     */
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public static Bitmap createVideoThumbnail(String url, int width, int height) {
        Bitmap bitmap = null;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        int kind = MediaStore.Video.Thumbnails.MINI_KIND;
        try {
            if (Build.VERSION.SDK_INT >= 14) {
                retriever.setDataSource(url, new HashMap<String, String>());
            } else {
                retriever.setDataSource(url);
            }
            bitmap = retriever.getFrameAtTime();
        } catch (IllegalArgumentException ex) {
            // Assume this is a corrupt video file
        } catch (RuntimeException ex) {
            // Assume this is a corrupt video file.
        } finally {
            try {
                retriever.release();
            } catch (RuntimeException ex) {
                // Ignore failures while cleaning up.
            }
        }
        if (kind == MediaStore.Images.Thumbnails.MICRO_KIND && bitmap != null) {
            bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height,
                    ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        }
        return bitmap;
    }

}
