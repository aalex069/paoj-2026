package com.pao.laboratory05.angajati;

import java.util.Scanner;

/**
 * Exercise 3 — Angajați
 *
 * Cerințele complete se află în:
 * src/com/pao/laboratory05/Readme.md → secțiunea "Exercise 3 — Angajați"
 *
 * Creează fișierele de la zero în acest pachet, apoi rulează Main.java
 * pentru a verifica output-ul așteptat din Readme.
 */
public class Main {
    public static void main(String[] args) {
        AngajatService service = AngajatService.getInstance();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Gestionare Angajați =====");
            System.out.println("1. Adaugă angajat");
            System.out.println("2. Listare după salariu");
            System.out.println("3. Caută după departament");
            System.out.println("0. Ieșire");
            System.out.print("Opțiune: ");

            if (!scanner.hasNextInt()) {
                scanner.nextLine();
                continue;
            }

            int optiune = scanner.nextInt();
            scanner.nextLine();

            if (optiune == 0) {
                System.out.println("La revedere!");
                break;
            }

            switch (optiune) {
                case 1:
                    System.out.print("Nume: ");
                    String nume = scanner.nextLine();
                    System.out.print("Departament (nume): ");
                    String deptNume = scanner.nextLine();
                    System.out.print("Departament (locatie): ");
                    String locatie = scanner.nextLine();
                    System.out.print("Salariu: ");
                    double salariu = scanner.nextDouble();
                    scanner.nextLine();

                    service.addAngajat(new Angajat(nume, new Departament(deptNume, locatie), salariu));
                    break;
                case 2:
                    System.out.println("--- Angajați după salariu (descrescător) ---");
                    service.listBySalary();
                    break;
                case 3:
                    System.out.print("Departament: ");
                    String cautat = scanner.nextLine();
                    System.out.println("--- Angajați din IT ---");
                    service.findByDepartament(cautat);
                    break;
                default:
                    System.out.println("Optiune invalida");
            }
        }
        scanner.close();
    }
}
