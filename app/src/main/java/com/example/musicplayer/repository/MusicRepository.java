package com.example.musicplayer.repository;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;

import com.example.musicplayer.R;
import com.example.musicplayer.model.Music;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MusicRepository {

    public static final String TAG = "musicFileInformation";
    private static MusicRepository sInstance;
    private Context mContext;
    private MediaPlayer mMediaPlayer;
    private MutableLiveData<Music> mMutableLiveData = new MutableLiveData<>();

    private List<Music> mMusicsList = new ArrayList<>();

    public static MusicRepository getInstance(Context context) {
        if (sInstance == null)
            sInstance = new MusicRepository(context);
        return sInstance;
    }

    public MutableLiveData<Music> getMutableLiveData() {
        return mMutableLiveData;
    }

    public MediaPlayer getMediaPlayer() {
        return mMediaPlayer;
    }

    private MusicRepository(Context context) {
        mContext = context.getApplicationContext();
        getMusicsFromStorage();
    }

    public void getMusicsFromStorage() {
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.ALBUM_ID
        };
        Cursor cursor = mContext.getContentResolver().query(
                uri,
                projection,
                null,
                null,
                null);
        if (cursor != null || cursor.getCount() != 0) {
            try {
                cursor.moveToFirst();

                do {
                    Music music = new Music();
                    music.setId(cursor.getLong(cursor.getColumnIndex(projection[0])));
                    music.setMusicName(cursor.getString(cursor.getColumnIndex(projection[1])));
                    music.setAlbum(cursor.getString(cursor.getColumnIndex(projection[2])));
                    music.setSinger(cursor.getString(cursor.getColumnIndex(projection[3])));
                    music.setDuration(cursor.getString(cursor.getColumnIndex(projection[4])));

                    long albumId = cursor.getLong(cursor.getColumnIndex(projection[5]));
                    // loadAlbumArt(music, albumId);
//                    music.setAlbumArt(ContentUris.withAppendedId(
//                            Uri.parse(cursor.getString(cursor.getColumnIndex(projection[6]))), albumId));

                    mMusicsList.add(music);
                } while (cursor.moveToNext());

            } catch (Exception e) {
                //todo
            } finally {
                cursor.close();
            }
        }
    }

    public List<Music> getMusics(){
        return mMusicsList;
    }

    public void loadAlbumArt(Music music, long albumId) {
        Cursor cursor = mContext.getContentResolver().query(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Audio.Albums._ID, MediaStore.Audio.Albums.ALBUM_ART},
                MediaStore.Audio.Albums._ID + "=?",
                new String[]{String.valueOf(albumId)},
                null);

        if (cursor.moveToFirst()) {
            String path = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ART));
            // do whatever you need to do
//            if (cursor.getColumnIndex(MediaStore.Audio.Albums._ID) == albumId)
            music.setAlbumArt(ContentUris.withAppendedId(Uri.parse(path), albumId));
        }
    }

    public void playMusic(Music music) {
        mMutableLiveData.setValue(music);
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
            startPlayMusic(music);
        } else {
            startPlayMusic(music);
        }
    }

    private void startPlayMusic(Music music) {
        Uri contentUri = ContentUris.withAppendedId(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, music.getId());

        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setAudioAttributes(new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .build());
        try {
            mMediaPlayer.setDataSource(mContext, contentUri);
            mMediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mMediaPlayer.start();
        mMutableLiveData.postValue(music);
    }

    public int currentPosition(){
        return getMediaPlayer().getCurrentPosition();
    }

    public void pauseMusic(){
        mMediaPlayer.pause();
    }

    public void startMusic(){
        mMediaPlayer.start();
    }

    public void setNewPosition(int newPosition){
        mMediaPlayer.seekTo(newPosition);
    }
}
