package br.com.luger.dev.precad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/ola/mundo")
    public String index(Model model) {
        // Adicione dados ao modelo se necessário
        return "index";
    }
}
