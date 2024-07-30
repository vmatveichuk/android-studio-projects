package com.example.galeriaimagem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText editTextName;
    EditText editTextPassword;
    EditText salvoName;
    EditText salvoSenha;
    Button buttonLogin;
    Button buttonSign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextName = findViewById(R.id.editTextTextPersonName2);
        editTextPassword = findViewById(R.id.editTextTextPersonName);
        buttonLogin = findViewById(R.id.button2);
        buttonSign = findViewById(R.id.button);
        buttonSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,
                        Criacao_usuario.class);
                ;
                startActivity(intent);
            }
        });

    }

    public void buttonLogar(View view) {
        usuario usuarioFinal = usuario.getInstance();
        String editUsername = editTextName.getText().toString();
        String editPassword = editTextPassword.getText().toString();
        boolean check=false;

        for (int i=0;i<usuarioFinal.getUsuarios().size();i++){
            if (usuarioFinal.getNameList(i).equalsIgnoreCase(editUsername) && usuarioFinal.getSenhaList(i).equalsIgnoreCase(editPassword)) {
                check=true;

                Intent intent = new Intent(getApplicationContext(), lista.class);
                intent.putExtra("username", editUsername);
                startActivity(intent);
            }
        }
        if (check==false){
            AlertDialog.Builder builder = new AlertDialog.Builder(
                    MainActivity.this);
            builder.setTitle("Login ou senha incorreto");
            builder.setMessage("quer tentar de novo?");
            builder.setNegativeButton("Sim",null);
            builder.setPositiveButton("Nao",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    });

            builder.create().show();
        }
    }
}