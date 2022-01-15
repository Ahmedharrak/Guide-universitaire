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

public class UpdateActivity extends AppCompatActivity {

    UniversitesDBAdaptateur db;

    EditText editNom, editVille,editEmail,editAdresse,editTel,editEta;
    Button btnUpdate;
    ImageButton pickImag;

    byte[] image = null;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        id = getIntent().getIntExtra("id", 0);

        db = new UniversitesDBAdaptateur(this);

        Universite universite = db.getUniversiteById2(id);


        editNom = (EditText) findViewById(R.id.editName);
        editVille = (EditText) findViewById(R.id.editVille);
        pickImag = (ImageButton) findViewById(R.id.pickImg);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editAdresse = (EditText) findViewById(R.id.editAdresse);
        editTel = (EditText) findViewById(R.id.editTel);
        editEta = (EditText) findViewById(R.id.editEta);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);

        editNom.setText(universite.getNom());
        editVille.setText(universite.getVille());
        editEmail.setText(universite.getEmail());
        editAdresse.setText(universite.getAdresse());
        editTel.setText(universite.getTel() + "");
        editEta.setText(universite.getEta());

        Bitmap bitmap = BitmapFactory.decodeByteArray(universite.getImage(), 0, universite.getImage().length);
        pickImag.setImageBitmap(bitmap);
        image = getBytes(bitmap);


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nom = editNom.getText().toString();

                String ville = editVille.getText().toString();

                String email = editEmail.getText().toString();
                String adresse = editAdresse.getText().toString();
                String eta = editEta.getText().toString();

                int tel;

                if (editTel.getText().toString().equals("")) {
                    tel = 0;
                } else {
                    tel = Integer.parseInt(editTel.getText().toString());
                }
                BitmapDrawable drawable = (BitmapDrawable) pickImag.getDrawable();
                Bitmap bitmap = drawable.getBitmap();
                image = getBytes(bitmap);


                Universite newUniversite= new Universite(id, nom, ville, image,email,adresse,tel,eta);

                db.updateUniversite(newUniversite);

                Toast.makeText(UpdateActivity.this, "Mise à jour réussie", Toast.LENGTH_LONG).show();
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
            Intent i = new Intent(UpdateActivity.this, AjouterActivity.class);
            startActivity(i);
            return true;
        }
        else
        if(id == R.id.itemsupprimer) {
            Intent i = new Intent(UpdateActivity.this, SupprimerActivity.class);
            startActivity(i);
            return true;
        }
        else
        if(id == R.id.itemlister) {
            Intent i = new Intent(UpdateActivity.this, ListerActivity.class);
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