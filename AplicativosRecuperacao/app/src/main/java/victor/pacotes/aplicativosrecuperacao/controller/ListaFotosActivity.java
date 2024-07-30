package victor.pacotes.aplicativosrecuperacao.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.jetbrains.annotations.NotNull;

import victor.pacotes.aplicativosrecuperacao.GlideApp;
import victor.pacotes.aplicativosrecuperacao.R;
import victor.pacotes.aplicativosrecuperacao.model.Arquivo;

public class ListaFotosActivity extends AppCompatActivity {

    StorageReference storageRef;
    RecyclerView listaFotos;
    FirestoreRecyclerAdapter<Arquivo, ArquivoViewHolder> adapter;
    FirebaseUser firebaseUser;
    FirestoreRecyclerOptions<Arquivo> options;
    String usuarioId;
    Query query;
    Button adicionarFoto;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    FirebaseStorage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listafotos);

        storage = FirebaseStorage.getInstance();

        storageRef = storage.getReference();

        firebaseFirestore = FirebaseFirestore.getInstance();

        firebaseAuth = FirebaseAuth.getInstance();

        adicionarFoto = findViewById(R.id.botaoAdicionarFoto);

        listaFotos = findViewById(R.id.recyclerViewListaFotos);

        adicionarFoto.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), TirarFotoActivity.class);
            startActivity(intent);
        });

        firebaseUser = firebaseAuth.getCurrentUser();

        assert firebaseUser != null;

        usuarioId = firebaseUser.getUid();

        query = firebaseFirestore.collection("arquivos").whereEqualTo("usuarioId", usuarioId);

        options = new FirestoreRecyclerOptions.Builder<Arquivo>().setQuery(query, Arquivo.class).build();

        adapter = new FirestoreRecyclerAdapter<Arquivo, ArquivoViewHolder>(options) {
            @NonNull
            @NotNull
            @Override
            public ArquivoViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_foto, parent, false);

                return new ArquivoViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull @NotNull ListaFotosActivity.ArquivoViewHolder holder, int position, @NonNull @NotNull Arquivo model) {

                holder.nomeFoto.setText(model.getNomeFoto());

                StorageReference pathReference = storageRef.child("fotos/" + model.getReferenciaImagem());

                GlideApp.with(ListaFotosActivity.this).load(pathReference).into(holder.imagem);
            }
        };

        listaFotos.setHasFixedSize(true);

        listaFotos.setLayoutManager(new LinearLayoutManager(this));

        listaFotos.setAdapter(adapter);

        adapter.startListening();
    }

    private static class ArquivoViewHolder extends RecyclerView.ViewHolder {

        TextView nomeFoto;

        ImageView imagem;

        public ArquivoViewHolder(@NonNull @NotNull View itemView) {

            super(itemView);

            nomeFoto = itemView.findViewById(R.id.textViewFotoNome);

            imagem = itemView.findViewById(R.id.imageViewFoto);
        }
    }
}