package com.singal.zy.optionitemview.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import java.io.File;

/**
 *
 * 适配7.0，那么只需要这样引用这个库，然后只需要改动一行代码即可完成适配啦
 *
 * 使用步骤 1.AndroidManifest.xml中完成FileProvider的注册，
 * <application>
 *     <provider
 *          android:name="android.support.v4.content.FilePorvider"
 *          android:authorities="${applicationId}.android7.fileprovider"
 *          android:exported="false"
 *          android:grantUriPermissions="true"
 *     >
 *         <meta-data
 *              android:name="android.support.FILE_PROVIDER_PATHS"
 *              android.resource="@xml/file_paths"
 *         />
 *     </provider>
 * </application>
 *
 *android:authorities不要写死，因为该library最终可能会让多个项目引用，
 * 而android:authorities是不可以重复的，如果两个app中定义了相同的，则后者无法安装到手机中
 * （authority conflict）
 *
 * file_paths文件：
 *  <paths xmlns:android="http://schemas.android.com/apk/res/android">
 *      <root-path name="root" path=""/>
 *      <files-path name="files" path=""/>
 *      <cache-path name="cache" path=""/>
 *      <external-path name="external" path=""/>
 *      <external-files-path name="external-file-path" path=""/>
 *      <external-cache-path name="external-cache-path" path=""/>
 *  </paths>
 *
 *
 * 使用：
 *  拍照
 *  public void takePhotoNoCompress(View view){
 *      Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
 *      if(takePictureIntent.resolveActivity(getPackageManager()) != null){
 *          String filename = new SimpleDateFormat("yyyyMMdd-HHmmss",Locale.CHINA)
 *                  .format(new Data()) + ".png";
 *          File file = new File(Environment.getExternalStorageDirectory(),filename);
 *          mCurrentPhotoPath = file.getAbsolutePath();
 *
 *          Uri fileUri = FileProvider7.getUriForFile(this,file);
 *
 *          takePictureIntent.addFlags(FLAG_GRANT_READ_URI_PERMISSION
 *                              | FLAG_GRANT_WRITE_URI_PERMISSION
 *                              | Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
 *          takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,fileUri);
 *          startActivityForResult(takePictureIntent,REQUEST_CODE_TAKE_PHOTO);
 *
 *      }
 *  }
 *
 *  安装apk
 *  FileProvider7.setIntentDataAndType(this,
 *                      intent,
 *                      "application/vnd.android.package-archive",
 *                      file,
 *                      true);
 *
 * Created by li on 2017/8/28.
 */

public class FileProvider7 {

    public static Uri getUriForFile(Context context, File file){
        Uri uri = null;
        if(Build.VERSION.SDK_INT >= 24){
            uri = getUriForFile24(context,file);
        }else {
            uri = Uri.fromFile(file);
        }

        return uri;
    }


    private static Uri getUriForFile24(Context context, File file) {
        Uri fileUri = android.support.v4.content.FileProvider.getUriForFile(context,
                context.getPackageName() + ".android7.fileprovider",file);

        return fileUri;
    }

    public static void setIntentDataAndType(Context context,
                                            Intent intent,
                                            String type,
                                            File file,
                                            boolean writeAble){
        if(Build.VERSION.SDK_INT >= 24){
            intent.setDataAndType(getUriForFile(context,file),type);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            if(writeAble){
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            }
        }else {
            intent.setDataAndType(Uri.fromFile(file),type);
        }

    }

}
