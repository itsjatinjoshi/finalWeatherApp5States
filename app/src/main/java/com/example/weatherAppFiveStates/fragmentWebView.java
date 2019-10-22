package com.example.weatherAppFiveStates;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 */
public class fragmentWebView extends Fragment {
    WebView webView;
    ProgressDialog progressDialog;
    String url1;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //  webView = (WebView)view.findViewById(R.id.webview);
        // String url = getIntent().getStringExtra("url");
        // progressDialog = new ProgressDialog(webview.this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_web_view, container, false);

        try {
            url1 = getArguments().getString("news");
        } catch (Exception e) {
            System.out.println("crash" + e.getMessage());

        }


        // System.out.println("uuuuuu" + url1);


        webView = (WebView) rootView.findViewById(R.id.webView);
        //  progressDialog = (ProgressBar)rootView.findViewById(R.id.progressBar);
        progressDialog = new ProgressDialog(getContext());
        //   progressDialog.setVisibility(View.VISIBLE);

        //  webView.setWebViewClient(new MywebView());

        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.setInitialScale(1);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        WebSettings webSettings = webView.getSettings();
        webView.setWebViewClient(new MywebView());
        webView.loadUrl(url1);


        return rootView;


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    public class MywebView extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);

            progressDialog.setMessage("Page is Loading...Please wait");
            progressDialog.setTitle("Please wait..!!!");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.show();
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressDialog.dismiss();
        }
    }
}
