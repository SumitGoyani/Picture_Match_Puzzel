package com.example.picture_match_puzzel.Activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.picture_match_puzzel.Adapters.lavel_adapter;
import com.example.picture_match_puzzel.R;

public class No_Time_Activity extends AppCompatActivity {

        GridView gridView;
        Button button,back;
        SharedPreferences preferences;
        SharedPreferences.Editor editor;
        TextView textView;
        Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_time);
        gridView=findViewById(R.id.no_time_grid_view);
        button=findViewById(R.id.warning_button1);
        preferences=getSharedPreferences("pre",MODE_PRIVATE);
        editor=preferences.edit();
        editor.putString("status","notime");

        lavel_adapter lavelAdapter=new lavel_adapter(No_Time_Activity.this,preferences);
        gridView.setAdapter(lavelAdapter);

        String actionBarTitle=getIntent().getStringExtra("level");
        toolbar=findViewById(R.id.Toolbar);
        textView=findViewById(R.id.Time_text_view);
        back=findViewById(R.id.back_button);
        editor.putInt("lastlevel",-1);
        editor.commit();

        textView.setText(actionBarTitle);
        setActionBar(toolbar);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(No_Time_Activity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(No_Time_Activity.this);
                builder.setTitle("How To Play Match 1");
                builder.setMessage("Tap on a square to turn it over and see the"+"\n"+" picture it hides."
                        +"\n \n"+"Tap on another square to turn it over too."
                        +"\n \n"+"if the picture match,they'll stay facing up, if not,"+"\n"+"both will turn over again."
                        +"\n \n"+"Find all couples.");

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.show();
            }
        });

    }
}