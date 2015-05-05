package com.vodyasov.amr.ui;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vodyasov.amr.R;
import com.vodyasov.amr.service.MusicService;
import com.vodyasov.amr.service.util.BroadcastActions;

public class MusicFragment extends Fragment
{
    public static final String DEFAULT_STREAM_URL = "";

    private Context mContext;
    private Messenger mPlayerMessenger;
    private boolean mIsBound = false;

    private ServiceConnection mConnection = new ServiceConnection()
    {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service)
        {
            mPlayerMessenger = new Messenger(service);
            mIsBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name)
        {
            mIsBound = false;
            mPlayerMessenger = null;
        }
    };

    private BroadcastReceiver mStreamTitleReceiver = new BroadcastReceiver()
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {

        }
    };

    private BroadcastReceiver mHeadersReceiver = new BroadcastReceiver()
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {

        }
    };

    public static MusicFragment newInstance()
    {
        MusicFragment f = new MusicFragment();
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mContext = getActivity().getApplicationContext();
        mContext.bindService(new Intent(mContext, MusicService.class), mConnection, Context.BIND_AUTO_CREATE);
        mContext.registerReceiver(mStreamTitleReceiver, new IntentFilter(BroadcastActions.ACTION_STREAM_TITLE));
        mContext.registerReceiver(mHeadersReceiver, new IntentFilter(BroadcastActions.ACTION_HEADERS));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view  = inflater.inflate(R.layout.fragment_music, container, false);
        return view;
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        mContext.unregisterReceiver(mStreamTitleReceiver);
        mContext.unregisterReceiver(mHeadersReceiver);
        mContext.unbindService(mConnection);
    }

    private void sendMessage(Message msg)
    {
        if (mIsBound && mPlayerMessenger != null)
        {
            try
            {
                mPlayerMessenger.send(msg);
            }
            catch (RemoteException e)
            {
                e.printStackTrace();
            }
        }
    }
}
