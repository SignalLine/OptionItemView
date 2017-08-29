package com.singal.zy.normal_libs.web;

import android.content.Intent;

/**
 *
 */

public interface IFileUploadChooser {



    void openFileChooser();

    void fetchFilePathFromIntent(int requestCode, int resultCode, Intent data);
}
