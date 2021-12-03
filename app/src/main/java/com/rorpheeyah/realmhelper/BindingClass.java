package com.rorpheeyah.realmhelper;

import android.text.TextUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.rorpheeyah.realmhelper.helper.TimeAgo;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Calendar;

public class BindingClass {

    @BindingAdapter({"app:setImageUrl"})
    public static void setImageViewResource(@NonNull AppCompatImageView imageView, String imgUrl) {
        Glide.with(imageView.getContext())
                .load(imgUrl)
                .error(ContextCompat.getDrawable(imageView.getContext(), R.drawable.placeholder))
                .into(imageView);
    }

    @BindingAdapter({"app:setImageLoading"})
    public static void setImageLoading(@NonNull AppCompatImageView imageView, String img) {
        Glide.with(imageView.getContext()).load(ContextCompat.getDrawable(imageView.getContext(), R.drawable.loading_64)).into(imageView);
    }

    @BindingAdapter({"app:timeAgo"})
    public static void setTimeAgo(@NonNull TextView tv, long timeStamp) {
        TimeAgo timeAgo = new TimeAgo(tv.getContext());
        tv.setText(timeAgo.getTimeAgo(new Timestamp(timeStamp)));
    }

    @BindingAdapter({"app:age"})
    public static void setAge(@NonNull TextView tv, long timeStamp) {
        tv.setText(String.format(tv.getContext().getString(R.string.year), getAge(timeStamp) + ""));
    }

    public static int getAge(long timeStamp){
        Calendar dob = Calendar.getInstance();
        dob.setTimeInMillis(timeStamp);

        Calendar today = Calendar.getInstance();

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if(today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
            age--;
        }
        return (int) Math.floor(age);
    }

    @BindingAdapter(value = {"app:dob", "app:behavior"})
    public static void setBehavior(@NonNull TextView tv, long timeStamp, String behavior) {
        String b;

        if(timeStamp == 0){
            b = behavior;
            if(TextUtils.isEmpty(behavior)) b = "";
        }else{
            if(TextUtils.isEmpty(behavior)){
                b = String.format(tv.getContext().getString(R.string.year), getAge(timeStamp) + "");
            }else{
                b = String.format(tv.getContext().getString(R.string.year), getAge(timeStamp) + "") + " | " + behavior;
            }
        }

        tv.setText(b);
    }

    @BindingAdapter({"app:distance"})
    public static void setDistance(@NonNull TextView tv, double distance) {
        DecimalFormat format = new DecimalFormat("0.#");
        if(distance >= 1000){
            tv.setText(String.format(tv.getContext().getString(R.string.distance_km),  format.format(Math.floor(distance/1000)) + ""));
        }else{
            tv.setText(String.format(tv.getContext().getString(R.string.distance_m),  format.format(Math.floor(distance)) + ""));
        }
    }

    @BindingAdapter({"app:weight"})
    public static void setWeight(@NonNull TextView tv, double weight) {
        DecimalFormat format = new DecimalFormat("0.#");
        tv.setText(String.format(tv.getContext().getString(R.string.kg),  format.format(Math.floor(weight)) + ""));
    }
}
