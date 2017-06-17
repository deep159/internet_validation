package com.jaspal.internetvalidation;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by sunil on 3/4/2017.
 */

public class CheckNetwork {
    public static boolean isInternetAvailble(Context context)
    {
        ConnectivityManager manager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
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
