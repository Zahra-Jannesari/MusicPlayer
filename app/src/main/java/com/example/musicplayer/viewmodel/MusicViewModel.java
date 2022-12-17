package com.example.musicplayer.viewmodel;

import android.app.Application;
import android.media.MediaPlayer;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.musicplayer.R;
import com.example.musicplayer.model.Music;
import com.example.musicplayer.repository.MusicRepository;
import com.example.musicplayer.utils.DurationUtils;
import com.example.musicplayer.view.activity.MusicPlayerActivity;

import java.util.List;

public class MusicViewModel extends AndroidViewModel {
    private Music mMusic;
    private MusicRepository mRepository;
    private LiveData<Music> mMusicLiveData;
    private int mMusicIndex;
    private boolean isPlayed = true;

    public MusicViewModel(@NonNull Application application) {
        super(application);
        mRepository = MusicRepository.getInstance(getApplication());
        mMusicLiveData = mRepository.getMutableLiveData();

    }

    public LiveData<Music> getMusicLiveData() {
        return mMusicLiveData;
    }

    public List<Music> getMusics() {
        return mRepository.getMusics();
    }

    public boolean isPlayed() {
        return isPlayed;
    }

    public void setPlayed(boolean played) {
        isPlayed = played;
    }

    public Music getMusic() {
        return mMusic;
    }

    public void setMusic(Music music) {
        mMusic = music;
    }

    public String getMusicName() {
        return mMusic.getMusicName();
    }

    public String getAlbum() {
        return mMusic.getAlbum();
    }

    public String getSinger() {
        return mMusic.getSinger();
    }

    public long getMusicId() {
        return mMusic.getId();
    }

    public MediaPlayer getMediaPlayer() {
        return mRepository.getMediaPlayer();
    }

    public void playMusic() {
        for (int i = 0; i < getMusics().size(); i++) {
            if (getMusics().get(i).getId() == mMusic.getId())
                mMusicIndex = i;
        }
        mRepository.playMusic(mMusic);
        MusicPlayerActivity.start(getApplication(), mMusic);

    }

    public String getTimer() {
        return DurationUtils.convertToTimerMode(mMusic.getDuration());
    }

    public int getCurrentPosition() {
        return mRepository.currentPosition();
    }

    public void setNewPosition(int newPosition) {
        mRepository.setNewPosition(newPosition);
    }

    public int changeModeIcon() {
        if (isPlayed)
            return R.drawable.ic_play;
        else
            return R.drawable.ic_pause;
    }

    public void setPause() {
        if (isPlayed) {
            mRepository.pauseMusic();
            isPlayed = false;
        } else {
            mRepository.startMusic();
            isPlayed = true;
        }
    }

    public void next() {
        if (mMusicIndex < (getMusics().size() - 1))
            setMusic(getMusics().get(++mMusicIndex));
        else {
            mMusicIndex = 0;
            setMusic(getMusics().get(mMusicIndex));
        }

        mRepository.playMusic(mMusic);
    }

    public void prev() {
        if (mMusicIndex > 0)
            setMusic(getMusics().get(--mMusicIndex));
        else {
            mMusicIndex = getMusics().size() - 1;
            setMusic(getMusics().get(mMusicIndex));
        }

        mRepository.playMusic(mMusic);
    }
}