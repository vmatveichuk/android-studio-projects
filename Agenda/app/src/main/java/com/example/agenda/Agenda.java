package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


public class Agenda extends AppCompatActivity {

    ListView listView;
    ArrayList<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        listView = findViewById(R.id.listViewItens);
        Button buttonAdd = findViewById(R.id.buttonAdd);

        loadListaFromFile();
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setSelector(android.R.color.darker_gray);

        updateListView();

        View.OnClickListener buttonAddOnClick = v -> {
            Intent intent = new Intent(Agenda.this, agendaActivtyItem.class);
            startActivity(intent);
        };
        buttonAdd.setOnClickListener(buttonAddOnClick);
    }

    void updateListView(){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                Agenda.this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                list
        );
        listView.setAdapter(adapter);
        saveListaToFile();
    }
    void loadListaFromFile(){
        try{
            InputStream stream = openFileInput("list.txt");
            InputStreamReader streamReader = new InputStreamReader(stream);
            BufferedReader reader = new BufferedReader(streamReader);
            String line;
            while((line = reader.readLine()) != null){
                list.add(line);
            }
            reader.close();
            streamReader.close();
            stream.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    void saveListaToFile(){
        try {
            OutputStream stream = Agenda.this.openFileOutput(
                    "lista.txt",
                    MODE_PRIVATE
            );
            OutputStreamWriter writer = new OutputStreamWriter(stream);
            for(int i=0;i<list.size();i++){
                writer.write(list.get(i));
                writer.write("\n");
            }
            writer.flush();
            writer.close();
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}