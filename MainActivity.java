package com.jaspal.internetvalidation;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button mInternetButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mInternetButton= (Button) findViewById(R.id.check_internet_button);
        mInternetButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {

        if(isInternetAvailble())
        {
            Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();
        }
        else {

            showErrorDialodg();
        }

    }

    public void showErrorDialodg()
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(false);
        builder.setTitle("Need Internet");
        builder.setMessage("This app need internet so to connect with network please press enable");
        builder.setPositiveButton("enable", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent=new Intent();
                intent.setAction(Settings.ACTION_WIFI_SETTINGS);
                startActivityForResult(intent,20);
            }
        });
        AlertDialog alert=builder.create();
        alert.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==20)
        {
            if(CheckNetwork.isInternetAvailble(MainActivity.this))
            {
                super.onResume();
            }
            else {
                showErrorDialodg();
            }
        }

    }

    public boolean isInternetAvailble()
    {
        ConnectivityManager manager= (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info=manager.getActiveNetworkInfo();
        if(info!=null && info.isConnected())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}
