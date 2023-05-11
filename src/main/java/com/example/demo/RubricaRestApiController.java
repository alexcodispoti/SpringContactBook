package com.example.demo;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.Contatto;
import com.example.demo.repository.ContattiRepo;
import com.example.demo.util.ErrorResponse;

@RestController
public class RubricaRestApiController {

    @Autowired
    public ContattiRepo contattiRepo;
    // Facciamo 2 servizi che restituiscono il nome dell'app e la versione dell'app

    // Collegare una variabile(appname e appversion) dell'application.properties
    // file ad una variabile creata nella classe ApiController
    @Value("${application.name}")
    String appName;

    @Value("${application.version}")
    String appVersion;

    // Get Mapping per ricevere info su Versione e Nome dell'App
    @GetMapping("/api/v1/version")
    public String versione() {
        return "{\"application \" : \"" + appName + " " + appVersion + "\"}";
    }

    @GetMapping("/api/v1/contatti")
    public ArrayList<Contatto> contatti() {

        return (ArrayList<Contatto>) contattiRepo.findAll();
    }

    @GetMapping("/api/v1/contatti/{id}")
    public Contatto contatto(@PathVariable Long id) throws ContattoNonTrovatoException {

        Optional<Contatto> cont=contattiRepo.findById(id);
        if(cont.isEmpty()) throw new ContattoNonTrovatoException();
        return cont.get();
    }

    @GetMapping("/api/v1/contatti/search")
    public ArrayList<Contatto> searchcontatti(@RequestParam(value = "key", defaultValue = "") String key) throws ContattoNonTrovatoException {
        ArrayList<Contatto> cont=contattiRepo.findByNomeContainingOrCognomeContainingOrTelefonoContaining(key, key, key);
        if(cont.isEmpty()) throw new ContattoNonTrovatoException();
        return cont;
    }

    @PostMapping("/api/v1/contatti")
    public ResponseEntity<Contatto> addContatto(@RequestBody Contatto contatto) {
        contattiRepo.save(contatto);
        return new ResponseEntity<Contatto>(contatto, HttpStatus.OK);
    }

    @DeleteMapping("/api/v1/contatti/{id}")
    public ResponseEntity<Void> eliminacontatto(@PathVariable Long id) {

        if (contattiRepo.existsById(id)) {
            contattiRepo.deleteById(id);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/api/v1/contatti/{id}")
    public Contatto editcontatto(@PathVariable Long id, @RequestBody Contatto contatto) {
        contatto.setId(id);
        return contattiRepo.save(contatto);
    }

    // Annotation Questa eccezzione gestira solo questa Classe

    // @ExceptionHandler(NoSuchElementException.class)
    // public ResponseEntity<ErrorResponse> francoECiccio(NoSuchElementException exception) {
    //     ErrorResponse resp = new ErrorResponse("Id non trovato", HttpStatus.NOT_FOUND.value());
    //     return new ResponseEntity<ErrorResponse>(resp, HttpStatus.NOT_FOUND);
    // }

    @ExceptionHandler(ContattoNonTrovatoException.class)
    public ResponseEntity<ErrorResponse> sugo(ContattoNonTrovatoException exception) {
        ErrorResponse resp = new ErrorResponse(exception.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<ErrorResponse>(resp, HttpStatus.NOT_FOUND);
    }


}
