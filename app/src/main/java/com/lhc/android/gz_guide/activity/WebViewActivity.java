package com.lhc.android.gz_guide.activity;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.lhc.android.gz_guide.R;

public class WebViewActivity extends BaseActivity {

    public static final String CONTENT_LINK = "content_link";
    public static final String TITLE = "title";

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webView = (WebView) findViewById(R.id.wv_detail);
        setUpWebView();

        String link = getIntent().getStringExtra(CONTENT_LINK);
        String title = getIntent().getStringExtra(TITLE);

        if(title != null) {
            getSupportActionBar().setTitle(title);
        }

        webView.loadUrl(link);
    }

    public void setUpWebView(){
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.supportZoom();
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());
    }

    @Override
    public int getTitleRes() {
        return R.string.detail_page;
    }

}
