package com.harrak.devoir3;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class SupprimerActivity2 extends AppCompatActivity {



UniversitesDBAdaptateur db;

    EditText editNom, editVille;
    Button btnDelete;
    ImageButton pickImag;

    byte[] image = null;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supprimer2);

        id = getIntent().getIntExtra("id", 0);

        db = new UniversitesDBAdaptateur(this);

        Universite universite = db.getUniversiteById2(id);


        editNom = (EditText) findViewById(R.id.editName);
        editVille = (EditText) findViewById(R.id.editVille);
        pickImag = (ImageButton) findViewById(R.id.pickImg);

        btnDelete = (Button) findViewById(R.id.btnDelete);

        editNom.setText(universite.getNom());
        editVille.setText(universite.getVille());

        Bitmap bitmap = BitmapFactory.decodeByteArray(universite.getImage(), 0, universite.getImage().length);
        pickImag.setImageBitmap(bitmap);
        image = getBytes(bitmap);


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showAlert();


            }
        });


    }
    private void showAlert() {

        AlertDialog.Builder alertBilder = new AlertDialog.Builder(this);
        alertBilder.setTitle("Supprimer la confirmation")
                .setMessage("Êtes-vous sûr?")
                .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // delete contact
                        db.deleteUniversite(id);
                        finish();
                    }
                })
                .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = alertBilder.create();
        dialog.show();
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
            Intent i = new Intent(SupprimerActivity2.this, AjouterActivity.class);
            startActivity(i);
            return true;
        }
        else
        if(id == R.id.itemsupprimer) {
            Intent i = new Intent(SupprimerActivity2.this, SupprimerActivity.class);
            startActivity(i);
            return true;
        }
        else
        if(id == R.id.itemlister) {
            Intent i = new Intent(SupprimerActivity2.this, ListerActivity.class);
            startActivity(i);
            return true;
        }
        else finish();
        return super.onOptionsItemSelected(item);
    }

    public void openGalleries(View view) {

        Intent intentImg = new Intent(Intent.ACTION_GET_CONTENT);
        intentImg.setType("image/*");
        startActivityForResult(intentImg, 100);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 100) {

            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap decodeStream = BitmapFactory.decodeStream(inputStream);
                pickImag.setImageBitmap(decodeStream);

                image = getBytes(decodeStream);

            } catch (Exception ex) {
                Log.e("ex", ex.getMessage());
            }

        }
    }

    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

}