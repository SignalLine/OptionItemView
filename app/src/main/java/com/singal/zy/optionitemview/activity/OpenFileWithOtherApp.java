package com.singal.zy.optionitemview.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.singal.zy.optionitemview.R;
import com.singal.zy.optionitemview.util.FileOpenUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class OpenFileWithOtherApp extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_file_with_other_app);

        findViewById(R.id.pdf).setOnClickListener(this);
        findViewById(R.id.ppt).setOnClickListener(this);
        findViewById(R.id.word).setOnClickListener(this);
        findViewById(R.id.text).setOnClickListener(this);

    }

    String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/aaa";
    String pdfName = "android_UI.pdf";
    String pptName = "javascript.ppt";
    String wordName = "android_UI.doc";
    String textName = "avd.txt";

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pdf:

                try{
                    //  为本地pdf文件的路径
                    Intent intent =  FileOpenUtils.getPdfFileIntent(path + "/" + pdfName);
                    startActivity(intent);
                }catch (Exception e){
                    //没有安装第三方的软件会提示
                    Toast toast = Toast.makeText(OpenFileWithOtherApp.this, "没有找到打开该文件的应用程序", Toast.LENGTH_SHORT);
                    toast.show();
                }

                break;
            case R.id.ppt:
                try{
                    Intent intent =  FileOpenUtils.getPPTFileIntent(path + "/" + pptName);
                    startActivity(intent);
                }catch (Exception e){
                    //没有安装第三方的软件会提示
                    Toast toast = Toast.makeText(OpenFileWithOtherApp.this, "没有找到打开该文件的应用程序", Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;
            case R.id.word:
                try{
                    Intent intent =  FileOpenUtils.getWordFileIntent(path + "/" + wordName);
                    startActivity(intent);
                }catch (Exception e){
                    //没有安装第三方的软件会提示
                    Toast toast = Toast.makeText(OpenFileWithOtherApp.this, "没有找到打开该文件的应用程序", Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;
            case R.id.text://打开text
                try{
                    Intent intent =  FileOpenUtils.getTextFileIntent(path + "/" + textName);
                    startActivity(intent);
                }catch (Exception e){
                    //没有安装第三方的软件会提示
                    Toast toast = Toast.makeText(OpenFileWithOtherApp.this, "没有找到打开该文件的应用程序", Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;
            default:
                break;
        }
    }
}
