package com.harrak.devoir3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button ajouter_button,button_supprimer,button_lister,bottom_quitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ajouter_button = findViewById(R.id.b1);
        button_supprimer = findViewById(R.id.button_supprimer);
        button_lister = findViewById(R.id.button_lister);
        bottom_quitter = findViewById(R.id.bottom_quitter);
        ajouter_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AjouterActivity.class);
                startActivity(i);
            }
        });
        button_supprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SupprimerActivity.class);
                startActivity(i);
            }
        });
        button_lister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ListerActivity.class);
                startActivity(i);
            }
        });
        bottom_quitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_universites, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.itemajouter) {
            Intent i = new Intent(MainActivity.this, AjouterActivity.class);
            startActivity(i);
            return true;
        }
        else
        if(id == R.id.itemsupprimer) {
            Intent i = new Intent(MainActivity.this, SupprimerActivity.class);
            startActivity(i);
            return true;
        }
        else
        if(id == R.id.itemlister) {
            Intent i = new Intent(MainActivity.this, ListerActivity.class);
            startActivity(i);
            return true;
        }
        else finish();
        return super.onOptionsItemSelected(item);
    }
}