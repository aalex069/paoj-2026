package com.pao.project.foodDelivery.model;

public class Curier extends Utilizator {
    private StatusCurier status;

    public Curier(String nume, String email) {
        super(nume, email);
        this.status = StatusCurier.DISPONIBIL;
    }

    public void nextStatus() {
        this.status = this.status.getNext();
    }

    public StatusCurier getStatus() {
        return status;
    }

    @Override
    public String getTipUtilizator() {
        return "CURIER";
    }
}