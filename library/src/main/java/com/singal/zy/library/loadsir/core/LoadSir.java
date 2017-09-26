package com.singal.zy.library.loadsir.core;

import com.singal.zy.library.loadsir.LoadSirUtil;
import com.singal.zy.library.loadsir.callback.Callback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by li on 2017/9/26.
 */

public class LoadSir {


    private static volatile LoadSir loadSir;
    private Builder builder;

    public static LoadSir getDefault() {
        if (loadSir == null) {
            synchronized (LoadSir.class) {
                if (loadSir == null) {
                    loadSir = new LoadSir();
                }
            }
        }
        return loadSir;
    }

    private LoadSir() {
        this.builder = new Builder();
    }

    private void setBuilder(Builder builder) {
        this.builder = builder;
    }

    private LoadSir(Builder builder) {
        this.builder = builder;
    }

    public LoadService register(Object target, Callback.OnReloadListener onReloadListener) {
        return register(target, onReloadListener, null);
    }

    public <T> LoadService register(Object target, Callback.OnReloadListener onReloadListener, Convertor<T>
            convertor) {
        TargetContext targetContext = LoadSirUtil.getTargetContext(target);
        return new LoadService<>(convertor, targetContext, onReloadListener, builder);
    }

    public static Builder beginBuilder() {
        return new Builder();
    }

    public static class Builder {
        private List<Callback> callbacks = new ArrayList<>();
        private Class<? extends Callback> defaultCallback;

        public Builder addCallback(Callback callback) {
            callbacks.add(callback);
            return this;
        }

        public Builder setDefaultCallback(Class<? extends Callback> defaultCallback) {
            this.defaultCallback = defaultCallback;
            return this;
        }

        List<Callback> getCallbacks() {
            return callbacks;
        }

        Class<? extends Callback> getDefaultCallback() {
            return defaultCallback;
        }

        public void commit() {
            getDefault().setBuilder(this);
        }

        public LoadSir build() {
            return new LoadSir(this);
        }

    }

}
