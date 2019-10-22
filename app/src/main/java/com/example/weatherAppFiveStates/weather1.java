package com.example.weatherAppFiveStates;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.icu.text.LocaleDisplayNames;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;

import androidx.swiperefreshlayout.widget.CircularProgressDrawable;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;
import static java.util.Calendar.DAY_OF_WEEK;


/**
 * A simple {@link Fragment} subclass.
 */
public class weather1 extends Fragment{


    TextView currentWeatherDay, minTemp, feelsLike, maxTemp, weatherState, currentTemp,
            humidity, predictability, tvMoreDetails,
            ivDayMax, ivDayMin, tvDay, cityname;

    SwipeRefreshLayout refreshLayout;
    ConstraintLayout weatherPage;
    ImageView weatherImage, ivDay;

    String presentDay, currentTime, urlNews;

    ArrayList<ConsolidatedWeather> weathers;
    RecyclerView recyclerView;

    ProgressDialog progressDialog;


    Handler handler = new Handler();
    int apiDelayed = 5*1000; //1 second=1000 milisecond, 5*1000=5seconds
    Runnable runnable;

    public weather1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading..."); // Setting Message
        progressDialog.setTitle("ProgressDialog"); // Setting Title
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
        progressDialog.show(); // Display Progress Dialog
        progressDialog.setCancelable(false);
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                progressDialog.dismiss();
            }
        }).start();
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//
//                // TODO Auto-generated method stub
       //  progressDialog = new ProgressDialog(getContext());


        Calendar calendar = Calendar.getInstance();
        Date now = new Date();
        calendar.setTime(now);

        calendar.add(DAY_OF_WEEK, 1);

        SimpleDateFormat sdf1 = new SimpleDateFormat("EEEE");
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM-yyyy-dd 'at' HH:mm");
        currentTime = sdf.format(new Date());
        presentDay = sdf1.format(now);
        return inflater.inflate(R.layout.fragment_weather1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        refreshLayout = view.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {


                refreshLayout.setRefreshing(false);
              //  getWeather();
            }
        });


        cityname = view.findViewById(R.id.cityname);
        currentWeatherDay = view.findViewById(R.id.currentWeatherDay);
        minTemp = view.findViewById(R.id.minTemp);
        maxTemp = view.findViewById(R.id.maxTemp);
        weatherState = view.findViewById(R.id.weatherState);
        currentTemp = view.findViewById(R.id.currentTemp);
        humidity = view.findViewById(R.id.humidity);
        predictability = view.findViewById(R.id.predictability);
        feelsLike = view.findViewById(R.id.feelsLike);

        weatherImage = view.findViewById(R.id.weatherImage);
        ivDay = view.findViewById(R.id.ivDay);


        ivDayMax = view.findViewById(R.id.tvDayMax);

        ivDayMin = view.findViewById(R.id.tvDayMin);


        tvDay = view.findViewById(R.id.tvDay);
        tvMoreDetails = view.findViewById(R.id.tvMoreDetail);


        recyclerView = (RecyclerView) view.findViewById(R.id.recycleView);
        weatherPage = (ConstraintLayout) view.findViewById(R.id.weatherPage);
       // mLoginFormView = findViewById(R.id.login_form);


        //progressDialog = new ProgressDialog(getContext());
        getWeather();

    }

    public void getWeather() {

//        refreshLayout.setRefreshing(false);

        weatherPojo weatherpojo = retrofitInstance.getRetrofitInstance().create(weatherPojo.class);
        Call<Montreal> call = weatherpojo.getWeather();

        call.enqueue(new Callback<Montreal>() {
            @Override
            public void onResponse(Call<Montreal> call, Response<Montreal> response) {
                Montreal montreal = response.body();

                currentWeatherDay.setText(presentDay + " ," + currentTime);
                cityname.setText(montreal.getTitle());
                weatherState.setText(montreal.getConsolidatedWeather().get(0).getWeatherStateName());
                currentTemp.setText(montreal.getConsolidatedWeather().get(0).getTheTemp().toString().substring(0, 2) + "°");
                maxTemp.setText(montreal.getConsolidatedWeather().get(0).getMaxTemp().toString().substring(0, 2) + "°");
                minTemp.setText(montreal.getConsolidatedWeather().get(0).getMinTemp().toString().substring(0, 2) + "°");
                feelsLike.setText("Feels Like " + montreal.getConsolidatedWeather().get(0).getTheTemp().toString().substring(0, 2) + "°");
                predictability.setText(montreal.getConsolidatedWeather().get(0).getPredictability() + "");
                humidity.setText(montreal.getConsolidatedWeather().get(0).getHumidity() + "");

                Picasso.get().load(new StringBuilder("https://www.metaweather.com/static/img/weather/png/")
                        .append(montreal.getConsolidatedWeather().get(0).getWeatherStateAbbr())
                        .append(".png").toString()).into(weatherImage);

                final String news = montreal.getSources().get(0).getUrl().concat("6077243");
                // System.out.println("news will show here   " + news);


                tvMoreDetails.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Bundle bundle = new Bundle();
                        bundle.putString("news", news);

                        //Toast.makeText(getContext().getApplicationContext(),"link "+ news, Toast.LENGTH_LONG).show();

                        Navigation.findNavController(view).navigate(R.id.fragmentWebView, bundle);

                    }
                });


                weathers = new ArrayList<>(montreal.getConsolidatedWeather());

                setData(weathers);


            }


            @Override
            public void onFailure(Call<Montreal> call, Throwable t) {
                System.out.println("failure     " + t.getMessage());
            }
        });

    }

    public void setData(ArrayList<ConsolidatedWeather> wearray) {

        initView(wearray);

    }


    public void initView(ArrayList<ConsolidatedWeather> wearray) {
        wearray.remove(0);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(layoutManager);
        weatherAdapter adapter = new weatherAdapter(wearray, getActivity().getApplicationContext());
        recyclerView.setAdapter(adapter);
    }






}

