package com.pao.project.foodDelivery.model;

public class Client extends Utilizator {
    private Adresa adresaLivrare;

    public Client(String nume, String email, Adresa adresaLivrare) {
        super(nume, email);
        this.adresaLivrare = adresaLivrare;
    }

    public Adresa getAdresaLivrare() {
        return adresaLivrare;
    }

    @Override
    public String getTipUtilizator() {
        return "CLIENT";
    }
}