package com.vodyasov.amr;

import android.net.Uri;
import android.text.TextUtils;

public class AudiostreamMetadataManager
{
    private static AudiostreamMetadataManager sInstance;

    private Thread mThread;
    private String mUrlString;
    private boolean mIsRunning = false;
    private OnNewMetadataListener mListener;

    private AudiostreamMetadataManager() {}
    public static AudiostreamMetadataManager getInstance()
    {
        if (sInstance == null)
        {
            sInstance = new AudiostreamMetadataManager();
        }
        return sInstance;
    }

    public AudiostreamMetadataManager setUri(String stringUri)
    {
        mUrlString = stringUri;
        return this;
    }

    public AudiostreamMetadataManager setUri(Uri uri)
    {
        mUrlString = uri.toString();
        return this;
    }

    public AudiostreamMetadataManager setCallbacks(OnNewMetadataListener listener)
    {
        mListener = listener;
        return this;
    }

    public void start()
    {
        if (!TextUtils.isEmpty(mUrlString) && mListener != null)
        {
            //stop previous task if running
            stop();

            mThread = new Thread(new AudiostreamMetadataRetriever(mUrlString, mListener));
            mThread.start();
            mIsRunning = true;
        }
    }

    public void stop()
    {
        if (mIsRunning && mThread != null && !mThread.isInterrupted())
        {
            mThread.interrupt();
            mIsRunning = false;
        }
    }

    public boolean isRunning()
    {
        return mIsRunning;
    }
}
