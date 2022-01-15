package com.harrak.devoir3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.accessibility.AccessibilityManager;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.harrak.devoir3.Universite;

import java.util.ArrayList;


 public class UniversitesDBAdaptateur extends SQLiteOpenHelper  {


    private static final String DB_NAME = "universites.db";
    private static final int DB_VESION = 4;

    private static final String KEY_ID = "id";
    private static final String KEY_NOM = "nom";
    private static final String KEY_VILLE = "ville";
    private static final String KEY_IMG = "image";
     private static final String KEY_TEL = "tel";
     private static final String KEY_EMAIL = "email";
     private static final String KEY_ADRESSE = "adresse";
     private static final String KEY_ETA = "eta";



     private static final String TABLE_UNIVERSITES = "table_universites";


    public UniversitesDBAdaptateur(Context context) {
        super(context, DB_NAME, null, DB_VESION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String create_table = "create table " + TABLE_UNIVERSITES + "(" + KEY_ID + " integer primary key autoincrement,"
                + KEY_NOM + " varchar(255) DEFAULT'',"
                + KEY_VILLE + " varchar(255) DEFAULT'',"
                + KEY_IMG + " blob, "
                + KEY_EMAIL + "  TEXT, "
                + KEY_ADRESSE + " TEXT, "
                + KEY_TEL + " integer, "
                + KEY_ETA + " TEXT)";

        Log.d("create", create_table);
        db.execSQL(create_table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String delete_query = "DROP table if exists " + TABLE_UNIVERSITES;
        db.execSQL(delete_query);

        onCreate(db);

    }


    public void AjouterUniversite(Universite universite) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NOM, universite.getNom());
        values.put(KEY_VILLE, universite.getVille());
        values.put(KEY_IMG, universite.getImage());
        values.put(KEY_EMAIL, universite.getEmail());
        values.put(KEY_ADRESSE, universite.getAdresse());
        values.put(KEY_TEL, universite.getTel());
        values.put(KEY_ETA,universite.getEta());
        db.insert(TABLE_UNIVERSITES, null, values);

    }

    public ArrayList<Universite> getAllUniversites() {
        ArrayList<Universite> universites = new ArrayList<>();

        String select_query = "select * from " + TABLE_UNIVERSITES;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        if (cursor.moveToFirst()) {

            do {

                int id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
                String nom = cursor.getString(cursor.getColumnIndex(KEY_NOM));
                String ville = cursor.getString(cursor.getColumnIndex(KEY_VILLE));
                byte[] image = cursor.getBlob(cursor.getColumnIndex(KEY_IMG));
                String email = cursor.getString(cursor.getColumnIndex(KEY_EMAIL));
                String adresse = cursor.getString(cursor.getColumnIndex(KEY_ADRESSE));
                int tel = cursor.getInt(cursor.getColumnIndex(KEY_TEL));
                String eta = cursor.getString(cursor.getColumnIndex(KEY_ETA));

                Universite universite = new Universite(id, nom, ville, image,email,adresse,tel,eta);

                universites.add(universite);

            } while (cursor.moveToNext());

        }

        return universites;
    }

    public Universite getUniversiteById(int id) {

        SQLiteDatabase db = this.getReadableDatabase();

        String select_query = "select * from " + TABLE_UNIVERSITES + "where id=" + id;

        Cursor cursor = db.rawQuery(select_query, null);

        Universite universite = null;

        if (cursor.moveToFirst()) {

            int id_universite = cursor.getInt(cursor.getColumnIndex(KEY_ID));
            String nom = cursor.getString(cursor.getColumnIndex(KEY_NOM));
            String ville = cursor.getString(cursor.getColumnIndex(KEY_VILLE));
            byte[] image = cursor.getBlob(cursor.getColumnIndex(KEY_IMG));
            String email = cursor.getString(cursor.getColumnIndex(KEY_EMAIL));
            String adresse = cursor.getString(cursor.getColumnIndex(KEY_ADRESSE));
            int tel = cursor.getInt(cursor.getColumnIndex(KEY_TEL));
            String eta = cursor.getString(cursor.getColumnIndex(KEY_ETA));


            universite = new Universite(id_universite, nom, ville, image,email,adresse,tel,eta);

        }
        return universite;
    }

    public Universite getUniversiteById2(int id) {

        Universite universite = null;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_UNIVERSITES, new String[]{"id", "nom", "ville", "image", "email", "adresse", "tel","eta"}, "id=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor.moveToFirst()) {

            int id_universite = cursor.getInt(cursor.getColumnIndex(KEY_ID));
            String nom = cursor.getString(cursor.getColumnIndex(KEY_NOM));
            String ville = cursor.getString(cursor.getColumnIndex(KEY_VILLE));
            byte[] image = cursor.getBlob(cursor.getColumnIndex(KEY_IMG));
            String email = cursor.getString(cursor.getColumnIndex(KEY_EMAIL));
            String adresse = cursor.getString(cursor.getColumnIndex(KEY_ADRESSE));
            int tel = cursor.getInt(cursor.getColumnIndex(KEY_TEL));
            String eta = cursor.getString(cursor.getColumnIndex(KEY_ETA));

            universite = new Universite(id_universite, nom, ville, image,email,adresse,tel,eta);

        }

        return universite;

    }

    public void updateUniversite(Universite universite) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NOM, universite.getNom());
        values.put(KEY_VILLE, universite.getVille());
        values.put(KEY_IMG, universite.getImage());
        values.put(KEY_EMAIL, universite.getEmail());
        values.put(KEY_ADRESSE, universite.getAdresse());
        values.put(KEY_TEL, universite.getTel());
        values.put(KEY_ETA, universite.getEta());

        db.update(TABLE_UNIVERSITES, values, "id=?", new String[]{String.valueOf(universite.getId())});

    }


    public void deleteUniversite(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_UNIVERSITES, "id=?", new String[]{String.valueOf(id)});

    }




}

