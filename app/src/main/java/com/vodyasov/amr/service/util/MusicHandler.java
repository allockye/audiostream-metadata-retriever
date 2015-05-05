package com.vodyasov.amr.service.util;


import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

public class MusicHandler extends Handler implements HandlerActions
{
    private WeakReference<IPlayer> ref;

    public MusicHandler(IPlayer player)
    {
        ref = new WeakReference<IPlayer>(player);
    }

    @Override
    public void handleMessage(Message msg)
    {
        IPlayer player = ref.get();
        if (player == null)
        {
            return;
        }
        switch(msg.what)
        {
            case PLAY:
                player.play();
                break;
            case PLAY_STRING_URI:
                break;
            case PLAY_URI:
                break;
            case STOP:
                break;
            default:
                super.handleMessage(msg);
        }
    }
}
