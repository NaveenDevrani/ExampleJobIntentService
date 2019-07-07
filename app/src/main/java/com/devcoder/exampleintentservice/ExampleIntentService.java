package com.devcoder.exampleintentservice;

import android.app.IntentService;
import android.app.Notification;
import android.app.job.JobInfo;
import android.content.Intent;
import android.os.Build;
import android.os.PowerManager;
import android.os.SystemClock;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import static com.devcoder.exampleintentservice.App.CHANNEL_ID;
import static com.devcoder.exampleintentservice.ExampleJobIntentService.MYINTENT;

public class ExampleIntentService extends IntentService
{
    private static final String TAG="ExampleIntentService";
    private PowerManager.WakeLock wakeLock;
    public ExampleIntentService()
    {
        super( "ExampleIntentService" );
        setIntentRedelivery( false ); //false -> for Non-sticky and true -> for Sticky service

    }

    @Override
    public void onCreate()
    {
        Log.d( TAG, "onCreate" );
        super.onCreate();
        PowerManager powerManager =(PowerManager)getSystemService( POWER_SERVICE );
        assert powerManager != null;
        wakeLock=powerManager.newWakeLock( PowerManager.PARTIAL_WAKE_LOCK,"ExampleApp: WalkLock" );
        wakeLock.acquire();
        Log.d( TAG, "Walk lock Acquired" );

        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O)
        {
            Notification notification=new NotificationCompat.Builder( this,CHANNEL_ID)
                    .setContentTitle( "Example intent service" )
                    .setContentText( "Running..." )
                    .setSmallIcon( R.mipmap.ic_launcher )
                    .build();
            startForeground( 1,notification );
        }
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {
        Log.d( TAG, "onHandleIntent" );
        String Sinten = intent.getStringExtra( MYINTENT );
        for(int i = 0; i <= 10; i++) {
            Log.d( TAG, " "+Sinten+ ": "+i );
            SystemClock.sleep( 1000 );
        }
    }

    @Override
    public void onDestroy()
    {
        Log.d( TAG, "onDestroy" );
        super.onDestroy();
        wakeLock.release();
        Log.d( TAG, "walk lock released" );
    }
}
