package com.example.demo.model;


import com.example.demo.ContactNumberConstraint;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "contatti")
public class Contatto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

   
    // @Pattern
    // @Email
    
    @Size(min=2,max=1024,message = "Il campo può contenere massimo 1024 car")
    @NotBlank(message="Il campo nome non può essere vuoto") //Annotation per verificare che l'input inserito non sia vuoto.
    String nome;
    @Size(min=2,max=1024,message = "Il campo può contenere massimo 1024 car")
    @NotBlank(message="Il campo nome non può essere vuoto") //Annotation per verificare che l'input inserito non sia vuoto.
    String cognome;
    @Size(min=2,max=1024,message = "Il campo può contenere massimo 1024 car")
    @NotBlank(message="Il campo nome non può essere vuoto") //Annotation per verificare che l'input inserito non sia vuoto.
    @ContactNumberConstraint
    String telefono;

    // Constructor
    public Contatto(String nome, String cognome, String telefono, Long id) {
        this.nome = nome;
        this.cognome = cognome;
        this.telefono = telefono;
        this.id = id;
    }

    // Costruttore vuoto
    public Contatto() {
    }

    // Metodo toString
    @Override
    public String toString() {
        return nome + " " +
                cognome + " " +
                telefono;

    }

    

    // Getter and Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


}
