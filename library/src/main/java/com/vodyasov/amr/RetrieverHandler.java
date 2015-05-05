package com.vodyasov.amr;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;
import java.util.List;

class RetrieverHandler extends Handler implements Headers
{
    public static final int ACTION_METADATA = 0;
    public static final int ACTION_HEADERS = 1;

    private WeakReference<AudiostreamMetadataRetriever> ref;

    public RetrieverHandler(AudiostreamMetadataRetriever retriever)
    {
        ref = new WeakReference<AudiostreamMetadataRetriever>(retriever);
    }

    @Override
    public void handleMessage(Message msg)
    {
        super.handleMessage(msg);

        AudiostreamMetadataRetriever retriever = ref.get();
        if (retriever == null)
        {
            return;
        }
        switch (msg.what)
        {
            case(ACTION_METADATA):
            {
                String streamTitle = (String) msg.obj;
                retriever.mOnNewMetadataListener.onNewStreamTitle(retriever.mUrlString, streamTitle);
                break;
            }
            case(ACTION_HEADERS):
            {
                Bundle data = msg.getData();
                List<String> name = data.getStringArrayList(KEY_NAME);
                List<String> desc = data.getStringArrayList(KEY_DESC);
                List<String> br = data.getStringArrayList(KEY_BR);
                List<String> genre = data.getStringArrayList(KEY_GENRE);
                List<String> info = data.getStringArrayList(KEY_INFO);
                retriever.mOnNewMetadataListener.onNewHeaders(retriever.mUrlString, name, desc, br, genre, info);
                break;
            }
        }
    }
}
