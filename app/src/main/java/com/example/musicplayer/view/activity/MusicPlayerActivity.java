package com.example.musicplayer.view.activity;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.example.musicplayer.model.Music;
import com.example.musicplayer.view.fragment.MusicPlayerFragment;

public class MusicPlayerActivity extends SingleFragmentActivity {

    public static final String EXTRA_PLAYED_MUSIC = "com.example.musicplayer.playedMusic";

    public static void start(Context context, Music music) {
        Intent starter = new Intent(context, MusicPlayerActivity.class);
        starter.putExtra(EXTRA_PLAYED_MUSIC, music);
        starter.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(starter);
    }

    @Override
    public Fragment createFragment() {
        return MusicPlayerFragment.newInstance(
                (Music) getIntent().getSerializableExtra(EXTRA_PLAYED_MUSIC));
    }
}