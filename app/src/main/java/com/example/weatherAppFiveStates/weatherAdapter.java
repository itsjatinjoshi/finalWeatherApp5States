package com.example.weatherAppFiveStates;


import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
public class weatherAdapter extends RecyclerView.Adapter<weatherAdapter.ViewHolder>{

    private ArrayList<ConsolidatedWeather> wearray;
    private Context mcontext;

    public weatherAdapter(ArrayList<ConsolidatedWeather> wearray, Context mcontext) {
        this.wearray = wearray;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        // Picasso.get().load("https://www.metaweather.com/static/img/weather/png/").into(holder.ivDay);


        setImage(holder.ivDay,(wearray.get(position).getWeatherStateAbbr()));
        // setText(holder.tvDay,wearray.get(position).getApplicableDate());

//        Picasso.get().load(link).into(img);
//        Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(imageView);


        //  Glide.with().load("https://www.metaweather.com/static/img/weather/png/").into(ivDay);
        //    Picasso.get().load(getWeImage()).into(holder.ivDay);

        holder.tvDay.setText(wearray.get(position).getApplicableDate());
        holder.tvDayMax.setText(wearray.get(position).getMaxTemp().toString().substring(0,2));
        holder.tvDayMin.setText(wearray.get(position).getMinTemp().toString().substring(0,3));

    }

    @Override
    public int getItemCount() {

        return wearray.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView ivDay;
        TextView tvDay, tvDayMax, tvDayMin;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivDay = itemView.findViewById(R.id.ivDay);
            tvDay = itemView.findViewById(R.id.tvDay);
            tvDayMax = itemView.findViewById(R.id.tvDayMax);
            tvDayMin = itemView.findViewById(R.id.tvDayMin);

            itemView.setTag(this);

        }
    }




    public void setImage(ImageView img,String code)
    {
        Picasso.get().load("https://www.metaweather.com/static/img/weather/png/"+code+".png").into(img);
    }


}
