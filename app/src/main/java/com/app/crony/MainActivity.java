package com.app.crony;

import android.app.ProgressDialog;
import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    WebView webview;
    ProgressDialog progressBar;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); //hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        webview = findViewById(R.id.webview);
        progressBar = new ProgressDialog(mContext);
        progressBar.setMessage("Loading...");
        progressBar.setCancelable(true);
        progressBar.show();
        webview.getSettings().setJavaScriptEnabled(true); // enable javascript
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        // webview.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);

        //webview.getSettings().setBuiltInZoomControls(true);
        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setLoadWithOverviewMode(true);
        webSettings.setLoadWithOverviewMode(true);

        webview.loadUrl("http://bdfinancial.services.twsserver.com/front/");
        webview.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                progressBar.show();
                /*if (url.startsWith("tel:")) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
                    startActivity(intent);
                    return true;
                } else if (url != null && url.startsWith("whatsapp://")) {
                    view.getContext().startActivity(
                            new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                    return true;
                } else {*/
                   // view.loadUrl(url);
                    return false;
              //  }

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (progressBar.isShowing()) {
                    progressBar.dismiss();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (webview.canGoBack()) {
            webview.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
