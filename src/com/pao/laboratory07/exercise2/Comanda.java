package com.pao.laboratory07.exercise2;

import com.pao.laboratory07.exercise1.OrderState;

public abstract sealed class Comanda permits ComandaStandard, ComandaRedusa, ComandaGratuita {
    protected String nume;
    protected OrderState stare = OrderState.PLACED;

    public Comanda(String nume) {
        this.nume = nume;
    }

    public abstract double pretFinal();
    public abstract String descriere();
}

final class ComandaStandard extends Comanda {
    private final double pret;

    public ComandaStandard(String nume, double pret) {
        super(nume);
        this.pret = pret;
    }

    @Override public double pretFinal() { return pret; }

    @Override
    public String descriere() {
        return String.format("STANDARD: %s, pret: %.2f lei [%s]", nume, pretFinal(), stare);
    }
}

final class ComandaRedusa extends Comanda {
    private final double pret;
    private final int discountProcent;

    public ComandaRedusa(String nume, double pret, int discountProcent) {
        super(nume);
        this.pret = pret;
        this.discountProcent = discountProcent;
    }

    @Override public double pretFinal() { return pret * (1 - discountProcent / 100.0); }

    @Override
    public String descriere() {
        return String.format("DISCOUNTED: %s, pret: %.2f lei (-%d%%) [%s]", nume, pretFinal(), discountProcent, stare);
    }
}

final class ComandaGratuita extends Comanda {
    public ComandaGratuita(String nume) { super(nume); }

    @Override public double pretFinal() { return 0.0; }

    @Override
    public String descriere() {
        return String.format("GIFT: %s, gratuit [%s]", nume, stare);
    }
}