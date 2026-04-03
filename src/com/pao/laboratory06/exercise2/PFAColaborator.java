package com.pao.laboratory06.exercise2;
import java.util.Scanner;

public class PFAColaborator extends Colaborator implements PersoanaFizica {
    private double cheltuieliLunare;

    @Override
    public void citeste(Scanner in) {
        this.nume = in.next();
        this.prenume = in.next();
        this.venitBrutLunar = in.nextDouble();
        this.cheltuieliLunare = in.nextDouble();
    }

    @Override
    public double calculeazaVenitNetAnual() {
        double venitNet = (this.venitBrutLunar - this.cheltuieliLunare) * 12;

        double salMin = 4050.0;

        double impozit = 0.10 * venitNet;

        double cass;
        if (venitNet < 6 * salMin) {
            cass = 0.10 * (6 * salMin);
        } else if (venitNet <= 72 * salMin) {
            cass = 0.10 * venitNet;
        } else {
            cass = 0.10 * (72 * salMin);
        }

        double cas = 0;
        if (venitNet >= 12 * salMin && venitNet <= 24 * salMin) {
            cas = 0.25 * (12 * salMin);
        } else if (venitNet > 24 * salMin) {
            cas = 0.25 * (24 * salMin);
        }

        return venitNet - impozit - cass - cas;
    }

    @Override
    public String tipContract() { return "PFA"; }

    @Override
    public TipColaborator getTip() {
        return TipColaborator.PFA;
    }
}