package com.example.nilesh.music_player;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by Nilesh on 03-01-2017.
 */

public class MyPlayService extends Service  implements MediaPlayer.OnCompletionListener,MediaPlayer.OnPreparedListener,
        MediaPlayer.OnErrorListener,MediaPlayer.OnSeekCompleteListener,MediaPlayer.OnInfoListener,
        MediaPlayer.OnBufferingUpdateListener {
    private MediaPlayer mediaPlayer= new MediaPlayer();
    private String sntAudiolink;

    @Override
    public void onCreate() {
        mediaPlayer.setOnBufferingUpdateListener(this);
        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.setOnErrorListener(this);
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnSeekCompleteListener(this);
        mediaPlayer.setOnInfoListener(this);
        mediaPlayer.reset();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        sntAudiolink=intent.getExtras().getString("sentAudioLink");
        mediaPlayer.reset();
        if (!mediaPlayer.isPlaying()){
            try{
                mediaPlayer.setDataSource();
                mediaPlayer.prepareAsync();

            }catch (IllegalArgumentException | IllegalStateException e){
                e.printStackTrace();
            } catch (IOException ignored){

            }
        }return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer!=null){
            if (mediaPlayer.isPlaying()){
                mediaPlayer.stop();

            }
            mediaPlayer.release();

        }
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {

    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        stopmedia();
        stopSelf();

    }

    @Override
    public boolean onError(MediaPlayer mediaPlayer, int what, int extra) {
        switch (what){
            case MediaPlayer.MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK:
                Toast.makeText(this,"ohno",Toast.LENGTH_LONG);
                break;
            case MediaPlayer.MEDIA_ERROR_SERVER_DIED:
                Toast.makeText(this,"ohno",Toast.LENGTH_LONG);
                break;
            case MediaPlayer.MEDIA_ERROR_UNKNOWN:
                Toast.makeText(this,"ohno",Toast.LENGTH_LONG);
                break;
        }


        return false;
    }

    @Override
    public boolean onInfo(MediaPlayer mediaPlayer, int i, int i1) {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        playmedia();

    }

    @Override
    public void onSeekComplete(MediaPlayer mediaPlayer) {

    }
    public void playmedia(){

        if(!mediaPlayer.isPlaying()){
            mediaPlayer.start();
        }
    }
    public void stopmedia(){

        if(mediaPlayer.isPlaying()){
            mediaPlayer.stop();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
