package com.example.weatherAppFiveStates;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebViewFragment;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class fragmentTest extends Fragment implements  View.OnClickListener {

    TextView tvWebView;


    public fragmentTest() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_test, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvWebView = view.findViewById(R.id.tvWebView);


    }

    private void loadFragment(String url) {
        WebViewFragment webViewFragment = new WebViewFragment();
        Bundle b = new Bundle();
        b.putString("url", url);
        webViewFragment.setArguments(b);
//
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.add(R.id.hostFragment, fragmentWebView.class);
//        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View view) {

    }
}
