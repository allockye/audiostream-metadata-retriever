package com.vodyasov.amr.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.os.Messenger;
import android.util.Log;

import com.vodyasov.amr.OnNewMetadataListener;
import com.vodyasov.amr.service.util.IPlayer;
import com.vodyasov.amr.service.util.MusicHandler;

import java.util.List;

public class MusicService extends Service implements IPlayer, MediaPlayer.OnPreparedListener, OnNewMetadataListener
{
    public static final String LOG_TAG = MusicService.class.getName();

    private Context mContext;
    private Messenger mMessenger;

    private MediaPlayer mPlayer;
    private Uri mUri;
    private boolean mIsPlaying = false;


    @Override
    public void onCreate()
    {
        super.onCreate();
        mContext = getApplication().getApplicationContext();
        mMessenger = new Messenger(new MusicHandler(this));

        mPlayer = new MediaPlayer();
        mPlayer.setOnPreparedListener(this);
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return mMessenger.getBinder();
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }

    @Override
    public void play(String stringUri)
    {

    }

    @Override
    public void play(Uri uri)
    {

    }

    @Override
    public void play()
    {

    }

    @Override
    public void stop()
    {

    }

    @Override
    public void onPrepared(MediaPlayer mp)
    {

    }

    @Override
    public void onNewHeaders(String stringUri, List<String> name, List<String> desc, List<String> br, List<String> genre, List<String> info)
    {

    }

    @Override
    public void onNewStreamTitle(String stringUri, String streamTitle)
    {
        Log.v(LOG_TAG, String.format("Uri: %1$s # streamTitle: %2$s", stringUri, streamTitle));
    }
}
