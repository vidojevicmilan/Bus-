package com.nocomment.bus;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;


public class AB extends Activity {
    private String array_spinnerA[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ab);
        ucitajA();
        setupbuttonKako();
        setupIstorijaButton();
        setupOmiljeneButton();
        setupLinijeButton();
        setupKontaktButton();
    }

    public void ucitajA()
    {
        array_spinnerA=new String[MainActivity.mapa.brStanica];
        for(int i=0;i<MainActivity.mapa.brStanica;i++){
            array_spinnerA[i]=MainActivity.mapa.Stanice[i].naziv;
        }
        Spinner s = (Spinner) findViewById(R.id.spinnerA);
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item, array_spinnerA);
        s.setAdapter(adapter);
       s = (Spinner) findViewById(R.id.spinnerB);
        ArrayAdapter adapter2 = new ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item, array_spinnerA);
        s.setAdapter(adapter2);
    }

    private void hideAll(){
        TextView t1=(TextView)findViewById(R.id.textViewPrvi);
        TextView t2=(TextView)findViewById(R.id.textViewDrugi);
        TextView t3=(TextView)findViewById(R.id.textViewTreci);
        TextView t4=(TextView)findViewById(R.id.textViewCetvrti);
        TextView t5=(TextView)findViewById(R.id.textViewPeti);
        t1.setText("");
        t2.setText("");
        t3.setText("");
        t4.setText("");
        t5.setText("");
    }

    private void setupbuttonKako() {
        hideAll();
        Button promeniSmerButton = (Button)findViewById(R.id.buttonKako);
        promeniSmerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1;
                String s2;
                Spinner spinner = (Spinner)findViewById(R.id.spinnerA);
                s1 = spinner.getSelectedItem().toString();
                spinner = (Spinner)findViewById(R.id.spinnerB);
                s2 = spinner.getSelectedItem().toString();
                TextView t1=(TextView)findViewById(R.id.textViewPrvi);
                TextView t2=(TextView)findViewById(R.id.textViewDrugi);
                TextView t3=(TextView)findViewById(R.id.textViewTreci);
                TextView t4=(TextView)findViewById(R.id.textViewCetvrti);
                TextView t5=(TextView)findViewById(R.id.textViewPeti);
                String povratnaVrednost=MainActivity.mapa.putAB(MainActivity.mapa.Nis.findNode(s1), MainActivity.mapa.Nis.findNode(s2));
               String pov[]=povratnaVrednost.split("\n");
                switch (pov[0]){
                    case "Ista":
                        t1.setText("Odabrali ste iste stanice...");
                        t2.setText("stigli ste! =)");
                        t3.setText("");
                        t4.setText("");
                        t5.setText("");
                        break;
                    case "Nema":
                        t1.setText("Ne postoji mogucnost...");
                        t2.setText("");
                        t3.setText("");
                        t4.setText("");
                        t5.setText("");
                        break;
                    case "Linija":
                        t1.setText("Vozite se linijom");
                        t2.setText(pov[2]);
                        t3.setText("");
                        t4.setText("");
                        t5.setText("Stizete u "+pov[1]);
                        break;
                    case "AB":
                        t1.setText("Krenite linijom");
                        t2.setText(pov[2]);
                        t3.setText("do "+pov[3]+" i sacekajte");
                        t4.setText(pov[4]);
                        t5.setText("Stizete u "+pov[1]);
                        break;
                    default:
                        t1.setText("Greska");
                        break;

                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ab, menu);
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

    private void setupOmiljeneButton() {
        ImageButton omiljeneButton = (ImageButton)findViewById(R.id.omiljenebutton);
        omiljeneButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(AB.this,Omiljene.class));
                finish();
            }
        });
    }

    private void setupIstorijaButton() {
        ImageButton istorijaButton = (ImageButton)findViewById(R.id.istorijabutton);
        istorijaButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(AB.this,Istorija.class));
                finish();
            }
        });
    }

    private void setupKontaktButton() {
        ImageButton abButton = (ImageButton)findViewById(R.id.kontaktbutton);
        abButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(AB.this,Kontakt.class));
                finish();
            }
        });
    }

    private void setupLinijeButton() {
        ImageButton linijeButton = (ImageButton)findViewById(R.id.linijebutton);
        linijeButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(AB.this,MainActivity.class));
                finish();
            }
        });
    }
}
