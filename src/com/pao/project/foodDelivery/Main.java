package com.pao.project.foodDelivery;

import com.pao.project.foodDelivery.model.*;
import com.pao.project.foodDelivery.service.ComandaService;
import com.pao.project.foodDelivery.service.LivrareService;
import com.pao.project.foodDelivery.service.UtilizatorService;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        ComandaService comandaService = ComandaService.getInstance();
        LivrareService livrareService = LivrareService.getInstance();
        UtilizatorService userService = UtilizatorService.getInstance();

        System.out.println("\n  Utilizatori");
        Curier c1 = new Curier("Matei", "matei@gmail.com");
        userService.adaugaUtilizator(c1);

        Adresa adresaClient = new Adresa("Strada Iovan Ion", 10, "Bucuresti");
        Client client = new Client("Alex", "alex@gmail.com", adresaClient);
        userService.adaugaUtilizator(client);

        // Produse si restaurante
        Produs p1 = new Produs("Pizza", 15.0);
        Produs p2 = new Produs("Pepsi", 5.0);
        Restaurant restaurant = new Restaurant("Bucataria Arinei", Arrays.asList(p1, p2));

        System.out.println("\n  Plasare comenzi");
        comandaService.plaseazaComanda(client, restaurant, Arrays.asList(p1, p2));
        comandaService.plaseazaComanda(client, restaurant, List.of(p1));

        System.out.println("\n  Procesare comenzi");
        livrareService.proceseazaCoadaComenzi();

        System.out.println("\n  Avansare status livrare");
        List<Livrare> livrariActuale = livrareService.getToateLivarile();

        if (!livrariActuale.isEmpty()) {
            Livrare primaLivrare = livrariActuale.get(0);

            while (!primaLivrare.getStatus().isFinal()) {
                livrareService.avanseazaStareLivrare(primaLivrare);
            }
        }

        System.out.println("\n  Procesare comenzi");
        livrareService.proceseazaCoadaComenzi();

        System.out.println("\n Istoric comenzi " + client.getNume());
        List<Comanda> comenziAlex = comandaService.getComenziPentruClient(client);
        comenziAlex.forEach(c -> System.out.println("Comanda #" + c.getId() + " - Total: " + c.getProduse().size() + " produse"));

        System.out.println("\n Cautare livrare aferenta unei comenzi");
        Livrare l = livrareService.getLivrareDupaIdComanda(1);
        if (l != null)
            System.out.println("Livrare gasita: " + l.getStatus());

    }
}