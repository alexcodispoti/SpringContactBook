package com.example.demo;

public class ContattoNonTrovatoException extends Exception {

    @Override
    public String getMessage() {

        return "Contatto non trovato";
    }

    
}
