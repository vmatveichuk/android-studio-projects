package com.example.galeriaimagem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class contatosAdapter extends RecyclerView.Adapter<contatosHolder> {

    private Context context;
    private ArrayList<contato> contatoList;

    public contatosAdapter(Context context, ArrayList<contato> contatoList) {
        this.context = context;
        this.contatoList = contatoList;
    }

    @NonNull
    @Override
    public contatosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new contatosHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull contatosHolder holder, int position) {
        contato contato = contatoList.get(position);
        holder.setNameContato(contato.getNameC());
        holder.setPhoneContato(contato.getPhoneC());
        holder.setAdressContato(contato.getAdressC());

    }

    @Override
    public int getItemCount() {
        return contatoList==null?0:contatoList.size();
    }
}
