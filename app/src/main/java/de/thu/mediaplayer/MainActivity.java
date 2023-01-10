package de.thu.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ContentResolver contentResolver = getContentResolver();
        Uri musicUri = MediaStore.Audio.Media.INTERNAL_CONTENT_URI;
        Cursor musicCursor = contentResolver.query(musicUri, null, null, null, null);

        try {

            MediaPlayer mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setDataSource(getApplicationContext(),musicUri);
            mediaPlayer.prepare();
            mediaPlayer.start();

        } catch (IOException e) {
            e.printStackTrace();
        }

        logMusikList();
    }

    private void logMusikList(){
        ContentResolver contentResolver = getContentResolver();
        Uri musicUri = MediaStore.Audio.Media.INTERNAL_CONTENT_URI;
        Cursor musicCursor = contentResolver.query(musicUri, null, null, null, null);

        int titleColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
        int artistColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
        int albumColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.ALBUM);
        int durationColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.DURATION);

        while (musicCursor.moveToNext()){
            String title = musicCursor.getString(titleColumn);
            String artist = musicCursor.getString(artistColumn);
            String album = musicCursor.getString(albumColumn);
            String duration = musicCursor.getString(durationColumn);
            Log.d("MusicRetriever", "Title: " + title + " | Artist: " + artist + " | Album: " + album + " | Duration: " + duration);
        }


    }

}