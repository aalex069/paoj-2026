package com.pao.project.foodDelivery.model;

import java.util.Objects;

public abstract class Utilizator {
    private String nume;
    private String email;

    public Utilizator(String nume, String email) {
        this.nume = nume;
        this.email = email;
    }

    public abstract String getTipUtilizator();

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Utilizator{" +
                "nume='" + nume + '\'' +
                ", email='" + email + '\'' +
                ", tip='" + getTipUtilizator() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Utilizator that = (Utilizator) o;
        return Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(email);
    }
}