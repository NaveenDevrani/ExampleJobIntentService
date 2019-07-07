package com.devcoder.exampleintentservice.jobs;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

public class ExampleJobService extends JobService
{
    private static final String TAG = "ExampleJobService";
    private Boolean isJobCancelled = false;

    @Override
    public boolean onStartJob(final JobParameters jobParameters)
    {
        Log.d( TAG, "onStartJob: " );
        new Thread( new Runnable()
        {
            @Override
            public void run()
            {
                for(int i = 0; i < 10; i++) {
                    Log.d( TAG, "run: " + i );
                    if(isJobCancelled)
                        return;
                    try {
                        Thread.sleep( 1000 );
                    } catch(InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                jobFinished( jobParameters, false );
            }
        } ).start();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters)
    {
        Log.d( TAG, "onStopJob: " );
        Log.d( TAG, "job before completion" );
        isJobCancelled = true;
        return false;
    }
}
