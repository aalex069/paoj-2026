package com.pao.laboratory06.exercise2;
import java.util.Scanner;

public class CIMColaborator extends Colaborator implements PersoanaFizica {
    private boolean bonus;

    @Override
    public void citeste(Scanner in) {
        this.nume = in.next();
        this.prenume = in.next();
        this.venitBrutLunar = in.nextDouble();
        String b = in.next();
        this.bonus = b.equalsIgnoreCase("DA");
    }

    @Override
    public double calculeazaVenitNetAnual() {
        double net = venitBrutLunar * 12 * 0.55;
        return bonus ? net * 1.10 : net;
    }

    @Override
    public String tipContract() { return "CIM"; }

    @Override
    public boolean areBonus() { return bonus; }

    @Override
    public TipColaborator getTip() {
        return TipColaborator.CIM;
    }
}