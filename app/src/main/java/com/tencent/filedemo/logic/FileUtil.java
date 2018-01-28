package com.tencent.filedemo.logic;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by chaochen on 2018/1/27.
 */

public class FileUtil {

    /**
      * 存储信息，可以选择是否在文件尾部追加
      * 该方法适合偶尔少量的文件读写，频繁读写的情况下，不应该频繁进行文件流的创建和关闭
      */
    public static boolean saveData(File file, byte[] data, boolean addDataInFileEnd) {
        /** 检查参数 */
        if (file == null || data == null) {
            return false;
        }
        try {
            /** 如果文件不存在，则创建文件 */
            if (!file.exists()) {
                file.createNewFile();
            }

            FileOutputStream outputStream = new FileOutputStream(file, addDataInFileEnd);
            outputStream.write(data);
            outputStream.flush();
            outputStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
