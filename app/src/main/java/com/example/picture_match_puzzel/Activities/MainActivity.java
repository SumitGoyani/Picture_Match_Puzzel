package com.example.picture_match_puzzel.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import com.example.picture_match_puzzel.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    Button time,normal,hard,removeads,share,moregame;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        time=findViewById(R.id.no_time);
        normal=findViewById(R.id.normal_button);
        hard=findViewById(R.id.hard_button);
        removeads=findViewById(R.id.remove_add);
        share=findViewById(R.id.share_button);
        moregame=findViewById(R.id.more_game_button);

        //getSupportActionBar().setTitle("Select Mode");
        toolbar=findViewById(R.id.Tool_Bar);
        //setActionBar(toolbar);

        time.setOnClickListener(this);
        normal.setOnClickListener(this);
        hard.setOnClickListener(this);
       removeads.setOnClickListener(this);
        share.setOnClickListener(this);
        moregame.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==time.getId())
        {
            Intent intent =new Intent(MainActivity.this,No_Time_Activity.class);
            intent.putExtra("level","Not Time Limit");
            intent.putExtra("status","notime");
            startActivity(intent);
        }
        if (view.getId()==normal.getId())
        {
            Intent intent =new Intent(MainActivity.this,normal_activity.class);
            intent.putExtra("status","normal");
            startActivity(intent);
        }
        if (view.getId()==hard.getId())
        {
            Intent intent =new Intent(MainActivity.this,hard_level_activity.class);
            intent.putExtra("status","hard");
            startActivity(intent);
        }
        if (view.getId()==removeads.getId())
        {
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("REMOVE ADS");
            builder.setMessage("Are You Want To Remove Add" +"\n"+
                    "Then, Pay 49 INR..");

            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            builder.show();
        }
        if (view.getId()==share.getId())
        {
            Intent intent=new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT,"Share");
            intent.putExtra(Intent.EXTRA_TEXT,"Share with your friend");
            startActivity(Intent.createChooser(intent,"Share"));

        }
        if (view.getId()==moregame.getId())
        {
            AlertDialog.Builder builder =new AlertDialog.Builder(this);
            builder.setTitle("More Game");
            builder.setMessage("You Want More Game Get From Play Store");
            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {
                    dialogInterface.dismiss();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                }
            });
            builder.show();
        }

    }
}