package com.example.musicplayer.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.musicplayer.R;
import com.example.musicplayer.databinding.FragmentMusicListBinding;
import com.example.musicplayer.model.Music;
import com.example.musicplayer.view.adapter.MusicListAdapter;
import com.example.musicplayer.view.adapter.MusicPagerAdapter;
import com.example.musicplayer.viewmodel.MusicListViewModel;

import java.util.ArrayList;
import java.util.List;

public class MusicListFragment extends Fragment {

    private FragmentMusicListBinding mBinding;
    private MusicListViewModel mMusicListViewModel;
    private MusicListAdapter mMusicListAdapter;

    public MusicListFragment() {
        // Required empty public constructor
    }

    public static MusicListFragment newInstance() {
        MusicListFragment fragment = new MusicListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMusicListViewModel = new MusicListViewModel(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_music_list,
                container,
                false);

        initViews();
        return mBinding.getRoot();
    }

    private void initViews() {
        mBinding.recyclerViewMusicList.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
    }

    private void updateUI() {
        List<Music> mMusics = mMusicListViewModel.getMusics();
        mMusicListAdapter = new MusicListAdapter(getActivity(), mMusics);
        mBinding.recyclerViewMusicList.setAdapter(mMusicListAdapter);
    }
}