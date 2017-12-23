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

import static com.chandrakant.abc.crm_app.CallActivity.dname;
import static com.chandrakant.abc.crm_app.CallActivity.du;
import static com.chandrakant.abc.crm_app.CallActivity.number;


/**
 * Created by ABC on 02/08/2017.
 */

public class ListAdapter extends BaseAdapter {
    Context mContext;


    ArrayList<CallData> datas = new ArrayList<>();


    public ListAdapter(Context mContext, ArrayList<CallData> datas) {
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
    public View getView(int position, View view, ViewGroup viewGroup) {
        final TextView number3,callername;
        final TextView calltime;
        final TextView date;

        final ImageView play;
        View v= View.inflate(mContext,R.layout.call_layout,null);

         number3=(TextView)v.findViewById(R.id.number);
        calltime=(TextView)v.findViewById(R.id.calltime);
        callername=(TextView)v.findViewById(R.id.callername);
        date=(TextView)v.findViewById(R.id.date);
        play=(ImageView)v.findViewById(R.id.play);
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
                    String num = mynum(number);
                    mediaPlayer = new MediaPlayer();
                    Uri myUri = Uri.parse(Environment.getExternalStorageDirectory().getAbsolutePath() + "/camera_app/"+dname+"_" + number+"_" +du+ ".mp3");
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
