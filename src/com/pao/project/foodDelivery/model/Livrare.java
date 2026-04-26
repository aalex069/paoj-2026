package com.pao.project.foodDelivery.model;

import java.util.Objects;

public class Livrare {
    private Comanda comanda;
    private Curier curier;
    private StatusLivrare status;

    public Livrare(Comanda comanda, Curier curier) {
        this.comanda = comanda;
        this.curier = curier;
        this.status = StatusLivrare.PREGATIRE;
    }

    public Comanda getComanda() {
        return comanda;
    }

    public void setComanda(Comanda comanda) {
        this.comanda = comanda;
    }

    public Curier getCurier() {
        return curier;
    }

    public void setCurier(Curier curier) {
        this.curier = curier;
    }

    public StatusLivrare getStatus() {
        return status;
    }

    public void setStatus(StatusLivrare status) {
        this.status = status;
    }

    public void nextStatus() {
        this.status = status.getNext();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Livrare livrare = (Livrare) o;
        return Objects.equals(comanda, livrare.comanda);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(comanda);
    }
}
