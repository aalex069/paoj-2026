package com.pao.laboratory08.exercise1;

import java.io.*;
import java.util.*;

public class Main {
    // Calea către fișierul cu date — relativă la rădăcina proiectului
    private static final String FILE_PATH = "src/com/pao/laboratory08/tests/studenti.txt";

    public static void main(String[] args) throws Exception {
        // TODO: Implementează conform Readme.md
        //
        // 1. Citește studenții din FILE_PATH cu BufferedReader
        // 2. Citește comanda din stdin: PRINT, SHALLOW <nume> sau DEEP <nume>
        // 3. Execută comanda:
        //    - PRINT → afișează toți studenții
        //    - SHALLOW <nume> → shallow clone + modifică orașul clonei la "MODIFICAT" + afișează
        //    - DEEP <nume> → deep clone + modifică orașul clonei la "MODIFICAT" + afișează

        List<Student> listaStudenti = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String nume = parts[0].trim();
                    int varsta = Integer.parseInt(parts[1].trim());
                    String oras = parts[2].trim();
                    String strada = parts[3].trim();

                    listaStudenti.add(new Student(nume, varsta, new Adresa(oras, strada)));
                }
            }
        } catch (IOException e) {
            System.err.println("Eroare");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        String[] cmdParts = input.split(" ", 2);
        String comanda = cmdParts[0];

        if (comanda.equals("PRINT")) {
            for (Student s : listaStudenti) {
                System.out.println(s);
            }
        }
        else if (comanda.equals("SHALLOW") || comanda.equals("DEEP")) {
                String numeCautat = cmdParts[1];
                Student original = null;

                for (Student s : listaStudenti) {
                    if (s.getNume().equals(numeCautat)) {
                        original = s;
                        break;
                    }
                }

                if (original != null) {
                    Student clona = (comanda.equals("SHALLOW")) ? original.shallowClone() : original.deepClone();

                    clona.getAdresa().setOras("MODIFICAT");

                    System.out.println("Original: " + original);
                    System.out.println("Clona: " + clona);
                } else {
                    System.out.println("Studentul " + numeCautat + " nu a fost găsit.");
                }
            }

        scanner.close();
    }
        //System.out.println("TODO: implementează exercițiul 1");
}
