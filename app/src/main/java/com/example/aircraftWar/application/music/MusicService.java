package com.example.aircraftWar.application.music;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.example.aircraftWar.Music;

import java.util.Map;

public class MusicService extends Service {

    private static final String TAG = "MusicService";
    private int bgmId;
    private int resId;
    IBinder binder;
    String action;
    private MediaPlayer player;
    private SoundPool soundPool;
    private Map<Integer,Integer> soundMap;

    public MusicService() {
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "==== MusicService onStartCommand ===");
        soundMap = SoundManager.soundMap;
        soundPool = SoundManager.soundPool;
        Bundle bundle = intent.getExtras();
        action = bundle.getString("action");

        if ("play".equals(action)) {
            bgmId = bundle.getInt("bgmId");
            //播放背景音乐
            playMusic();
        } else if ("stop".equals(action)) {
            //结束游戏
            resId = bundle.getInt("resId");
            playSound();
            stopMusic();
        } else if ("pause".equals(action)) {
            //暂停背景音乐
            pauseMusic();
        } else if ("change".equals(action)) {
            //更换背景音乐
            stopMusic();
            bgmId = bundle.getInt("bgmId");
            playMusic();
        } else if ("playsound".equals(action)) {
            resId = bundle.getInt("resId");
            //音效播放
            playSound();
        }
        return super.onStartCommand(intent, flags, startId);
    }
    //播放音效
    public void playSound(){
        Integer soundId = soundMap.get(resId);
        if(soundId != null){
            soundPool.play(soundId,1,1,1,0,1);
        }
    }
    //播放音乐
    public void playMusic(){
        if(player == null){
            player = MediaPlayer.create(this, bgmId);
            player.setLooping(true);
        }
        player.start();
    }
    //暂停播放
    public void pauseMusic(){
        if(player != null && player.isPlaying()){
            player.pause();
        }
    }

    /**
     * 停止播放
     */
    public void stopMusic() {
        if (player != null) {
            player.stop();
            player.reset();//重置
            player.release();//释放
            player = null;
        }
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "==== MusicService onCreate ===");
    }

    @Override
    public IBinder onBind(Intent intent) {
      binder = new Music.Stub() {
          @Override
          public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

          }

          @Override
          public String getAction() throws RemoteException {
              return action;
          }

          @Override
          public void setAction(String maction) throws RemoteException {
              action = maction;
          }

          @Override
          public int getBgmId() throws RemoteException {
              return bgmId;
          }

          @Override
          public void setBgmId(int mbgmId) throws RemoteException {
              bgmId = mbgmId;
          }

          @Override
          public int getResId() throws RemoteException {
              return resId;
          }

          @Override
          public void setResId(int mresId) throws RemoteException {
              resId = mresId;
          }
      };
        return binder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopMusic();
    }
}