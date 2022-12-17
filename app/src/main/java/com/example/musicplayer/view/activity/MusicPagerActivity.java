package com.example.musicplayer.view.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.example.musicplayer.R;
import com.example.musicplayer.databinding.ActivityMusicPagerBinding;
import com.example.musicplayer.view.adapter.MusicPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MusicPagerActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_PERMISION = 1;
    private ActivityMusicPagerBinding mBinding;

    public static void start(Context context) {
        Intent intent = new Intent(context, MusicPagerActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_music_pager);
        if(checkPermission()){
            //todo:set musics
            initViews();
        }
    }

    private void initViews() {
        MusicPagerAdapter adapter = new MusicPagerAdapter(this);
        mBinding.pager.setAdapter(adapter);

        final String[] tabText = {"singers", "albums", "songs"};

        new TabLayoutMediator(mBinding.tabLayout, mBinding.pager,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        tab.setText(tabText[position]);
                    }
                }).attach();
    }

    public boolean checkPermission(){
        int readExternalPermission = ContextCompat.checkSelfPermission(
                this, Manifest.permission.READ_EXTERNAL_STORAGE);

        if(readExternalPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_CODE_PERMISION);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISION) {
            if (grantResults.length > 0 &&
                    permissions[0].equals(Manifest.permission.READ_EXTERNAL_STORAGE))

                if (grantResults[0] == PackageManager.PERMISSION_DENIED)
                    ActivityCompat.requestPermissions(
                            this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            REQUEST_CODE_PERMISION);
//                    Toast.makeText(
//                            getApplicationContext(), "Please allow storage permission", Toast.LENGTH_LONG);
                else{
//                    //todo:set musics
                    initViews();
                }
        }
    }
}