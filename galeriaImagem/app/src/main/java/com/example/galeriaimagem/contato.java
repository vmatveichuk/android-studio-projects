package com.example.galeriaimagem;

public class contato {
    private String nameC;
    private String phoneC;
    private String adressC;

    public contato(String name, String phone, String adress) {
        this.nameC = name;
        this.phoneC = phone;
        this.adressC = adress;
    }

    public String getNameC() {
        return nameC;
    }

    public String getPhoneC() {
        return phoneC;
    }

    public String getAdressC() {
        return adressC;
    }
}
