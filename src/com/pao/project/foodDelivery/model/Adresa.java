package com.pao.project.foodDelivery.model;

import java.util.Objects;

public final class Adresa {
    private final String strada;
    private final int numar;
    private final String oras;

    public Adresa(String strada, int numar, String oras) {
        this.strada = strada;
        this.numar = numar;
        this.oras = oras;
    }

    public String getStrada() {
        return strada;
    }

    public int getNumar() {
        return numar;
    }

    public String getOras() {
        return oras;
    }

    @Override
    public String toString() {
        return strada + ", nr. " + numar + ", " + oras;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Adresa adresa = (Adresa) o;
        return numar == adresa.numar && Objects.equals(strada, adresa.strada) && Objects.equals(oras, adresa.oras);
    }

    @Override
    public int hashCode() {
        return Objects.hash(strada, numar, oras);
    }
}