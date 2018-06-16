package com.example.admin.tetris;

public class Stein {

   public int farbcode;
   public int x1, y1;
   public int x2, y2;
   public int x3, y3;
   public int x4, y4;
   private Stein stein;

  /*
  creates a copy Instances of Stein
   */

   public Stein(Stein stein) {
      this.stein = stein;
      this.x1= stein.x1; this.x2= stein.x2;
      this.x3= stein.x3; this.x4= stein.x4;
      this.y1= stein.y1; this.y2= stein.y2;
      this.y3= stein.y3; this.y4= stein.y4;
  }

  public int getFarbcode() {
       return this.farbcode;
  }

    public Stein(int f) { // alle Steine um 1 nach unten veschoben // TODO enum for farbcode

        switch (f) { // Quadrat Rot
            case 1:
                x1 = 0; y1 = 7;
                x2 = 0; y2 = 8;
                x3 = 1; y3 = 7;
                x4 = 1; y4 = 8;

                farbcode = 1;
                break;

            case 2:    // Z Teil    Blau
                x1 = 0;y1 = 7;
                x2 = 0;y2 = 8;
                x3 = 1;y3 = 8;
                x4 = 1;y4 = 9;

                farbcode = 2;
                break;

            case 3: // i Teil
                x1 = 0;y1 = 6;
                x2 = 0;y2 = 7;
                x3 = 0;y3 = 8;
                x4 = 0;y4 = 9;

                farbcode = 3;
                break;

            case 4: // T Teil
                x1 = 0;y1 = 8;
                x2 = 1;y2 = 7;
                x3 = 1;y3 = 8;
                x4 = 1;y4 = 9;

                farbcode = 4;
                break;

            case 5: // S Teil
                x1 = 0;y1 = 7;
                x2 = 0;y2 = 8;
                x3 = 1;y3 = 6;
                x4 = 1;y4 = 7;

                farbcode = 5;
                break;

            case 6:  // j Teil
                x1 = 0;y1 = 7;
                x2 = 0;y2 = 8;
                x3 = 0;y3 = 9;
                x4 = 1;y4 = 9;

                farbcode = 6;
                break;

            case 7:  //  l Teil
                x1 = 0;y1 = 7;
                x2 = 0;y2 = 8;
                x3 = 0;y3 = 9;
                x4 = 1;y4 = 7;

                farbcode = 7;
                break;
        }
    }

    public void verschieben(int x, int y) {
        x1 = x1 + x;
        y1 = y1 + y;
        x2 = x2 + x;
        y2 = y2 + y;
        x3 = x3 + x;
        y3 = y3 + y;
        x4 = x4 + x;
        y4 = y4 + y;
    }

    public void dreheStein() {
        int tmp_x1, tmp_y1;
        int tmp_x2, tmp_y2;
        int tmp_x3, tmp_y3;

        tmp_x1 = drehe_Um_x1(y2);
        tmp_y1 = drehe_Um_y1(x2);
        x2 = tmp_x1;
        y2 = tmp_y1;

        tmp_x2 = drehe_Um_x1(y3);
        tmp_y2 = drehe_Um_y1(x3);
        x3 = tmp_x2;
        y3 = tmp_y2;

        tmp_x3 = drehe_Um_x1(y4);
        tmp_y3 = drehe_Um_y1(x4);
        x4 = tmp_x3;
        y4 = tmp_y3;
    }

    public int drehe_Um_x1(int y) {
        return x1 + y - y1;
    }

    public int drehe_Um_y1(int x) {
        return y1 - x + x1;
    }

    public int getMinXKoordinate(int x1, int x2, int x3, int x4) {
        return Math.min(Math.min(x1,x2),Math.min(x3,x4));
    }
}