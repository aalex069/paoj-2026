package com.pao.project.foodDelivery.model;

public enum StatusLivrare {
    PREGATIRE, IN_CURS_DE_LIVRARE, LIVRAT;

    public boolean isFinal() {
        return this == LIVRAT;
    }

    public StatusLivrare getNext() {
        return switch (this) {
            case PREGATIRE -> IN_CURS_DE_LIVRARE;
            case IN_CURS_DE_LIVRARE -> LIVRAT;
            default -> this;
        };
    }
}