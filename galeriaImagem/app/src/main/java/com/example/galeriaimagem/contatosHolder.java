package com.example.galeriaimagem;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class contatosHolder extends RecyclerView.ViewHolder {
    private TextView txtName;
    private TextView txtPhone;
    private TextView txtAdress;



    public contatosHolder(@NonNull View itemView) {
        super(itemView);

        txtName = itemView.findViewById(R.id.txt_name);
        txtPhone = itemView.findViewById(R.id.txt_phone);
        txtAdress = itemView.findViewById(R.id.txt_adress);

    }
    public void setNameContato(String Name) {
        this.txtName.setText(Name);
    }
    public void setPhoneContato(String phone) {
        this.txtPhone.setText(phone);
    }
    public void setAdressContato(String adress) {
        this.txtAdress.setText(adress);
    }
}
