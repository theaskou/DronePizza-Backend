package org.example.dronepizzabackend.controller;

import org.example.dronepizzabackend.model.Pizza;
import org.example.dronepizzabackend.repositories.PizzaRepository;
import org.example.dronepizzabackend.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PizzaController {

    @Autowired
    PizzaService pizzaService;

    @GetMapping("/menu")
    public List<Pizza> pizzaList(){
        return pizzaService.getAllPizzas();
    }
}
