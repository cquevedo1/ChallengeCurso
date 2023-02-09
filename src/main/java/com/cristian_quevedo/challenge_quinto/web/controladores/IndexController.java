package com.cristian_quevedo.challenge_quinto.web.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String index(ModelMap model) {
        try {
            return "index";
        } catch (Exception e) {
            model.put("error", e.getMessage());
            return "index";
        }
    }
}