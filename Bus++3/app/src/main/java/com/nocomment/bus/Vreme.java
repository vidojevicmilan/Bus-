package com.nocomment.bus;

import android.os.CountDownTimer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


/**
 * Created by Milan on 10-May-15.
 */
public class Vreme{

    protected int sat;
    protected int min;
    protected int sek;


    public Vreme(){
        min=sek=sat=0;
    }

    public Vreme(int s,int m,int ss){
        sat=s;
        min=m;
        sek=ss;
    }

    public Vreme(String x){
        String pom[]=new String[3];
        pom=x.split(":");
        sat= Integer.parseInt(pom[0]);
        min= Integer.parseInt(pom[1]);
        sek= Integer.parseInt(pom[2]);
    }

    public String toString(){
        String pom= sat + ":" + min + ":" + sek;
        return pom;

    }

    public String satToString(){
        return Integer.toString(sat);
    }

    public String minToString(){
        return Integer.toString(min);
    }



    public void add(Vreme x){
        int pom=0;
        this.sek+=x.sek;
        if(sek>59)
        {
            this.sek=this.sek % 60;
            this.min++;
        }

        this.min+=x.min;
        if(min>59)
        {
            this.min=this.min % 60;
            this.sat++;
        }

        this.sat+=x.sat;
        if(sat>23)
        {
            this.sat=this.sat % 24;

        }
    }

    public void sub(Vreme x){
        int pom=0;
        this.sek-=x.sek;
        if(this.sek<0)
        {
            this.sek+=60;
            this.min--;
        }

        this.min-=x.min;
        if(this.min<0)
        {
            this.min+=60;
            this.sat--;
        }

        this.sat-=x.sat;
        if(this.sat<0)
        {
            this.sat+=24;
        }
    }


    public String vratiCurrentTime(){
        Calendar cal = Calendar.getInstance();
        cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(cal.getTime());
    }

    public String vratiCurrentDate(){
        String weekDay;
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.US);

        Calendar calendar = Calendar.getInstance();
        weekDay = dayFormat.format(calendar.getTime());
        return weekDay;
    }

    public boolean jeManje(Vreme x)
    {
        Vreme pom = new Vreme(this.toString());
        if(this.sat>x.sat)
            return false;
        else

        if(this.sat<x.sat)
            return true;
        else

        if(this.min>x.min)
            return false;
        else

        if(this.min<x.min)
            return true;
        else

        if(this.sek>x.sek)
            return false;
        else

        if(this.sek<x.sek)
            return true;
        else
            return false;

    }



}