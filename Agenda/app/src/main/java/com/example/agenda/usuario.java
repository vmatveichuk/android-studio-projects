package com.example.agenda;

import java.util.ArrayList;
import java.util.List;

public class usuario {
    private List<usuarioDetails> usuarios;
    private static usuario usuarioInstancia;


    private usuario() {
        usuarios = new ArrayList<>();
    }

    public void setUsuarios(String name, String senha) {
        usuarioDetails usuariofinal = new usuarioDetails();
        usuariofinal.setName(name);
        usuariofinal.setSenha(senha);
        usuarios.add(usuariofinal);
    }

    public List<usuarioDetails> getUsuarios() {
        return usuarios;
    }


    public static usuario getInstance() {
        if (usuarioInstancia == null) {
            usuarioInstancia = new usuario();
        }
        return usuarioInstancia;
    }

    public String getNameList(int num) {
        return usuarios.get(num).getName();
    }
    public String getSenhaList(int num) {
        return usuarios.get(num).getSenha();

    }
}
