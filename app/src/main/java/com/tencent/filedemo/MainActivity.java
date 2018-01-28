package com.tencent.filedemo;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.tencent.filedemo.logic.FileUtil;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public static int NUM = 200;
    public static String mAppPath;
    public static String TAG = MainActivity.class.getSimpleName();
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;
        mAppPath = mContext.getExternalFilesDir(Environment.DIRECTORY_DCIM).getPath()+File.separator;
//        Log.v(TAG,mAppPath);
        new Thread(new Runnable() {
            @Override
            public void run() {
                test();
            }
        }).start();

    }

    private void test() {
        String outPut = new String();
        outPut += initFiles(mAppPath);
        outPut += listFiles(mAppPath);
        Log.v(TAG, outPut);
    }

    private String listFiles(String path) {
        long startTime = System.currentTimeMillis();
        File dir = new File(path);
        for(File child : dir.listFiles()){
            String name = child.getName();
            name += ".j";
        }
        long endTime = System.currentTimeMillis();
        return "listFiles:"+(endTime-startTime)+"ms\t\t";
    }

    private String initFiles(String path) {
        File file = new File(path);
        file.mkdirs();
        long startTime = System.currentTimeMillis();
        for(int i=0; i<NUM; i++){
            byte[] data = "task1".getBytes();
            String filePath =path+"task"+i+".txt";
            File tmp = new File(filePath);
            try {
                tmp.createNewFile();
                FileUtil.saveData(tmp,data,true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        long endTime = System.currentTimeMillis();
        return "initFiles:"+(endTime-startTime)+"ms\t\t";
    }

}
