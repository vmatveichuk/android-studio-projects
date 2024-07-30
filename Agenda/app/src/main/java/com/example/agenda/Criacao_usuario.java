package com.example.agenda;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Criacao_usuario extends AppCompatActivity {
    Button salvar;
    EditText nameCriado;
    EditText senhaCriado;
    usuario usuarioBase = usuario.getInstance();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        salvar = findViewById(R.id.button3);
        System.out.println(R.id.editTextTextPersonName3);
        nameCriado = findViewById(R.id.editTextTextPersonName3);
        senhaCriado = findViewById(R.id.editTextTextPersonName4);

    }
    public void salvar(View view) {
        //System.out.println(nameCriado.getText().toString());
        usuarioBase.setUsuarios(nameCriado.getText().toString(),senhaCriado.getText().toString());
    }
}
