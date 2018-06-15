package com.example.admin.tetris;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class SpielsteinAnzeige extends View {

    private SpielFeld spielFeld;
    private ArrayList<Stein> steinList;
    private Bitmap quadrat_Teil = BitmapFactory.decodeResource(getResources(), R.drawable.quadratteil);
    private Bitmap t_Teil = BitmapFactory.decodeResource(getResources(), R.drawable.tteil);
    private Bitmap z_Teil = BitmapFactory.decodeResource(getResources(), R.drawable.zteil);
    private Bitmap s_Teil = BitmapFactory.decodeResource(getResources(), R.drawable.steil);
    private Bitmap j_Teil = BitmapFactory.decodeResource(getResources(), R.drawable.jteil);
    private Bitmap l_Teil = BitmapFactory.decodeResource(getResources(), R.drawable.lteil);
    private Bitmap i_Teil = BitmapFactory.decodeResource(getResources(), R.drawable.iteil);


    private Random random = new Random();

    public SpielsteinAnzeige(Context context, SpielFeld spielFeld) {
        super(context);
        this.spielFeld = spielFeld;
        steinList = spielFeld.getSteinListe();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        steinList = spielFeld.getSteinListe();

        if (steinList.size() > 0) {
            Stein s = steinList.get(steinList.size() - 1);

            if (s.farbcode == 1) {
                canvas.drawBitmap(quadrat_Teil, 10, 10, null);
            }

            if (s.farbcode == 2) {
                canvas.drawBitmap(z_Teil, 10, 10, null);
            }

            if (s.farbcode == 3) {
                canvas.drawBitmap(i_Teil, 10, 10, null);
            }

            if (s.farbcode == 4) {
                canvas.drawBitmap(t_Teil, 10, 10, null);
            }

            if (s.farbcode == 5) {
                canvas.drawBitmap(s_Teil, 10, 10, null);
            }

            if (s.farbcode == 6) {
                canvas.drawBitmap(j_Teil, 10, 10, null);
            }

            if (s.farbcode == 7) {
                canvas.drawBitmap(l_Teil, 10, 10, null);
            }
        }
    }
}
