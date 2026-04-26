package com.pao.project.foodDelivery.service;

import com.pao.project.foodDelivery.exception.InvalidOperationException;
import com.pao.project.foodDelivery.model.Comanda;
import com.pao.project.foodDelivery.model.Curier;
import com.pao.project.foodDelivery.model.Livrare;

import java.util.*;

public class LivrareService {

    private final Map<Integer, Livrare> livrari = new HashMap<>();

    private LivrareService() {
    }

    public static LivrareService getInstance() {
        return Holder.INSTANCE;
    }

    public void creeazaLivrare(Comanda comanda, Curier curier) {
        curier.nextStatus();

        Livrare livrare = new Livrare(comanda, curier);
        livrari.put(comanda.getId(), livrare);

        System.out.println("Comanda #" + comanda.getId() + " a fost preluată de curierul " + curier.getNume());
    }

    private boolean esteDejaInLivrare(Comanda comanda) {
        return livrari.containsKey(comanda.getId());
    }

    public void proceseazaCoadaComenzi() {

        Set<Comanda> toateComenzile = ComandaService.getInstance().getComenzi();

        List<Comanda> comenziDeProcesat = toateComenzile.stream()
                .filter(comanda -> !esteDejaInLivrare(comanda))
                .toList();

        for (Comanda c : comenziDeProcesat) {
            Curier curier = UtilizatorService.getInstance().getCurierRandomDisponibil();

            if (curier == null) {
                System.out.println("Nu sunt curieri liberi.");
                break;
            }

            creeazaLivrare(c, curier);
        }
    }

    public void avanseazaStareLivrare(Livrare livrare) {
        if (livrare.getStatus().isFinal()) {
            throw new InvalidOperationException("Livrarea este deja finalizata.");
        }

        livrare.nextStatus();
        System.out.println("Livrarea pentru comanda #" + livrare.getComanda().getId() +
                " a avansat la: " + livrare.getStatus());
        if (livrare.getStatus().isFinal()) {
            livrare.getCurier().nextStatus();
        }
    }

    public List<Livrare> getToateLivarile() {
        return new ArrayList<>(livrari.values());
    }

    public Livrare getLivrareDupaIdComanda(int idComanda) {
        return livrari.get(idComanda);
    }

    private static class Holder {
        private static final LivrareService INSTANCE = new LivrareService();
    }
}