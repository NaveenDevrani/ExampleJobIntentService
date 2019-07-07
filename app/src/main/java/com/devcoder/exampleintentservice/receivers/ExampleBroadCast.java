package com.devcoder.exampleintentservice.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

public class ExampleBroadCast extends BroadcastReceiver
{

    @Override
    public void onReceive(Context context, Intent intent)
    {
        if(Intent.ACTION_BOOT_COMPLETED.equals(intent ))
        {
            Toast.makeText( context, "Boot Completed", Toast.LENGTH_SHORT ).show();
        }
        // this intent work above 23 API level // for  will user dynamic broadcast which from java code on App class
        if(ConnectivityManager.CONNECTIVITY_ACTION.equals( intent ))
        {
            Toast.makeText( context, "Connectivity changed", Toast.LENGTH_SHORT ).show();
        }
        
        
    }
}

//we will learn everything about BroadcastReceivers in Android,
// with which we can listen to different system and application events.
// This way we can execute code when the device boots up, when the internet connectivity of the phone changes, when we receive an SMS and a lot more. We can also send our own broadcasts and schedule work for a time in the future.
//In part 1 we will take a look at static, Manifest registered BroadcastReceivers,
// that will work even if the app itself is not running.
// By giving them an IntentFilter with an appropriate action string,
// we can have them listen to implicit broadcasts, like different system events. When these events happen, the onReceive method of our BroadcastReceiver will be called.
//We will also learn about the background execution changes that have been made on Android Nougat and Oreo, which for example change the way we can listen to the CONNECTIVITY_CHANGE broadcast.
