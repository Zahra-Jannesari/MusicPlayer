package com.example.musicplayer.view.adapter;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.musicplayer.R;
import com.example.musicplayer.databinding.FragmentMusicListBinding;
import com.example.musicplayer.databinding.MusicListItemBinding;
import com.example.musicplayer.model.Music;
import com.example.musicplayer.viewmodel.MusicViewModel;

import java.util.List;

public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.MusicHolder> {

    private List<Music> mMusics;
    private Context mContext;

    public List<Music> getMusics() {
        return mMusics;
    }

    public void setMusics(List<Music> musics) {
        mMusics = musics;
    }

    public MusicListAdapter(Context context, List<Music> musics) {
        mMusics = musics;
        mContext = context.getApplicationContext();
    }

    @NonNull
    @Override
    public MusicHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MusicHolder(DataBindingUtil.inflate(
                LayoutInflater.from(mContext),
                R.layout.music_list_item,
                parent,
                false));
    }

    @Override
    public void onBindViewHolder(@NonNull MusicHolder holder, int position) {
        holder.bindMusic(mMusics.get(position));
    }

    @Override
    public int getItemCount() {
        return mMusics.size();
    }

    class MusicHolder extends RecyclerView.ViewHolder {

        private MusicListItemBinding mMusicListItemBinding;

        public MusicHolder(MusicListItemBinding musicListItemBinding) {
            super(musicListItemBinding.getRoot());
            mMusicListItemBinding = musicListItemBinding;
            mMusicListItemBinding.setMusicViewModel(new MusicViewModel((Application) mContext));
        }

        public void bindMusic(Music music) {
            mMusicListItemBinding.getMusicViewModel().setMusic(music);
            mMusicListItemBinding.executePendingBindings();

//            Bitmap picture = getMusicPicture(music);
        //    if (picture != null)
//                Glide.with(mContext)
//                        .asBitmap()
//                        .load(picture)
//                        .placeholder(R.mipmap.ic_placeholder)
//                        .into(mMusicListItemBinding.imgviewMusicPic);
        }

        private Bitmap getMusicPicture(Music music) {
            Bitmap bitmap = null;
            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
            try {
//                retriever.setDataSource(music.getPath());
                byte[] embedPic = retriever.getEmbeddedPicture();
                bitmap = BitmapFactory.decodeByteArray(embedPic, 0, embedPic.length);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    retriever.release();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            return bitmap;
//            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
//            retriever.setDataSource(music.getPath());
//            byte[] pic = retriever.getEmbeddedPicture();
//            retriever.release();
//            return pic;
        }
    }
}
