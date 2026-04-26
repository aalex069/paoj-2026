package com.pao.project.foodDelivery.model;

public enum StatusCurier {
    DISPONIBIL, IN_LIVRARE;

    public StatusCurier getNext() {
        return switch (this) {
            case DISPONIBIL -> IN_LIVRARE;
            case IN_LIVRARE -> DISPONIBIL;
        };
    }
}