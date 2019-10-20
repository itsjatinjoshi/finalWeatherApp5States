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


    public  String url;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //  webView = (WebView)view.findViewById(R.id.webview);
        // String url = getIntent().getStringExtra("url");
        // progressDialog = new ProgressDialog(webview.this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        webView = view.findViewById(R.id.webView);




        Bundle bundle = getArguments();
        url = bundle.getString("news");




      //  String url = getIntent().getStringExtra("url");
        progressDialog = new ProgressDialog(getContext().getApplicationContext());

        webView.setWebViewClient(new MywebView());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.loadUrl(url);
       // webView = (WebView) view.findViewById(R.id.webView);
//        try{
//            Bundle bundle = getArguments();
//            url = bundle.getString("news");
//            System.out.println("news here"  + url);
//
//
//        } catch (Exception e) {
//
//            System.out.println("kyu nai chalda" + e.getMessage());
//            // e.printStackTrace();
//        }

    }

    public class MywebView extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);

            progressDialog.setMessage("Page is Loading...Please wait");
            progressDialog.setTitle("Artical Loading !!!");
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

