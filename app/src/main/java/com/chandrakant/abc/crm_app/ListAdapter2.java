package com.chandrakant.abc.crm_app;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import static com.chandrakant.abc.crm_app.CallRecorded.ss;


/**
 * Created by ABC on 02/08/2017.
 */

public class ListAdapter2 extends BaseAdapter {
    Context mContext;


    ArrayList<CallData> datas = new ArrayList<>();


    public ListAdapter2(Context mContext, ArrayList<CallData> datas) {
        this.mContext = mContext;
        this.datas = datas;
    }



    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        final TextView number3,callername;
        final TextView calltime;
        final TextView date;

        final ImageView play;
        View v= View.inflate(mContext,R.layout.calllist_layout,null);

         number3=(TextView)v.findViewById(R.id.number);
        calltime=(TextView)v.findViewById(R.id.calltime);
        callername=(TextView)v.findViewById(R.id.callername);
        date=(TextView)v.findViewById(R.id.date);
        play=(ImageView)v.findViewById(R.id.play1);
         number3.setText(datas.get(position).getNumber());
        calltime.setText(datas.get(position).getCallTime());
        date.setText(datas.get(position).getDate());
        callername.setText(datas.get(position).getName());


        v.setTag(datas.get(position).getId());

        play.setOnClickListener(new View.OnClickListener() {
            boolean pause=true;
            MediaPlayer mediaPlayer;
            @Override
            public void onClick(View view) {
                if(pause) {
                    String od=datas.get(position).getRecording();
                    ss=od.substring(38);
                    mediaPlayer = new MediaPlayer();
                    Uri myUri = Uri.parse(Environment.getExternalStorageDirectory().getAbsolutePath() + "/gosales/"+ss);
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    try {
                        mediaPlayer.setDataSource(view.getContext(), myUri);
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                pause=false;
                    play.setImageResource(android.R.drawable.ic_media_pause);
                }
                else if(mediaPlayer !=null&&mediaPlayer.isPlaying())
                {
                    mediaPlayer. pause();
                    pause=true;
                    play.setImageResource(android.R.drawable.ic_media_play);
                }


            }
        });

        return v;
    }

    static String mynum(String number)
    {
        String nu=number;
        return nu;
    }

}
