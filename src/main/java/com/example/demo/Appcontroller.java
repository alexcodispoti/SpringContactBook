package com.example.demo;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.example.demo.model.Contatto;
import com.example.demo.repository.ContattiRepo;
import com.example.demo.util.ErrorResponse;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Appcontroller implements WebMvcConfigurer {

    // Dependency injection
    @Autowired
    ContattiRepo contattiRepo;

    @GetMapping("/contatti") // Url
    public String contatti(Model model) {
        ArrayList<Contatto> contatti = (ArrayList<Contatto>) contattiRepo.findAll();
        model.addAttribute("contatti", contatti); // con questo model vado a passargli l'arraylist contatti , con una
                                                  // label speciale(attributename) che poi utilizzero nel template html
                                                  // "contatti"|
        return "contatti"; // template che devo utilizzare
    }

    @GetMapping("/contatti/{id}") // Url che noi utilizziamo come parametro (id)
    public String contatto(@PathVariable Long id, Model model) {

        Contatto contatto = contattiRepo.findById(id).get();
        model.addAttribute("contatto", contatto);

        return "contatto"; // template che voglio utilizzare
    }

    @GetMapping("/search") // Url che noi utilizziamo come parametro (id)
    public String search(@RequestParam(value = "key", defaultValue = "") String key, Model model) {
        ArrayList<Contatto> contacts = contattiRepo.findByNomeContainingOrCognomeContainingOrTelefonoContaining(key,
                key, key);

        model.addAttribute("contatti", contacts);
        return "search"; // template che voglio utilizzare
    }

    @GetMapping("/contatti/{id}/edit") // Url che noi utilizziamo per accedere al dettaglio del contatto prendendo come
                                       // parametro (id)
    // utilizziamo pathvariable perchè prendiamo una variabile di tipo Long
    // direttamente dalla path(/contatti/{id})
    public String contattoedit(@PathVariable Long id, Model model) {
        Contatto contatto = contattiRepo.findById(id).get();
        model.addAttribute("contatto", contatto);

        return "edit";
    }

    @PostMapping("/contatti/{id}/edit")
    public String contattoeditpost(@PathVariable Long id, Contatto contatto) {
        contatto.setId(id);
        contattiRepo.save(contatto);
        return "redirect:/contatti/" + contatto.getId();
    }

    @GetMapping("/contatti/add")
    public String aggiungiContatto(Contatto contatto, Model model) {
        model.addAttribute("contatto", contatto);
        return "add";
    }

    @PostMapping("/contatti/add")
    public String aggiungiContattopost(@Valid Contatto contatto,BindingResult result) {
        if(result.hasErrors()){
            return "add";
        }
        // contatto.setNome(contatto.getNome().trim());
        contattiRepo.save(contatto);
        return "redirect:/contatti/" + contatto.getId();
    }

    @GetMapping("/contatti/{id}/delete")
    public String rimuoviContattopost(@PathVariable Long id) {
        contattiRepo.deleteById(id);
        return "redirect:/contatti";
    }

    

    // lista contatti(barra ricerca)/contatti (lista contatti)
    // dettaglio contatto - azioni contestuali - contatti /{id} (elimina/modifica)
    // aggiungi contatto / contatti / add
    // modificacontatto /contatti/{id}/edit
}
