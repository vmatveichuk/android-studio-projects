package com.example.galeriaimagem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;

public class detalhe extends AppCompatActivity {
    EditText name;
    EditText phone;
    EditText adress;
    Spinner opcoes;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalhe);
        opcoes= findViewById(R.id.spinner);
        name = findViewById(R.id.editTextTextPersonName6);
        phone= findViewById(R.id.editTextTextPersonName7);
        adress= findViewById(R.id.editTextTextPersonName8);

//        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.opcoes, android.R.layout.simple_spinner_item);
//        opcoes.setAdapter(adapter);
    }
    final int RESULT_CAME_FROM_THIRD = 10001;
    public void adicionar(View view) {
        String nome= name.getText().toString();
        String telefone= phone.getText().toString();
        String endereco= adress.getText().toString();

        Intent envio= new Intent(getApplicationContext(), lista.class);

        Bundle parametros = new Bundle();

        parametros.putString("name",nome);
        parametros.putString("phone",telefone);
        parametros.putString("adress",endereco);

        envio.putExtras(parametros);
        startActivity(envio);
    }
}
