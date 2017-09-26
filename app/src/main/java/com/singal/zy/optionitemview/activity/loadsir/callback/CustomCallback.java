package com.singal.zy.optionitemview.activity.loadsir.callback;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.singal.zy.library.loadsir.callback.Callback;
import com.singal.zy.optionitemview.R;

/**
 */
public class CustomCallback extends Callback {

    @Override
    protected int onCreateView() {
        return R.layout.loadsir_layout_custom;
    }

    @Override
    protected boolean onRetry(final Context context, View view) {
        Toast.makeText(context.getApplicationContext(), "Hello  ssss :p", Toast.LENGTH_SHORT).show();
        (view.findViewById(R.id.iv_gift)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context.getApplicationContext(), "It's your sss! :p", Toast.LENGTH_SHORT).show();
            }
        });
        return true;
    }
}
