package com.example.admin.tetris;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button buttonStart;
    private Button drehen;
    private Button rechts;
    private Button unten;
    private Button links;
    private TextView PunkteTextView;
    private TextView highscoreLevelTextView;
    private TextView aktuellerLevelTextView;
    private Tetris tetris;
    private SpielsteinAnzeige spielsteinAnzeige;
    private boolean pause = true;  // Spiel startet mit Button Klick
    private MediaPlayer mediaPlayer;
    private int stopMediaplayer;
    private SpielFeld spielFeld = new SpielFeld();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource( this, Uri.parse("android.resource://com.example.admin.tetris/raw/tetrismusik"));
            mediaPlayer.prepare();
        } catch (Exception e) {e.printStackTrace();}


        buttonStart = (Button) findViewById(R.id.buttonstart);
        drehen = (Button) findViewById(R.id.buttondrehen);
        rechts = (Button) findViewById(R.id.buttonrechts);
        unten = (Button) findViewById(R.id.buttonunten);
        links = (Button) findViewById(R.id.buttonlinks);
        PunkteTextView = (TextView) findViewById(R.id.textViewPunkte);
        highscoreLevelTextView= (TextView) findViewById(R.id.textViewHighscore);
        aktuellerLevelTextView = (TextView) findViewById(R.id.levelText);

        spielsteinAnzeige = new SpielsteinAnzeige(this, spielFeld);
        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(300,300);
        spielsteinAnzeige.setLayoutParams(params1);
        RelativeLayout relativeSteinAnzeige = (RelativeLayout) findViewById(R.id.relativelayout1);
        spielsteinAnzeige.setBackgroundColor(Color.GREEN);
        relativeSteinAnzeige.addView(spielsteinAnzeige);

        tetris = new Tetris(this,spielsteinAnzeige, spielFeld);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(480, 900);
        tetris.setLayoutParams(params);
        RelativeLayout relativeTetris = (RelativeLayout) findViewById(R.id.relativelayout);
        tetris.setBackgroundColor(Color.YELLOW);
        relativeTetris.addView(tetris);
        buttonStart.setOnClickListener(new View.OnClickListener() {

           int tmp=0;
            @Override
            public void onClick(View v) {
                stopMediaplayer = mediaPlayer.getCurrentPosition();
                 tmp++;

                if(buttonStart.getText().equals("Start")) {
                    buttonStart.setText("Pause");
                    pause = false;

                    if(tmp==1) {
                        mediaPlayer.start();
                        mediaPlayer.setLooping(true);
                    } else if(tmp>1) {
                        mediaPlayer.seekTo(stopMediaplayer);
                        mediaPlayer.start();
                    }
                }

                else if(buttonStart.getText().equals("Pause")) {
                    buttonStart.setText("Start");
                    pause = true;
                    mediaPlayer.pause();
                }

            }
        });
    }

    @Override
    public void onRestart() {
        super.onRestart();
        pause = false;
        mediaPlayer.seekTo(stopMediaplayer);
        mediaPlayer.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        pause = true;
        mediaPlayer.stop();
        mediaPlayer.pause();
        stopMediaplayer = mediaPlayer.getCurrentPosition();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        finish();
    }


public Button getButtonRechts() { return rechts;}
public Button getButtonUnten() { return unten;}
public Button getButtonLinks() { return links;}
public Button getButtonDrehen() { return drehen; }

    public boolean getPause() {  return pause;}
    public void setPause(boolean pause) { this.pause=pause;}
    public TextView getHighscoreLevelTextview() { return highscoreLevelTextView; }
    public TextView getPunkteTextview() { return PunkteTextView; }
    public TextView getAktuellerLevelTextView() { return aktuellerLevelTextView;}
    public MediaPlayer getMediaPlayer() {  return mediaPlayer; }
}
