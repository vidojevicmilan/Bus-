package com.nocomment.bus;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import android.content.res.AssetManager;

import org.w3c.dom.Text;


public class RedVoznje extends Activity {

    //public static String str = new String("OVO JE BLABLA!");
    private int smer = 1;
    public String passedVar = null;
    public String dan = "Radni dan";
    public int kliknut=1;
    private TextView passedView = null;
    private RadioGroup radioGroup;
    private Linija odabranaLinija;
    //private RadioButton radioButton;
    MalibuCountDownTimer countDownTimer;
    boolean started=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_red_voznje);

        passedVar = getIntent().getStringExtra("naziv");
        //String message =passedVar;
        //Toast.makeText(RedVoznje.this, passedVar, Toast.LENGTH_SHORT).show();//str

        int i = 0;
        while(!MainActivity.mapa.Linije[i].nazivLinije.equals(passedVar)){
            //odabranaLinija= MainActivity.mapa.Linije[i];
            i++;
        }
        //if(i==0)
            odabranaLinija= MainActivity.mapa.Linije[i];
        Toast.makeText(RedVoznje.this,odabranaLinija.nazivLinije,Toast.LENGTH_LONG).show();
       // else
       //     odabranaLinija=MainActivity.mapa.Linije[i];
        try {
            printRedVoznje(passedVar,dan);
        } catch (IOException e) {
            e.printStackTrace();
        }



        setupPromeniSmerButton();
        setupRadioButtonChange();
        setupSpinner();
        setupSpinnerChange();
    }


    /*public void onResume(){
        super.onResume();
        int i = 0;
        while(!MainActivity.mapa.Linije[i].nazivLinije.equals(passedVar)){
            odabranaLinija= MainActivity.mapa.Linije[i];
            i++;
        }

        try {
            printRedVoznje(passedVar,dan);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    */
    //proma reda voznje
    private void setupRadioButtonChange() {
        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);



        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radiob1 = (RadioButton)findViewById(R.id.radioButton1);
                RadioButton radiob2 = (RadioButton)findViewById(R.id.radioButton2);
               // RadioButton radiob3 = (RadioButton)findViewById(R.id.radioButton3);
                if(radiob1.isChecked())
                    kliknut=1;
                else if(radiob2.isChecked())
                    kliknut=2;
                else
                    kliknut=3;
                if(kliknut==1)
                    dan="Radni dan";
                else if(kliknut==2)
                    dan="Subota";
                else
                    dan="Nedelja";
                try {

                    printRedVoznje(passedVar,dan);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Spinner spinner = (Spinner)findViewById(R.id.spinnerRedVoznje);
                String selektovan = spinner.getSelectedItem().toString();
                menjanjeVremena(selektovan);
            }
        });
    }
    //promena smera
    private void setupPromeniSmerButton() {
        //hideAll();
        Button promeniSmerButton = (Button)findViewById(R.id.promenismerbutton);
        promeniSmerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(smer == 1)
                    smer = 2;
                else
                    smer = 1;
                try {
                    printRedVoznje(passedVar,dan);
                    //Toast.makeText(RedVoznje.this, "Promena smera", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Spinner spinner = (Spinner)findViewById(R.id.spinnerRedVoznje);
                String selektovan = spinner.getSelectedItem().toString();
                menjanjeVremena(selektovan);
            }
        });
    }

    public void setupSpinner()
    {
       try {
           Linija linija = new Linija();
           int index = 0;
           while (!MainActivity.mapa.Linije[index].nazivLinije.equals(passedVar)) {
               index++;
           }
           linija = MainActivity.mapa.Linije[index];
           int brojStanica = ((linija.brRuta / 2) + 1);

           String array_spinner[];
           array_spinner = new String[brojStanica];
           int k = 0;
           for (int i = 0; i < linija.brRuta; i++) {
               if (linija.ruta[i] instanceof LinkedNode) {
                   LinkedNode stanica = (LinkedNode) linija.ruta[i];
                   //int brojStanice = Integer.parseInt(id);

                   array_spinner[k++] = stanica.naziv;
               }
           }


           Spinner s = (Spinner) findViewById(R.id.spinnerRedVoznje);
           ArrayAdapter adapter = new ArrayAdapter(this,
                   android.R.layout.simple_spinner_dropdown_item, array_spinner);
           s.setAdapter(adapter);
       }
       catch (Exception e){
           Toast.makeText(RedVoznje.this,e.toString(),Toast.LENGTH_SHORT).show();
       }
    }
    //proeman stanice
    public void setupSpinnerChange() {
        final Spinner spinner = (Spinner)findViewById(R.id.spinnerRedVoznje);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                //Spinner spinner = (Spinner)findViewById(R.id.spinnerRedVoznje);
                String selektovan = spinner.getSelectedItem().toString();
                menjanjeVremena(selektovan);
            }


            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                Toast.makeText(RedVoznje.this,"NITA",Toast.LENGTH_SHORT);
            }

        });
    }

    public void menjanjeVremena(String selected){
        //if(started==true) {
       //     countDownTimer.cancel();
       //     started = false;
      //  }
      //  int dan=0;
        try{
        TextView textRecenica=(TextView)findViewById(R.id.preostaloVreme);
        textRecenica.setText("Prvi sledeci bus stize za");
        TextView textVreme=(TextView)findViewById(R.id.vreme);
        RadioButton radiob1 = (RadioButton)findViewById(R.id.radioButton1);
        RadioButton radiob2 = (RadioButton)findViewById(R.id.radioButton2);
        RadioButton radiob3 = (RadioButton)findViewById(R.id.radioButton3);
        String selektovan = selected;
        if(radiob1.isChecked()){
            //dan=1;
            textVreme.setText(odabranaLinija.izracunajPrviSledeci(MainActivity.mapa.Nis.findNode(selektovan)).toString());
            //countDownTimer = new MalibuCountDownTimer(30000, 1000,selektovan,dan);
            //countDownTimer.start();
          //  started=true;

        }
        else if(radiob2.isChecked())
        {
           // dan=2;
            textVreme.setText(odabranaLinija.izracunajPrviSledeciSubota(MainActivity.mapa.Nis.findNode(selektovan)).toString());
           // countDownTimer = new MalibuCountDownTimer(30000, 1000,selektovan,dan);
           // countDownTimer.start();
           // started=true;
        }
        else {
            //dan = 3;
            textVreme.setText(odabranaLinija.izracunajPrviSledeciNedelja(MainActivity.mapa.Nis.findNode(selektovan)).toString());
            //countDownTimer = new MalibuCountDownTimer(30000, 1000,selektovan,dan);
            //countDownTimer.start();
            //started=true;
        }


        Toast.makeText(RedVoznje.this,"Selektovana stanica: "+selektovan,Toast.LENGTH_SHORT).show();

        //Linija linija = new Linija();
        //int index = 0;
        // while (!MainActivity.mapa.Linije[index].nazivLinije.equals(passedVar)) {
        //     index++;
        // }

        //text.setText(MainActivity.mapa.Linije[index].izracunajPrviSledeci(MainActivity.mapa.Nis.findNode(selektovan)).toString());
        //text.setText("Prvi sledeci bus stize za");
        //text=(TextView)findViewById(R.id.vreme);
        //text.setText(odabranaLinija.izracunajPrviSledeci(MainActivity.mapa.Nis.findNode(selektovan)).toString());
        //countDownTimer = new MalibuCountDownTimer(50000, 1000,selektovan,dan);
        //countDownTimer.start();
        //started=true;
        //
        }
            catch(Exception ec){
                Log.e(ec.toString(), "nzm");
                Toast.makeText(RedVoznje.this,ec.toString(),Toast.LENGTH_SHORT);
                TextView textRecenica=(TextView)findViewById(R.id.preostaloVreme);
                textRecenica.setText("");
                TextView textVreme=(TextView)findViewById(R.id.vreme);
                textVreme.setText("");
            }
    }


    private int vratiIndexLinije(String nazivLinije,int Smer){
        int indexLinije = 0;
        while(!MainActivity.mapa.Linije[indexLinije].nazivLinije.equals(passedVar)){
            indexLinije++;
        }
        int IDlinije = MainActivity.mapa.Linije[indexLinije].id;
        for(int i=0;i<MainActivity.mapa.brLinija;i++){
            if(MainActivity.mapa.Linije[i].id==IDlinije)
                if(MainActivity.mapa.Linije[i].smer==Smer)
                    return i;
        }
        return -1;

    }

    private void printRedVoznje(String passedVar,String day) throws IOException {

        TextView smerText = (TextView)findViewById(R.id.smerText);


        /*int indexLinije = 0;
        while(!MainActivity.mapa.Linije[indexLinije].nazivLinije.equals(passedVar)){
            indexLinije++;
        }*/
        //int indexUNizuLinija;

        Linija linija = MainActivity.mapa.Linije[vratiIndexLinije(passedVar,this.smer)];
        /*int i=0;
        while(!((MainActivity.mapa.Linije[i].id!=linija.id) && (smer!=MainActivity.mapa.Linije[i].smer))){
            i++;
        }*/
       // linija= MainActivity.mapa.Linije[i];
            // smer je 1 za prvi smer, 2 za drugi smer


              //  RadioGroup rg = (RadioGroup) findViewById(R.id.radiogroup);
            //    String value = ((RadioButton) findViewById(rg.getCheckedRadioButtonId())).getText().toString();
            //    Toast.makeText(RedVoznje.this, value, Toast.LENGTH_SHORT).show();

                //int point = 0;
               // Linija linija = MainActivity.mapa.Linije[indexLinije];

                //while(linija.id != indexLinije+1 && smer != linija.smer){
                //    point++;
                //    linija = MainActivity.mapa.Linije[point];
                //
                //}

                smerText.setText(linija.nazivLinije);
                //hideAll();
                if (day.equals("Radni dan")) {
                    populateView(linija, 1);
                }
                else if(day.equals("Subota")){
                    populateView(linija, 2);
                }
                else if(day.equals("Nedelja")){
                    populateView(linija, 3);
                }


                //int brojRedova = countLines("nbanja.txt");
                //smerText.setText(Integer.toString(brojRedova));


        }

    public void hideAll(){

        TextView tv = (TextView)findViewById(R.id.textView_05);
        tv.setText("");
        tv = (TextView)findViewById(R.id.textView_06);
        tv.setText("");
        tv = (TextView)findViewById(R.id.textView_07);
        tv.setText("");
         tv = (TextView)findViewById(R.id.textView_08);
        tv.setText("");
         tv = (TextView)findViewById(R.id.textView_09);
        tv.setText("");
         tv = (TextView)findViewById(R.id.textView_10);
        tv.setText("");
         tv = (TextView)findViewById(R.id.textView_11);
        tv.setText("");
        tv = (TextView)findViewById(R.id.textView_12);
        tv.setText("");
        tv = (TextView)findViewById(R.id.textView_13);
        tv.setText("");
        tv = (TextView)findViewById(R.id.textView_14);
        tv.setText("");
        tv = (TextView)findViewById(R.id.textView_15);
        tv.setText("");
        tv = (TextView)findViewById(R.id.textView_16);
        tv.setText("");
        tv = (TextView)findViewById(R.id.textView_17);
        tv.setText("");
        tv = (TextView)findViewById(R.id.textView_18);
        tv.setText("");
        tv = (TextView)findViewById(R.id.textView_19);
        tv.setText("");
        tv = (TextView)findViewById(R.id.textView_20);
        tv.setText("");
        tv = (TextView)findViewById(R.id.textView_21);
        tv.setText("");
        tv = (TextView)findViewById(R.id.textView_22);
        tv.setText("");
        tv = (TextView)findViewById(R.id.textView_23);
        tv.setText("");
        tv = (TextView)findViewById(R.id.textView_00);
        tv.setText("");
        tv = (TextView)findViewById(R.id.textView_01);
        tv.setText("");
        tv = (TextView)findViewById(R.id.textView_02);
        tv.setText("");



    }

    private void populateView(Linija linija, int i) {
        hideAll();
        if(i == 1){
            //String[] polasci = new String[linija.brP];
            for(int j = 0; j<linija.brP; j++){
                switch(Integer.parseInt(linija.polasci[j].satToString())){
                    case 05:
                        TextView tv5 = (TextView)findViewById(R.id.textView_05);
                        if(tv5.getText().equals(""))
                            tv5.setText("    "+linija.polasci[j].minToString());
                        else
                        tv5.setText(tv5.getText() + "; " + linija.polasci[j].minToString());
                        break;
                    case 06:
                        TextView tv6 = (TextView)findViewById(R.id.textView_06);
                        if(tv6.getText().equals(""))
                            tv6.setText("    "+linija.polasci[j].minToString());
                        else
                            tv6.setText(tv6.getText() + "; " + linija.polasci[j].minToString());
                        break;
                    case 07:
                        TextView tv7 = (TextView)findViewById(R.id.textView_07);
                        if(tv7.getText().equals(""))
                            tv7.setText("    "+linija.polasci[j].minToString());
                        else
                            tv7.setText(tv7.getText() + "; " + linija.polasci[j].minToString());
                        break;
                    case 8:
                        TextView tv8 = (TextView)findViewById(R.id.textView_08);
                        if(tv8.getText().equals(""))
                            tv8.setText("    "+linija.polasci[j].minToString());
                        else
                            tv8.setText(tv8.getText() + "; " + linija.polasci[j].minToString());
                        break;
                    case 9:
                        TextView tv9 = (TextView)findViewById(R.id.textView_09);
                        if(tv9.getText().equals(""))
                            tv9.setText("    "+linija.polasci[j].minToString());
                        else
                            tv9.setText(tv9.getText() + "; " + linija.polasci[j].minToString());
                        break;
                    case 10:
                        TextView tv10 = (TextView)findViewById(R.id.textView_10);
                        if(tv10.getText().equals(""))
                            tv10.setText("    "+linija.polasci[j].minToString());
                        else
                            tv10.setText(tv10.getText() + "; " + linija.polasci[j].minToString());
                        break;
                    case 11:
                        TextView tv11 = (TextView)findViewById(R.id.textView_11);
                        if(tv11.getText().equals(""))
                            tv11.setText("    "+linija.polasci[j].minToString());
                        else
                            tv11.setText(tv11.getText() + "; " + linija.polasci[j].minToString());
                        break;
                    case 12:
                        TextView tv12 = (TextView)findViewById(R.id.textView_12);
                        if(tv12.getText().equals(""))
                            tv12.setText("    "+linija.polasci[j].minToString());
                        else
                            tv12.setText(tv12.getText() + "; " + linija.polasci[j].minToString());
                        break;
                    case 13:
                        TextView tv13 = (TextView)findViewById(R.id.textView_13);
                        if(tv13.getText().equals(""))
                            tv13.setText("    "+linija.polasci[j].minToString());
                        else
                            tv13.setText(tv13.getText() + "; " + linija.polasci[j].minToString());
                        break;
                    case 14:
                        TextView tv14 = (TextView)findViewById(R.id.textView_14);
                        if(tv14.getText().equals(""))
                            tv14.setText("    "+linija.polasci[j].minToString());
                        else
                            tv14.setText(tv14.getText() + "; " + linija.polasci[j].minToString());
                        break;
                    case 15:
                        TextView tv15 = (TextView)findViewById(R.id.textView_15);
                        if(tv15.getText().equals(""))
                            tv15.setText("    "+linija.polasci[j].minToString());
                        else
                            tv15.setText(tv15.getText() + "; " + linija.polasci[j].minToString());
                        break;
                    case 16:
                        TextView tv16 = (TextView)findViewById(R.id.textView_16);
                        if(tv16.getText().equals(""))
                            tv16.setText("    "+linija.polasci[j].minToString());
                        else
                            tv16.setText(tv16.getText() + "; " + linija.polasci[j].minToString());
                        break;
                    case 17:
                        TextView tv17 = (TextView)findViewById(R.id.textView_17);
                        if(tv17.getText().equals(""))
                            tv17.setText("    "+linija.polasci[j].minToString());
                        else
                            tv17.setText(tv17.getText() + "; " + linija.polasci[j].minToString());
                        break;
                    case 18:
                        TextView tv18 = (TextView)findViewById(R.id.textView_18);
                        if(tv18.getText().equals(""))
                            tv18.setText("    "+linija.polasci[j].minToString());
                        else
                            tv18.setText(tv18.getText() + "; " + linija.polasci[j].minToString());
                        break;
                    case 19:
                        TextView tv19 = (TextView)findViewById(R.id.textView_19);
                        if(tv19.getText().equals(""))
                            tv19.setText("    "+linija.polasci[j].minToString());
                        else
                            tv19.setText(tv19.getText() + "; " + linija.polasci[j].minToString());
                        break;
                    case 20:
                        TextView tv20 = (TextView)findViewById(R.id.textView_20);
                        if(tv20.getText().equals(""))
                            tv20.setText("    "+linija.polasci[j].minToString());
                        else
                            tv20.setText(tv20.getText() + "; " + linija.polasci[j].minToString());
                        break;
                    case 21:
                        TextView tv21 = (TextView)findViewById(R.id.textView_21);
                        if(tv21.getText().equals(""))
                            tv21.setText("    "+linija.polasci[j].minToString());
                        else
                            tv21.setText(tv21.getText() + "; " + linija.polasci[j].minToString());
                        break;
                    case 22:
                        TextView tv22 = (TextView)findViewById(R.id.textView_22);
                        if(tv22.getText().equals(""))
                            tv22.setText("    "+linija.polasci[j].minToString());
                        else
                            tv22.setText(tv22.getText() + "; " + linija.polasci[j].minToString());
                        break;
                    case 23:
                        TextView tv23 = (TextView)findViewById(R.id.textView_23);
                        if(tv23.getText().equals(""))
                            tv23.setText("    "+linija.polasci[j].minToString());
                        else
                            tv23.setText(tv23.getText() + "; " + linija.polasci[j].minToString());
                        break;
                    case 0:
                        TextView tv00 = (TextView)findViewById(R.id.textView_00);
                        if(tv00.getText().equals(""))
                            tv00.setText("    "+linija.polasci[j].minToString());
                        else
                            tv00.setText(tv00.getText() + "; " + linija.polasci[j].minToString());
                        break;
                    case 1:
                        TextView tv01 = (TextView)findViewById(R.id.textView_01);
                        if(tv01.getText().equals(""))
                            tv01.setText("    "+linija.polasci[j].minToString());
                        else
                            tv01.setText(tv01.getText() + "; " + linija.polasci[j].minToString());
                        break;
                    case 2:
                        TextView tv02 = (TextView)findViewById(R.id.textView_02);
                        if(tv02.getText().equals(""))
                            tv02.setText("    "+linija.polasci[j].minToString());
                        else
                            tv02.setText(tv02.getText() + "; " + linija.polasci[j].minToString());
                        break;
                    default:
                        break;
                }
            }
        }
        else if(i==2) {
            String[] polasci = new String[linija.brSubotaP];
            for (int j = 0; j < linija.brSubotaP; j++) {
                switch (Integer.parseInt(linija.subotaPolasci[j].satToString())) {
                    case 05:
                        TextView tv5 = (TextView) findViewById(R.id.textView_05);
                        if (tv5.getText().equals(""))
                            tv5.setText("    " + linija.subotaPolasci[j].minToString());
                        else
                            tv5.setText(tv5.getText() + "; " + linija.subotaPolasci[j].minToString());
                        break;
                    case 06:
                        TextView tv6 = (TextView) findViewById(R.id.textView_06);
                        if (tv6.getText().equals(""))
                            tv6.setText("    " + linija.subotaPolasci[j].minToString());
                        else
                            tv6.setText(tv6.getText() + "; " + linija.subotaPolasci[j].minToString());
                        break;
                    case 07:
                        TextView tv7 = (TextView) findViewById(R.id.textView_07);
                        if (tv7.getText().equals(""))
                            tv7.setText("    " + linija.subotaPolasci[j].minToString());
                        else
                            tv7.setText(tv7.getText() + "; " + linija.subotaPolasci[j].minToString());
                        break;
                    case 8:
                        TextView tv8 = (TextView) findViewById(R.id.textView_08);
                        if (tv8.getText().equals(""))
                            tv8.setText("    " + linija.subotaPolasci[j].minToString());
                        else
                            tv8.setText(tv8.getText() + "; " + linija.subotaPolasci[j].minToString());
                        break;
                    case 9:
                        TextView tv9 = (TextView) findViewById(R.id.textView_09);
                        if (tv9.getText().equals(""))
                            tv9.setText("    " + linija.subotaPolasci[j].minToString());
                        else
                            tv9.setText(tv9.getText() + "; " + linija.subotaPolasci[j].minToString());
                        break;
                    case 10:
                        TextView tv10 = (TextView) findViewById(R.id.textView_10);
                        if (tv10.getText().equals(""))
                            tv10.setText("    " + linija.subotaPolasci[j].minToString());
                        else
                            tv10.setText(tv10.getText() + "; " + linija.subotaPolasci[j].minToString());
                        break;
                    case 11:
                        TextView tv11 = (TextView) findViewById(R.id.textView_11);
                        if (tv11.getText().equals(""))
                            tv11.setText("    " + linija.subotaPolasci[j].minToString());
                        else
                            tv11.setText(tv11.getText() + "; " + linija.subotaPolasci[j].minToString());
                        break;
                    case 12:
                        TextView tv12 = (TextView) findViewById(R.id.textView_12);
                        if (tv12.getText().equals(""))
                            tv12.setText("    " + linija.subotaPolasci[j].minToString());
                        else
                            tv12.setText(tv12.getText() + "; " + linija.subotaPolasci[j].minToString());
                        break;
                    case 13:
                        TextView tv13 = (TextView) findViewById(R.id.textView_13);
                        if (tv13.getText().equals(""))
                            tv13.setText("    " + linija.subotaPolasci[j].minToString());
                        else
                            tv13.setText(tv13.getText() + "; " + linija.subotaPolasci[j].minToString());
                        break;
                    case 14:
                        TextView tv14 = (TextView) findViewById(R.id.textView_14);
                        if (tv14.getText().equals(""))
                            tv14.setText("    " + linija.subotaPolasci[j].minToString());
                        else
                            tv14.setText(tv14.getText() + "; " + linija.subotaPolasci[j].minToString());
                        break;
                    case 15:
                        TextView tv15 = (TextView) findViewById(R.id.textView_15);
                        if (tv15.getText().equals(""))
                            tv15.setText("    " + linija.subotaPolasci[j].minToString());
                        else
                            tv15.setText(tv15.getText() + "; " + linija.subotaPolasci[j].minToString());
                        break;
                    case 16:
                        TextView tv16 = (TextView) findViewById(R.id.textView_16);
                        if (tv16.getText().equals(""))
                            tv16.setText("    " + linija.subotaPolasci[j].minToString());
                        else
                            tv16.setText(tv16.getText() + "; " + linija.subotaPolasci[j].minToString());
                        break;
                    case 17:
                        TextView tv17 = (TextView) findViewById(R.id.textView_17);
                        if (tv17.getText().equals(""))
                            tv17.setText("    " + linija.subotaPolasci[j].minToString());
                        else
                            tv17.setText(tv17.getText() + "; " + linija.subotaPolasci[j].minToString());
                        break;
                    case 18:
                        TextView tv18 = (TextView) findViewById(R.id.textView_18);
                        if (tv18.getText().equals(""))
                            tv18.setText("    " + linija.subotaPolasci[j].minToString());
                        else
                            tv18.setText(tv18.getText() + "; " + linija.subotaPolasci[j].minToString());
                        break;
                    case 19:
                        TextView tv19 = (TextView) findViewById(R.id.textView_19);
                        if (tv19.getText().equals(""))
                            tv19.setText("    " + linija.subotaPolasci[j].minToString());
                        else
                            tv19.setText(tv19.getText() + "; " + linija.subotaPolasci[j].minToString());
                        break;
                    case 20:
                        TextView tv20 = (TextView) findViewById(R.id.textView_20);
                        if (tv20.getText().equals(""))
                            tv20.setText("    " + linija.subotaPolasci[j].minToString());
                        else
                            tv20.setText(tv20.getText() + "; " + linija.subotaPolasci[j].minToString());
                        break;
                    case 21:
                        TextView tv21 = (TextView) findViewById(R.id.textView_21);
                        if (tv21.getText().equals(""))
                            tv21.setText("    " + linija.subotaPolasci[j].minToString());
                        else
                            tv21.setText(tv21.getText() + "; " + linija.subotaPolasci[j].minToString());
                        break;
                    case 22:
                        TextView tv22 = (TextView) findViewById(R.id.textView_22);
                        if (tv22.getText().equals(""))
                            tv22.setText("    " + linija.subotaPolasci[j].minToString());
                        else
                            tv22.setText(tv22.getText() + "; " + linija.subotaPolasci[j].minToString());
                        break;
                    case 23:
                        TextView tv23 = (TextView) findViewById(R.id.textView_23);
                        if (tv23.getText().equals(""))
                            tv23.setText("    " + linija.subotaPolasci[j].minToString());
                        else
                            tv23.setText(tv23.getText() + "; " + linija.subotaPolasci[j].minToString());
                        break;
                    case 00:
                        TextView tv00 = (TextView) findViewById(R.id.textView_00);
                        if (tv00.getText().equals(""))
                            tv00.setText("    " + linija.subotaPolasci[j].minToString());
                        else
                            tv00.setText(tv00.getText() + "; " + linija.subotaPolasci[j].minToString());
                        break;
                    case 01:
                        TextView tv01 = (TextView) findViewById(R.id.textView_01);
                        if (tv01.getText().equals(""))
                            tv01.setText("    " + linija.subotaPolasci[j].minToString());
                        else
                            tv01.setText(tv01.getText() + "; " + linija.subotaPolasci[j].minToString());
                        break;
                    case 02:
                        TextView tv02 = (TextView) findViewById(R.id.textView_02);
                        if (tv02.getText().equals(""))
                            tv02.setText("    " + linija.subotaPolasci[j].minToString());
                        else
                            tv02.setText(tv02.getText() + "; " + linija.subotaPolasci[j].minToString());
                        break;
                }

            }
        }

        else if(i==3) {
            String[] polasci = new String[linija.brNedeljaP];
            for (int j = 0; j < linija.brNedeljaP; j++) {
                switch (Integer.parseInt(linija.nedeljaPolasci[j].satToString())) {
                    case 05:
                        TextView tv5 = (TextView) findViewById(R.id.textView_05);
                        if (tv5.getText().equals(""))
                            tv5.setText("    " + linija.nedeljaPolasci[j].minToString());
                        else
                            tv5.setText(tv5.getText() + "; " + linija.nedeljaPolasci[j].minToString());
                        break;
                    case 06:
                        TextView tv6 = (TextView) findViewById(R.id.textView_06);
                        if (tv6.getText().equals(""))
                            tv6.setText("    " + linija.nedeljaPolasci[j].minToString());
                        else
                            tv6.setText(tv6.getText() + "; " + linija.nedeljaPolasci[j].minToString());
                        break;
                    case 07:
                        TextView tv7 = (TextView) findViewById(R.id.textView_07);
                        if (tv7.getText().equals(""))
                            tv7.setText("    " + linija.nedeljaPolasci[j].minToString());
                        else
                            tv7.setText(tv7.getText() + "; " + linija.nedeljaPolasci[j].minToString());
                        break;
                    case 8:
                        TextView tv8 = (TextView) findViewById(R.id.textView_08);
                        if (tv8.getText().equals(""))
                            tv8.setText("    " + linija.nedeljaPolasci[j].minToString());
                        else
                            tv8.setText(tv8.getText() + "; " + linija.nedeljaPolasci[j].minToString());
                        break;
                    case 9:
                        TextView tv9 = (TextView) findViewById(R.id.textView_09);
                        if (tv9.getText().equals(""))
                            tv9.setText("    " + linija.nedeljaPolasci[j].minToString());
                        else
                            tv9.setText(tv9.getText() + "; " + linija.nedeljaPolasci[j].minToString());
                        break;
                    case 10:
                        TextView tv10 = (TextView) findViewById(R.id.textView_10);
                        if (tv10.getText().equals(""))
                            tv10.setText("    " + linija.nedeljaPolasci[j].minToString());
                        else
                            tv10.setText(tv10.getText() + "; " + linija.nedeljaPolasci[j].minToString());
                        break;
                    case 11:
                        TextView tv11 = (TextView) findViewById(R.id.textView_11);
                        if (tv11.getText().equals(""))
                            tv11.setText("    " + linija.nedeljaPolasci[j].minToString());
                        else
                            tv11.setText(tv11.getText() + "; " + linija.nedeljaPolasci[j].minToString());
                        break;
                    case 12:
                        TextView tv12 = (TextView) findViewById(R.id.textView_12);
                        if (tv12.getText().equals(""))
                            tv12.setText("    " + linija.nedeljaPolasci[j].minToString());
                        else
                            tv12.setText(tv12.getText() + "; " + linija.nedeljaPolasci[j].minToString());
                        break;
                    case 13:
                        TextView tv13 = (TextView) findViewById(R.id.textView_13);
                        if (tv13.getText().equals(""))
                            tv13.setText("    " + linija.nedeljaPolasci[j].minToString());
                        else
                            tv13.setText(tv13.getText() + "; " + linija.nedeljaPolasci[j].minToString());
                        break;
                    case 14:
                        TextView tv14 = (TextView) findViewById(R.id.textView_14);
                        if (tv14.getText().equals(""))
                            tv14.setText("    " + linija.nedeljaPolasci[j].minToString());
                        else
                            tv14.setText(tv14.getText() + "; " + linija.nedeljaPolasci[j].minToString());
                        break;
                    case 15:
                        TextView tv15 = (TextView) findViewById(R.id.textView_15);
                        if (tv15.getText().equals(""))
                            tv15.setText("    " + linija.nedeljaPolasci[j].minToString());
                        else
                            tv15.setText(tv15.getText() + "; " + linija.nedeljaPolasci[j].minToString());
                        break;
                    case 16:
                        TextView tv16 = (TextView) findViewById(R.id.textView_16);
                        if (tv16.getText().equals(""))
                            tv16.setText("    " + linija.nedeljaPolasci[j].minToString());
                        else
                            tv16.setText(tv16.getText() + "; " + linija.nedeljaPolasci[j].minToString());
                        break;
                    case 17:
                        TextView tv17 = (TextView) findViewById(R.id.textView_17);
                        if (tv17.getText().equals(""))
                            tv17.setText("    " + linija.nedeljaPolasci[j].minToString());
                        else
                            tv17.setText(tv17.getText() + "; " + linija.nedeljaPolasci[j].minToString());
                        break;
                    case 18:
                        TextView tv18 = (TextView) findViewById(R.id.textView_18);
                        if (tv18.getText().equals(""))
                            tv18.setText("    " + linija.nedeljaPolasci[j].minToString());
                        else
                            tv18.setText(tv18.getText() + "; " + linija.nedeljaPolasci[j].minToString());
                        break;
                    case 19:
                        TextView tv19 = (TextView) findViewById(R.id.textView_19);
                        if (tv19.getText().equals(""))
                            tv19.setText("    " + linija.nedeljaPolasci[j].minToString());
                        else
                            tv19.setText(tv19.getText() + "; " + linija.nedeljaPolasci[j].minToString());
                        break;
                    case 20:
                        TextView tv20 = (TextView) findViewById(R.id.textView_20);
                        if (tv20.getText().equals(""))
                            tv20.setText("    " + linija.nedeljaPolasci[j].minToString());
                        else
                            tv20.setText(tv20.getText() + "; " + linija.nedeljaPolasci[j].minToString());
                        break;
                    case 21:
                        TextView tv21 = (TextView) findViewById(R.id.textView_21);
                        if (tv21.getText().equals(""))
                            tv21.setText("    " + linija.nedeljaPolasci[j].minToString());
                        else
                            tv21.setText(tv21.getText() + "; " + linija.nedeljaPolasci[j].minToString());
                        break;
                    case 22:
                        TextView tv22 = (TextView) findViewById(R.id.textView_22);
                        if (tv22.getText().equals(""))
                            tv22.setText("    " + linija.nedeljaPolasci[j].minToString());
                        else
                            tv22.setText(tv22.getText() + "; " + linija.nedeljaPolasci[j].minToString());
                        break;
                    case 23:
                        TextView tv23 = (TextView) findViewById(R.id.textView_23);
                        if (tv23.getText().equals(""))
                            tv23.setText("    " + linija.nedeljaPolasci[j].minToString());
                        else
                            tv23.setText(tv23.getText() + "; " + linija.nedeljaPolasci[j].minToString());
                        break;
                    case 00:
                        TextView tv00 = (TextView) findViewById(R.id.textView_00);
                        if (tv00.getText().equals(""))
                            tv00.setText("    " + linija.nedeljaPolasci[j].minToString());
                        else
                            tv00.setText(tv00.getText() + "; " + linija.nedeljaPolasci[j].minToString());
                        break;
                    case 01:
                        TextView tv01 = (TextView) findViewById(R.id.textView_01);
                        if (tv01.getText().equals(""))
                            tv01.setText("    " + linija.nedeljaPolasci[j].minToString());
                        else
                            tv01.setText(tv01.getText() + "; " + linija.nedeljaPolasci[j].minToString());
                        break;
                    case 02:
                        TextView tv02 = (TextView) findViewById(R.id.textView_02);
                        if (tv02.getText().equals(""))
                            tv02.setText("    " + linija.nedeljaPolasci[j].minToString());
                        else
                            tv02.setText(tv02.getText() + "; " + linija.nedeljaPolasci[j].minToString());
                        break;
                }
            }
        }
    }

    //(Linija,koji red voznje, indeks u nizu) dan: 1=radni dan, 2=subota, 3=nedelja
    public void populateListView(Linija linija, int dan){
        //hideAll();
       if(dan == 1){
            String[] polasci = new String[linija.brP];
            List<String> arrayList = new ArrayList<String>();
            for(int i = 0; i<linija.brP; i++){
                polasci[i] = linija.polasci[i].toString();
                arrayList.add(polasci[i].toString());
            }
            ListView listView = (ListView)findViewById(R.id.linijelist);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arrayList);
            listView.setAdapter(adapter);
        }
       else if(dan == 2){
            String[] polasci = new String[linija.brSubotaP];
            List<String> arrayList = new ArrayList<String>();
            for(int i = 0; i<linija.brSubotaP; i++){
                polasci[i] = linija.subotaPolasci[i].toString();
                arrayList.add(polasci[i].toString());
            }
            ListView listView = (ListView)findViewById(R.id.linijelist);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arrayList);
            listView.setAdapter(adapter);
       }
       else if(dan == 3){
           String[] polasci = new String[linija.brNedeljaP];
           List<String> arrayList = new ArrayList<String>();
           for(int i = 0; i<linija.brNedeljaP; i++){
               polasci[i] = linija.nedeljaPolasci[i].toString();
               arrayList.add(polasci[i].toString());
           }
           ListView listView = (ListView)findViewById(R.id.linijelist);
           ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arrayList);
           listView.setAdapter(adapter);
       }
    }

    public int countLines(String filename) throws IOException {
        //AssetManager am;
        //am = getAssets();
        //InputStream is = am.open(filename);
        Resources resources = getResources();
        int rId = resources.getIdentifier("com.nocomment.bus:raw/"+filename,null,null);
        InputStream is = resources.openRawResource(rId);
        try {
            byte[] c = new byte[1024];
            int count = 0;
            int readChars = 0;
            boolean empty = true;
            while ((readChars = is.read(c)) != -1) {
                empty = false;
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n') {
                        ++count;
                    }
                }
            }
            return (count == 0 && !empty) ? 1 : count;
        } finally {
            is.close();
        }
    }
    //Options menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_red_voznje, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Navigation panel



    //pokusaj
    public class MalibuCountDownTimer extends CountDownTimer
    {
        String selektovan;
        int dan;
        public MalibuCountDownTimer(long startTime, long interval,String x,int i)
        {
            super(startTime, interval);
            selektovan=x;
            dan=i;
        }

        @Override
        public void onFinish()
        {
            TextView text=(TextView)findViewById(R.id.vreme);
            if(dan==1)
            text.setText(odabranaLinija.izracunajPrviSledeci(MainActivity.mapa.Nis.findNode(selektovan)).toString());
            else if(dan==2)
                text.setText(odabranaLinija.izracunajPrviSledeciSubota(MainActivity.mapa.Nis.findNode(selektovan)).toString());
                else
                text.setText(odabranaLinija.izracunajPrviSledeciNedelja(MainActivity.mapa.Nis.findNode(selektovan)).toString());
        }

        @Override
        public void onTick(long millisUntilFinished)
        {
            TextView text=(TextView)findViewById(R.id.vreme);
            if(dan==1)
                text.setText(odabranaLinija.izracunajPrviSledeci(MainActivity.mapa.Nis.findNode(selektovan)).toString());
            else if(dan==2)
                text.setText(odabranaLinija.izracunajPrviSledeciSubota(MainActivity.mapa.Nis.findNode(selektovan)).toString());
            else
                text.setText(odabranaLinija.izracunajPrviSledeciNedelja(MainActivity.mapa.Nis.findNode(selektovan)).toString());
        }
    }

}
