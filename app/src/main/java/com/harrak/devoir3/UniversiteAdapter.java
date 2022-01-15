package com.harrak.devoir3;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class UniversiteAdapter extends ArrayAdapter<Universite>{
    Context context;
    int resource;

    public UniversiteAdapter(@NonNull Context context, int resource, @NonNull List<Universite> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(resource, parent, false  );

        TextView tvNom = (TextView)convertView.findViewById(R.id.tvName);
        TextView tvVille = (TextView)convertView.findViewById(R.id.tvVille);
        ImageView imgUser = (ImageView) convertView.findViewById(R.id.imgUser);



        Universite currentUniversite = getItem(position);

        tvNom.setText(currentUniversite.getNom());

        tvVille.setText(currentUniversite.getVille());



        Bitmap bitmap = BitmapFactory.decodeByteArray(currentUniversite.getImage(), 0, currentUniversite.getImage().length);
        imgUser.setImageBitmap(bitmap);


        return convertView;
    }
}
