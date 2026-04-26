package com.pao.project.foodDelivery.service;

import com.pao.project.foodDelivery.exception.InvalidOperationException;
import com.pao.project.foodDelivery.model.Client;
import com.pao.project.foodDelivery.model.Comanda;
import com.pao.project.foodDelivery.model.Produs;
import com.pao.project.foodDelivery.model.Restaurant;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class ComandaService {

    private final Set<Comanda> comenzi = new TreeSet<>();

    private ComandaService() {
    }

    public static ComandaService getInstance() {
        return Holder.INSTANCE;
    }

    public void plaseazaComanda(Client client, Restaurant restaurant, List<Produs> produse) {
        if (client == null || restaurant == null || produse == null || produse.isEmpty()) {
            throw new InvalidOperationException("Datele comenzii sunt incomplete.");
        }
        for (Produs p : produse) {
            if (!restaurant.getMeniu().contains(p)) {
                throw new InvalidOperationException("Produsul " + p.getNume() + " nu aparține restaurantului " + restaurant.getNume());
            }
        }

        Comanda nouaComanda = new Comanda(client, restaurant, produse);
        System.out.println("Comanda " + nouaComanda.getId() + " a fost plasata");
        comenzi.add(nouaComanda);
    }

    public Set<Comanda> getComenzi() {
        return comenzi;
    }

    public List<Comanda> getComenziPentruClient(Client client) {
        if (client == null) {
            throw new InvalidOperationException("Clientul nu poate fi null.");
        }

        return comenzi.stream()
                .filter(c -> c.getClient().equals(client))
                .collect(Collectors.toList());
    }

    private static class Holder {
        private static final ComandaService INSTANCE = new ComandaService();
    }
}