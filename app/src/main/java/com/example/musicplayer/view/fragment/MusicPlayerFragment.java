package com.example.musicplayer.view.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.musicplayer.R;
import com.example.musicplayer.databinding.FragmentMusicPlayerBinding;
import com.example.musicplayer.model.Music;
import com.example.musicplayer.utils.DurationUtils;
import com.example.musicplayer.viewmodel.MusicViewModel;

public class MusicPlayerFragment extends Fragment {

    public static final String ARGS_MUSIC_PLAYED = "argsMusicPlayed";
    private FragmentMusicPlayerBinding mBinding;
    private MusicViewModel mMusicViewModel;
    private Music mMusic;

    public MusicPlayerFragment() {
        // Required empty public constructor
    }

    public static MusicPlayerFragment newInstance(Music music) {
        MusicPlayerFragment fragment = new MusicPlayerFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARGS_MUSIC_PLAYED, music);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMusic = (Music) getArguments().getSerializable(ARGS_MUSIC_PLAYED);

        mMusicViewModel = new ViewModelProvider(this).get(MusicViewModel.class);
        mMusicViewModel.getMusicLiveData().observe(this, new Observer<Music>() {
            @Override
            public void onChanged(Music music) {

            }
        });
        mMusicViewModel.setMusic(mMusic);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_music_player,
                container,
                false);

        mBinding.setMusicViewModel(mMusicViewModel);
        setSeekbar();
        if(mMusicViewModel.isPlayed())
        mBinding.btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.btnPlay.setImageResource(mMusicViewModel.changeModeIcon());
                mMusicViewModel.setPause();
            }
        });
        return mBinding.getRoot();
    }

    private void setSeekbar() {
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mBinding.seekBarMusic.setProgress(mMusicViewModel.getCurrentPosition());
                handler.postDelayed(this, 1000);
                mBinding.txtviewStartSeekbar.setText(
                        DurationUtils.convertToTimerMode(mMusicViewModel.getCurrentPosition()));
            }
        };
        handler.postDelayed(runnable, 1000);

        mBinding.seekBarMusic.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mMusicViewModel.setNewPosition(seekBar.getProgress());
            }
        });
    }
}