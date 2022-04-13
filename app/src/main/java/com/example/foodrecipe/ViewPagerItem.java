package com.example.foodrecipe;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ViewPagerItem extends AppCompatActivity {

    String txt;
    ImageView imageView;

    public ViewPagerItem(String txt,ImageView imageView)
    {
        this.txt = txt;
        this.imageView = imageView;
    }
}
