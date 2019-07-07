package com.devcoder.exampleintentservice.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.devcoder.exampleintentservice.R;
import com.devcoder.exampleintentservice.jobs.ExampleJobIntentService;
import com.devcoder.exampleintentservice.jobs.ExampleJobService;
import com.devcoder.exampleintentservice.services.ExampleIntentService;

import static com.devcoder.exampleintentservice.jobs.ExampleJobIntentService.MYINTENT;

public class MainActivity extends AppCompatActivity
{

    private static final String TAG = "MainActivity";
    Button btn, btn_stop_jobservice, btn_start_jobservice,btn_intentservice;
    EditText et_text;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        btn = findViewById( R.id.btn );
        btn_stop_jobservice = findViewById( R.id.btn_stop_jobservice );
        btn_start_jobservice = findViewById( R.id.btn_start_jobservice );
        btn_intentservice = findViewById( R.id.btn_intentservice );
        et_text = findViewById( R.id.et_text );


        btn.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String text = et_text.getText().toString();
                if(text != null && text.isEmpty()) {
                    et_text.setError( "Please enter input" );
                } else {
                    Intent intent = new Intent( MainActivity.this, ExampleJobIntentService.class );
                    intent.putExtra( MYINTENT, text );

                    ExampleJobIntentService.enqueuework( MainActivity.this, intent );
                }
            }
        } );
        btn_start_jobservice.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ScheduleJobService();
            }
        } );
        btn_stop_jobservice.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                CancelJobScheduler();
            }
        } );
        btn_intentservice.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String text=et_text.getText().toString();
                Intent intentservice=new Intent( MainActivity.this, ExampleIntentService.class );
                intentservice.putExtra( MYINTENT ,text);

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    startForegroundService( intentservice );
                }
            }
        } );

    }

    public void ScheduleJobService()
    {
        ComponentName componentName = new ComponentName( this, ExampleJobService.class );
        JobInfo jobInfo = new JobInfo.Builder( 123, componentName )
                .setRequiredNetworkType( JobInfo.NETWORK_TYPE_ANY ) // this for any of  network
                .setPersisted( true ) //this for if device is rooted device
                .setPeriodic( 15 * 60 * 1000 ) /// this for to perform task periodically
                .build();
        JobScheduler scheduler = (JobScheduler) getSystemService( JOB_SCHEDULER_SERVICE );
        assert scheduler != null;
        int resultcode = scheduler.schedule( jobInfo );
        if(resultcode == JobScheduler.RESULT_SUCCESS) {
            Log.d( TAG, "successfully job scheduled: " );
        } else {
            Log.d( TAG, "job scheduler failed" );
        }

    }

    private void CancelJobScheduler()
    {
        JobScheduler jobScheduler = (JobScheduler) getSystemService( JOB_SCHEDULER_SERVICE );
        assert jobScheduler != null;
        jobScheduler.cancel( 123 );
        Log.d( TAG, "Cancelled JobScheduler " );
    }

}
