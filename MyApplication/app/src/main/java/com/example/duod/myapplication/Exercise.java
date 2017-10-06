package com.example.duod.myapplication;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;

public class Exercise extends AppCompatActivity {
    VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        videoView=(VideoView)findViewById(R.id.videoView);
        String videoUrl="https://youtu.be/1919eTCoESo";
        Uri uri=Uri.parse(videoUrl);
        videoView.setMediaController(new MediaController(this));
        videoView.setOnCompletionListener(new PlayerOnCompletionListener());
        videoView.setVideoURI(uri);
        videoView.start();
        videoView.requestFocus();
        
    }
    class PlayerOnCompletionListener implements MediaPlayer.OnCompletionListener{
        public void onCompletion(MediaPlayer mediaPlayer){
            Toast.makeText(Exercise.this,"plays",Toast.LENGTH_SHORT).show();
        }
    }
}
