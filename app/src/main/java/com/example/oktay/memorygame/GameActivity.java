package com.example.oktay.memorygame;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;

import java.util.Random;

public class GameActivity extends AppCompatActivity implements View.OnClickListener{

    private int elemanSayisi;

    private MemoryButton[] butonlar;
    private int[] butonResimler;
    private int[] butonLokasyonlar;

    private MemoryButton secilenButton1;
    private MemoryButton secilenButton2;

    private boolean mesgul=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        GridLayout gridLayout=(GridLayout)findViewById(R.id.activity_game);

        int satirSayisi=gridLayout.getRowCount();
        int sutunSayisi=gridLayout.getColumnCount();

        elemanSayisi=satirSayisi*sutunSayisi;

        butonlar=new MemoryButton[elemanSayisi];
        butonResimler=new int[elemanSayisi/2];

        butonResimler[0]=R.drawable.card1;
        butonResimler[1]=R.drawable.card2;
        butonResimler[2]=R.drawable.card3;
        butonResimler[3]=R.drawable.card4;
        butonResimler[4]=R.drawable.card5;
        butonResimler[5]=R.drawable.card6;
        butonResimler[6]=R.drawable.card7;
        butonResimler[7]=R.drawable.card8;

        butonLokasyonlar=new int[elemanSayisi];

        butonKaristir();

        for(int r=0;r<satirSayisi;r++){
            for(int c=0;c<sutunSayisi;c++){
                MemoryButton gecici=new MemoryButton(this,r,c,
                        butonResimler[butonLokasyonlar[r*sutunSayisi+c]]);
                gecici.setId(View.generateViewId());
                gecici.setOnClickListener(this);
                butonlar[r*sutunSayisi+c]=gecici;
                gridLayout.addView(gecici);
            }
        }

    }

    private void butonKaristir() {
        Random rnd=new Random();
        for (int i=0;i<elemanSayisi;i++)
            butonLokasyonlar[i]=i % (elemanSayisi/2);

        /* karıstırma islemi*/
        for (int i=0;i<elemanSayisi;i++){
            int rSayi=rnd.nextInt(elemanSayisi);
            int gecici=butonLokasyonlar[i];
            butonLokasyonlar[i]=butonLokasyonlar[rSayi];
            butonLokasyonlar[rSayi]=gecici;
        }

    }

    @Override
    public void onClick(View v) {
        if(mesgul)
            return;
        MemoryButton mButton=(MemoryButton) v;

        if (mButton.isEslestimi())
            return;
        if (secilenButton1==null){
            secilenButton1=mButton;
            secilenButton1.dondur();
            return;
        }
        if (secilenButton1.getId()==mButton.getId())
            return;
        if (secilenButton1.getOnYuzResimId()==mButton.getOnYuzResimId()){
            mButton.dondur();

            mButton.setEslestimi(true);
            secilenButton1.setEslestimi(true);

            mButton.setEnabled(false);
            secilenButton1.setEnabled(false);
            secilenButton1=null;
            return;
        }
        else {
            secilenButton2=mButton;
            secilenButton2.dondur();
            mesgul=true;

            final Handler handler=new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    secilenButton1.dondur();
                    secilenButton2.dondur();
                    secilenButton1=null;
                    secilenButton2=null;
                    mesgul=false;
                }
            }, 500);
        }

    }
}
