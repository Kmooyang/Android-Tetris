package com.example.admin.tetris;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.graphics.Color;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Tetris extends View implements View.OnClickListener{

    private MediaPlayer mediaPlayer;
    private SpielFeld spielFeld;
    private MainActivity mainActivity;
    private Button drehen;
    private Button rechts;
    private Button unten;
    private Button links;
    private  Timer timer = new Timer();
    private Random random = new Random();
    private  ArrayList<Stein> steinListe;// = spielFeld.getpieceList();
    private SpielsteinAnzeige spielsteinAnzeige;
    private TextView aktuellerLevelTextView;
    private TextView highscoreLevelTextView;
    private TextView aktuellePunkteTextView;
    private Punkte punkte;
    private final int score=10;
    private int timerZyklus =250; // Timer Aufruf
    private int level=0;
    private boolean pause;

    public Tetris(Context context, SpielsteinAnzeige spielsteinAnzeige, SpielFeld spielFeld) {
        super(context);

        this.mainActivity = (MainActivity) context;
        this.spielsteinAnzeige=spielsteinAnzeige;
        this.spielFeld =  spielFeld;
        pause = mainActivity.getPause();
        steinListe = spielFeld.getSteinListe();
        mediaPlayer = mainActivity.getMediaPlayer();
        punkte = new Punkte(context);

        aktuellerLevelTextView = mainActivity.getAktuellerLevelTextView();
        highscoreLevelTextView = mainActivity.getHighscoreLevelTextview();
        aktuellePunkteTextView = mainActivity.getPunkteTextview();

        aktuellerLevelTextView.append("0");
        aktuellePunkteTextView.append("0");
        highscoreLevelTextView.append(""+punkte.ladeHighscore());

        drehen = mainActivity.getButtonDrehen();
        rechts = mainActivity.getButtonRechts();
        unten = mainActivity.getButtonUnten();
        links = mainActivity.getButtonLinks();

        drehen.setOnClickListener(this);
        rechts.setOnClickListener(this);
        unten.setOnClickListener(this);
        links.setOnClickListener(this);
        gameLoop();
    }

public void gameLoop() {

    timer.schedule(new TimerTask() {

        @Override
        public void run() {
            mainActivity.runOnUiThread(new TimerTask() {

                @Override
                public void run() {

                   if(istGameOver()==false && mainActivity.getPause()==false ) {

                           spielFeld.nachUnten(spielFeld.aktuellerStein()); // getCurrrentPiece
                           spielFeld.nächsterStein(); // nächsten Stein anzeigen

                        if (spielFeld.nachUntenverschiebar(spielFeld.aktuellerStein()) == false) {
                            int gelöschteReihen = spielFeld.löscheReihe();
                            steinListe.remove(spielFeld.aktuellerStein());
                            steinListe.add(new Stein(random.nextInt(7) + 1));
                            spielsteinAnzeige.invalidate();

                            if (gelöschteReihen > 0) {
                                punkte.setAktuellePunkte(punkte.getAktuellePunkte() + gelöschteReihen * score);
                                int tmp = punkte.getAktuellePunkte();
                                punkte.setLevel();

                                aktuellePunkteTextView.setText("Punkte:" +" "+ tmp);
                                aktuellerLevelTextView.setText("Level" +" "+ punkte.getLevel());

                                if (punkte.getLevel() > punkte.ladeHighscore()) {
                                    punkte.schreibeHighscore();
                                    highscoreLevelTextView.setText("Highscore:" +" "+ punkte.getLevel());
                                }
                            }

                            if(punkte.getLevel()>level) {
                               level++;
                               timerZyklus = timerZyklus - (punkte.getLevel()*20);
                               timer.cancel();
                               timer = new Timer();
                               gameLoop();
                            }
                        }
                        invalidate();
                    }
                }
            });
        }
    }, 0, timerZyklus);
}

public boolean istGameOver() {

        if( spielFeld.checkGameOver(spielFeld.aktuellerStein())==true ) {
            timer.cancel();
            steinListe.clear();
            spielFeld.clearSpielFeld();
            mainActivity.setPause(true);
            mediaPlayer.stop();
            zeigeGameOverScreen();
            return true;
    }
    return false;
    }

public void zeigeGameOverScreen() {
  Intent intent = new Intent(this.getContext(), GameOverScreen.class);
  getContext().startActivity(intent);
}

    @Override
    protected void onDraw(Canvas canvas) {
        int[][] Feld= spielFeld.getSpielFeld();

        super.onDraw(canvas);
        Paint p = new Paint();

        for (int a = 0; a < spielFeld.getHöhe(); a++) {
            for (int b = 0; b < spielFeld.getBreite(); b++) {

              int color  = spielFeld.codeToColor(a,b);
              p.setColor(color);
              canvas.drawRect(b*30, a*30, b*30+30, a*30+30,p);
            }
        }
    }

    @Override
    public void onClick(View v) {
       if(mainActivity.getPause()==false) {

        switch(v.getId()) {
            case R.id.buttonrechts:
                spielFeld.nachRechtsverschieben(spielFeld.aktuellerStein());
                invalidate();
                break;
            case R.id.buttonunten:
                spielFeld.steinSchnellFallen(spielFeld.aktuellerStein());
                invalidate();
                break;
            case R.id.buttonlinks:
                spielFeld.nachLinksverschieben(spielFeld.aktuellerStein());
                invalidate();
                break;
            case R.id.buttondrehen:
                spielFeld.rotiereStein(spielFeld.aktuellerStein());
                invalidate();
                break;
        }
       }
    }
}
