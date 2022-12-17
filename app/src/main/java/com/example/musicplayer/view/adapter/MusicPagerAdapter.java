package com.example.musicplayer.view.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.musicplayer.view.fragment.AlbumListFragment;
import com.example.musicplayer.view.fragment.MusicListFragment;
import com.example.musicplayer.view.fragment.SingerListFragment;

import java.util.ArrayList;
import java.util.List;

public class MusicPagerAdapter extends FragmentStateAdapter {

    private List<Fragment> mFragments = new ArrayList<Fragment>(){{
        add(SingerListFragment.newInstance());
        add(AlbumListFragment.newInstance());
        add(MusicListFragment.newInstance());
    }};
    
    public MusicPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getItemCount() {
        return mFragments.size();
    }
}