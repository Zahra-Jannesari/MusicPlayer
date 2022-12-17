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
import com.example.musicplayer.databinding.SingerListItemBinding;

public class SingerListFragment extends Fragment {

    private FragmentMusicListBinding mBinding;

    public SingerListFragment() {
        // Required empty public constructor
    }


    public static SingerListFragment newInstance() {
        SingerListFragment fragment = new SingerListFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    private void updateUI(){
        //todo
    }

    private class SingerHolder extends RecyclerView.ViewHolder{

        SingerListItemBinding mSingerListItemBinding;

        public SingerHolder(SingerListItemBinding singerListItemBinding) {
            super(singerListItemBinding.getRoot());
            
            mSingerListItemBinding = singerListItemBinding;
        }

        public void bindSinger(){
            //todo
        }
    }

    private class SingerAdapter extends RecyclerView.Adapter<SingerHolder>{
        //todo

        @NonNull
        @Override
        public SingerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new SingerHolder(DataBindingUtil.inflate(
                    LayoutInflater.from(getContext()),
                    R.layout.singer_list_item,
                    parent,
                    false));
        }

        @Override
        public void onBindViewHolder(@NonNull SingerHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }
}