package com.singal.zy.library.loadsir.core;

import com.singal.zy.library.loadsir.callback.Callback;

/**
 * Created by li on 2017/9/26.
 */

public interface Convertor<T> {

    Class<? extends Callback> map (T t);

}
