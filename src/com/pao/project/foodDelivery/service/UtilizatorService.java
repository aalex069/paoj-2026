package com.pao.project.foodDelivery.service;

import com.pao.project.foodDelivery.exception.InvalidOperationException;
import com.pao.project.foodDelivery.model.Client;
import com.pao.project.foodDelivery.model.Curier;
import com.pao.project.foodDelivery.model.StatusCurier;
import com.pao.project.foodDelivery.model.Utilizator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class UtilizatorService {

    private final List<Utilizator> utilizatori = new ArrayList<>();

    private UtilizatorService() {
    }

    public static UtilizatorService getInstance() {
        return Holder.INSTANCE;
    }

    public void adaugaUtilizator(Utilizator u) {
        if (u == null) {
            throw new InvalidOperationException("Utilizatorul nu poate fi null.");
        }

        for (Utilizator existing : utilizatori) {
            if (existing.getEmail().equals(u.getEmail())) {
                throw new InvalidOperationException("Email-ul " + u.getEmail() + " este deja inregistrat.");
            }
        }

        utilizatori.add(u);
        System.out.println("Utilizatorul " + u.getNume() + " a fost inregistrat.");
    }

    public List<Utilizator> getUtilizatori() {
        return new ArrayList<>(utilizatori);
    }

    public List<Client> getClienti() {
        return utilizatori.stream()
                .filter(u -> u instanceof Client)
                .map(u -> (Client) u)
                .collect(Collectors.toList());
    }

    public List<Curier> getCurieri() {
        return utilizatori.stream()
                .filter(u -> u instanceof Curier)
                .map(u -> (Curier) u)
                .collect(Collectors.toList());
    }

    public Curier getCurierRandomDisponibil() {
        List<Curier> disponibili = utilizatori.stream()
                .filter(u -> u instanceof Curier)
                .map(u -> (Curier) u)
                .filter(c -> c.getStatus() == StatusCurier.DISPONIBIL)
                .toList();

        if (disponibili.isEmpty()) {
            return null;
        }

        int index = new Random().nextInt(disponibili.size());

        return disponibili.get(index);
    }

    private static class Holder {
        private static final UtilizatorService INSTANCE = new UtilizatorService();
    }
}