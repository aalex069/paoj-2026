package com.pao.project.foodDelivery.model;

import java.util.List;
import java.util.Objects;

public class Comanda implements Comparable<Comanda> {
    private static int idCounter = 1;
    private final int id;
    private Client client;
    private Restaurant restaurant;
    private List<Produs> produse;

    public Comanda(Client client, Restaurant restaurant, List<Produs> produse) {
        this.id = idCounter++;
        this.client = client;
        this.restaurant = restaurant;
        this.produse = produse;
    }

    private double calculeazaTotal() {
        return produse.stream().mapToDouble(Produs::getPret).sum();
    }

    public int getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Produs> getProduse() {
        return produse;
    }

    public void setProduse(List<Produs> produse) {
        this.produse = produse;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Comanda comanda = (Comanda) o;
        return id == comanda.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public int compareTo(Comanda altaComanda) {
        return Integer.compare(this.id, altaComanda.id);
    }
}
