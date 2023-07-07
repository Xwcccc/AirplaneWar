package com.example.aircraftWar.application.music;

import android.media.AudioAttributes;
import android.media.SoundPool;

import java.util.HashMap;
import java.util.Map;

public class SoundManager {
    public static SoundPool soundPool;
    public static Map<Integer,Integer> soundMap = new HashMap<>();

    static{
        //或者用Audio stream
        AudioAttributes audioAttributes = new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build();
        soundPool = new SoundPool.Builder().setAudioAttributes(audioAttributes)
                .setMaxStreams(8).build();
    }
}
