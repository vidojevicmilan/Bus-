package com.nocomment.bus;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.test.suitebuilder.annotation.LargeTest;
import android.util.EventLog;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class MainActivity extends Activity{
    boolean ucitaoFajlove=false;
    private String[] sveLinije;
    String idString = null;
    Integer idInt = null;
    public static Mapa mapa;
    public int brojac = 0;
    String[] s;
    //Mapa mapa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView l=(ListView)findViewById(R.id.linijelist);
        clearList(l);
        mapa=new Mapa();

    if(ucitaoFajlove==false) {
        this.fajlUnosSvihStanica("stanice.txt");
        this.ucitajStanice();
        this.ucitajPuteve("putevi.txt");
        this.unosSvihLinija("linije.txt");
        this.unosPodatakaOLinijama();
        sveLinije=new String[mapa.brLinija];
        pupuniSveLinije();
        populateListView();
        ucitaoFajlove=true;
    }
        setupLongClick();
        setupIstorijaButton();
        setupOmiljeneButton();
        setupAbButton();
        setupKontaktButton();

        //int brLinija = 0;
       // try {
       //     brLinija = countLines("stanice.txt");
       // } catch (IOException e) {
       //     e.printStackTrace();
       // }
        //TextView tv = (TextView)findViewById(R.id.textView);
        //tv.setText(Integer.toString(mapa.brLinija));

        registerClickCallback();
    }
    private void pupuniSveLinije(){
        for(int i=0;i< mapa.brLinija;i++)
            sveLinije[i]=mapa.Linije[i].nazivLinije;
    }

    public void clearList(ListView lv){
        String[] string = new String[1];
                string[0] = "";
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,                     //Context for the activity
                R.layout.linijelistitem, //Layout to use
                string);                  //Items to display

        //Configure the list view

        lv.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    private void setupKontaktButton() {
        ImageButton kontaktButton = (ImageButton)findViewById(R.id.kontaktbutton);
        kontaktButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this,Kontakt.class));
                finish();
            }
        });
    }

    private void setupOmiljeneButton() {
        ImageButton omiljeneButton = (ImageButton)findViewById(R.id.omiljenebutton);
        omiljeneButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this,Omiljene.class));
                finish();
            }
        });
    }

    private void setupIstorijaButton() {
        ImageButton istorijaButton = (ImageButton)findViewById(R.id.istorijabutton);
        istorijaButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this,Istorija.class));
                finish();
            }
        });
    }

    private void setupAbButton() {
        ImageButton abButton = (ImageButton)findViewById(R.id.abbutton);
        abButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this,AB.class));
                finish();
            }
        });
    }

    public void populateListView() {
        //Create list of items
        //baguje ovo sranje!!!
       // String[] linije=new String[mapa.brLinija];
       // for(int i=0;i<mapa.brLinija;i++)
       //     linije[i]=mapa.Linije[i].nazivLinije;
        ListView list = (ListView)findViewById(R.id.linijelist);
        s = new String[]{"Minovo Naselje - Niska Banja","Bubanj - Donja Vrezina","Njegoseva - Donji Komren",
                "Zeleznicka stanica - Somborska","Mokranjceva - Medosevac","Kruzna linija - smer A","Zeleznicka stanica - Duvaniste"};
       // s = new String[]{"Minovo Naselje - Niska Banja","Bubanj - Donja Vrezina","Njegoseva - Donji Komren",
       //         "Zeleznicka stanica - Somborska"};


        //Build adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,                     //Context for the activity
                                                                R.layout.linijelistitem, //Layout to use
                                                                s);                  //Items to display

        //Configure the list view

        list.setAdapter(adapter);
        list.setEnabled(true);
        //list.setAdapter(null);

    }

    private void registerClickCallback() {
        final ListView list = (ListView)findViewById(R.id.linijelist);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                //TextView textView = (TextView) viewClicked;
                String message = String.valueOf(id);

                String ime = list.getItemAtPosition(position).toString();

               // Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
                //Toast.makeText(MainActivity.this, ime, Toast.LENGTH_LONG).show();

                String idString = s[(int)id];
                //Integer idInt = Integer.parseInt(String.valueOf(id));
                startActivity(new Intent(MainActivity.this,RedVoznje.class).putExtra("naziv", idString));
                //dodajUIstoriju(ime);
                dodajUHistory("istorija.txt",ime);

            }
        });
    }
    //dodavanje u omiljene linije
    public void dodajUOmiljene(String imeFajla,String novaLinija){

        try{
            /*AssetManager am;
            am = getAssets();
            InputStream is = am.open(imeFajla);
            InputStreamReader isr = new InputStreamReader(is);

            BufferedReader b=new BufferedReader(isr);
            int count;
            count = countLines(imeFajla)+1;
            BufferedReader B=new BufferedReader(isr);
            String favorites[]=new String[count];
            for(int i=0;i<count;i++)
                favorites[i]=B.readLine();
            B.close();
            int i=0;*/
            //File file = new File("data/data/com.nocomment.bus/"+imeFajla);
            File file = new File(MainActivity.this.getFilesDir(), imeFajla);
            if(!file.exists()) {
                OutputStreamWriter out = new OutputStreamWriter(openFileOutput(imeFajla, MODE_WORLD_WRITEABLE));
                out.close();
                //file.createNewFile();
            }

            FileInputStream fis = MainActivity.this.openFileInput(imeFajla);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder B = new StringBuilder();
            String line;

            String favorites[]=new String[20];
            int count=0;
            while ((line = bufferedReader.readLine()) != null) {
                favorites[count++]=line;
            }
            int i=0;
            while(i<count){
                if(favorites[i].equals(novaLinija))
                {
                    Toast.makeText(MainActivity.this,novaLinija + " vec postoji u omiljenim.",Toast.LENGTH_SHORT).show();
                    return;
                }
                i++;
            }
            //File file=new File(imeFajla);
            //OutputStreamWriter out = new OutputStreamWriter(openFileOutput(imeFajla, MODE_WORLD_WRITEABLE));
            //File file = new File(MainActivity.this.getFilesDir(), imeFajla);
            String filename = imeFajla;
            FileOutputStream outputStream;
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            for(int j=0;j<count;j++){
                //out.write(favorites[j]+"\n");
                outputStream.write(favorites[j].getBytes());
                outputStream.write("\n".getBytes());
                //Toast.makeText(MainActivity.this,"Upisuje",Toast.LENGTH_SHORT).show();
            }
            //out.write(novaLinija + "\n");
            outputStream.write(novaLinija.getBytes());
            outputStream.write("\n".getBytes());
            Toast.makeText(MainActivity.this,novaLinija+" je upisana u omiljene.",Toast.LENGTH_SHORT).show();
            outputStream.close();
            //out.close();
        }
        catch(IOException e){
            Toast.makeText(MainActivity.this,"ERROR KOD UPISA NOVE LINIJE\n"+e,Toast.LENGTH_SHORT).show();
        }
    }
    //dodavanje u istoriju
    public void dodajUHistory(String imeFajla, String novaLinija){

        try{
            /*AssetManager am;
            am = getAssets();
            InputStream is = am.open(imeFajla);
            InputStreamReader isr = new InputStreamReader(is);

            BufferedReader b=new BufferedReader(isr);
            int count;
            count = countLines(imeFajla)+1;
            BufferedReader B=new BufferedReader(isr);
            String favorites[]=new String[count];
            for(int i=0;i<count;i++)
                favorites[i]=B.readLine();
            B.close();
            int i=0;*/
            //File file = new File("data/data/com.nocomment.bus/"+imeFajla);
            File file = new File(MainActivity.this.getFilesDir(), imeFajla);
            if(!file.exists()) {
                OutputStreamWriter out = new OutputStreamWriter(openFileOutput(imeFajla, MODE_WORLD_WRITEABLE));
                out.close();
                //file.createNewFile();
            }

            FileInputStream fis = MainActivity.this.openFileInput(imeFajla);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder B = new StringBuilder();
            String line;
            //sve linije
            String favorites[]=new String[20];
            int count=0;
            while ((line = bufferedReader.readLine()) != null) {
                favorites[count++]=line;
            }
            String pom[]=new String[20];
            int i=0;
            int i2=0;
            while(i<count){
                if(!favorites[i].equals(novaLinija))
                {
                    //Toast.makeText(MainActivity.this,novaLinija + " vec postoji u omiljenim.",Toast.LENGTH_SHORT).show();
                    pom[i2++]=favorites[i];
                }
                i++;
            }
            //File file=new File(imeFajla);
            //OutputStreamWriter out = new OutputStreamWriter(openFileOutput(imeFajla, MODE_WORLD_WRITEABLE));
            //File file = new File(MainActivity.this.getFilesDir(), imeFajla);
            String filename = imeFajla;
            FileOutputStream outputStream;
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(novaLinija.getBytes());
            outputStream.write("\n".getBytes());
            //Toast.makeText(MainActivity.this,"Upisuje novu history",Toast.LENGTH_SHORT).show();
            if(i2>10)
                i2=10;
            for(int j=0;j<i2;j++){
                //out.write(favorites[j]+"\n");
                outputStream.write(pom[j].getBytes());
                outputStream.write("\n".getBytes());
                //Toast.makeText(MainActivity.this,"Upisuje history",Toast.LENGTH_SHORT).show();
            }
            //out.write(novaLinija + "\n");


            outputStream.close();
            //out.close();
        }
        catch(IOException e){
            Toast.makeText(MainActivity.this,"ERROR KOD UPISA U HISTORY\n"+e,Toast.LENGTH_SHORT).show();
        }
    }


    public void setupLongClick(){
        final ListView listView = (ListView)findViewById(R.id.linijelist);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String ime = listView.getItemAtPosition(position).toString();
                dodajUOmiljene("omiljene.txt" ,ime);
                return true;
            }
        });

    }

    public void dodajUOmiljene(String ime){
        Toast.makeText(MainActivity.this, ime, Toast.LENGTH_SHORT).show();

    }

    public void dodajUIstoriju(String ime){
        String[] istorija = new String[mapa.brLinija+1];

        try {
            AssetManager am = getAssets();
            InputStream is = am.open(ime);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader b=new BufferedReader(isr);

            int i = countLines("istorija.txt");
            int j = 0;

            while(j<i){
                istorija[j] = b.readLine();
                j++;
            }

            for(j=0; j < i; j++){
                if(istorija[j].equals(ime)){
                    String pom = istorija[j];
                    istorija[0] = istorija[j];
                    istorija[j] = pom;

                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public int countLines(String filename) throws IOException {
        AssetManager am;
        am = getAssets();
        InputStream is = am.open(filename);
        //InputStream is = getResources().openRawResource(R.raw.nbanja);
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

    public void ucitajPuteve(String ime){
        //graf.InsertEdgeSVremenom(1, 2,new Vreme(0,5,0));
        //format stanica1,stanica2,vreme:sat minut sekund
		/*
		 String pom=b.readLine();
		String[] s=pom.split(" ");
		ime= s[0];
		godina=Integer.parseInt(s[1]);
		ocena=Integer.parseInt(s[2]);
		 */
        try{
            AssetManager am;
            am = getAssets();
            InputStream is = am.open(ime);
            InputStreamReader isr = new InputStreamReader(is);
            //FileReader f = new FileReader(ime);
            BufferedReader b=new BufferedReader(isr);

            String ucitan = b.readLine();
            while(ucitan!=null)
            {
                String[] pom=ucitan.split("	");
                this.mapa.Nis.InsertEdgeSVremenom(Integer.parseInt(pom[0]),Integer.parseInt(pom[1]),new Vreme(Integer.parseInt(pom[2]),Integer.parseInt(pom[3]),Integer.parseInt(pom[4])));
                ucitan=b.readLine();

            }
            b.close();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }

    }

    protected void fajlUnesiStanicu(String ime){
        if (mapa.Stanice==null){
            mapa.Stanice=new LinkedNode[1];
            mapa.Stanice[mapa.brStanica++]=new LinkedNode(mapa.brStanica,ime);
            //Stanice[brStanica-1].naziv=ime;
        }
        else
        {
            LinkedNode pom[]=new LinkedNode[mapa.brStanica];
            for(int i =0;i<mapa.brStanica;i++)
                pom[i]=mapa.Stanice[i];
            mapa.Stanice=new LinkedNode[mapa.brStanica+1];
            for(int i =0;i<mapa.brStanica;i++)
                mapa.Stanice[i]=pom[i];
            mapa.Stanice[mapa.brStanica++]=new LinkedNode(mapa.brStanica);
            mapa.Stanice[mapa.brStanica-1].naziv=ime;
        }
    }

    public void ucitajStanice(){
          int i=0;
           while(i++<mapa.brStanica)
           {
               mapa.Nis.InsertNode(i,mapa.Stanice[i-1].naziv);//voditi racuna da Node dobijaju indexe od 1 a ne od 0!!!
           }
    }

    public void fajlUnosSvihStanica(String ime){
        try{

            AssetManager am;
            am = getAssets();
            InputStream is = am.open(ime);
            InputStreamReader isr = new InputStreamReader(is);
            //FileReader f = new FileReader(ime);
            BufferedReader b=new BufferedReader(isr);
            //FileReader f = new FileReader(ime);
            //BufferedReader b=new BufferedReader(f);

            String imeStanice = b.readLine();
            while(imeStanice!=null)
            {
                this.fajlUnesiStanicu(imeStanice);
                imeStanice=b.readLine();
            }
            b.close();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }

    protected void unesiLiniju(String ime){
        if (mapa.Linije==null){
            mapa.Linije=new Linija[1];
            mapa.Linije[mapa.brLinija++]=new Linija(ime);
        }
        else
        {
            Linija pom[]=new Linija[mapa.brLinija];
            for(int i =0;i<mapa.brLinija;i++)
                pom[i]=mapa.Linije[i];
            mapa.Linije=new Linija[mapa.brLinija+1];
            for(int i =0;i<mapa.brLinija;i++)
                mapa.Linije[i]=pom[i];
            mapa.Linije[mapa.brLinija++]=new Linija(ime);
        }
    }

    public void unosSvihLinija(String ime){
        try{

            AssetManager am;
            am = getAssets();
            InputStream is = am.open(ime);
            InputStreamReader isr = new InputStreamReader(is);
            //FileReader f = new FileReader(ime);
            BufferedReader b=new BufferedReader(isr);
            //FileReader f = new FileReader(ime);
            //BufferedReader b=new BufferedReader(f);

            String imeLinije = b.readLine();
            while(imeLinije!=null)
            {
                this.unesiLiniju(imeLinije);
                imeLinije=b.readLine();
            }
            b.close();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }

    public void unosLinije(String ime,int i)
    {
        try{

            AssetManager am;
            am = getAssets();
            InputStream is = am.open(ime);
            InputStreamReader isr = new InputStreamReader(is);
            //FileReader f = new FileReader(ime);
            BufferedReader b=new BufferedReader(isr);
            //FileReader f = new FileReader(ime);
            //BufferedReader b=new BufferedReader(f);
            String pom = b.readLine();
            int br=0;
            this.mapa.Linije[i].id=Integer.parseInt(pom);
            pom=b.readLine();
            this.mapa.Linije[i].smer=Integer.parseInt(pom);
            pom=b.readLine();
            while(!pom.equals("vremepolazaka"))
            {
                if(br==0){
                     this.mapa.Linije[i].addToRuta(mapa.Nis.findNode(Integer.parseInt(pom)));
                     br=(br+1)%2;
                    //System.out.println(br);
                    pom=b.readLine();
                }
                else
                {
                    String[] s=pom.split(" ");
                    this.mapa.Linije[i].addToRuta(mapa.Nis.findEdge(Integer.parseInt(s[0]),Integer.parseInt(s[1])));
                    br=(br+1)%2;
                    //System.out.println(br);
                    pom=b.readLine();

                }
            }
            pom=b.readLine();


            while(!pom.equals("subotnjiredvoznje"))
            {
                this.mapa.Linije[i].addToPolasci(new Vreme(pom));
                pom=b.readLine();
            }
            pom=b.readLine();

            while(!pom.equals("nedeljniredvoznje"))
            {
                this.mapa.Linije[i].addToSubotaPolasci(new Vreme(pom));
                pom=b.readLine();
            }
            pom=b.readLine();


            while(pom!=null)
            {
                this.mapa.Linije[i].addToNedeljaPolasci(new Vreme(pom));
                pom=b.readLine();
            }

            b.close();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }

    public void unosPodatakaOLinijama(){
        //System.out.println("broj linija je:"+this.brLinija);
        for(int i=0;i<this.mapa.brLinija;i++) {
            //Log.v("Log: ",this.mapa.Linije[i].nazivLinije);

            this.unosLinije(this.mapa.Linije[i].nazivLinije + ".txt", i);
        }
    }

}

