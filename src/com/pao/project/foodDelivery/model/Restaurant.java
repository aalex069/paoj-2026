package com.pao.project.foodDelivery.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Restaurant {
    private String nume;
    private List<Produs> meniu = new ArrayList<>();

    public Restaurant(String nume, List<Produs> meniu) {
        this.nume = nume;
        this.meniu = meniu;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public List<Produs> getMeniu() {
        return meniu;
    }

    public void setMeniu(List<Produs> meniu) {
        this.meniu = meniu;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "nume='" + nume + '\'' +
                ", meniu=" + meniu +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return Objects.equals(nume, that.nume) && Objects.equals(meniu, that.meniu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nume, meniu);
    }
}
