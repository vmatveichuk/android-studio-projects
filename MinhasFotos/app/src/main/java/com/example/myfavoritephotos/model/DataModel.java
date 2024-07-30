package com.example.myfavoritephotos.model;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

public class DataModel {
    @SuppressLint("StaticFieldLeak")
    private static final DataModel instance = new DataModel();

    public static DataModel getInstance(){
        return instance;
    }

    private ImageDatabase database;
    public ArrayList<Image> images;
    private Context context;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setContext(Context context) {
        this.context = context;
        database = new ImageDatabase(context);
        images = database.retrieveCitiesFromDb();
        addImage(new Image("Computador", "photo1"));
        addImage(new Image("PC Gamer", "photo2"));
        addImage(new Image("Desktop", "photo3"));
        addImage(new Image("Notebook", "photo4"));
    }

    public ArrayList<Image> getImages() {
        return images;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void addImage(Image image) {
        Integer id = database.createImageInDb(image);
        if (id > 0) {
            image.setId(id);
            images.add(image);
        } else {
            Toast.makeText(context, "Add image problem", Toast.LENGTH_LONG).show();
        }
    }
}
