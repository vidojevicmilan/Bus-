package com.nocomment.bus;

        import android.app.Activity;
        import android.content.Context;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.content.Intent;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.ImageButton;
        import android.view.View.OnClickListener;
        import android.view.View;
        import android.widget.ListView;
        import android.widget.Toast;

        import java.io.BufferedReader;
        import java.io.File;
        import java.io.FileInputStream;
        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.io.OutputStreamWriter;


public class Istorija extends Activity {

    String[] historylista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_istorija);

        setupLinijeButton();
        setupOmiljeneButton();
        setupAbButton();
        setupKontaktButton();
        registerClickCallback();
    }

    @Override
    public void onResume(){
        super.onResume();
        ucitajIstoriju("istorija.txt");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_istorija, menu);
        return true;
    }

    private void registerClickCallback() {
        final ListView list = (ListView)findViewById(R.id.istorijalist);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                //TextView textView = (TextView) viewClicked;
                String message = String.valueOf(id);

                String ime = list.getItemAtPosition(position).toString();

                // Toast.makeText(Omiljene.this, message, Toast.LENGTH_LONG).show();
                //Toast.makeText(Omiljene.this, ime, Toast.LENGTH_LONG).show();

                String idString = historylista[(int)id];
                //Integer idInt = Integer.parseInt(String.valueOf(id));
                startActivity(new Intent(Istorija.this,RedVoznje.class).putExtra("naziv", idString));
                //dodajUIstoriju(ime);
                dodajUHistory("istorija.txt",ime);

            }
        });
    }

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
            File file = new File(Istorija.this.getFilesDir(), imeFajla);
            if(!file.exists()) {
                OutputStreamWriter out = new OutputStreamWriter(openFileOutput(imeFajla, MODE_WORLD_WRITEABLE));
                out.close();
                //file.createNewFile();
            }

            FileInputStream fis = Istorija.this.openFileInput(imeFajla);
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
            Toast.makeText(Istorija.this,"ERROR KOD UPISA U HISTORY\n"+e,Toast.LENGTH_SHORT).show();
        }
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

    public void ucitajIstoriju(String imeFajla){
        try{
           /* AssetManager am;
            am = getAssets();
            InputStream is = am.open(imeFajla);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader B=new BufferedReader(isr);*/
            int count = 0;

            FileInputStream fis = Istorija.this.openFileInput(imeFajla);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder B = new StringBuilder();
            String line;

            String favorites[]=new String[20];
            int i=0;
            while ((line = bufferedReader.readLine()) != null) {
                favorites[i++]=line;
            }

/*
            File file = new File(Omiljene.this.getFilesDir(), imeFajla);
            String string = "Hello world!";
            FileOutputStream outputStream;
            outputStream = openFileOutput(imeFajla, Omiljene.this.MODE_PRIVATE);
            outputStream.write(string.getBytes());
*/
            // count = countLines(imeFajla)+1;
            if(i>10)
                i=10;
           // Toast.makeText(Istorija.this, Integer.toString(count), Toast.LENGTH_SHORT).show();
            String zaAdapter[]=new String[i];
            for(int j=0;j<i;j++){
                zaAdapter[j]=favorites[j];
            }
            historylista=zaAdapter;

            //    String favorites[]=B.split("\n");

            //for(int i=0;i<count;i++)
            //     favorites[i]=bufferedReader.readLine();


            //outputStream.close();
            //B.close();
            //Build adapter


            // while ((line = bufferedReader.readLine()) != null) {
            //      B.append(line+"\n");
            // }
            ListView list = (ListView)findViewById(R.id.istorijalist);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,                     //Context for the activity
                    R.layout.linijelistitem, //Layout to use
                    zaAdapter);                  //Items to display

            //Configure the list view

            list.setAdapter(adapter);
            list.setEnabled(true);
        }
        catch(Exception e){
            Toast.makeText(Istorija.this, "Nema podataka o predjasnjem pretrazivanju.", Toast.LENGTH_SHORT).show();
        }
    }


    private void setupLinijeButton() {
        ImageButton linijeButton = (ImageButton)findViewById(R.id.linijebutton);
        linijeButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(Istorija.this,MainActivity.class));
                finish();
            }
        });
    }

    private void setupOmiljeneButton() {
        ImageButton omiljeneButton = (ImageButton)findViewById(R.id.omiljenebutton);
        omiljeneButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(Istorija.this,Omiljene.class));
                finish();
            }
        });
    }

    private void setupAbButton() {
        ImageButton abButton = (ImageButton)findViewById(R.id.abbutton);
        abButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(Istorija.this,AB.class));
                finish();
            }
        });
    }

    private void setupKontaktButton() {
        ImageButton kontaktButton = (ImageButton)findViewById(R.id.kontaktbutton);
        kontaktButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(Istorija.this,Kontakt.class));
                finish();
            }
        });
    }
}
