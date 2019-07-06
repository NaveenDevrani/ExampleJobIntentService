package com.devcoder.exampleintentservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button btn;
    EditText et_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn=findViewById( R.id.btn );
        et_text=findViewById( R.id.et_text );


        btn.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String text=et_text.getText().toString();
                if(text!=null && text.isEmpty())
                {
                    et_text.setError( "Please enter input" );
                }else{
                    Intent intent=new Intent( MainActivity.this,ExampleJobIntentService.class );
                    intent.putExtra( ExampleJobIntentService.MYINTENT,text );

                        ExampleJobIntentService.enqueuework( MainActivity.this,intent );
                }

            }
        } );


    }
}
