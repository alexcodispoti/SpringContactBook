package com.example.demo;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
// import org.springframework.http.HttpStatus;

//Classe universale alla quale vengono reinderizzati tutti gli errori
@Controller
public class WebControllerError implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {

        Object responsestatus = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Object errormessage = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
        Object exception = request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);

        model.addAttribute("status", responsestatus.toString());
        model.addAttribute("error", errormessage.toString());
        model.addAttribute("exception", exception.toString());

        // if (responsestatus != null) {
        // Integer responseStatusint = Integer.parseInt(responsestatus.toString());
        // ! Faccio due If per tornare , in base all'errore trovato, al template da restituire
        

        // if (responseStatusint == HttpStatus.NOT_FOUND.value()) {

        // return "error-404";

        // }
        // else if(responseStatusint == HttpStatus.INTERNAL_SERVER_ERROR.value()){

        // return "error-500";
        // }

        return "error";
    }

}
