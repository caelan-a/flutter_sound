package com.dooboolab.fluttersound;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.media.AudioManager;

import android.os.Environment;

public class AudioModel {
  final public static String DEFAULT_FILE_LOCATION = Environment.getExternalStorageDirectory().getPath() + "/default.mp4";
  public int subsDurationMillis = 10;
  public long peakLevelUpdateMillis = 800;
  public boolean shouldProcessDbLevel = true;

  private MediaRecorder mediaRecorder;
  private Runnable recorderTicker;
  private Runnable dbLevelTicker;
  private long recordTime = 0;
  public final double micLevelBase = 2700;
  boolean useEarpiece = false;

  private MediaPlayer mediaPlayer;
  private long playTime = 0;

  private AudioManager audioManager;

  public MediaRecorder getMediaRecorder() {
    return mediaRecorder;
  }

  public void setMediaRecorder(MediaRecorder mediaRecorder) {
    this.mediaRecorder = mediaRecorder;
  }

  public Runnable getRecorderTicker() {
    return recorderTicker;
  }

  public void setRecorderTicker(Runnable recorderTicker) {
    this.recorderTicker = recorderTicker;
  }

  public Runnable getDbLevelTicker() {
    return dbLevelTicker;
  }

  public void setDbLevelTicker(Runnable ticker){
    this.dbLevelTicker = ticker;
  }

  public long getRecordTime() {
    return recordTime;
  }

  public void setRecordTime(long recordTime) {
    this.recordTime = recordTime;
  }

  public MediaPlayer getMediaPlayer() {
    return mediaPlayer;
  }

  public void setMediaPlayer(MediaPlayer mediaPlayer, AudioManager audioManager) {
    this.mediaPlayer = mediaPlayer;
    this.audioManager = audioManager; //
    if(audioManager != null) {
      mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC); //
      if(useEarpiece == true) {
        this.audioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
        this.audioManager.setSpeakerphoneOn(false);
      } else {
        this.audioManager.setMode(AudioManager.MODE_NORMAL);
        this.audioManager.setSpeakerphoneOn(true);  
      }
    }
  }

  public void useEarpiece(boolean use) {
    this.useEarpiece = use;
  }

  public long getPlayTime() {
    return playTime;
  }

  public void setPlayTime(long playTime) {
    this.playTime = playTime;
  }
}