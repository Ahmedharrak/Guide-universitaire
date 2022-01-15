package com.harrak.devoir3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ListerActivity extends AppCompatActivity {

    UniversitesDBAdaptateur db;
    ListView universiteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lister);


        db = new UniversitesDBAdaptateur(this);

        universiteList = (ListView) findViewById(R.id.universiteList);




        universiteList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Universite selected_universite = (Universite) parent.getItemAtPosition(position);

                Intent intent = new Intent(ListerActivity.this, UpdateActivity.class);

                intent.putExtra("id", selected_universite.getId());

                startActivity(intent);

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        ArrayList<Universite> universites = db.getAllUniversites();

        UniversiteAdapter contactAdapter = new UniversiteAdapter(this, R.layout.item_contact, universites);

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
            Intent i = new Intent(ListerActivity.this, AjouterActivity.class);
            startActivity(i);
            return true;
        }
        else
        if(id == R.id.itemsupprimer) {
            Intent i = new Intent(ListerActivity.this, SupprimerActivity.class);
            startActivity(i);
            return true;
        }
        else
        if(id == R.id.itemlister) {
            Intent i = new Intent(ListerActivity.this, ListerActivity.class);
            startActivity(i);
            return true;
        }
        else finish();
        return super.onOptionsItemSelected(item);
    }

}