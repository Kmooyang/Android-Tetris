package com.example.admin.tetris;

import java.util.ArrayList;
import java.util.Random;

public class SpielFeld {

   	 public static final int höhe =30;
     public static  final int breite=16;
     public static final int SpielFeld[][]=new int[höhe][breite];

   	 private final Random random = new Random();
     private ArrayList<Stein> steinListe = new ArrayList<Stein>();


     public SpielFeld() {
         steinListe.add(new Stein(random.nextInt(7)+1));
         steinListe.add(new Stein(random.nextInt(7)+1));
     }

    public void clearSpielFeld() {
        for(int i=0; i<höhe; i++) {
            for(int j=0; j<breite; j++) {
                SpielFeld[i][j]=0;
            }
        }
    }

    public  ArrayList<Stein> getSteinListe(){
        return steinListe;
    }

    public Stein aktuellerStein()  {
              return steinListe.get(steinListe.size() - 2);
     }

    public Stein nächsterStein() {
         return steinListe.get(steinListe.size()-1);
    }

     public void setzeStein(Stein spielStein) {
        SpielFeld[spielStein.x1][spielStein.y1] = spielStein.farbcode;
        SpielFeld[spielStein.x2][spielStein.y2] = spielStein.farbcode;
        SpielFeld[spielStein.x3][spielStein.y3] = spielStein.farbcode;
        SpielFeld[spielStein.x4][spielStein.y4] = spielStein.farbcode;
    }

    public void löscheStein(Stein spielStein) {
        SpielFeld[spielStein.x1][spielStein.y1] = 0;
        SpielFeld[spielStein.x2][spielStein.y2] = 0;
        SpielFeld[spielStein.x3][spielStein.y3] = 0;
        SpielFeld[spielStein.x4][spielStein.y4] = 0;
    }

    public  void verschieben(Stein spielStein, int x, int y)   {
        löscheStein(spielStein);
        spielStein.verschieben(x, y);
        setzeStein(spielStein);
    }

    public void nachRechtsverschieben(Stein spielStein){
           if(spielStein.nachRechtsverschiebar(spielStein)==true) {
               verschieben(spielStein, 0, 1);
           }
    }

    public  void nachLinksverschieben(Stein spielStein){
      if(spielStein.nachLinksverschiebar(spielStein)==true) {
          verschieben(spielStein, 0, -1);
        }
    }

    public  void nachUnten(Stein spielStein) {
        if(spielStein.nachUntenverschiebar(spielStein)==true) {
            verschieben(spielStein, 1, 0);
        }
    }

    public void steinSchnellFallen(Stein spielStein) {
        löscheStein(spielStein);
        while(spielStein.nachUntenverschiebar(spielStein)==true) {
            nachUnten(spielStein);
         }
        setzeStein(spielStein);
    }

    public void rotiereStein(Stein spielStein) {

         if(spielStein.steinRotierbar(spielStein)==true && spielStein.farbcode!=1) { // Quadrat braucht nicht rotiert werden
             löscheStein(spielStein);
             spielStein.dreheStein();
             setzeStein(spielStein);
         }
             setzeStein(spielStein);
     }

    public int löscheReihe() {
        int tmp = 0;
        int gelöschteReihen = 0;
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        int[][] SpielFeldKopie;

        for (int i = 0; i < höhe; i++) {
            for (int j = breite - 1; j >= 0; j--) {
                if (SpielFeld[i][j] == 0) {
                    break;
                }
                if (j == 0) {
                    tmp = i;
                    arrayList.add(tmp);
                    gelöschteReihen++;
                    löscheReihe(tmp);
                }
            }
        }

        if (gelöschteReihen >= 1) {  // nur wenn Reihen gelöscht werden muss nachgerücckt werden
            int maxReihe = arrayList.size()-1;
            SpielFeldKopie = new int[höhe-maxReihe-1][breite];

            for (int i = 0; i < SpielFeldKopie.length; i++) {
                for (int j = 0; j < SpielFeldKopie[1].length; j++) {
                    SpielFeldKopie[i][j] = SpielFeld[i][j];
                }
            }

            for(int i=0; i<SpielFeldKopie.length; i++) {
                löscheReihe(i);
            }

            for (int i = 0; i < SpielFeldKopie.length; i++) {
                for (int j = 0; j < SpielFeldKopie[1].length; j++) {
                    SpielFeld[i+maxReihe+1][j] = SpielFeldKopie[i][j];
                }
            }
        }
   return gelöschteReihen;
    }

    public void löscheReihe(int r){
            for (int i = 0; i < breite; i++) {
                SpielFeld[r][i] = 0;
            }
    }

    public boolean checkGameOver(Stein spielStein) {
       if(spielStein.nachUntenverschiebar(spielStein) == false && spielStein.getMinXKoordinate(
       spielStein.x1, spielStein.x2, spielStein.x3, spielStein.x4)<=1) {
           return true;
       }
    return false;
    }

    public static int[][] getSpielFeld() {
        return SpielFeld;
    }

    public static int getHöhe() {
        return höhe;
    }

    public static int getBreite() {
        return breite;
    }

    /*public int[][] getF() {
         return this.SpielFeld;
    }
*/

}