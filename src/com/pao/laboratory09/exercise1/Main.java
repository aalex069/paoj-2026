package com.pao.laboratory09.exercise1;

import java.io.*;
import java.util.*;

public class Main {
    private static final String OUTPUT_FILE = "output/lab09_ex1.ser";

    public static void main(String[] args) throws Exception {
        // TODO: Implementează conform Readme.md
        //
        // 1. Citește N din stdin, apoi cele N tranzacții (id suma data contSursa contDestinatie tip)
        // 2. Setează câmpul note = "procesat" pe fiecare tranzacție înainte de serializare
        // 3. Serializează lista de tranzacții în OUTPUT_FILE cu ObjectOutputStream (try-with-resources)
        // 4. Deserializează lista din OUTPUT_FILE cu ObjectInputStream (try-with-resources)
        // 5. Procesează comenzile din stdin până la EOF:
        //    - LIST          → afișează toate tranzacțiile, câte una pe linie
        //    - FILTER yyyy-MM → afișează tranzacțiile cu data care începe cu yyyy-MM
        //                       sau "Niciun rezultat." dacă nu există
        //    - NOTE id        → afișează "NOTE[id]: <valoarea câmpului note>"
        //                       sau "NOTE[id]: not found" dacă id-ul nu există
        //
        // Format linie tranzacție:
        //   [id] data tip: suma RON | contSursa -> contDestinatie
        //   Ex: [1] 2024-01-15 CREDIT: 1500.00 RON | RO01SRC1 -> RO01DST1

        Scanner sc = new Scanner(System.in).useLocale(Locale.US);

        if (!sc.hasNextInt()) return;

        int n = sc.nextInt();
        List<Tranzactie> listaInitiala = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int id = sc.nextInt();
            double suma = sc.nextDouble();
            String data = sc.next();
            String sursa = sc.next();
            String dest = sc.next();
            TipTranzactie tip = TipTranzactie.valueOf(sc.next());

            Tranzactie t = new Tranzactie(id, suma, data, sursa, dest, tip);
            t.setNote("procesat");
            listaInitiala.add(t);
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(OUTPUT_FILE))) {
            oos.writeObject(listaInitiala);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Tranzactie> listaCitita = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(OUTPUT_FILE))) {
            listaCitita = (List<Tranzactie>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (listaCitita == null) return;

        while (sc.hasNext()) {
            String comanda = sc.next();
            switch (comanda) {
                case "LIST":
                    for (Tranzactie t : listaCitita) {
                        System.out.println(t);
                    }
                    break;

                case "FILTER":
                    String prefix = sc.next();
                    boolean gasit = false;
                    for (Tranzactie t : listaCitita) {
                        if (t.getData().startsWith(prefix)) {
                            System.out.println(t);
                            gasit = true;
                        }
                    }
                    if (!gasit) {
                        System.out.println("Niciun rezultat.");
                    }
                    break;

                case "NOTE":
                    int searchId = sc.nextInt();
                    Tranzactie tranzactieNote = null;
                    for (Tranzactie t : listaCitita) {
                        if (t.getId() == searchId) {
                            tranzactieNote = t;
                            break;
                        }
                    }
                    if (tranzactieNote != null) {
                        System.out.println("NOTE[" + searchId + "]: " + tranzactieNote.getNote());
                    } else {
                        System.out.println("NOTE[" + searchId + "]: not found");
                    }
                    break;
            }
        }
        sc.close();

    }
}
