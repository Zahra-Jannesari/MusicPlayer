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
import com.example.musicplayer.databinding.AlbumListItemBinding;
import com.example.musicplayer.databinding.FragmentMusicListBinding;

public class AlbumListFragment extends Fragment {

    private FragmentMusicListBinding mBinding;
    //todo

    public AlbumListFragment() {
        // Required empty public constructor
    }

    public static AlbumListFragment newInstance() {
        AlbumListFragment fragment = new AlbumListFragment();
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

    private void updateUI() {
        //todo
    }

    private class AlbumHolder extends RecyclerView.ViewHolder {

        private AlbumListItemBinding mAlbumListItemBinding;

        public AlbumHolder(AlbumListItemBinding albumListItemBinding) {
            super(albumListItemBinding.getRoot());

            mAlbumListItemBinding = albumListItemBinding;
        }

        public void bindAlbum() {
            //todo
        }
    }

    private class AlbumAdapter extends RecyclerView.Adapter<AlbumHolder> {
        //todo

        @NonNull
        @Override
        public AlbumHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new AlbumHolder(DataBindingUtil.inflate(
                    LayoutInflater.from(getContext()),
                    R.layout.album_list_item,
                    parent,
                    false));
        }

        @Override
        public void onBindViewHolder(@NonNull AlbumHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }
}