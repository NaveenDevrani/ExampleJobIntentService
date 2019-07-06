package com.devcoder.exampleintentservice;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

public class ExampleJobIntentService extends JobIntentService
{
    public static final String MYINTENT = "my intent";
    private static final String TAG = "ExampleJobIntentService";
//  private Boolean isStopJob=false;

    static void enqueuework(Context context, Intent intent)
    {
        enqueueWork( context, ExampleJobIntentService.class, 123, intent );
    }

    @Override
    public void onCreate()
    {
        Log.d( TAG, "onCreate: " );
        super.onCreate();
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent)
    {

        String Sinten = intent.getStringExtra( MYINTENT );
        for(int i = 0; i < 5; i++) {
            if(isStopped())
                return;

            Log.d( TAG, "onHandleWork: " );
            SystemClock.sleep( 1000 );
        }
    }

    @Override
    public boolean onStopCurrentWork()
    {
//        isStopJob=true;
        Log.d( TAG, "onStopCurrentWork: " );

        return super.onStopCurrentWork();
    }

    @Override
    public void onDestroy()
    {
        Log.d( TAG, "onDestroy: " );
        super.onDestroy();
    }
}
