package com.example.galeriaimagem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class lista  extends AppCompatActivity {
       Button buttonAdd;
//    RecyclerView reciclerView;
//    List<String> contatos;
//    final int RESULT_CAME_FROM_THIRD = 10001;

//    String opcoes;

    private ArrayList<contato> contatoList;
    private RecyclerView rvContato;
    private static final String TAG = "lista";

    String nameNew;
    String telefoneNew;
    String enderecoNew;

    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Intent recebe= getIntent();

        Bundle parametros= recebe.getExtras();

        if (parametros!=null){
            nameNew=parametros.getString("name");
            telefoneNew=parametros.getString("phone");
            enderecoNew=parametros.getString("adress");
            contatoList.add(new contato(nameNew, telefoneNew, enderecoNew));
            setData();
        }

        init();
        generateData();
        setData();

//        buttonAdd= findViewById(R.id.button4);
//        reciclerView = findViewById(R.id.list);
//        contatos= new ArrayList<>();
//        contatos.add(name);
//        contatos.add(adress);
//        contatos.add(phone);
//        contatos.add(opcoes);
//
//        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getApplicationContext());
//        reciclerView.setLayoutManager(linearLayoutManager);

       // customAdapter customAdapter= new customAdapter((ArrayList<String>) contatos, lista.this);
        //reciclerView.setAdapter(customAdapter);
//        buttonAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(lista.this,
//                        detalhe.class);
//                ;
//                startActivityForResult(intent,RESULT_CAME_FROM_THIRD);
//            }
//        });
    }
    private void init() {
        contatoList = new ArrayList<>();
        rvContato = findViewById(R.id.rv_contatos);
    }
  
    private void generateData() {
        contatoList.add(new contato("Joao", "6144-0790", "Thurusbango,60"));
        contatoList.add(new contato("Lucas", "1234-0894", "Araucaria,48"));
        contatoList.add(new contato("Filipe", "4579-0131", "Brasilio,100"));
        contatoList.add(new contato("Victor", "4164-1029", "Iguaçu,54"));
        contatoList.add(new contato("Caique", "1234-5479", "SeteSetembro,121"));
        contatoList.add(new contato("Matias", "4564-4113", "Thrusbago,80"));
    }
    private void setData() {
        rvContato.setLayoutManager(new LinearLayoutManager(this));
        rvContato.setAdapter(new contatosAdapter(this,contatoList));
    }

    public void add(View view) {
        Intent intent = new Intent(lista.this,
                detalhe.class);
        ;
        startActivity(intent);
    }

    public void atualiza(View view){
        System.out.println("AUGUSTO VAI SAIR PELADO");
    }


}
