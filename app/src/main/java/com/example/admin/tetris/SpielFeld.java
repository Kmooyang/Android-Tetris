package com.example.admin.tetris;

import android.graphics.Color;
import android.graphics.Point;
import java.util.ArrayList;
import java.util.Random;

public class SpielFeld {

   	 private final int höhe =30;
     private final int breite=16;
     private int spielFeld[][]=new int[höhe*2][breite*2];
   	 private final Random random = new Random();
     private ArrayList<Stein> steinListe = new ArrayList<Stein>();
     private  final int anzahlSteine = 7;

    public SpielFeld() {
         steinListe.add(new Stein(random.nextInt(anzahlSteine)+1));
         steinListe.add(new Stein(random.nextInt(anzahlSteine)+1));
     }

     public int codeToColor(int x, int y) {
        if(spielFeld[x][y]==0) return Color.parseColor("#FFFF00");  // Yellow
        if(spielFeld[x][y]==1) return Color.parseColor("#FF0000"); ; // Red
        if(spielFeld[x][y]==2) return Color.parseColor("#00FF00"); ; // Green
        if(spielFeld[x][y]==3) return Color.parseColor("#0000FF"); ; //Blue
        if(spielFeld[x][y]==4) return Color.parseColor("#BEBEBE"); ; //Gray
        if(spielFeld[x][y]==5) return Color.parseColor("#00FFFF"); ; //Cyan
        if(spielFeld[x][y]==6) return Color.parseColor("#000000"); ; //Black
        if(spielFeld[x][y]==7) return Color.parseColor("#FF00FF"); ; //Magenta
        return -1;
     }

    public void clearSpielFeld() {
        for(int i=0; i<höhe; i++) {
            for(int j=0; j<breite; j++) {
                spielFeld[i][j]= 0;
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

     private void setzeStein(Stein spielStein) {
         spielFeld[spielStein.x1][spielStein.y1] = spielStein.farbcode;
         spielFeld[spielStein.x2][spielStein.y2] = spielStein.farbcode;
         spielFeld[spielStein.x3][spielStein.y3] = spielStein.farbcode;
         spielFeld[spielStein.x4][spielStein.y4] = spielStein.farbcode;
    }

    private void löscheStein(Stein spielStein) {
        spielFeld[spielStein.x1][spielStein.y1] = 0;
        spielFeld[spielStein.x2][spielStein.y2] = 0;
        spielFeld[spielStein.x3][spielStein.y3] = 0;
        spielFeld[spielStein.x4][spielStein.y4] = 0;
    }

    private boolean verschiebarStein(Stein SpielStein, int x, int y) {
        int tmp =0;

        Point p1 = new Point(SpielStein.x1, SpielStein.y1);
        Point p2 = new Point(SpielStein.x2, SpielStein.y2);
        Point p3 = new Point(SpielStein.x3, SpielStein.y3);
        Point p4 = new Point(SpielStein.x4, SpielStein.y4);

        Point tmp1 = new Point(SpielStein.x1+x, SpielStein.y1+y);
        Point tmp2 = new Point(SpielStein.x2+x, SpielStein.y2+y);
        Point tmp3 = new Point(SpielStein.x3+x, SpielStein.y3+y);
        Point tmp4 = new Point(SpielStein.x4+x, SpielStein.y4+y);

        ArrayList<Point> steinKoordinaten = new ArrayList<Point>();
        steinKoordinaten.add(tmp1);
        steinKoordinaten.add(tmp2);
        steinKoordinaten.add(tmp3);
        steinKoordinaten.add(tmp4);

        for(Point p : steinKoordinaten ) {

            if(p.x< höhe && p.y>=0 && p.y< breite && spielFeld[p.x][p.y]==0) {
                tmp++;
            }

            else if(p.equals(p1) || p.equals(p2) || p.equals(p3) || p.equals(p4)) {
                tmp++;
            }
        }

        if(tmp==4) {
            return true;
        }
        return false;
    }

    private boolean steinRotierbar(Stein SpielStein) {
        int tmp =0;
        ArrayList<Point> steinKoordinaten = new ArrayList<Point>();

        Stein tmpStein = new Stein(SpielStein);
        Point p1 = new Point(SpielStein.x1, SpielStein.y1);
        Point p2 = new Point(SpielStein.x2, SpielStein.y2);
        Point p3 = new Point(SpielStein.x3, SpielStein.y3);
        Point p4 = new Point(SpielStein.x4, SpielStein.y4);

        tmpStein.dreheStein();

        Point tmp1 = new Point(tmpStein.x1, tmpStein.y1);
        Point tmp2 = new Point(tmpStein.x2, tmpStein.y2);
        Point tmp3 = new Point(tmpStein.x3, tmpStein.y3);
        Point tmp4 = new Point(tmpStein.x4, tmpStein.y4);

        steinKoordinaten.add(tmp1);
        steinKoordinaten.add(tmp2);
        steinKoordinaten.add(tmp3);
        steinKoordinaten.add(tmp4);

        for(Point p : steinKoordinaten ) {

            if(p.x< höhe && p.x>=0 && p.y>=0 && p.y< breite && spielFeld[p.x][p.y]==0) {
                tmp++;
            }

            else if(p.equals(p1) || p.equals(p2) || p.equals(p3) || p.equals(p4)) {
                tmp++;
            }
        }

        if(tmp==4) {
            return true;
        }
        return false;
    }

    private  boolean nachLinksverschiebar(Stein SpielStein) {
        if(verschiebarStein(SpielStein, 0, -1)==true) {
            return true;
        }
        return false;
    }

    private boolean nachRechtsverschiebar(Stein SpielStein){
        if(verschiebarStein(SpielStein, 0,1) == true) {
            return true;
        }
        return false;
    }

    public boolean nachUntenverschiebar(Stein SpielStein) {
        if(verschiebarStein(SpielStein, 1,0)==true) {
            return true;
        }
        return false;
    }


    private void verschieben(Stein spielStein, int x, int y)   {
        löscheStein(spielStein);
        spielStein.verschieben(x, y);
        setzeStein(spielStein);
    }

    public void nachRechtsverschieben(Stein spielStein){
           if(nachRechtsverschiebar(spielStein)==true) {
               verschieben(spielStein, 0, 1);
           }
    }

    public  void nachLinksverschieben(Stein spielStein){
      if(nachLinksverschiebar(spielStein)==true) {
          verschieben(spielStein, 0, -1);
        }
    }

    public  void nachUnten(Stein spielStein) {
        if(nachUntenverschiebar(spielStein)==true) {
            verschieben(spielStein, 1, 0);
        }
    }

    public void steinSchnellFallen(Stein spielStein) {
        löscheStein(spielStein);

        while(nachUntenverschiebar(spielStein)==true) {
            nachUnten(spielStein);
         }
        setzeStein(spielStein);
    }

    public void rotiereStein(Stein spielStein) {

         if(steinRotierbar(spielStein)==true && spielStein.farbcode!=1) { // Quadrat braucht nicht rotiert werden
             löscheStein(spielStein);
             spielStein.dreheStein();
             setzeStein(spielStein);
         }
             setzeStein(spielStein);
     }

    public int löscheReihe() {

        int tmp=0;
        int gelöschteReihen=0;
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        int[][] SpielFeldKopie = new int[höhe][breite];

        for (int i = 0; i < höhe; i++) {
            for (int j = breite - 1; j >= 0; j--) {
                if (spielFeld[i][j]==0) {
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
            for (int i = 0; i < SpielFeldKopie.length; i++) {
                for (int j = 0; j < SpielFeldKopie[1].length; j++) {
                    SpielFeldKopie[i][j] = spielFeld[i][j];
                }
            }

            for (int i = 0; i < SpielFeldKopie.length; i++) {
                for (int j = 0; j < SpielFeldKopie[1].length; j++) {
                    spielFeld[i+gelöschteReihen][j] = SpielFeldKopie[i][j];
                }
            }
        }
        return gelöschteReihen;
    }

    public void löscheReihe(int r){
            for (int i = 0; i < breite; i++) {
                spielFeld[r][i] =0;
            }
    }

    public boolean checkGameOver(Stein spielStein) {
       if(nachUntenverschiebar(spielStein) == false && spielStein.getMinXKoordinate(
       spielStein.x1, spielStein.x2, spielStein.x3, spielStein.x4)<=1) {
           return true;
       }
    return false;
    }

    public int[][] getSpielFeld() {
        return this.spielFeld;
    }

    public  int getHöhe() {
        return this.höhe;
    }

    public int getBreite() {
        return this.breite;
    }
}