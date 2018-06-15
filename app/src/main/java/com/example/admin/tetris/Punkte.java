package com.example.admin.tetris;

import android.content.Context;
import android.content.SharedPreferences;

public class Punkte {

private int aktuellePunkte =0;
private MainActivity mainActivity;
private int level = 0;

public Punkte(Context context) {
    mainActivity = (MainActivity) context;
}

    public void schreibeHighscore() {  // Als Highscore wird er höchste erreichte Level gespeichert
        SharedPreferences pref = mainActivity.getSharedPreferences("GAME",0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("HIGHSCORE", level);
        editor.commit();
    }

    public int ladeHighscore() {
        SharedPreferences pref = mainActivity.getSharedPreferences("GAME",0);
        return pref.getInt("HIGHSCORE",0);
    }


public void setAktuellePunkte(int aktuellePunkte) {
    this.aktuellePunkte = aktuellePunkte;
}

public int getAktuellePunkte() {
    return aktuellePunkte;
}

public boolean setLevel() {
        if(aktuellePunkte>=20) {
           this.level = 1;
            return true;
        }

        if(aktuellePunkte>=60) {
        this.level = 2;
            return true;
        }

        if(aktuellePunkte>=100) {
        this.level = 3;
            return true;
        }

        if(aktuellePunkte>=140) {
        this.level = 4;
            return true;
        }

        if(aktuellePunkte>=180) {
        this.level = 5;
            return true;
        }

    if(aktuellePunkte>=220) {
        this.level = 6;
        return true;
        }

    if(aktuellePunkte>=260) {
        this.level = 7;
    return true;
        }

    return false;
    }

    public int getLevel() {
        return level;
}

}
