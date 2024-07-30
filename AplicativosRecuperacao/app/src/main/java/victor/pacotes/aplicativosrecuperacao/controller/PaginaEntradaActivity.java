package victor.pacotes.aplicativosrecuperacao.controller;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import victor.pacotes.aplicativosrecuperacao.R;

public class PaginaEntradaActivity extends AppCompatActivity {

    Button iniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_entrada);

        iniciar = findViewById(R.id.botaoIniciar);

        iniciar.setOnClickListener(v -> {

            Intent intent = new Intent(getApplicationContext(), ListaFotosActivity.class);

            startActivity(intent);
        });
    }
}