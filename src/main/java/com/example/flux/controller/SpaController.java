package com.example.flux.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpaController {
    @GetMapping({"/", "/dashboard", "/another-route"})
    public String index() {
        return "index";
    }
}
