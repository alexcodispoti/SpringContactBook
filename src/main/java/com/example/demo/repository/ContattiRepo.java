package com.example.demo.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import com.example.demo.model.Contatto;

public interface ContattiRepo extends CrudRepository<Contatto, Long> {

    ArrayList<Contatto> 
    findByNomeContainingOrCognomeContainingOrTelefonoContaining(String nome, String cognome,String telefono);
    
}
