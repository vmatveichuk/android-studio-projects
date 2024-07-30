package victor.pacotes.aplicativosrecuperacao.controller;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

import victor.pacotes.aplicativosrecuperacao.R;
import victor.pacotes.aplicativosrecuperacao.model.Arquivo;

public class TirarFotoActivity extends AppCompatActivity {
    ImageView imagemFotoTirada;
    EditText editTextNomeFoto;

    FirebaseStorage storage;
    StorageReference storageRef;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;

    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tirar_foto);

        Button botaoTirar = findViewById(R.id.botaoTirarFoto);
        Button botaoSalvar = findViewById(R.id.botaoSalvarFoto);
        editTextNomeFoto = findViewById(R.id.editTextNomeFoto);
        imagemFotoTirada = findViewById(R.id.imagemFotoTirada);

        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        botaoTirar.setOnClickListener(v -> dispatchTakePictureIntent());

        botaoSalvar.setOnClickListener(v -> uploadDeImagem());
    }

    @SuppressLint("QueryPermissionsNeeded")
    private void dispatchTakePictureIntent() {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {

            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {

            Bundle extras = data.getExtras();

            Bitmap imageBitmap = (Bitmap) extras.get("data");

            imagemFotoTirada.setImageBitmap(imageBitmap);
        }
    }

    private void uploadDeImagem() {

        ProgressDialog progressDialog
                = new ProgressDialog(this);
        progressDialog.setTitle("Fazendo o upload...");
        progressDialog.show();

        imagemFotoTirada.setDrawingCacheEnabled(true);

        imagemFotoTirada.buildDrawingCache();

        Bitmap bitmap = ((BitmapDrawable) imagemFotoTirada.getDrawable()).getBitmap();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

        byte[] data = baos.toByteArray();

        String referenciaFoto = System.currentTimeMillis() + ".JPEG";

        StorageReference fileRef = storage
                .getReference()
                .child("fotos")
                .child(referenciaFoto);

        UploadTask uploadTask = fileRef.putBytes(data);

        uploadTask.addOnFailureListener(exception -> progressDialog.dismiss()).addOnSuccessListener(taskSnapshot -> {

            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

            assert firebaseUser != null;

            String usuarioId = firebaseUser.getUid();

            String nomeFoto = editTextNomeFoto.getText().toString();

            Arquivo arquivo = new Arquivo(usuarioId, nomeFoto, referenciaFoto);

            firebaseFirestore.collection("arquivos").document().set(arquivo).addOnSuccessListener(unused -> {

                Toast.makeText(this, "Arquivo salvo", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), ListaFotosActivity.class);

                startActivity(intent);

            }).addOnFailureListener(e -> Toast.makeText(this, "Falha "+e.getMessage(), Toast.LENGTH_SHORT).show());

            progressDialog.dismiss();

        });
    }
}