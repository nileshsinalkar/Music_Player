package com.example.nilesh.music_player;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
        Intent serviceIntent;
    Button stopbutton;
    Button startbutton;
    private boolean boolMusicPlaying=false;
    String strAudioLink="10.mp3";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try{
            serviceIntent = new Intent(MainActivity.this,MyPlayService.class);
            initViews();
            setListeners();


        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),e.getClass().getName(),Toast.LENGTH_SHORT).show();
        }
    }
    private  void initViews(){
         stopbutton=(Button)findViewById(R.id.stopmusic);
        startbutton=(Button)findViewById(R.id.startmusic);

    }
    private void setListeners(){
        startbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!boolMusicPlaying){
                    playaudio();
                    boolMusicPlaying=true;

                }
                else{
                    Toast.makeText(getApplicationContext(),"Music Cannot be played",Toast.LENGTH_LONG).show();
                }
            }
        });
        stopbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (boolMusicPlaying){
                    stopaudio();
                    boolMusicPlaying= false ;

                }
                else{
                    Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void playaudio(){
        serviceIntent.putExtra("sendaudiolink",strAudioLink);
        try{
            startService(serviceIntent);
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),e.getClass().getName()+ "" +e.getMessage(),Toast.LENGTH_LONG).show();
        }

    }
    private void stopaudio(){
        try{
            stopService(serviceIntent);
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),e.getClass().getName()+ "" +e.getMessage(),Toast.LENGTH_LONG).show();
        }
        boolMusicPlaying=false;

    }
}
