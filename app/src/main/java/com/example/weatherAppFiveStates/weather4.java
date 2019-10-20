package com.example.weatherAppFiveStates;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.util.Calendar.DAY_OF_WEEK;


/**
 * A simple {@link Fragment} subclass.
 */
public class weather4 extends Fragment {


    TextView currentWeatherDay, minTemp, feelsLike, maxTemp, weatherState, currentTemp,
            humidity, predictability, tvMoreDetails,


    ivDayMax, ivDayMin, tvDay, cityname;


    ImageView weatherImage, ivDay;

    String presentDay, currentTime, urlNews;

    ArrayList<ConsolidatedWeather> weathers;
    RecyclerView recyclerView;

    public weather4() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
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


        recyclerView = view.findViewById(R.id.recycleView);

//        tvMoreDetails.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//           System.out.println("more details " + tvMoreDetails);
//
//            }
        // });


        getWeather();
    }

    public void getWeather() {

        weatherPojo weatherpojo = retrofitInstance.getRetrofitInstance().create(weatherPojo.class);
        Call<Montreal> call = weatherpojo.getWeatherNewDelhi();

        call.enqueue(new Callback<Montreal>() {
            @Override
            public void onResponse(Call<Montreal> call, Response<Montreal> response) {
                Montreal montreal = response.body();

                currentWeatherDay.setText(presentDay + " ," + currentTime);
                cityname.setText(montreal.getTitle());
                weatherState.setText(montreal.getConsolidatedWeather().get(0).getWeatherStateName());
                currentTemp.setText(montreal.getConsolidatedWeather().get(0).getTheTemp().toString().substring(0, 2) + "°C");
                maxTemp.setText(montreal.getConsolidatedWeather().get(0).getMaxTemp().toString().substring(0, 2) + "°");
                minTemp.setText(montreal.getConsolidatedWeather().get(0).getMinTemp().toString().substring(0, 2) + "°");
                feelsLike.setText("Feels Like "+montreal.getConsolidatedWeather().get(0).getTheTemp().toString().substring(0, 2) + "°");
                predictability.setText(montreal.getConsolidatedWeather().get(0).getPredictability() + "");
                humidity.setText(montreal.getConsolidatedWeather().get(0).getHumidity() + "");

                Picasso.get().load(new StringBuilder("https://www.metaweather.com/static/img/weather/png/")
                        .append(montreal.getConsolidatedWeather().get(0).getWeatherStateAbbr())
                        .append(".png").toString()).into(weatherImage);

                final String news = montreal.getSources().get(0).getUrl().concat("1261481");
                // System.out.println("news will show here   " + news);


                tvMoreDetails.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        //  view.setTag(news);
                        // Toast.makeText(getContext().getApplicationContext(), news,Toast.LENGTH_LONG).show();

                        NavController navController = Navigation.findNavController(getActivity(), R.id.hostFragment);

                        navController.navigate(R.id.fragmentWebView);

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
