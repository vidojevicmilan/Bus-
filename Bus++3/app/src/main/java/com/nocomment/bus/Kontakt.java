package com.nocomment.bus;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.ImageButton;
import android.view.View.OnClickListener;
import android.view.View;
import android.net.Uri;
import android.widget.Button;
import android.widget.TextView;


public class Kontakt extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kontakt);

        setupIstorijaButton();
        setupOmiljeneButton();
        setupAbButton();
        setupLinijeButton();

        setupEmailButton();
    }

    private void setupEmailButton() {
        Button emailButton = (Button)findViewById(R.id.emailbutton);

        emailButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String subject = new String("BusPlusPlus Feedback");
                String email = new String("No.Comment.Dev@gmail.com");
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri data = Uri.parse("mailto:" + email + "?subject=" + subject);
                intent.setData(data);
                startActivity(intent);

                finish();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_kontakt, menu);
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

    private void setupLinijeButton() {
        ImageButton linijeButton = (ImageButton)findViewById(R.id.linijebutton);
        linijeButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(Kontakt.this,MainActivity.class));
                finish();

            }
        });
    }

    private void setupOmiljeneButton() {
        ImageButton omiljeneButton = (ImageButton)findViewById(R.id.omiljenebutton);
        omiljeneButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(Kontakt.this,Omiljene.class));
                finish();
            }
        });
    }

    private void setupIstorijaButton() {
        ImageButton historyButton = (ImageButton)findViewById(R.id.istorijabutton);
        historyButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(Kontakt.this,Istorija.class));
                finish();
            }
        });
    }

    private void setupAbButton() {
        ImageButton abButton = (ImageButton)findViewById(R.id.abbutton);
        abButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(Kontakt.this,AB.class));
                finish();
            }
        });
    }
}
