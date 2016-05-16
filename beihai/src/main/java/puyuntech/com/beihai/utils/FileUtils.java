package puyuntech.com.beihai.utils;

import com.nicodelee.utils.L;

import java.io.File;

/**
 * @作者 周翔
 * @创建时间 2016-03-28 上午 10:59
 * @描述 文件操作工具类
 * @修改时间 2016-03-28 上午 10:59
 * @修改描述
 * @修改者 周翔
 **/
public class FileUtils {

    /**
     * 删除文件
     *
     * @param file
     */
    public static boolean deleteFile(File file) {
        boolean flag = false;
        if (file.exists()) { // 判断文件是否存在
            if (file.isFile()) { // 判断是否是文件
                flag = file.delete(); // delete()方法 你应该知道 是删除的意思;
            } else if (file.isDirectory()) { // 否则如果它是一个目录
                File files[] = file.listFiles(); // 声明目录下所有的文件 files[];
                for (int i = 0; i < files.length; i++) { // 遍历目录下所有的文件
                    flag = deleteFile(files[i]); // 把每个文件 用这个方法进行迭代
                    if (!flag) {
                        return flag;
                    }
                }
            }
            file.delete();
        } else {
            L.v("文件不存在！" + "\n");
        }
        return flag;
    }
}
