package com.singal.zy.optionitemview.activity.jsbridge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.JsPromptResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.apkfuns.jsbridge.JsBridge;
import com.singal.zy.optionitemview.R;
import com.singal.zy.optionitemview.activity.base.JSBaseActivity;

public class WebViewActivity extends JSBaseActivity {

    private JsBridge mJsBridge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("System WebView");
        mJsBridge = JsBridge.loadModule();

        final WebView webView = new WebView(this);
        setContentView(webView);

        webView.getSettings().setJavaScriptEnabled(true);
        WebView.setWebContentsDebuggingEnabled(true);
        webView.loadUrl("file:///android_asset/index.html");
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
                mJsBridge.callJsPrompt(message,result);

                return true;
            }

            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                Log.d(JsBridge.TAG,consoleMessage.message());

                return true;
            }
        });

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.d(JsBridge.TAG,"start load JsBridge");

                mJsBridge.injectJs(view);
            }
        });
    }

    @Override
    protected void onDestroy() {
        mJsBridge.release();
        super.onDestroy();
    }
}
