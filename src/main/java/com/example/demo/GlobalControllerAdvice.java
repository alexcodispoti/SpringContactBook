package com.example.demo;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

//Registrerà tutti gli editor di testo provenienti da <input type="text"> e trimmera in automatico ogni elemento
@ControllerAdvice
public class GlobalControllerAdvice {

    @InitBinder 
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor stringtrimmer = new StringTrimmerEditor(false); 
        binder.registerCustomEditor(String.class, stringtrimmer);
    }
}

