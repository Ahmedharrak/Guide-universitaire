package com.harrak.devoir3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class SupprimerActivity extends AppCompatActivity {


    UniversitesDBAdaptateur db;
    ListView universiteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supprimer);


        db = new UniversitesDBAdaptateur(this);

        universiteList = (ListView) findViewById(R.id.universiteList);




        universiteList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Universite selected_universite = (Universite) parent.getItemAtPosition(position);

                Intent intent = new Intent(SupprimerActivity.this, SupprimerActivity2.class);

                intent.putExtra("id", selected_universite.getId());

                startActivity(intent);

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        ArrayList<Universite> contacts = db.getAllUniversites();

        UniversiteAdapter contactAdapter = new UniversiteAdapter(this, R.layout.item_contact, contacts);

        universiteList.setAdapter(contactAdapter);

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
            Intent i = new Intent(SupprimerActivity.this, AjouterActivity.class);
            startActivity(i);
            return true;
        }
        else
        if(id == R.id.itemsupprimer) {
            Intent i = new Intent(SupprimerActivity.this, SupprimerActivity.class);
            startActivity(i);
            return true;
        }
        else
        if(id == R.id.itemlister) {
            Intent i = new Intent(SupprimerActivity.this, ListerActivity.class);
            startActivity(i);
            return true;
        }
        else finish();
        return super.onOptionsItemSelected(item);
    }

}