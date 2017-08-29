package com.singal.zy.optionitemview.activity.jsbridge;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.JsPromptResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import com.apkfuns.jsbridge.JsBridge;
import com.apkfuns.jsbridge.common.IPromptResult;
import com.apkfuns.jsbridge.common.IWebView;
import com.singal.zy.optionitemview.activity.base.JSBaseActivity;

public class CustomWebViewActivity extends JSBaseActivity {

    private JsBridge mJsBridge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Custom WebView");
        mJsBridge = JsBridge.loadModule();

        WebView.setWebContentsDebuggingEnabled(true);

        final CustomWebView customWebView = new CustomWebView(this);
        setContentView(customWebView);

        customWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mJsBridge.injectJs(customWebView);
            }
        });

        customWebView.setPromptResult(new PromptResultCallback() {
            @Override
            public void onResult(String args, PromptResultImpl promptResult) {
                mJsBridge.callJsPrompt(args,promptResult);
            }
        });

        customWebView.loadUrl("file:///android_asset/sample.html");
    }


    public static class CustomWebView extends FrameLayout implements IWebView{
        private WebView webView;
        private PromptResultCallback callback;

        public CustomWebView(Context context) {
            super(context);
            this.webView = new WebView(context);
            this.webView.getSettings().setJavaScriptEnabled(true);
            addView(this.webView);
            this.webView.setWebChromeClient(new WebChromeClient(){
                @Override
                public boolean onJsPrompt(WebView view, String url,
                                          String message, String defaultValue, JsPromptResult result) {
                    if (callback != null) {
                        callback.onResult(message, new PromptResultImpl(result));
                    }
                    return true;
                }

                @Override
                public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                    Log.d(JsBridge.TAG, consoleMessage.message());
                    return true;
                }
            });
        }

        public void setWebViewClient(WebViewClient webViewClient) {
            this.webView.setWebViewClient(webViewClient);
        }

        @Override
        public void loadUrl(String url) {
            webView.loadUrl(url);
        }

        public void setPromptResult(final PromptResultCallback callback) {
            this.callback = callback;
        }
    }

    public static class PromptResultImpl implements IPromptResult {
        private JsPromptResult jsPromptResult;

        public PromptResultImpl(JsPromptResult jsPromptResult) {
            this.jsPromptResult = jsPromptResult;
        }

        @Override
        public void confirm(String result) {
            this.jsPromptResult.confirm(result);
        }
    }
    public interface PromptResultCallback{
        void onResult(String args,PromptResultImpl promptResult);
    }

    @Override
    protected void onDestroy() {
        mJsBridge.release();
        super.onDestroy();
    }
}
