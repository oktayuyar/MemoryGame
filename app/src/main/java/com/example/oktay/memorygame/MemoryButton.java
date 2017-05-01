package com.example.oktay.memorygame;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.Button;
import android.widget.GridLayout;

/**
 * Created by oktay on 10/25/16.
 */
public class MemoryButton extends Button{
    protected int satir;
    protected int sutun;

    protected int onYuzResimId;

    protected boolean eslestimi=false;
    protected boolean donduruldumu=false;

    protected Drawable onYuzResim;
    protected Drawable arkaYuzResim;

    public boolean isEslestimi() {
        return eslestimi;
    }

    public void setEslestimi(boolean eslestimi) {
        this.eslestimi = eslestimi;
    }

    public int getOnYuzResimId() {
        return onYuzResimId;
    }


    public MemoryButton(Context context,int sa,int su,int onYuzId){
        super(context);
        satir=sa;
        sutun=su;
        onYuzResimId=onYuzId;

        onYuzResim=context.getDrawable(onYuzResimId);
        arkaYuzResim=context.getDrawable(R.drawable.icon);
        setBackground(arkaYuzResim);

        GridLayout.LayoutParams gecici=new GridLayout.LayoutParams(
                GridLayout.spec(satir),GridLayout.spec(sutun)
        );

        gecici.width=GridLayout.LayoutParams.WRAP_CONTENT;
        gecici.height=GridLayout.LayoutParams.WRAP_CONTENT;
        setLayoutParams(gecici);

    }

    public void dondur(){
        if(eslestimi)
            return;
        if(donduruldumu){
            setBackground(arkaYuzResim);
            donduruldumu=false;
        }
        else {
            setBackground(onYuzResim);
            donduruldumu=true;
        }
    }
}
