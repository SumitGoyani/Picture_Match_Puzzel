package com.example.picture_match_puzzel.Activities;



import androidx.appcompat.app.AppCompatActivity;


import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toolbar;
;

import com.example.picture_match_puzzel.Adapters.play_adapter;
import com.example.picture_match_puzzel.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class level_play_activity extends AppCompatActivity {
    int level;
    String status;
    ArrayList<String>imagearr=new ArrayList<>();
    List<String>arraylist=new ArrayList<>();
    ProgressBar progressBar;
    GridView gridView;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
     int numimage,collum,maxtime,delaytime;
     TextView textView;
     Toolbar toolbar;
     Button back,go;
    TextView textView1,textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_play);
        level=getIntent().getIntExtra("level",0);//1
        status=getIntent().getStringExtra("status");//notime
        progressBar=findViewById(R.id.progress);
        gridView=findViewById(R.id.grid_view_play);
        preferences=getSharedPreferences("pre",MODE_PRIVATE);
        editor=preferences.edit();
        toolbar=findViewById(R.id.Toolbar);
        textView=findViewById(R.id.Time_text_view);
        back=findViewById(R.id.back_button);

        editor.putInt("level",level);//1
        editor.commit();
        setActionBar(toolbar);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (status.equals("notime")) {
                    Intent intent = new Intent(level_play_activity.this, No_Time_Activity.class);
                    startActivity(intent);

                }
                if (status.equals("hard")) {
                    Intent intent = new Intent(level_play_activity.this, hard_level_activity.class);

                    startActivity(intent);

                }
                if (status.equals("Normal")) {
                    Intent intent = new Intent(level_play_activity.this, normal_activity.class);

                    startActivity(intent);

                }
                finish();

            }
        });
        int min=0;
        int max=25;
        int rendom=new Random().nextInt(max-min)+min;
        if (status.equals("notime")) {
            if (level <= 3)//1
            {
                numimage = 6;
                maxtime=150;
                delaytime=5;
                collum = 3;
            }
            else if (level > 3 && level <= 6) {
                numimage = 8;
                maxtime=250;
                delaytime=7;
                collum = 4;
            }
            else if (level > 6 && level <= 10) {
                numimage = 10;
                maxtime=350;
                delaytime=9;
                collum = 4;
            }
        }
        if (status.equals("hard")) {
            if (level <= 3)//1
            {
                numimage = 6;
                maxtime=5;
                delaytime=5;
                collum = 3;
            }
            else if (level > 3 && level <= 6) {
                numimage = 8;
                maxtime=10;
                delaytime=7;
                collum = 4;
            }
            else if (level > 6 && level <= 10) {
                numimage = 10;
                maxtime=20;
                delaytime=9;
                collum = 4;
            }
        }
        if (status.equals("normal")) {
            if (level <= 3)//1
            {
                numimage = 6;
                maxtime=10;
                delaytime=5;
                collum = 3;
            }
            else if (level > 3 && level <= 6) {
                numimage = 8;
                maxtime=15;
                delaytime=7;
                collum = 4;
            }
            else if (level > 6 && level <= 10)
            {
                numimage = 10;
                maxtime=25;
                delaytime=9;
                collum = 4;
            }
        }

        String [] images =new String[0];
        try {
            images=getAssets().list("");
            imagearr=new ArrayList<String>(Arrays.asList(images));
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("rando,="+rendom);
        arraylist=imagearr.subList(rendom,numimage+rendom);

        arraylist.addAll(arraylist);
        Collections.shuffle(arraylist);
        Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.time_show_diloge);
        textView1=dialog.findViewById(R.id.time_give_txt);
        textView2=dialog.findViewById(R.id.tips_txt);
        go=dialog.findViewById(R.id.go_button);
        textView1.setText("Time : "+status);
        textView2.setText("You Have "+delaytime+"Second To Memorize "+"\n"+"All Images");
        dialog.show();

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                play_adapter playAdapter=new play_adapter(level_play_activity.this,preferences,arraylist,progressBar,textView,delaytime,maxtime,status);
                gridView.setNumColumns(collum);
                gridView.setAdapter(playAdapter);
                dialog.cancel();
            }
        });











    }

}
