package com.example.musicplayer.viewmodel;

import android.content.Context;
import android.net.Uri;

import androidx.lifecycle.ViewModel;

import com.example.musicplayer.model.Music;
import com.example.musicplayer.repository.MusicRepository;

import java.util.List;

public class MusicListViewModel {

    private MusicRepository mRepository;

    public MusicListViewModel(Context context){
        mRepository = MusicRepository.getInstance(context);
    }
    public List<Music> getMusics(){
        return mRepository.getMusics();
    }
}
