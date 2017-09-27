package com.applozic.mobicomkit.uiwidgets.conversation;

import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.PopupWindow;

import com.applozic.mobicomkit.uiwidgets.R;
import com.applozic.mobicomkit.uiwidgets.conversation.activity.ConversationActivity;
import com.applozic.mobicomkit.uiwidgets.conversation.activity.MobiComKitActivityInterface;
import com.applozic.mobicomkit.uiwidgets.conversation.activity.MobicomLocationActivity;

import java.util.List;

/**
 * Created by reytum on 19/3/16.
 */
public class MultimediaOptionsGridView {
    public PopupWindow showPopup;
    FragmentActivity context;
    GridView multimediaOptions;
    private Uri capturedImageUri;

    public MultimediaOptionsGridView(FragmentActivity context, GridView multimediaOptions) {
        this.context = context;
        this.multimediaOptions = multimediaOptions;
    }

    public void setMultimediaClickListener(final List<String> keys) {
        capturedImageUri = null;

        multimediaOptions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                executeMethod(keys.get(position));
            }
        });
    }

    public void executeMethod(String key) {
        if (key.equals(context.getResources().getString(R.string.location))) {
            ((MobiComKitActivityInterface) context).processLocation();
        } else if (key.equals(context.getString(R.string.camera))) {
            ((MobiComKitActivityInterface) context).isTakePhoto(true);
            ((MobiComKitActivityInterface) context).processCameraAction();
        } else if (key.equals(context.getString(R.string.file))) {
            ((MobiComKitActivityInterface) context).isAttachment(true);
            ((MobiComKitActivityInterface) context).processAttachment();
        } else if (key.equals(context.getString(R.string.audio))) {
            ((MobiComKitActivityInterface) context).showAudioRecordingDialog();
        } else if (key.equals(context.getString(R.string.video))) {
            ((MobiComKitActivityInterface) context).isTakePhoto(false);
            ((MobiComKitActivityInterface) context).processVideoRecording();
        } else if (key.equals(context.getString(R.string.contact))) {
            ((MobiComKitActivityInterface) context).processContact();
        } else if (key.equals(context.getString(R.string.contact))) {
            new ConversationUIService(context).sendPriceMessage();
        }
        multimediaOptions.setVisibility(View.GONE);
    }
}