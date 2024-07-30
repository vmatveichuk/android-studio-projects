package com.example.agenda;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class agendaActivtyItem extends AppCompatActivity {

    EditText editTextItemName;
    EditText editTextItemAddress;
    EditText editTextItemPhone;
    Spinner spinnerItemType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda_item);

        editTextItemName = findViewById(R.id.editTextItemName);
        editTextItemAddress = findViewById(R.id.editTextItemAddress);
        editTextItemPhone = findViewById(R.id.editTextItemPhone);
        spinnerItemType = findViewById(R.id.spinnerItemType);
        Button buttonSaveItem = findViewById(R.id.buttonSaveItem);

        View.OnClickListener saveItemOnClick = v -> {

            String name = editTextItemName.getText().toString();
            String address = editTextItemAddress.getText().toString();
            String phone = editTextItemPhone.getText().toString();
            String type = spinnerItemType.getSelectedItem().toString();

            AgendaItem item = new AgendaItem(name, address, phone, type);

            String value = item.name + ", " + item.address + ", " + item.phone + ", " + item.type;

            saveItemToFile(value);

            AlertDialog.Builder builder = new AlertDialog.Builder(agendaActivtyItem.this);
            builder.setTitle(android.R.string.dialog_alert_title);
            builder.setMessage("Item adicionado!");
            builder.setPositiveButton(android.R.string.ok,
                    (dialog, which) -> {
                        editTextItemName.setText("");
                        editTextItemAddress.setText("");
                        editTextItemPhone.setText("");
                    });
            builder.create().show();
        };
        buttonSaveItem.setOnClickListener(saveItemOnClick);
    }

    void saveItemToFile(String item){
        try {
            OutputStream stream = agendaActivtyItem.this.openFileOutput(
                    "list.txt",
                    MODE_PRIVATE
            );
            OutputStreamWriter writer = new OutputStreamWriter(stream);
            writer.write(item);
            writer.write("\n");
            writer.flush();
            writer.close();
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}